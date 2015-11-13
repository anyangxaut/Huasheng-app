package com.huasheng.dao;

public interface QtkSearch {

	public String chart(String latesttime, String storenum);
	public String table(String starttime, String endtime);
	public String alarm(String starttime, String endtime);
}
