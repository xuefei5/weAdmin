package com.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
	
	/*md5 加密*/
	public static String md5(String str) {
		return DigestUtils.md5Hex(str);
	}
	
	/*md5 salt值*/
	private static final String salt = "weAdmin";
	
	/*md5 将表单信息一次加密*/
	public static String inputPassToFormPass(String inputPass) {
		String str = ""+salt.charAt(0)+salt.charAt(1) + inputPass +salt.charAt(3) + salt.charAt(5);
		return md5(str);
	}
	
	/*md5 将传过来的表单信息加密*/
	public static String formPassToDBPass(String formPass) {
		String str = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(4);
		return md5(str);
	}
	
	/*md5 二次加密*/
	public static String inputPassToDbPass(String inputPass) {
		String formPass = inputPassToFormPass(inputPass);
		String dbPass = formPassToDBPass(formPass);
		return dbPass;
	}
	
	public static void main(String[] args) {
		System.out.println("用户加密信息："+"ys123456");
		System.out.println("用户表单信息加密："+inputPassToFormPass("ys123456"));
		System.out.println("用户数据信息加密："+formPassToDBPass("ys123456"));
		System.out.println("用户表单加密数据加密存储："+inputPassToDbPass("ys123456"));
	}
	
}
