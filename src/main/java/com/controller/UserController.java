package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
 	@RequestMapping(value="/toLoginPage", method=RequestMethod.GET)
    public String toLoginPage() {
        return "login";
    }
 	
 	/**
 	 * 用户登录
 	 * @author yangsheng
 	 * */
 	@RequestMapping(value="/login")
 	public String login(HttpServletRequest request){
 		
 		String name = request.getParameter("name");
 		String password = request.getParameter("password");
 		logger.info("用户"+name+"在尝试进行登录操作");
 		
 		if(userSV.login(name, password)) {
 			logger.info("用户"+name+"在尝试登录失败");
 			return "main";
 		}else {
 			logger.info("用户"+name+"在尝试登录成功");
 			return "login";
 		}
 	}
 	
 	
}
