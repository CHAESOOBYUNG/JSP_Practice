package com.practice.board.model;

import java.sql.Timestamp;

public class BoardVO {
	
	private int bno;
	private String title;
	private String writer; 
	private Timestamp regdate;
	private String content;
	
	public BoardVO() {
		
	}
	public BoardVO(int bno, String title, String writer, Timestamp regdate, String content) {
		super();
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.regdate = regdate;
		this.content = content;
	}
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
}
