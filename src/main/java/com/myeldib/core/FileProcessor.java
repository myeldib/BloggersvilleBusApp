package com.myeldib.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.myeldib.actor.Company;
import com.myeldib.actor.ICompany;
import com.myeldib.actor.IJourney;
import com.myeldib.actor.IJourneyPool;
import com.myeldib.actor.Journey;
import com.myeldib.actor.JourneyPool;
import com.myeldib.actor.TimeTable;

/**
 * Class used to process service record from file
 */
public class FileProcessor implements IFileProcessor {

	private static final Logger logger = LogManager.getLogger();

	private final String FIELD_SEPARATOR = " ";

	private File inputFile = null;
	private IJourneyProcessor journeyProcessor = null;
	private IJourneyWriter journeyWriter = null;

	public FileProcessor(File inputFile) {
		this.inputFile = inputFile;
		journeyProcessor = new JourneyProcessor();
		journeyWriter = new JourneyWriter();

	}

	/**
	 * @return true if file is processed successfully, otherwise false
	 */
	public boolean process() {
		String line = null;
		boolean success = true;
		IJourneyPool journeyPool = new JourneyPool();

		try {
			BufferedReader bufferReader = new BufferedReader(new FileReader(inputFile));

			// read service records
			while ((line = bufferReader.readLine()) != null) {
				TimeTable timeTable = null;
				ICompany company = null;
				IJourney journey = null;

				String[] entries = line.split(FIELD_SEPARATOR);

				if (entries.length > 3) {
					logger.warn("invalid entry:" + line);
					logger.info("supported entry format <Grotty|Posh HH:MM HH:MM>");
					continue;
				}

				company = new Company(entries[0]);

				if (!company.isValid())
					continue;

				timeTable = new TimeTable(entries[1], entries[2]);

				if (!timeTable.isValid())
					continue;

				journey = new Journey(company, timeTable);
				journeyPool.add(journey);

			}
			bufferReader.close();

			// process journey entries to produce efficient journey pool
			journeyPool = journeyProcessor.processJournys(journeyPool);
			// write journey pool to file
			if (inputFile.isAbsolute()) {
				success = journeyWriter.writeJourneys(inputFile.getParent(), journeyPool);
			} else {
				success = journeyWriter.writeJourneys(null, journeyPool);
			}
		} catch (Exception e) {
			logger.fatal("invalid file:" + inputFile.getName());
			return false;
		}
		return success;
	}
}
