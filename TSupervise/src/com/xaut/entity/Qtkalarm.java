package com.xaut.entity;

import java.io.Serializable;

public class Qtkalarm implements Serializable {//可序列化类
	
	private int id;
	private int storenum;
	private String time, reason;
    
	public Qtkalarm(int id, int storenum, String time, String reason) {
		super();
		this.id = id;
		this.storenum = storenum;
		this.time = time;
		this.reason = reason;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStorenum() {
		return storenum;
	}

	public void setStorenum(int storenum) {
		this.storenum = storenum;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}


}
