package com.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bean.Contact;
import com.bean.Order;
import com.bean.User;
import com.result.CodeMsg;
import com.result.Result;
import com.service.interfaces.IContactSV;
import com.utils.CommonUtil;
import com.utils.LocalConstants;


@Controller
@RequestMapping("/contact")
public class ContactController extends BaseController{
	
	// 日志输出
	private static final transient Logger logger = Logger
			.getLogger(ContactController.class);
	
	@Autowired
	IContactSV iContactSV;
	

	/**
	 * 联系信息添加
	 * 
	 * @author xuefei
	 * @throws ParseException 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/addContact",method = RequestMethod.POST)
	@ResponseBody
	public Result<CodeMsg> addContact(HttpServletRequest req) {
		try{
			JSONObject jsonObj = super.getInputObject(req);
			Contact contact = new Contact();
			//联系时间--格式转换
			String contactTime = CommonUtil.fomatDate(jsonObj.getString("contactTime"), "MM/dd/yyyy", "yyyy-MM-dd HH:mm:ss");
			contact.setContactTime(contactTime);
			
			//预约时间--格式转换
			String subscribeTime = CommonUtil.fomatDate(jsonObj.getString("subscribeTime"), "MM/dd/yyyy", "yyyy-MM-dd HH:mm:ss");
			contact.setSubscribeTime(subscribeTime);
			//联系内容
			contact.setContent(jsonObj.getString("content"));
			//是否有机会
			contact.setIsChance(jsonObj.getString("isChance"));
			//客户ID
			contact.setCustomerId(jsonObj.getInteger("customerId"));
			
			//主键Id
			contact.setId(jsonObj.getInteger("id"));

		if (iContactSV.addContact(contact)) {
			logger.info("联系记录保存成功");
			return Result.success(CodeMsg.CANTACT_ADD_SUCCESS);
		} else {
			logger.info("联系记录保存失败");
			return Result.error(CodeMsg.CANTACT_ADD_FAIL);
		}
		
		}catch(Exception e){
			e.printStackTrace();
			if(null==e.getMessage()){
				return Result.error(CodeMsg.CANTACT_ADD_FAIL_EXCEPTION);
			}
			return Result.error(new CodeMsg(101404, e.getMessage()));
		}
		
	}
	
	/**
	 * 联系信息删除
	 * 
	 * @author xuefei
	 */
	@RequestMapping(value = "/deleteContact")
	@ResponseBody
	public Result<CodeMsg> deleteContact(HttpServletRequest request) {
		JSONObject jsonObj = super.getInputObject(request);
		int id = Integer.parseInt(jsonObj.getString("id"));
		if (iContactSV.deleteContact(id)) {
			logger.info("联系信息删除成功");
			return Result.success(CodeMsg.CANTACT_DELETE_SUCCESS);
		} else {
			logger.info("联系信息删除失败");
			return Result.error(CodeMsg.CANTACT_DELETE_FAIL);
		}
	}
	
	/**
	 * 根据客户Id查询联系记录
	 * 
	 * @author xuefei
	 */
	@RequestMapping(value = "/qryContactInfoByCustId")
	@ResponseBody
	public Result<List<Contact>> qryCustAndOrderByCustId(HttpServletRequest request) {
		JSONObject jsonObj = super.getInputObject(request);
		//根据客户Id查询联系信息
		int id = Integer.parseInt(jsonObj.getString("id"));
		//根据联系信息ID查询订单信息
		List<Contact> contacts = iContactSV.qryByCustId(id);
		
		
		return Result.success(contacts);
	}

}
