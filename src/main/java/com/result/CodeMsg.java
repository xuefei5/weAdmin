package com.result;

public class CodeMsg {
	private int code;
	private String msg;
	
	//通用返回
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static CodeMsg SERVER_ERROR = new CodeMsg(-999, "服务端异常");
	
	
	//登录模块 1002XX
	public static CodeMsg LOGIN_NAME_OR_PSWD_ERROR = new CodeMsg(100201, "用户名或密码不正确");
	public static CodeMsg LOGIN_NAME_NOT_EXIST = new CodeMsg(100202, "用户名不存在");
	public static CodeMsg LOGIN_SUCCESS = new CodeMsg(100203, "用户登录成功");
	
	//商品模块 1003XX
	public static CodeMsg PRODUCT_NOT_EXIST = new CodeMsg(100301, "商品信息不存在");
	
	
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
