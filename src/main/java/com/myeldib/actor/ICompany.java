package com.myeldib.actor;
/**
 * Interface used to represent the company
 */
public interface ICompany {

	/**
	 * @return true if company record is valid, otherwise false
	 */
	public boolean isValid();
	/**
	 * @return company name
	 */
	public String getCompanyName();
}
