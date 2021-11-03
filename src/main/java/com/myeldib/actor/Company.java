package com.myeldib.actor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class used to represent the company
 */
public class Company implements ICompany{

	private static final Logger logger = LogManager.getLogger();

	public static final String GROTTY_COMPANY = "Grotty";
	public static final String POSH_COMPANY = "Posh";

	private String companyName = null;

	public Company(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * validate company name. Only two possible companies
	 * 1- Posh
	 * 2- Grotty
	 */
	@Override
	public boolean isValid() {
		
		if (!companyName.equals(GROTTY_COMPANY) && !companyName.equals(POSH_COMPANY)) {
			logger.warn("invalid company name:" + companyName);
			logger.warn("expected companies:" + GROTTY_COMPANY + " or " + POSH_COMPANY);

			return false;
		}
		return true;
	}
	/**
	 * @return company name
	 */
	@Override
	public String getCompanyName() {
		return companyName;
	}
}
