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
import com.result.CodeMsg;
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
 			logger.info("用户"+name+"在尝试登录成功");
 			return "main";
 		}else {
 			logger.info("用户"+name+"在尝试登录失败");
 			return "login";
 		}
 	}
 	
 	/**
 	 * 欢迎页面
 	 * @author yangsheng
 	 * */
 	@RequestMapping(value="/welcome")
 	public String welcome(){
 		return "welcome";
 	}
 	
 	/**
 	 * 用户页面
 	 * @author yangsheng
 	 * */
 	@RequestMapping(value="/user")
 	public String user(){
 		return "user";
 	}
 	
 	/**
 	 * 用户注销
 	 * @author yangsheng
 	 */
 	@RequestMapping(value="/logout")
 	public String logout(){
 		
 		if(userSV.logout()) {
 			logger.info("用户注销成功，跳转到登录页面");
 			return "login";
 		}else {
 			logger.info("用户注销失败，情况几乎不可能，直接返回登录页面");
 			return "login";
 		}
 	}
 	
 	/**
 	 * 用户添加
 	 * @author yangsheng
 	 */
 	@RequestMapping(value="/addUser")
 	@ResponseBody
 	public Result<CodeMsg> addUser(User user){
 		
 		if(userSV.addUser(user)) {
 			logger.info("用户添加成功");
 			return Result.success(CodeMsg.USER_ADD_SUCCESS);
 		}else {
 			logger.info("用户添加失败");
 			return Result.error(CodeMsg.USER_ADD_FAIL);
 		}
 	}
 	
 	/**
 	 * 用户删除
 	 * @author yangsheng
 	 */
 	@RequestMapping(value="/deleteUser")
 	@ResponseBody
 	public Result<CodeMsg> deleteUser(HttpServletRequest request){
 		
 		int id = Integer.parseInt(request.getParameter("id"));
 		if(userSV.deleteUser(id)) {
 			logger.info("用户删除成功");
 			return Result.success(CodeMsg.USER_DELETE_SUCCESS);
 		}else {
 			logger.info("用户删除失败");
 			return Result.error(CodeMsg.USER_DELETE_FAIL);
 		}
 	}
 	
 	/**
 	 * 用户更新
 	 * @author yangsheng
 	 */
 	@RequestMapping(value="/updateUser")
 	@ResponseBody
 	public Result<CodeMsg> updateUser(User user){
 		
 		if(userSV.updateUser(user)) {
 			logger.info("用户更新成功");
 			return Result.success(CodeMsg.USER_UPDATE_SUCCESS);
 		}else {
 			logger.info("用户更新失败");
 			return Result.error(CodeMsg.USER_UPDATE_FAIL);
 		}
 	}
 	
 	/**
 	 * 姓名查询用户
 	 * @author yangsheng
 	 */
 	@RequestMapping(value="/qryByName")
 	@ResponseBody
 	public Result<String> qryByName(HttpServletRequest request){
 		
 		String name = request.getParameter("name");
 		List<User> userList = userSV.qryByName(name);
 		if(null == userList || userList.size() == 0) {
 			return Result.success(String.valueOf(userList));
 		}else {
 			return Result.error(CodeMsg.QRY_USER_BY_NAME_FAIL);
 		}
 	}
}
