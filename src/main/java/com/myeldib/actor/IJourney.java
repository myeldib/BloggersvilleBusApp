package com.myeldib.actor;
/**
 * Interface used to represent the journey
 */
public interface IJourney {
	/**
	 * @return company record
	 */
	public ICompany getCompany();

	/**
	 * @return timetable record
	 */
	public ITimeTable getTimeTable();

	/**
	 * @param processed to flag if this journey entry is processed
	 */
	public void setProcessed(boolean processed);
	
	/**
	 * @return true if this journey entry is processed, otherwise false
	 */
	public boolean isProcessed();
}
