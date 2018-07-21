package com.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.User;
import com.result.Result;
import com.service.interfaces.IUserSV;

@Controller
@RequestMapping("/user")
public class UserController {
	
	//日志输出
	private static final transient Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	IUserSV userSV;
	
	/**
	 * 查询所有用户
	 * @author yangsheng
	 */
	@RequestMapping("/qryAllUser")
    @ResponseBody
    public Result<String> qryAllUser() {
		List<User> userList=userSV.qryAllUser();
 		return Result.success(String.valueOf(userList));
    } 

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
 	@RequestMapping(value="/", method=RequestMethod.GET)
 	public String entrySystem(){
 		return "adminIndex";
 	}
}
