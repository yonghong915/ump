package com.ump.cust.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ump.commons.web.ResultRsp;
import com.ump.cust.service.ICustService;

@Controller
@RequestMapping("/cust")
public class CustCtrler {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ICustService custService;

	@PostMapping(value = "findCustByCustCode")
	@ResponseBody
	public ResultRsp<Object> findCustByCustCode(String custCode) {
		logger.info("index......");
		// Cust cust = custService.findCustByCustCode(custCode);
		ResultRsp<Object> ar = new ResultRsp<>();
		// ar.setSucceed(cust);
		return ar;
	}
}
