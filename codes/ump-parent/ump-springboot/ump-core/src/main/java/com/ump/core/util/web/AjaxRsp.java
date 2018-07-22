package com.ump.core.util.web;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ump.exception.status.RestStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author fangyh
 * @since 2018-03-25 12:00:00
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AjaxRsp<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3613504609909411231L;
	/**
	 * 返回码值,默认值Const.FAI
	 */
	@Setter
	@Getter
	@JsonProperty("rspCode")
	private String rspCode = StatusCode.SUCCESS.code();
	/**
	 * 返回码值解析
	 */
	@Setter
	@Getter
	@JsonProperty("rspMsg")
	private String rspMsg = StatusCode.SUCCESS.message();
	/**
	 * 返回对象
	 */
	@Setter
	@Getter
	@JsonProperty("rspObj")
	private T rspObj;

	public AjaxRsp() {
	}

	public AjaxRsp(RestStatus statusCodes) {
		this(statusCodes, null);
	}

	public AjaxRsp(RestStatus statusCodes, T details) {
		this.rspCode = statusCodes.code();
		this.rspMsg = statusCodes.message();
		if (details != null) {
			this.rspObj = details;
		}
	}

	public AjaxRsp(String code, String msg) {
		this.rspCode = code;
		this.rspMsg = msg;
	}

	/**
	 * 设置成功值
	 * 
	 * @param obj
	 *            设置对象
	 * @param resMsg
	 *            设置码值解析
	 */
	public void setSucceed(T rspObj, String rspMsg) {
		this.setRspMsg(rspMsg);
		this.setSucceed(rspObj);
	}

	/**
	 * 设置成功值
	 * 
	 * @param obj
	 *            设置对象
	 */
	public void setSucceed(T rspObj) {
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
	public void setSucceedMsg(String rspMsg) {
		this.setRspCode(StatusCode.SUCCESS.code());
		this.setRspMsg(rspMsg);
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
		return "AjaxRsp [rspCode=" + rspCode + ", rspMsg=" + rspMsg + ", rspObj=" + rspObj + "]";
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
