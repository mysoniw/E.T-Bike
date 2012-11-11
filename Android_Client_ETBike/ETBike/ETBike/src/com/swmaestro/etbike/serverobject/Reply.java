package com.swmaestro.etbike.serverobject;

import java.util.Date;


public class Reply{

	private String message;
	private String writer;
	private Board board;
	private Date updatedTime;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedTimestamp(){
		Date date = getUpdatedTime();
		return date != null ?
				TimeUtils.getTimeStamp(date.getTime())
				: "";
	}

}
