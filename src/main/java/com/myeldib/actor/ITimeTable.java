package com.myeldib.actor;

import java.time.LocalTime;

/**
 * Interface used to represent the time table
 */
public interface ITimeTable {
	
	/**
	 * @return true if time table is valid, otherwise false
	 */
	public boolean isValid();
	/**
	 * @return departure time
	 */
	public LocalTime getDepartureTime();
	/**
	 * @return arrival time
	 */
	public LocalTime getArrivalTime();
}
