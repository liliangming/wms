package com.jeesite.modules.xhs.entity.warehouseoutput;

import java.io.Serializable;
import java.util.List;

public class QueryLogisticsResponseBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5628893876250056550L;

	private String message;

	private String com;

	private String status;

	private String state;

	private List<QueryLogisticsDataBO> data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<QueryLogisticsDataBO> getData() {
		return data;
	}

	public void setData(List<QueryLogisticsDataBO> data) {
		this.data = data;
	}
}
