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

import com.alibaba.fastjson.JSONObject;
import com.bean.Contact;
import com.bean.Order;
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
	public Result<CodeMsg> addContact(Contact Contact,HttpServletRequest req) {
		try{

		if (iContactSV.addContact(Contact)) {
			logger.info("联系记录添加成功");
			return Result.success(CodeMsg.CANTACT_ADD_SUCCESS);
		} else {
			logger.info("联系记录添加失败");
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
