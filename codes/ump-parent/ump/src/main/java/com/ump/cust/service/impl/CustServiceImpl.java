package com.ump.cust.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ump.core.base.service.BaseServiceImpl;
import com.ump.cust.dao.CustContractDao;
import com.ump.cust.dao.CustDao;
import com.ump.cust.model.Cust;
import com.ump.cust.model.CustContract;
import com.ump.cust.service.CustService;

@Service("custService")
public class CustServiceImpl extends BaseServiceImpl<Cust> implements CustService {

	@Autowired
	private CustDao custDao;

	@Autowired
	private CustContractDao custContractDao;

	@Override
	public Cust findCustByCustCode(String custCode) {
		return custDao.findCustByCustCode(custCode);
	}

	@Override
	public int insertCust(Cust cust) {
		CustContract c = new CustContract();
//		c.setPid("1234545");
//		custDao.insertCust(cust);
//		System.out.println("".substring(4));
		custContractDao.insertCust(c);
		return 1;
	}

}
