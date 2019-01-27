package com.ump.commons.web;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ump.commons.web.RestStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author fangyh
 * @since 2018-03-25 12:00:00
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultRsp<T> implements Serializable {

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
	private String rspCode;
	/**
	 * 返回码值解析
	 */
	@Setter
	@Getter
	@JsonProperty("rspMsg")
	private String rspMsg;
	/**
	 * 返回对象
	 */
	@Setter
	@Getter
	@JsonProperty("data")
	private transient T rspObj;

	public ResultRsp() {
	}

	public ResultRsp(RestStatus statusCodes) {
		this(statusCodes, null);
	}

	public ResultRsp(RestStatus statusCodes, T details) {
		this.rspCode = statusCodes.code();
		this.rspMsg = statusCodes.message();
		if (details != null) {
			this.rspObj = details;
		}
	}

	public ResultRsp(String code, String msg) {
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
		this.setRspCode(StatusCode.SUCCESS.code());
		this.setRspMsg(rspMsg);
		this.setRspObj(rspObj);
	}

	/**
	 * 设置成功值
	 * 
	 * @param obj
	 *            设置对象
	 */
	public void setSucceed(T rspObj) {
		setSucceed(rspObj, StatusCode.SUCCESS.message());
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
		return "ResultRsp [rspCode=" + rspCode + ", rspMsg=" + rspMsg + ", rspObj=" + rspObj + "]";
	}
}
