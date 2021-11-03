package com.myeldib.actor.comparator;

import java.util.Comparator;

import com.myeldib.actor.IJourney;
/**
 * Class used to sort journeys according to arrival time
 */
public class JourneyArrivalComparator implements Comparator<IJourney> {

	@Override
	public int compare(IJourney o1, IJourney o2) {

		return o1.getTimeTable().getArrivalTime().compareTo(o2.getTimeTable().getArrivalTime());
	}

}
