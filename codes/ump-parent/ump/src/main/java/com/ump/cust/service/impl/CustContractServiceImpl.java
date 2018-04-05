package com.ump.cust.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ump.core.base.service.BaseServiceImpl;
import com.ump.cust.dao.CustContractDao;
import com.ump.cust.model.CustContract;
import com.ump.cust.service.CustContractService;

@Service("custContractService")
public class CustContractServiceImpl extends BaseServiceImpl<CustContract> implements CustContractService {
	@Autowired
	private CustContractDao custContractDao;

	@Override
	public int insertCust(CustContract custContract) {
		return custContractDao.insertCust(custContract);
	}

}
