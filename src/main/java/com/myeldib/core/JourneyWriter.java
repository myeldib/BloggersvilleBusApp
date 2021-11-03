package com.myeldib.core;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.myeldib.actor.Company;
import com.myeldib.actor.ICompany;
import com.myeldib.actor.IJourney;
import com.myeldib.actor.IJourneyPool;
import com.myeldib.actor.ITimeTable;

/**
 * Class used to write journey entries
 */
public class JourneyWriter implements IJourneyWriter {
	private static final Logger logger = LogManager.getLogger();

	private String outputFileName = "modified_table.txt";

	public JourneyWriter() {
	}

	/**
	 * @param directoryPath to input directory
	 * @param journeyPool   to be written to a file
	 * @return true if file is written successfully, otherwise false
	 */
	public boolean writeJourneys(String directoryPath, IJourneyPool journeyPool) {
		PrintWriter writer = null;
		String path = "";
		try {
			if (directoryPath != null) {
				path = directoryPath + "/" + outputFileName;
			} else {

				path = outputFileName;
			}

			writer = new PrintWriter(path, "UTF-8");

			writePoshList(journeyPool, writer);
			writeGrottyList(journeyPool, writer);
			writer.print("-EOF-");

			writer.close();

			logger.info("file: " + path + " has been written successfully");

		} catch (IOException e) {
			logger.fatal("could not create output file: " + path);
			return false;
		}

		return true;
	}

	private void writePoshList(IJourneyPool journeyPool, PrintWriter writer) {

		for (int i = 0; i < journeyPool.getSize(); i++) {
			IJourney journey = journeyPool.get(i);
			ICompany company = journey.getCompany();
			ITimeTable timeTable = journey.getTimeTable();
			if (company.getCompanyName().equals(Company.POSH_COMPANY)) {

				writer.println(company.getCompanyName() + " " + timeTable.getDepartureTime().toString() + " "
						+ timeTable.getArrivalTime().toString());
			}
		}

		if (journeyPool.getSize() > 0)
			writer.println();
	}

	private void writeGrottyList(IJourneyPool journeyPool, PrintWriter writer) {

		for (int i = 0; i < journeyPool.getSize(); i++) {
			IJourney journey = journeyPool.get(i);
			ICompany company = journey.getCompany();
			ITimeTable timeTable = journey.getTimeTable();
			if (company.getCompanyName().equals(Company.GROTTY_COMPANY)) {

				writer.println(company.getCompanyName() + " " + timeTable.getDepartureTime().toString() + " "
						+ timeTable.getArrivalTime().toString());
			}
		}
	}

	/**
	 * @return name of the output file
	 */
	public String getOutputFile() {
		return outputFileName;
	}

}
