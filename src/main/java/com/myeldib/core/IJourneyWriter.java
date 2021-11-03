package com.myeldib.core;

import com.myeldib.actor.IJourneyPool;

/**
 * Class used to write journey entries
 */
public interface IJourneyWriter {

	/**
	 * @param directoryPath to input directory
	 * @param journeyPool   to be written to a file
	 * @return true if file is written successfully, otherwise false
	 */
	public boolean writeJourneys(String directoryPath, IJourneyPool journeyPool);

	/**
	 * @return name of the output file
	 */
	public String getOutputFile();
}
