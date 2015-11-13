package com.xaut.entity;

import java.io.Serializable;

public class Qtkentity implements Serializable {//可序列化类
	
	private int id;
	private int storenum;
	private String time;
    private double t1, t2, o2, co2, humidity;
    
	public Qtkentity(int id, int storenum, String time, double t1, double t2,
			double o2, double co2, double humidity) {
		super();
		this.id = id;
		this.storenum = storenum;
		this.time = time;
		this.t1 = t1;
		this.t2 = t2;
		this.o2 = o2;
		this.co2 = co2;
		this.humidity = humidity;
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

	public double getT1() {
		return t1;
	}

	public void setT1(float t1) {
		this.t1 = t1;
	}

	public double getT2() {
		return t2;
	}

	public void setT2(float t2) {
		this.t2 = t2;
	}

	public double getO2() {
		return o2;
	}

	public void setO2(float o2) {
		this.o2 = o2;
	}

	public double getCo2() {
		return co2;
	}

	public void setCo2(float co2) {
		this.co2 = co2;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}
	
    
}
