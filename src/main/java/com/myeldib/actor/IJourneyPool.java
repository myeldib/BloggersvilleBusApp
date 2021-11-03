package com.myeldib.actor;

import java.util.Comparator;

/**
 * Interface used to represent the pool of journeys
 */
public interface IJourneyPool {

	/**
	 * @param i
	 * @return journey from pool given index
	 */
	public IJourney get(int i);

	/**
	 * add journey to pool
	 * 
	 * @param journey
	 */
	public void add(IJourney journey);

	/**
	 * remove journey from pool given index i
	 * 
	 * @param i
	 */
	public void remove(int i);

	/**
	 * remove journey from pool
	 * 
	 * @param journey
	 */
	public void remove(IJourney journey);

	/**
	 * sort journey pool according to certain criteria
	 * 
	 * @param journeyComporator
	 */
	public void sort(Comparator<IJourney> journeyComporator);

	/**
	 * @return size of journey pool
	 */
	public int getSize();

}
