package com.myeldib.core;

import com.myeldib.actor.Company;
import com.myeldib.actor.IJourney;
import com.myeldib.actor.IJourneyPool;
import com.myeldib.actor.JourneyPool;
import com.myeldib.actor.comparator.JourneyArrivalComparator;
import com.myeldib.actor.comparator.JourneyDepartureComparator;
import com.myeldib.actor.util.JourneyUtil;

public class JourneyProcessor implements IJourneyProcessor {

	public JourneyProcessor() {
	}

	/**
	 * @param journeyPool takes a joint bus timetable with inefficient journeys and
	 *                    produces a modified
	 * @return timetable a journeyPool with efficient journeys
	 */
	@Override
	public IJourneyPool processJournys(IJourneyPool journeyPool) {

		// starts at the same time and reaches earlier
		journeyPool = selectByDeparture(journeyPool);
		// reset flag for upcoming processing
		journeyPool = JourneyUtil.resetProcessed(journeyPool);
		// starts later and reaches at the same time
		journeyPool = selectByArrival(journeyPool);
		// both companies offer a service with same departure and arrival times, choose
		// Posh
		journeyPool = selectByComfort(journeyPool);

		return journeyPool;
	}

	/**
	 * @param journeyPool of journeys
	 * @return modified journeyPool according to earliest arrival
	 */
	private IJourneyPool selectByDeparture(IJourneyPool journeyPool) {
		// return same journeyPool, nothing to process
		if (journeyPool.getSize() <= 1)
			return journeyPool;

		IJourneyPool selectedJourneys = new JourneyPool();

		journeyPool.sort(new JourneyDepartureComparator());

		for (int i = 0; i < journeyPool.getSize(); i++) {
			IJourney journey1 = journeyPool.get(i);
			IJourneyPool intermediateResults = new JourneyPool();

			// skip processed
			if (journey1.isProcessed())
				continue;

			intermediateResults.add(journey1);

			for (int j = i + 1; j < journeyPool.getSize(); j++) {
				IJourney journey2 = journeyPool.get(j);

				// skip processed
				if (journey2.isProcessed())
					continue;

				// same company
				if (JourneyUtil.equalCompanyName(journey1, journey2)) {
					// same departure time
					if (JourneyUtil.equalDeparture(journey1, journey2)) {
						journey2.setProcessed(true);
						intermediateResults.add(journey2);
					}

				}

			}

			// sort to bring earliest arrival to index 0
			intermediateResults.sort(new JourneyArrivalComparator());
			IJourney selectedJourney = intermediateResults.get(0);
			selectedJourneys.add(selectedJourney);

		}

		return selectedJourneys;

	}

	/**
	 * @param journeyPool of journeys
	 * @return modified journeyPool according to earliest departure
	 */
	private IJourneyPool selectByArrival(IJourneyPool journeyPool) {
		// return same journeyPool, nothing to process
		if (journeyPool.getSize() <= 1)
			return journeyPool;
		IJourneyPool selectedJourneys = new JourneyPool();

		journeyPool.sort(new JourneyArrivalComparator());

		for (int i = 0; i < journeyPool.getSize(); i++) {
			IJourney journey1 = journeyPool.get(i);
			IJourneyPool intermediateResults = new JourneyPool();

			// skip processed
			if (journey1.isProcessed())
				continue;

			intermediateResults.add(journey1);

			for (int j = i + 1; j < journeyPool.getSize(); j++) {
				IJourney journey2 = journeyPool.get(j);

				// skip processed
				if (journey2.isProcessed())
					continue;

				// same company
				if (JourneyUtil.equalCompanyName(journey1, journey2)) {
					// same arrival time
					if (JourneyUtil.equalArrival(journey1, journey2)) {
						journey2.setProcessed(true);
						intermediateResults.add(journey2);
					}

				}

			}
			// sort to bring earliest departure to last index
			intermediateResults.sort(new JourneyDepartureComparator());
			IJourney selectedJourney = intermediateResults.get(intermediateResults.getSize() - 1);
			selectedJourneys.add(selectedJourney);

		}

		return selectedJourneys;

	}

	/**
	 * choose Posh over Grotty
	 * 
	 * @param journeyPool of journeys
	 * @return modified journeyPool according to comfort
	 */
	private IJourneyPool selectByComfort(IJourneyPool journeyPool) {
		// return same journeyPool, nothing to process
		if (journeyPool.getSize() <= 1)
			return journeyPool;

		journeyPool.sort(new JourneyDepartureComparator());

		int i = 0;

		while (i < journeyPool.getSize()) {
			int j = i + 1;

			if (j < journeyPool.getSize()) {
				// same arrival and departure
				if (JourneyUtil.equalArrival(journeyPool.get(i), journeyPool.get(j))
						&& JourneyUtil.equalDeparture(journeyPool.get(i), journeyPool.get(j))) {

					// remove grotty entry
					if (journeyPool.get(i).getCompany().getCompanyName().equals(Company.GROTTY_COMPANY)) {
						journeyPool.remove(i);
						continue;
					}
					// remove grotty entry
					if (journeyPool.get(j).getCompany().getCompanyName().equals(Company.GROTTY_COMPANY)) {
						journeyPool.remove(j);
						continue;
					}
				}

			}
			i++;
		}

		return journeyPool;

	}
}
