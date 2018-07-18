package com.result;

public class CodeMsg {
	private int code;
	private String msg;
	
	//通用返回
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static CodeMsg SERVER_ERROR = new CodeMsg(-999, "服务端异常");
	
	
	//登录模块 1002XX
	
	//商品模块 1003XX
	
	//订单模块 1004XX
	
	//文件模块 1005XX
	
	
	private CodeMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
}
