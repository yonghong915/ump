package com.ump.cust.dao;

import com.ump.cust.model.Cust;

public interface CustDao {

	public Cust findCustByCustCode(String custCode);
	
	public int insertCust(Cust cust);
}
