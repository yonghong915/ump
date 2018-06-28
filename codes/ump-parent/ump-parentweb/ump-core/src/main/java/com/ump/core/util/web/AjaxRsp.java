package com.ump.core.util.web;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author fangyh
 * @since 2018-03-25 12:00:00
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AjaxRsp implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3613504609909411231L;
	/**
	 * 返回码值,默认值Const.FAI
	 */
	@JsonProperty("rspCode")
	private String rspCode = StatusCode.FAIL.code();
	/**
	 * 返回码值解析
	 */
	@JsonProperty("rspMsg")
	private String rspMsg;
	/**
	 * 返回对象
	 */
	@JsonProperty("rspObj")
	private Object rspObj;

	public AjaxRsp() {
	}

	public AjaxRsp(RestStatus statusCodes) {
		this(statusCodes, null);
	}

	public AjaxRsp(RestStatus statusCodes, Object details) {
		this.rspCode = statusCodes.code();
		this.rspMsg = statusCodes.message();
		if (details != null) {
			this.rspObj = details;
		}
	}

	public String getRspCode() {
		return rspCode;
	}

	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}

	public String getRspMsg() {
		return rspMsg;
	}

	public void setRspMsg(String rspMsg) {
		this.rspMsg = rspMsg;
	}

	public Object getRspObj() {
		return rspObj;
	}

	public void setRspObj(Object rspObj) {
		this.rspObj = rspObj;
	}

	/**
	 * 设置成功值
	 * 
	 * @param obj
	 *            设置对象
	 * @param resMsg
	 *            设置码值解析
	 */
	public void setSucceed(Object rspObj, String rspMsg) {
		this.setRspMsg(rspMsg);
		this.setSucceed(rspObj);
	}

	/**
	 * 设置成功值
	 * 
	 * @param obj
	 *            设置对象
	 */
	public void setSucceed(Object rspObj) {
		this.rspObj = rspObj;
		this.setRspCode(StatusCode.SUCCESS.code());
		this.setRspMsg("success");
	}

	/**
	 * 设置成功值
	 * 
	 * @param resMsg
	 *            返回码值解析
	 */
	public void setSucceedMsg(String resMsg) {
		this.setRspCode(StatusCode.SUCCESS.code());
		this.setRspMsg(resMsg);
	}

	/**
	 * 设置失败值
	 * 
	 * @param resMsg
	 *            返回码值解析
	 */
	public void setFailMsg(String resMsg) {
		this.rspObj = null;
		this.setRspCode(StatusCode.FAIL.code());
		this.setRspMsg(resMsg);
	}

	@Override
	public String toString() {
		return "AjaxRes [resCode=" + rspCode + ", resMsg=" + rspMsg + ", rspObj=" + rspObj + "]";
	}

	/**
	 * 设置没有权限返回值
	 * 
	 * @param auth
	 *            原值返回
	 * @return
	 */
	public boolean setNoAuth(boolean auth) {
		// if (!auth) {
		// this.obj = null;
		// this.setRes(Const.NO_AUTHORIZED);
		// this.setResMsg(Const.NO_AUTHORIZED_MSG);
		// }
		return auth;
	}

}
