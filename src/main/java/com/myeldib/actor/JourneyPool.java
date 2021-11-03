package com.myeldib.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.myeldib.actor.util.JourneyUtil;

/**
 * Class used to represent the pool of journeys
 */
public class JourneyPool implements IJourneyPool {

	ArrayList<IJourney> journeys = null;

	public JourneyPool() {
		journeys = new ArrayList<IJourney>();

	}

	/**
	 * get journey from pool
	 */
	@Override
	public IJourney get(int i) {
		return journeys.get(i);
	}

	/**
	 * add unique journey to pool
	 */
	@Override
	public void add(IJourney journey) {

		boolean isDuplicate = false;
		for (int i = 0; i < getSize(); i++) {

			IJourney tmpJourney = journeys.get(i);
			if (JourneyUtil.equalArrival(journey, tmpJourney) && JourneyUtil.equalDeparture(journey, tmpJourney)
					&& JourneyUtil.equalCompanyName(journey, tmpJourney)) {
				isDuplicate = true;
				break;
			}

		}

		if (getSize() == 0 || !isDuplicate)
			journeys.add(journey);

	}

	/**
	 * remove journey from pool given index
	 */
	@Override
	public void remove(int i) {
		journeys.remove(i);
	}

	/**
	 * remove a journey from pool
	 */
	@Override
	public void remove(IJourney journey) {
		journeys.remove(journey);

	}

	/**
	 * sort pool given a criteria
	 */
	@Override
	public void sort(Comparator<IJourney> journeyComporator) {
		Collections.sort(journeys, journeyComporator);

	}

	/**
	 * return size of pool
	 */
	@Override
	public int getSize() {
		return journeys.size();
	}

}
