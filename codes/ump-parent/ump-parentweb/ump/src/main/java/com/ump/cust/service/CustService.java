package com.ump.cust.service;

import com.ump.cust.model.Cust;

public interface CustService {
	public Cust findCustByCustCode(String custCode);
	
	public int insertCust(Cust cust);
}
