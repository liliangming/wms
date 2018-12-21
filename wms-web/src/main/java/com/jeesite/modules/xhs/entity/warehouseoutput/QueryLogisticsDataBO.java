package com.jeesite.modules.xhs.entity.warehouseoutput;

import java.io.Serializable;

public class QueryLogisticsDataBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5628893876250056550L;

	private String time;

	private String ftime;

	private String context;
	
	private String location;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFtime() {
		return ftime;
	}

	public void setFtime(String ftime) {
		this.ftime = ftime;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
