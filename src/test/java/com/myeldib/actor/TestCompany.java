package com.myeldib.actor;

import static org.junit.Assert.*;

import org.junit.Test;

import com.myeldib.actor.Company;
import com.myeldib.actor.ICompany;

public class TestCompany {

	@Test
	public void testValidCompany() {
 
		ICompany company = new Company("Posh");
		assertNotNull(company);
		
		boolean isValid = company.isValid();
		assertTrue(isValid);
		
		assertEquals(company.getCompanyName(), "Posh");
	}
	
	@Test
	public void testInValidCompany() {
		
		ICompany company = new Company("XYZ");
		assertNotNull(company);
	
		boolean isValid = company.isValid();
		assertFalse(isValid);
	}

}
