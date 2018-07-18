package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

public class RooCommonUtils {
	
	/*按key，获取json值*/
	public static String getJsonValue(JSONObject jsonObject , String key) {
		if(jsonObject == null || jsonObject.get(key) == null || jsonObject.get(key) == "") {
			return "";
		}
		return String.valueOf(jsonObject.get(key));
	}
	
	/*校验不为空，或空字符*/
	public static Boolean isEmptyJson(JSONObject jsonObject , String key) {
		if(jsonObject.get(key)==null || jsonObject.get(key)=="") {
			return true;
		}
		return false;
	}
	
	/*获取当前时间，存入数据库*/
	public static String getCurrentDate(){
		Date date = new Date();
		SimpleDateFormat Format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return String.valueOf(Format.format(date));
	}

}
