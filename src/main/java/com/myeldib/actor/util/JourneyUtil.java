package com.myeldib.actor.util;

import com.myeldib.actor.IJourney;
import com.myeldib.actor.IJourneyPool;

public class JourneyUtil {

	/**
	 * @param journey1
	 * @param journey2
	 * @return true if arrival times are equal, otherwise return false
	 */
	public static boolean equalArrival(IJourney journey1, IJourney journey2) {
		if (journey1.getTimeTable().getArrivalTime().equals(journey2.getTimeTable().getArrivalTime()))
			return true;
		else
			return false;
	}

	/**
	 * @param journey1
	 * @param journey2
	 * @return true if departure times are equal, otherwise return false
	 */
	public static boolean equalDeparture(IJourney journey1, IJourney journey2) {
		if (journey1.getTimeTable().getDepartureTime().equals(journey2.getTimeTable().getDepartureTime()))
			return true;
		else
			return false;
	}

	/**
	 * @param journey1
	 * @param journey2
	 * @return true if company names are equal, otherwise return false
	 */
	public static boolean equalCompanyName(IJourney journey1, IJourney journey2) {
		if (journey1.getCompany().getCompanyName().equals(journey2.getCompany().getCompanyName()))
			return true;
		else
			return false;
	}

	/**
	 * @param journeyPool of journeys
	 * @return journeyPool with reset flag
	 */
	public static IJourneyPool resetProcessed(IJourneyPool journeyPool) {
		for (int i = 0; i < journeyPool.getSize(); i++)
			journeyPool.get(i).setProcessed(false);
		return journeyPool;
	}
}
