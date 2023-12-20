package com.cg.sakila.Payload;

import java.time.LocalDate;

public class ErrorResp {

	private String message;
	private LocalDate timeStamp;
	private int statusCode;

	public ErrorResp() {
		super();
	}

	public ErrorResp(String message) {
		super();
		this.message = message;
		this.timeStamp = LocalDate.now();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDate getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "ErrorResp [message=" + message + ", time=" + timeStamp + "]";
	}

	public void setStatusCode(int value) {
		// TODO Auto-generated method stub
		
	}
	
	
	public int getStatusCode() {
		return statusCode;
	}

}