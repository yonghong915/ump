package com.ump.commons.web;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * @author fangyh
 * @since 2018-03-25 12:00:00
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResultRsp<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3613504609909411231L;
	/**
	 * 返回码值,默认值Const.FAI
	 */
	@JsonProperty("rspCode")
	private String rspCode = StatusCode.SUCCESS.code();
	/**
	 * 返回码值解析
	 */
	@JsonProperty("rspMsg")
	private String rspMsg = StatusCode.SUCCESS.message();
	/**
	 * 返回对象
	 */
	@JsonProperty("data")
	private transient T rspObj;

	/**
	 * 时间戳
	 */
	@JsonProperty("timestamp")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSZ", timezone = "GMT+8")
	private long timestamp = System.currentTimeMillis();

	public ResultRsp() {
	}

	public ResultRsp<T> setRsp(String code, String msg, T data) {
		this.setRspCode(code);
		this.setRspMsg(msg);
		if (null != data) {
			this.setRspObj(data);
		}
		return this;
	}

	public ResultRsp<T> message(RestStatus statusCodes, T data) {
		this.setRspCode(statusCodes.code());
		this.setRspMsg(statusCodes.message());
		if (null != data) {
			this.setRspObj(data);
		}
		return this;
	}

	@Override
	public String toString() {
		return "ResultRsp [rspCode=" + rspCode + ", rspMsg=" + rspMsg + ", rspObj=" + rspObj + "]";
	}
}
