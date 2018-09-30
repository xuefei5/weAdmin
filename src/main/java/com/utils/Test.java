package com.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args) {
		/*int i = 5000;
		Double j = (double) (i/100);
		DecimalFormat df=new DecimalFormat("0.00");
		System.out.println(String.valueOf(df.format(j)));*/
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		System.out.println(String.valueOf(sdf.format(date)));*/
		
		/*SimpleDateFormat sdf_input = new SimpleDateFormat("yyyyMMddhhmmss");//前台输入格式
        SimpleDateFormat sdf_target =new SimpleDateFormat("yyyy-MM-dd"); //账期展示格式
        try {
        	System.out.println(sdf_target.format(sdf_input.parse("20180520120202")) + "-" + sdf_target.format(sdf_input.parse("20180520120202")));
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		
		/*String str = "122M3912KB";
	    Pattern p=Pattern.compile("M(\\w+)KB");
	    Pattern pm=Pattern.compile("(\\w+)M");
	    Matcher m=p.matcher(str);
	    while(m.find()){
	    	String h = m.group(1);
	        System.out.println(h);
	    }*/
		
		/*List<String> list = new ArrayList<String>();
		list.add("h");
		System.out.println(list.size());*/
		
		/*double s = 0.23;
		System.out.println(String.valueOf(s));*/
		
		/*String startTime = "2018-04-01";
		startTime = startTime.replace("-", "") + "000000";
		System.out.println(startTime);*/
		
		/*Double j = Double.parseDouble("0.00");
		System.out.println(j);*/
		
		/*String t = "0.00";
		double c = Math.Round(Double.parseDouble(t), 2);*/
		
		/*DecimalFormat df=new DecimalFormat("0.00");
		double tmp=Math.floor(2.3);
		System.out.println(df.format(tmp));*/
		
		
		/*是否存在.字符*/
		/*String money = "22.00";
		if(money.contains(".")) {
			int index = money.indexOf(".");
			int length = money.length();
			System.out.println("index:"+index);
			System.out.println("length:"+length);
			
			if(length - index == 2) {
				money = money + "0";
			}else if (length - index == 1) {
				money = money + "00";
			}
		}else {
			money += ".00";
		}
		System.out.println(sumConvert("22.0"));*/
		
		
		/*Map<String,Object> oweFee = new HashMap<String,Object>();
		List<Map<String,Object>> list = new ArrayList();
		for(int i=0;i<10;i++) {
			
			oweFee.put("key", i);
			list.add(oweFee);
		}
		System.out.println(list);*/
		
		/*String s = "2018-09-16 00:00:00.0";
		System.out.println(s.substring(0, 10));*/
		
		/*List<String> s = new ArrayList<String>();
		s.add("a");
		s.add("n");
		
		for(int i = 0;i<s.size();i++) {
			System.out.println(s.get(i));
		}*/
		
		final String s = "HELLO";
		System.out.println(s);
	}
	
	public static String sumConvert(String sum) {
		if(sum.contains(".")) {
			int index = sum.indexOf(".");
			int length = sum.length();
			System.out.println("index:"+index);
			System.out.println("length:"+length);
			
			if(length - index == 2) {
				sum = sum + "0";
			}else if (length - index == 1) {
				sum = sum + "00";
			}
		}else {
			sum += ".00";
		}
		return sum;
	}
}
