package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 公共控制器
 * @author yangsheng
 *
 */
public class BaseController {
private static final transient Logger logger = Logger.getLogger(BaseController.class);
	
	/*字符串格式入参获取*/
	public String getInputString(HttpServletRequest request){
		
		BufferedReader br=null;
		String reqStr = "";
		try {
			request.setCharacterEncoding("UTF-8");
			br = new BufferedReader(new InputStreamReader( request.getInputStream(),"utf-8"));
			String line = null;
		    StringBuilder sb = new StringBuilder();
		    while((line = br.readLine())!=null){
	            sb.append(line);
	        }
		    reqStr = String.valueOf(sb);
		    logger.info("请求的入参：" + String.valueOf(sb));
		}catch (UnsupportedEncodingException e) {
			logger.info("请求入参编码异常！");
			e.printStackTrace();
		}catch (IOException e) {
			logger.info("io流异常！");
			e.printStackTrace();
		}finally {
			try {
				if(null != br) {
					br.close();
				}
			} catch (IOException e) {
				logger.info("关闭读取流失败！");
				e.printStackTrace();
			}
		}
        return reqStr;
	}
	
	/*json格式入参获取*/
	public JSONObject getInputObject(HttpServletRequest request){
		
		BufferedReader br=null;
		String reqStr = "";
		try {
			request.setCharacterEncoding("UTF-8");
			br = new BufferedReader(new InputStreamReader( request.getInputStream(),"utf-8"));
			String line = null;
		    StringBuilder sb = new StringBuilder();
		    while((line = br.readLine())!=null){
	            sb.append(line);
	        }
		    reqStr = String.valueOf(sb);
		    logger.info("请求的入参：" + String.valueOf(sb));
		}catch (UnsupportedEncodingException e) {
			logger.info("请求入参编码异常！");
			e.printStackTrace();
		}catch (IOException e) {
			logger.info("io流异常！");
			e.printStackTrace();
		}finally {
			try {
				if(null != br) {
					br.close();
				}
			} catch (IOException e) {
				logger.info("关闭读取流失败！");
				e.printStackTrace();
			}
		}
        
		JSONObject inObject = new JSONObject();
		try {
			inObject = (JSONObject) JSON.parse(reqStr);
		}catch(Exception e) {
			logger.info("非法报文请求！");
			inObject.put("code", "-9999");
			inObject.put("msg", "非法请求");
		}
		
        return inObject;
	}

}
