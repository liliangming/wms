package com.jeesite.modules.xhs.entity.warehouseoutput;

import java.io.Serializable;

public class LogisticsInfoResultBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5628893876250056550L;

	private int code = 0;

	private String msg;

	private QueryLogisticsResponseBO result;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public QueryLogisticsResponseBO getResult() {
		return result;
	}

	public void setResult(QueryLogisticsResponseBO result) {
		this.result = result;
	}
}
