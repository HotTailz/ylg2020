package com.yanda.common;

public class ServerResponse<T> {
	private int status;//返回到前端的状态码
	private String msg;
	private T data;//返回到前端的数据
	
	private ServerResponse() {
		
	}
	
	private ServerResponse(int status) {
		this.status = status;
	}
	
	private ServerResponse(int status,T data) {
		this.status = status;
		this.data = data;
	}
	
	private ServerResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	
	private ServerResponse(int status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	public static <T> ServerResponse<T> serverResponseBySuccess(){
		return new ServerResponse<T>(ResponseCode.SUSSCESS);
	}
	
	public static <T> ServerResponse<T> serverResponseBySuccess(T data){
		return new ServerResponse<T>(ResponseCode.SUSSCESS,data);
	}
	
	public static <T> ServerResponse<T> serverResponseBySuccess(String msg,T data){
		return new ServerResponse<T>(ResponseCode.SUSSCESS,msg,data);
	}
	
	public static <T> ServerResponse<T> serverResponseByError(){
		return new ServerResponse<T>(ResponseCode.ERROR);
	}
	
	public static <T> ServerResponse<T> serverResponseByError(String msg){
		return new ServerResponse<T>(ResponseCode.ERROR,msg);
	}
	
	public static <T> ServerResponse<T> serverResponseByError(int status){
		return new ServerResponse<T>(status);
	}
	
	public static <T> ServerResponse<T> serverResponseByError(int status,String msg){
		return new ServerResponse<T>(status,msg);
	}
	
	//判断接口是否正确返回
	
	public boolean isuccess() {
		return this.status==ResponseCode.SUSSCESS;
	}
	
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ServerResponse [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		ServerResponse serverResponse=new ServerResponse( 0,new Object());
		
		System.out.println(serverResponse);
		ServerResponse serverResponse1=new ServerResponse( 0,"123");
		
		System.out.println(serverResponse1);
	
		
	}

}
