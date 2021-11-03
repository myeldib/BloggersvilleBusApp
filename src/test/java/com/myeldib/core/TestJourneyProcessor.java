package com.myeldib.core;

import static org.junit.Assert.*;

import org.junit.Test;

import com.myeldib.actor.Company;
import com.myeldib.actor.ICompany;
import com.myeldib.actor.IJourney;
import com.myeldib.actor.IJourneyPool;
import com.myeldib.actor.ITimeTable;
import com.myeldib.actor.Journey;
import com.myeldib.actor.JourneyPool;
import com.myeldib.actor.TimeTable;
import com.myeldib.core.IJourneyProcessor;
import com.myeldib.core.JourneyProcessor;

public class TestJourneyProcessor {

	private Journey createJourney(String companyName, String departureTime, String arrivalTime) {
		ICompany company = new Company(companyName);
		assertNotNull(company);
		assertTrue(company.isValid());

		ITimeTable timeTable = new TimeTable(departureTime, arrivalTime);
		assertNotNull(timeTable);
		assertTrue(timeTable.isValid());
		Journey journey = new Journey(company, timeTable);
		assertNotNull(journey);

		return journey;
	}

	@Test
	public void testValidProcessJournys() {
		Journey journey1 = createJourney("Posh", "10:15", "11:10");
		Journey journey2 = createJourney("Posh", "10:10", "11:00");
		Journey journey3 = createJourney("Grotty", "10:10", "11:00");
		Journey journey4 = createJourney("Posh", "12:05", "12:30");
		Journey journey5 = createJourney("Grotty", "12:30", "13:25");
		Journey journey6 = createJourney("Grotty", "12:45", "13:25");
		Journey journey7 = createJourney("Posh", "17:25", "18:01");

		IJourneyProcessor journeyProcessor = new JourneyProcessor();
		assertNotNull(journeyProcessor);

		IJourneyPool journeyPool = new JourneyPool();
		assertNotNull(journeyPool);

		journeyPool.add(journey1);
		journeyPool.add(journey2);
		journeyPool.add(journey3);
		journeyPool.add(journey4);
		journeyPool.add(journey5);
		journeyPool.add(journey6);
		journeyPool.add(journey7);

		journeyPool = journeyProcessor.processJournys(journeyPool);
		assertEquals(5, journeyPool.getSize());
	}

	@Test
	public void testDuplicateSameCompany() {
		Journey journey1 = createJourney("Posh", "10:10", "11:00");
		Journey journey2 = createJourney("Posh", "10:10", "11:00");

		IJourneyProcessor journeyProcessor = new JourneyProcessor();
		assertNotNull(journeyProcessor);

		IJourneyPool journeyPool = new JourneyPool();
		assertNotNull(journeyPool);

		journeyPool.add(journey1);
		journeyPool.add(journey2);

		journeyPool = journeyProcessor.processJournys(journeyPool);
		assertEquals(1, journeyPool.getSize());
	}

	@Test
	public void testSameCompany_SameArrival_EarlyDeparture1() {
		Journey journey1 = createJourney("Posh", "10:15", "11:00");
		Journey journey2 = createJourney("Posh", "10:10", "11:00");

		IJourneyProcessor journeyProcessor = new JourneyProcessor();
		assertNotNull(journeyProcessor);

		IJourneyPool journeyPool = new JourneyPool();
		assertNotNull(journeyPool);

		journeyPool.add(journey1);
		journeyPool.add(journey2);

		journeyPool = journeyProcessor.processJournys(journeyPool);

		assertEquals(1, journeyPool.getSize());
		assertEquals("10:15", journeyPool.get(0).getTimeTable().getDepartureTime().toString());
	}

	@Test
	public void testSameCompany_SameArrival_EarlyDeparture2() {
		Journey journey1 = createJourney("Posh", "10:15", "11:00");
		Journey journey2 = createJourney("Posh", "10:10", "11:00");
		Journey journey3 = createJourney("Posh", "10:20", "11:00");

		IJourneyProcessor journeyProcessor = new JourneyProcessor();
		assertNotNull(journeyProcessor);

		IJourneyPool journeyPool = new JourneyPool();
		assertNotNull(journeyPool);

		journeyPool.add(journey1);
		journeyPool.add(journey2);
		journeyPool.add(journey3);

		journeyPool = journeyProcessor.processJournys(journeyPool);

		assertEquals(1, journeyPool.getSize());
		assertEquals("10:20", journeyPool.get(0).getTimeTable().getDepartureTime().toString());
	}

	@Test
	public void testSameCompany_SameArrival_EarlyDeparture3() {
		Journey journey1 = createJourney("Posh", "10:25", "10:45");
		Journey journey2 = createJourney("Posh", "10:10", "10:45");
		Journey journey3 = createJourney("Posh", "10:15", "10:45");
		Journey journey4 = createJourney("Posh", "10:05", "10:45");
		Journey journey5 = createJourney("Posh", "10:30", "10:40");
		Journey journey6 = createJourney("Posh", "15:30", "15:50");

		IJourneyProcessor journeyProcessor = new JourneyProcessor();
		assertNotNull(journeyProcessor);

		IJourneyPool journeyPool = new JourneyPool();
		assertNotNull(journeyPool);

		journeyPool.add(journey1);
		journeyPool.add(journey2);
		journeyPool.add(journey3);
		journeyPool.add(journey4);
		journeyPool.add(journey5);
		journeyPool.add(journey6);

		journeyPool = journeyProcessor.processJournys(journeyPool);

		assertEquals(3, journeyPool.getSize());
		assertEquals("10:25", journeyPool.get(0).getTimeTable().getDepartureTime().toString());
	}

	@Test
	public void testSameCompany_EarlyArrival_SameDeparture1() {
		Journey journey1 = createJourney("Posh", "10:40", "11:30");
		Journey journey2 = createJourney("Posh", "10:40", "11:15");
		Journey journey3 = createJourney("Posh", "10:40", "11:20");
		Journey journey4 = createJourney("Posh", "10:40", "11:25");

		IJourneyProcessor journeyProcessor = new JourneyProcessor();
		assertNotNull(journeyProcessor);

		IJourneyPool journeyPool = new JourneyPool();
		assertNotNull(journeyPool);

		journeyPool.add(journey1);
		journeyPool.add(journey2);
		journeyPool.add(journey3);
		journeyPool.add(journey4);

		journeyPool = journeyProcessor.processJournys(journeyPool);

		assertEquals(1, journeyPool.getSize());
		assertEquals("11:15", journeyPool.get(0).getTimeTable().getArrivalTime().toString());
	}

	@Test
	public void testSameCompany_EarlyArrival_DifferentDeparture() {
		Journey journey1 = createJourney("Posh", "10:40", "11:30");
		Journey journey2 = createJourney("Posh", "10:40", "11:15");
		Journey journey3 = createJourney("Posh", "10:40", "11:20");
		Journey journey4 = createJourney("Posh", "10:40", "11:25");
		Journey journey5 = createJourney("Posh", "10:50", "11:15");

		IJourneyProcessor journeyProcessor = new JourneyProcessor();
		assertNotNull(journeyProcessor);

		IJourneyPool journeyPool = new JourneyPool();
		assertNotNull(journeyPool);

		journeyPool.add(journey1);
		journeyPool.add(journey2);
		journeyPool.add(journey3);
		journeyPool.add(journey4);
		journeyPool.add(journey5);

		journeyPool = journeyProcessor.processJournys(journeyPool);

		assertEquals(1, journeyPool.getSize());
		assertEquals("10:50", journeyPool.get(0).getTimeTable().getDepartureTime().toString());
	}

	@Test
	public void testSameCompany_SameArrival_SameDeparture_DiffCompanies3() {
		Journey journey1 = createJourney("Posh", "10:40", "11:30");
		Journey journey2 = createJourney("Posh", "10:40", "11:15");
		Journey journey3 = createJourney("Posh", "10:40", "11:20");
		Journey journey4 = createJourney("Posh", "10:40", "11:25");
		Journey journey5 = createJourney("Posh", "10:50", "11:15");
		Journey journey6 = createJourney("Grotty", "10:50", "11:15");

		IJourneyProcessor journeyProcessor = new JourneyProcessor();
		assertNotNull(journeyProcessor);

		IJourneyPool journeyPool = new JourneyPool();
		assertNotNull(journeyPool);

		journeyPool.add(journey1);
		journeyPool.add(journey2);
		journeyPool.add(journey3);
		journeyPool.add(journey4);
		journeyPool.add(journey5);
		journeyPool.add(journey6);

		journeyPool = journeyProcessor.processJournys(journeyPool);

		assertEquals(1, journeyPool.getSize());
		assertEquals("11:15", journeyPool.get(0).getTimeTable().getArrivalTime().toString());
	}

	@Test
	public void testSameCompany_SameArrival_SameDeparture_DiffCompanies1() {
		Journey journey1 = createJourney("Posh", "10:10", "11:00");
		Journey journey2 = createJourney("Grotty", "10:10", "11:00");

		IJourneyProcessor journeyProcessor = new JourneyProcessor();
		assertNotNull(journeyProcessor);

		IJourneyPool journeyPool = new JourneyPool();
		assertNotNull(journeyPool);

		journeyPool.add(journey1);
		journeyPool.add(journey2);

		journeyPool = journeyProcessor.processJournys(journeyPool);

		assertEquals(1, journeyPool.getSize());
		IJourney journey = journeyPool.get(0);
		assertNotNull(journey);

		ICompany company = journey.getCompany();
		assertNotNull(company);

		assertEquals("Posh", company.getCompanyName());
	}

	@Test
	public void testSameCompany_SameArrival_SameDeparture_DiffCompanies2() {
		Journey journey1 = createJourney("Posh", "10:10", "11:00");
		Journey journey2 = createJourney("Grotty", "10:10", "11:00");
		Journey journey3 = createJourney("Posh", "10:12", "11:00");
		Journey journey4 = createJourney("Grotty", "10:12", "11:00");
		Journey journey5 = createJourney("Grotty", "11:12", "11:30");

		IJourneyProcessor journeyProcessor = new JourneyProcessor();
		assertNotNull(journeyProcessor);

		IJourneyPool journeyPool = new JourneyPool();
		assertNotNull(journeyPool);

		journeyPool.add(journey1);
		journeyPool.add(journey2);
		journeyPool.add(journey3);
		journeyPool.add(journey4);
		journeyPool.add(journey5);

		journeyPool = journeyProcessor.processJournys(journeyPool);

		assertEquals(2, journeyPool.getSize());
		assertEquals("Posh", journeyPool.get(0).getCompany().getCompanyName());
	}

	@Test
	public void testSameCompany_SameArrival_SameDeparture_DiffCompanies4() {
		Journey journey1 = createJourney("Posh", "10:10", "11:00");
		Journey journey2 = createJourney("Posh", "10:10", "11:01");
		Journey journey3 = createJourney("Posh", "10:12", "11:01");
		Journey journey4 = createJourney("Posh", "10:13", "11:01");

		IJourneyProcessor journeyProcessor = new JourneyProcessor();
		assertNotNull(journeyProcessor);

		IJourneyPool journeyPool = new JourneyPool();
		assertNotNull(journeyPool);

		journeyPool.add(journey1);
		journeyPool.add(journey2);
		journeyPool.add(journey3);
		journeyPool.add(journey4);

		journeyPool = journeyProcessor.processJournys(journeyPool);

		assertEquals(2, journeyPool.getSize());
		assertEquals("Posh", journeyPool.get(0).getCompany().getCompanyName());
	}
}
