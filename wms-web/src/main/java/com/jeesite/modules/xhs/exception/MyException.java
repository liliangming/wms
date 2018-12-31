package com.jeesite.modules.xhs.exception;

public class MyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3142908432046017664L;
	
	private String msg;
	
	public MyException()
	{
		super();
	}
	
	public MyException(String msg)
	{
		super(msg);
		this.msg = msg;
	}
	
	public MyException(String msg, Throwable t)
	{
		super(msg);
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
