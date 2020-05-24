package com.ump.commons.thread.monitor;

import java.io.Serializable;

public class MonitorMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7771096307440433816L;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
