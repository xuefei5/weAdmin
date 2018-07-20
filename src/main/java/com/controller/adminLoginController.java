package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.Test;
import com.result.CodeMsg;
import com.result.Result;
import com.service.TestService;


@Controller
@RequestMapping("/login")
public class adminLoginController {
	
	//日志输出
	private static final transient Logger logger = Logger.getLogger(adminLoginController.class);
	
	/**
	 * 进入登陆页面
	 * @author xuefei
	 * */
 	@RequestMapping(value="/adminLogin", method=RequestMethod.GET)
    public String home() {
        return "adminLogin";
    }
 	
 	/**
 	 * 登录系统
 	 * @author xuefei
 	 * */
 	@RequestMapping(value="/entrySystem", method=RequestMethod.GET)
 	public String entrySystem(){
 		return "adminIndex";
 	}
	 	
	 	
}
