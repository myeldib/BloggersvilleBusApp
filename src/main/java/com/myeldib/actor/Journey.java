package com.myeldib.actor;

/**
 * Class used to represent the journey
 */
public class Journey implements IJourney {

	private ICompany company;
	private ITimeTable timeTable;
	private boolean processed = false; //default state

	public Journey(ICompany company, ITimeTable timeTable) {
		this.company = company;
		this.timeTable = timeTable;
	}

	/**
	 * @return company record
	 */
	public ICompany getCompany() {
		return company;
	}

	/**
	 * @return timetable record
	 */
	public ITimeTable getTimeTable() {
		return timeTable;
	}

	/**
	 * @param processed to flag if this journey entry is processed
	 */
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	
	/**
	 * @return true if this journey entry is processed, otherwise false
	 */
	public boolean isProcessed() {
		return processed;
	}
}
