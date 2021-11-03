package com.myeldib.core;

import com.myeldib.actor.IJourneyPool;

/**
 * Class used to process journey entries
 */
public interface IJourneyProcessor {

	/**
	 * @param journeyPool takes a joint bus timetable with inefficient journeys and
	 *                    produces a modified
	 * @return timetable with efficient journeys
	 */
	public IJourneyPool processJournys(IJourneyPool journeyPool);

}
