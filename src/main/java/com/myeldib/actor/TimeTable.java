package com.myeldib.actor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class used to represent the time table
 */
public class TimeTable implements ITimeTable {
	private static final Logger logger = LogManager.getLogger();

	private LocalTime departureTime;
	private LocalTime arrivalTime;

	private String departureTimeStr;
	private String arrivalTimeStr;

	/**
	 * @param departureTimeStr
	 * @param arrivalTimeStr
	 *  isValid() should be called to validate input
	 */
	public TimeTable(String departureTimeStr, String arrivalTimeStr) {
		this.departureTimeStr = departureTimeStr;
		this.arrivalTimeStr = arrivalTimeStr;
	}

	/**
	 * validate arrival and departure times
	 * 1- journey is less than 1 hour
	 * 2- departure and arrival are not the same
	 * 3- departure is not after arrival
	 * 4- arrival and departure time format
	 */
	@Override
	public boolean isValid() {

		DateTimeFormatter strictTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
				.withResolverStyle(ResolverStyle.STRICT);

		try {
			departureTime = LocalTime.parse(departureTimeStr, strictTimeFormatter);
			arrivalTime = LocalTime.parse(arrivalTimeStr, strictTimeFormatter);

			// convert to sec
			long elapsed = arrivalTime.toSecondOfDay() - departureTime.toSecondOfDay();

			// Any service longer than an hour shall not be included.
			if (elapsed >= 3600) {
				logger.warn("service time is longer than one hour");
				return false;
			}
			if (departureTime.isAfter(arrivalTime)) {
				logger.warn("invalid service time departure time after arrival time");
				return false;

			}
			if (departureTime.equals(arrivalTime)) {
				logger.warn("invalid service time departure time and arrival time are the same");
				return false;
			}

			// bad format
		} catch (Exception e) {
			logger.error("bad time format, accepted format is HH:mm");
			return false;
		}

		return true;
	}

	/**
	 * @return departure time
	 */
	@Override
	public LocalTime getDepartureTime() {
		return departureTime;
	}

	/**
	 *@return return arrival time
	 */
	@Override
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}
}
