package com.myeldib.actor;

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
import com.myeldib.actor.comparator.JourneyDepartureComparator;

public class TestJourneyPool {

	public IJourney createJourney1() {
		ICompany poshCompany = new Company("Posh");
		assertTrue(poshCompany.isValid());

		ITimeTable poshTimeTable = new TimeTable("10:00", "10:25");
		assertTrue(poshTimeTable.isValid());

		Journey journey = new Journey(poshCompany, poshTimeTable);

		return journey;
	}

	public IJourney createJourney2() {
		ICompany poshCompany = new Company("Posh");
		assertTrue(poshCompany.isValid());

		ITimeTable poshTimeTable = new TimeTable("11:00", "11:25");
		assertTrue(poshTimeTable.isValid());

		Journey journey = new Journey(poshCompany, poshTimeTable);

		return journey;
	}

	@Test
	public void testGet() {
		IJourneyPool journeyPool = new JourneyPool();
		IJourney journey1 = createJourney1();

		journeyPool.add(journey1);

		IJourney journey2 = journeyPool.get(0);
		assertEquals(journey1, journey2);
	}

	@Test
	public void testAdd() {
		IJourneyPool journeyPool = new JourneyPool();
		IJourney journey1 = createJourney1();

		journeyPool.add(journey1);
		assertEquals(1, journeyPool.getSize());
	}

	@Test
	public void testRemoveInt() {
		IJourneyPool journeyPool = new JourneyPool();
		IJourney journey1 = createJourney1();

		journeyPool.add(journey1);
		assertEquals(1, journeyPool.getSize());

		journeyPool.remove(0);

		assertEquals(0, journeyPool.getSize());
	}

	@Test
	public void testRemoveIJourney() {
		IJourneyPool journeyPool = new JourneyPool();
		IJourney journey1 = createJourney1();

		journeyPool.add(journey1);
		assertEquals(1, journeyPool.getSize());

		journeyPool.remove(journey1);

		assertEquals(0, journeyPool.getSize());
	}

	@Test
	public void testSort() {
		IJourneyPool journeyPool = new JourneyPool();
		IJourney journey1 = createJourney1();
		IJourney journey2 = createJourney2();

		journeyPool.add(journey2);
		journeyPool.add(journey1);
		assertEquals(2, journeyPool.getSize());
		assertEquals("11:00", journeyPool.get(0).getTimeTable().getDepartureTime().toString());

		journeyPool.sort(new JourneyDepartureComparator());

		assertEquals("10:00", journeyPool.get(0).getTimeTable().getDepartureTime().toString());

	}

	@Test
	public void testRemoveDuplicates1() {
		IJourneyPool journeyPool = new JourneyPool();
		IJourney journey1 = createJourney1();

		journeyPool.add(journey1);
		journeyPool.add(journey1);

		assertEquals(1, journeyPool.getSize());
	}

	@Test
	public void testRemoveDuplicates2() {
		IJourneyPool journeyPool = new JourneyPool();
		IJourney journey1 = createJourney1();
		IJourney journey2 = createJourney1();

		journeyPool.add(journey1);
		journeyPool.add(journey2);

		assertEquals(1, journeyPool.getSize());
	}

}
