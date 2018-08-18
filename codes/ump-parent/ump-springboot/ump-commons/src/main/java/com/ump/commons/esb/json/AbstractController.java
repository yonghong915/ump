package com.ump.commons.esb.json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AbstractController<REQ, RSP> {
	/**
	 * 服务请求报文体
	 */
	private Class<REQ> req;
	/**
	 * 服务响应报文体
	 */
	private Class<RSP> rsp;

	public AbstractController() {
		// Type[] t = ((ParameterizedType)
		// getClass().getGenericSuperclass()).getActualTypeArguments();
		// req = (Class<REQ>) t[0];
		// rsp = (Class<RSP>) t[1];
	}

	public ChanMessage<SysHead, AppHead, RSP> execute(HttpServletRequest request, HttpServletResponse response) {
		ChanMessage<SysHead, AppHead, REQ> reqMsg = new ChanMessage<>();
		SysHead rspSysHead = new SysHead();
		reqMsg.setSysHead(rspSysHead);
		ChanMessage<SysHead, AppHead, RSP> rspMsg = new ChanMessage<>();
		rspMsg.setSysHead(rspSysHead);

		AppHead appHead = new AppHead();
		appHead.setTotalNum(10);
		rspMsg.setAppHead(appHead);

		rspMsg.setBody(null);
		return rspMsg;
	}
}
