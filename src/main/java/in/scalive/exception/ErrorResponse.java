package in.scalive.exception;

import java.time.LocalTime;

public class ErrorResponse {

	private Integer statusCode;
	private String message;
	private LocalTime localTime;
	
	
	
	
	
	
	
	
	
	
	public ErrorResponse() {
		
	}
	public ErrorResponse(Integer statusCode, String message, LocalTime localTime) {
		
		this.statusCode = statusCode;
		this.message = message;
		this.localTime = localTime;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalTime getLocalTime() {
		return localTime;
	}
	public void setLocalTime(LocalTime localTime) {
		this.localTime = localTime;
	}
	
	
}
