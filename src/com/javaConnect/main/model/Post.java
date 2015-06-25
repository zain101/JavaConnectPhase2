package com.javaConnect.main.model;

public class Post {
	private  String  body;
	private  String  timestamp;
	private  int   author_id;
	private  String  title;
	private  long  views;
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getViews() {
		return views;
	}
	public void setViews(long views) {
		this.views = views;
	}
}
