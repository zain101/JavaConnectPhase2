package com.javaConnect.auth.model;



public class PostModel {

	private String title;
	private String author_name;
	private int pid;
	private String timestamp;
	private String body;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

}
