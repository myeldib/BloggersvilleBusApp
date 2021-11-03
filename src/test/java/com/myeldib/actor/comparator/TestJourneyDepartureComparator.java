package com.myeldib.actor.comparator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import com.myeldib.actor.Company;
import com.myeldib.actor.ICompany;
import com.myeldib.actor.ITimeTable;
import com.myeldib.actor.Journey;
import com.myeldib.actor.TimeTable;
import com.myeldib.actor.comparator.JourneyDepartureComparator;

public class TestJourneyDepartureComparator {

	private Journey createJourney(String departureTime, String arrivalTime) {
		ICompany company = new Company("Posh");
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
	public void testCompare1() {

		Journey journey1 = createJourney("10:00", "10:30");
		Journey journey2 = createJourney("11:00", "11:30");

		JourneyDepartureComparator journeyDepartureComparator = new JourneyDepartureComparator();
		assertEquals(-1, journeyDepartureComparator.compare(journey1, journey2));

	}

	@Test
	public void testCompare2() {

		Journey journey1 = createJourney("10:00", "10:30");
		Journey journey2 = createJourney("11:00", "11:30");

		JourneyDepartureComparator journeyDepartureComparator = new JourneyDepartureComparator();
		assertEquals(1, journeyDepartureComparator.compare(journey2, journey1));

	}

	@Test
	public void testSort() {

		Journey journey1 = createJourney("10:15", "11:10");
		Journey journey2 = createJourney("11:10", "12:00");
		Journey journey3 = createJourney("12:05", "12:35");
		Journey journey4 = createJourney("09:15", "10:00");
		Journey journey5 = createJourney("15:01", "16:00");

		ArrayList<Journey> journeys = new ArrayList<Journey>();

		journeys.add(journey1);
		journeys.add(journey2);
		journeys.add(journey3);
		journeys.add(journey4);
		journeys.add(journey5);

		JourneyDepartureComparator journeyDepartureComparator = new JourneyDepartureComparator();
		Collections.sort(journeys, journeyDepartureComparator);

		assertEquals("09:15", journeys.get(0).getTimeTable().getDepartureTime().toString());

	}

}
