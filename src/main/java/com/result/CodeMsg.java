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
	public static CodeMsg PRODUCT_ADD_SUCCESS = new CodeMsg(100302, "添加商品成功");
	public static CodeMsg PRODUCT_ADD_FAIL = new CodeMsg(100303, "添加商品失败");
	public static CodeMsg PRODUCT_DELETE_SUCCESS = new CodeMsg(100304, "删除商品成功");
	public static CodeMsg PRODUCT_DELETE_FAIL = new CodeMsg(100305, "删除商品失败");
	public static CodeMsg PRODUCT_UPDATE_SUCCESS = new CodeMsg(100306, "商品更新成功");
	public static CodeMsg PRODUCT_UPDATE_FAIL = new CodeMsg(100307, "商品更新失败");
	public static CodeMsg PRODUCT_PURCHASE_SUCCESS = new CodeMsg(100308, "商品购买成功");
	public static CodeMsg PRODUCT_PURCHASE_FAIL = new CodeMsg(100309, "商品购买失败");
	
	//订单模块 1004XX
	public static CodeMsg ORDER_ADD_SUCCESS = new CodeMsg(100401, "添加订单成功");
	public static CodeMsg ORDER_ADD_FAIL = new CodeMsg(100402, "添加订单失败");
	public static CodeMsg ORDER_DELETE_SUCCESS = new CodeMsg(100403, "删除订单成功");
	public static CodeMsg ORDER_DELETE_FAIL = new CodeMsg(100404, "删除订单失败");
	
	//文件模块 1005XX
	
	//用户添加 1006xx
	public static CodeMsg USER_ADD_SUCCESS = new CodeMsg(100601, "用户添加成功");
	public static CodeMsg USER_ADD_FAIL = new CodeMsg(100602, "用户添加失败");
	
	//用户删除  1007xx
	public static CodeMsg USER_DELETE_SUCCESS = new CodeMsg(100701, "用户删除成功");
	public static CodeMsg USER_DELETE_FAIL = new CodeMsg(100702, "用户删除失败");
	
	//用户更新 1008xx
	public static CodeMsg USER_UPDATE_SUCCESS = new CodeMsg(100801, "用户更新成功");
	public static CodeMsg USER_UPDATE_FAIL = new CodeMsg(100802, "用户更新失败");
	
	//姓名查询用户 1009xx
	public static CodeMsg QRY_USER_BY_NAME_SUCCESS = new CodeMsg(100901, "查询用户信息不为空");
	public static CodeMsg QRY_USER_BY_NAME_FAIL = new CodeMsg(100902, "查询用户信息为空");
	
	
	
	//客户添加 1010xx
	public static CodeMsg CUSTOMER_ADD_SUCCESS = new CodeMsg(101001, "客户添加成功");
	public static CodeMsg CUSTOMER_ADD_FAIL = new CodeMsg(101002, "客户添加失败");
	public static CodeMsg CUSTOMER_ADD_FAIL_EXCEPTION = new CodeMsg(101003, "系统内部异常");
	
	//客户删除  1011xx
	public static CodeMsg CUSTOMER_DELETE_SUCCESS = new CodeMsg(101101, "客户删除成功");
	public static CodeMsg CUSTOMER_DELETE_FAIL = new CodeMsg(101102, "客户删除失败");
	
	//客户更新 1012xx
	public static CodeMsg CUSTOMER_UPDATE_SUCCESS = new CodeMsg(101201, "客户更新成功");
	public static CodeMsg CUSTOMER_UPDATE_FAIL = new CodeMsg(101202, "客户更新失败");
	
	//姓名查询客户 1013xx
	public static CodeMsg CUSTOMER_USER_BY_NAME_SUCCESS = new CodeMsg(101301, "查询客户信息不为空");
	public static CodeMsg CUSTOMER_USER_BY_NAME_FAIL = new CodeMsg(101302, "查询客户信息为空");
		
	public CodeMsg(int code, String msg) {
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
