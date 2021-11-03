package com.myeldib.core;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;

import com.myeldib.actor.Company;
import com.myeldib.actor.ICompany;
import com.myeldib.actor.IJourneyPool;
import com.myeldib.actor.ITimeTable;
import com.myeldib.actor.Journey;
import com.myeldib.actor.JourneyPool;
import com.myeldib.actor.TimeTable;
import com.myeldib.core.IJourneyProcessor;
import com.myeldib.core.IJourneyWriter;
import com.myeldib.core.JourneyProcessor;
import com.myeldib.core.JourneyWriter;

public class TestJourneyWriter {

	public static final String NEW_LINE = System.getProperty("line.separator");

	private IJourneyPool createJourneyPool() {

		ICompany poshCompany = new Company("Posh");
		assertTrue(poshCompany.isValid());

		ITimeTable poshTimeTable = new TimeTable("10:00", "10:25");
		assertTrue(poshTimeTable.isValid());

		Journey poshJourney = new Journey(poshCompany, poshTimeTable);

		ICompany grottyCompany = new Company("Grotty");
		assertTrue(grottyCompany.isValid());

		ITimeTable grottyTimeTable = new TimeTable("11:00", "11:25");
		assertTrue(grottyTimeTable.isValid());

		Journey grottyJourney = new Journey(grottyCompany, grottyTimeTable);

		IJourneyPool journeyPool = new JourneyPool();

		journeyPool.add(grottyJourney);
		journeyPool.add(poshJourney);

		return journeyPool;
	}

	@Test
	public void testWriteEmptyList() {
		IJourneyPool journeyPool = new JourneyPool();
		IJourneyWriter journeyWriter = new JourneyWriter();
		assertNotNull(journeyWriter);
		assertNotNull(journeyPool);

		// delete output file
		File outputFile = new File("src/test/resources/" + journeyWriter.getOutputFile());
		outputFile.delete();

		boolean success = journeyWriter.writeJourneys("src/test/resources", journeyPool);

		assertTrue(success);
		assertTrue(outputFile.exists());

		try {

			BufferedReader bufferReader = new BufferedReader(new FileReader(outputFile));

			String line = null;

			// read service records
			while ((line = bufferReader.readLine()) != null) {
				assertEquals("-EOF-", line);

			}

			bufferReader.close();

		} catch (Exception e) {

		}

	}

	@Test
	public void testWriteListGrottyPosh() {
		IJourneyWriter journeyWriter = new JourneyWriter();
		IJourneyProcessor journeyProcessor = new JourneyProcessor();
		IJourneyPool journeyPool = createJourneyPool();
		assertNotNull(journeyWriter);
		assertNotNull(journeyProcessor);
		assertNotNull(journeyPool);

		// delete output file
		File outputFile = new File("src/test/resources/" + journeyWriter.getOutputFile());
		outputFile.delete();

		journeyPool = journeyProcessor.processJournys(journeyPool);

		boolean success = journeyWriter.writeJourneys("src/test/resources", journeyPool);

		assertTrue(success);
		assertTrue(outputFile.exists());

		try {

			BufferedReader bufferReader = new BufferedReader(new FileReader(outputFile));

			String line = null;
			int line_count = 0;
			// read service records
			while ((line = bufferReader.readLine()) != null) {

				if (line_count == 0) {
					String[] entries = line.split(" ");
					assertEquals(3, entries.length);
					assertEquals("Posh", entries[0]);
					assertEquals("10:00", entries[1]);
					assertEquals("10:25", entries[2]);
				} else if (line_count == 1) {
					assertEquals("", line);
				} else if (line_count == 2) {
					String[] entries = line.split(" ");
					assertEquals(3, entries.length);
					assertEquals("Grotty", entries[0]);
					assertEquals("11:00", entries[1]);
					assertEquals("11:25", entries[2]);
				}

				else if (line_count == 3) {
					assertEquals("-EOF-", line);
				}
				line_count++;

			}

			bufferReader.close();

		} catch (Exception e) {

		}

	}

}
