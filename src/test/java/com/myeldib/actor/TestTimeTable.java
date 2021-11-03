package com.myeldib.actor;

import static org.junit.Assert.*;

import org.junit.Test;

import com.myeldib.actor.ITimeTable;
import com.myeldib.actor.TimeTable;

public class TestTimeTable {

	@Test
	public void testValidTimeTable() {
		String departureTime="10:00";
		String arrivalTime="10:34";
		ITimeTable timeTable = new TimeTable(departureTime, arrivalTime);
		assertNotNull(timeTable);
		
		boolean isValid = timeTable.isValid();
		assertTrue(isValid);
	}
	
	@Test
	public void testInValidDepartureFormat1() {
		String departureTime="10:1";
		String arrivalTime="10:34";
		ITimeTable timeTable = new TimeTable(departureTime, arrivalTime);
		assertNotNull(timeTable);
		
		boolean isValid = timeTable.isValid();
		assertFalse(isValid);
	}
	
	@Test
	public void testInValidDepartureFormat2() {
		String departureTime="10:60";
		String arrivalTime="10:34";
		ITimeTable timeTable = new TimeTable(departureTime, arrivalTime);
		assertNotNull(timeTable);
		
		boolean isValid = timeTable.isValid();
		assertFalse(isValid);
	}
	
	@Test
	public void testInValidDepartureFormat3() {
		String departureTime="10:10:00";
		String arrivalTime="10:34";
		ITimeTable timeTable = new TimeTable(departureTime, arrivalTime);
		assertNotNull(timeTable);
		
		boolean isValid = timeTable.isValid();
		assertFalse(isValid);
	}
	
	@Test
	public void testInValidArrivalFormat1() {
		String departureTime="10:00";
		String arrivalTime="10:3";
		ITimeTable timeTable = new TimeTable(departureTime, arrivalTime);
		assertNotNull(timeTable);
		
		boolean isValid = timeTable.isValid();
		assertFalse(isValid);
	}
	
	@Test
	public void testInValidArrivalFormat2() {
		String departureTime="10:00";
		String arrivalTime="10:64";
		ITimeTable timeTable = new TimeTable(departureTime, arrivalTime);
		assertNotNull(timeTable);
		
		boolean isValid = timeTable.isValid();
		assertFalse(isValid);
	}
	
	@Test
	public void testInValidArrivalFormat3() {
		String departureTime="10:00";
		String arrivalTime="10:34:00";
		ITimeTable timeTable = new TimeTable(departureTime, arrivalTime);
		assertNotNull(timeTable);
		
		boolean isValid = timeTable.isValid();
		assertFalse(isValid);
	}
	
	@Test
	public void testInValidElapsedTime() {
		String departureTime="10:00";
		String arrivalTime="11:00";
		ITimeTable timeTable = new TimeTable(departureTime, arrivalTime);
		assertNotNull(timeTable);
		
		boolean isValid = timeTable.isValid();
		assertFalse(isValid);
	}
	
	@Test
	public void testInValidSameArrivalTime() {
		String departureTime="10:00";
		String arrivalTime="10:00";
		ITimeTable timeTable = new TimeTable(departureTime, arrivalTime);
		assertNotNull(timeTable);
		
		boolean isValid = timeTable.isValid();
		assertFalse(isValid);
	}
	
	@Test
	public void testInValidSameDepartureTimeAfterArrivalTime() {
		String departureTime="11:00";
		String arrivalTime="10:00";
		ITimeTable timeTable = new TimeTable(departureTime, arrivalTime);
		assertNotNull(timeTable);
		
		boolean isValid = timeTable.isValid();
		assertFalse(isValid);
	}

}
