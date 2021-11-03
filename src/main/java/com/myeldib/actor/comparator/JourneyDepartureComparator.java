package com.myeldib.actor.comparator;

import java.util.Comparator;

import com.myeldib.actor.IJourney;
/**
 * Class used to sort journeys according to departure time
 */
public class JourneyDepartureComparator implements Comparator<IJourney> {

	@Override
	public int compare(IJourney o1, IJourney o2) {

		return o1.getTimeTable().getDepartureTime().compareTo(o2.getTimeTable().getDepartureTime());
	}

}
