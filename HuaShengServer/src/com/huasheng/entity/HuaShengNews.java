package com.huasheng.entity;
/**
 * 实体类，其实在这个project中没有用到
 * @author anyang
 *
 */
public class HuaShengNews {
	
	private int HSNewsId;

	private String Title;
	
	private String Content;
	
	private String Author;

    private String CreateDate;
	
	private String ClickCount;

	public int getHSNewsId() {
		return HSNewsId;
	}

	public void setHSNewsId(int hSNewsId) {
		HSNewsId = hSNewsId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}

	public String getClickCount() {
		return ClickCount;
	}

	public void setClickCount(String clickCount) {
		ClickCount = clickCount;
	}
	
}
