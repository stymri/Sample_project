package controller;

public class ResponseMessage {
	private int id;
	private String message;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ResponseMessage(int id, String message) {
		super();
		this.id = id;
		this.message = message;
	}
	public ResponseMessage() {
		super();
	}
	
}
