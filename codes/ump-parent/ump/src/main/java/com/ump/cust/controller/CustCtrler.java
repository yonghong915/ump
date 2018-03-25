package com.ump.cust.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ump.cfn.sysmgr.user.model.User;
import com.ump.core.base.controller.BaseCtrler;
import com.ump.core.util.web.AjaxRsp;
import com.ump.cust.model.Cust;
import com.ump.cust.service.CustService;

@Controller
@RequestMapping("/cust")
public class CustCtrler extends BaseCtrler<User> {
	@Autowired
	private CustService custService;

	@RequestMapping(value = "findCustByCustCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRsp findCustByCustCode(String custCode) {
		logger.info("index......");
		Cust cust = custService.findCustByCustCode(custCode);
		AjaxRsp ar = new AjaxRsp();
		ar.setSucceed(cust);
		return ar;
	}
}
