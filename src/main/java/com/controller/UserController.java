package com.controller;

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bean.Contact;
import com.bean.Customer;
import com.bean.Test;
import com.bean.User;
import com.result.CodeMsg;
import com.result.Result;
import com.service.interfaces.IUserSV;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	// 日志输出
	private static final transient Logger logger = Logger
			.getLogger(UserController.class);

	@Autowired
	IUserSV userSV;

	/**
	 * 分页查询用户
	 * 
	 * @author yangsheng
	 */
	@RequestMapping("/qryUserByPageNum")
	@ResponseBody
	public Result<Map<String,Object>> qryUserByPageNum(HttpServletRequest request) {

		JSONObject jsonObj = super.getInputObject(request);
		int startPage = Integer.parseInt(jsonObj.getString("startPage"));
		int endPage = Integer.parseInt(jsonObj.getString("endPage"));
		
		Map<String,Object> mapToClient = new HashMap<String,Object>();
		List<User> userList = userSV.qryUserByPageNum(startPage,endPage);
		mapToClient.put("userList", userList);
		return Result.success(mapToClient);
	}

	/**
	 * 查询所有用户
	 * 
	 * @author yangsheng
	 */
	@RequestMapping("/qryAllUser")
	@ResponseBody
	public Result<List<User>> qryAllUser() {
		List<User> userList = userSV.qryAllUser();
		return Result.success(userList);
	}

	/**
	 * 进入登陆页面
	 * 
	 * @author xuefei
	 * */
	@RequestMapping(value = "/toLoginPage", method = RequestMethod.GET)
	public String toLoginPage() {
		return "login";
	}

	/**
	 * 进入登陆页面
	 * 
	 * @author yangsheng
	 * */
	@RequestMapping(value = "/toMainPage", method = RequestMethod.GET)
	public String toMainPage() {
		if (userSV.isLoin()) {
			return "main";
		}
		return "login";
	}

	/**
	 * 用户登录
	 * 
	 * @author yangsheng
	 * */
	@RequestMapping(value="/login")
 	@ResponseBody
 	public Result<CodeMsg> login(HttpServletRequest request){
 		
 		JSONObject jsonObj = super.getInputObject(request);
 		String name = jsonObj.getString("name");
 		String password = jsonObj.getString("password");
 		
 		logger.info("用户"+name+"在尝试进行登录操作");
 		
 		List<User> listUser = userSV.qryByName(name);
 		
        if(null==listUser||listUser.size()<=0){//用户不存在
 			return Result.error(CodeMsg.LOGIN_NAME_NOT_EXIST);
 		}
        boolean isLogin = userSV.login(name, password);
 		if(!isLogin) {//用户名或者密码错误
 			return Result.error(CodeMsg.LOGIN_NAME_OR_PSWD_ERROR);
 		}
 		
 		return Result.success(CodeMsg.LOGIN_SUCCESS);
 		
 	}

	/**
	 * 欢迎页面
	 * 
	 * @author yangsheng
	 * */
	@RequestMapping(value = "/welcome")
	public String welcome() {
		return "welcome";
	}

	/**
	 * 用户页面
	 * 
	 * @author yangsheng
	 * */
	@RequestMapping(value = "/user")
	public String user() {
		return "user";
	}

	/**
	 * 增加用户页面
	 * 
	 * @author yangsheng
	 * */
	@RequestMapping(value = "/addUserHtml")
	public String addUserHtml() {
		return "addUser";
	}

	/**
	 * 用户注销
	 * 
	 * @author yangsheng
	 */
	@RequestMapping(value = "/logout")
	public String logout() {

		if (userSV.logout()) {
			logger.info("用户注销成功，跳转到登录页面");
			return "login";
		} else {
			logger.info("用户注销失败，情况几乎不可能，直接返回登录页面");
			return "login";
		}
	}

	/**
	 * 用户添加
	 * 
	 * @author yangsheng
	 */
	@RequestMapping(value = "/addUser")
	@ResponseBody
	public Result<CodeMsg> addUser(HttpServletRequest request) {
		
		String inputStr = super.getInputString(request);
		User user = JSON.parseObject(inputStr, new TypeReference<User>() {});
		
		if (userSV.addUser(user)) {
			logger.info("用户添加成功");
			return Result.success(CodeMsg.USER_ADD_SUCCESS);
		} else {
			logger.info("用户添加失败");
			return Result.error(CodeMsg.USER_ADD_FAIL);
		}
	}

	/**
	 * 用户删除
	 * 
	 * @author yangsheng
	 */
	@RequestMapping(value = "/deleteUser")
	@ResponseBody
	public Result<CodeMsg> deleteUser(HttpServletRequest request) {
		
		JSONObject jsonObj = super.getInputObject(request);
		int id = Integer.parseInt(jsonObj.getString("id"));
		if (userSV.deleteUser(id)) {
			logger.info("用户删除成功");
			return Result.success(CodeMsg.USER_DELETE_SUCCESS);
		} else {
			logger.info("用户删除失败");
			return Result.error(CodeMsg.USER_DELETE_FAIL);
		}
	}

	/**
	 * 用户更新
	 * 
	 * @author yangsheng
	 */
	@RequestMapping(value = "/updateUser",method = RequestMethod.POST)
	@ResponseBody
	public Result<CodeMsg> updateUser(HttpServletRequest request) {
		
		String inputStr = super.getInputString(request);
		User user = JSON.parseObject(inputStr, new TypeReference<User>() {});
		
		if (userSV.updateUser(user)) {
			logger.info("用户更新成功");
			return Result.success(CodeMsg.USER_UPDATE_SUCCESS);
		} else {
			logger.info("用户更新失败");
			return Result.error(CodeMsg.USER_UPDATE_FAIL);
		}
	}

	/**
	 * 姓名查询用户
	 * 
	 * @author yangsheng
	 */
	@RequestMapping(value = "/qryByName")
	@ResponseBody
	public Result<List<User>> qryByName(HttpServletRequest request) {

		String name = request.getParameter("name");
		List<User> userList = userSV.qryByName(name);

		return Result.success(userList);
	}
	
	/**
	 * id查询用户
	 * 
	 * @author yangsheng
	 */
	@RequestMapping(value = "/qryUserById")
	@ResponseBody
	public Result<User> qryUserById(HttpServletRequest request) {

		JSONObject jsonObj = super.getInputObject(request);
		int id = Integer.parseInt(jsonObj.getString("id"));

		return Result.success(userSV.qryUserById(id));
	}
	
	/**
	 * 查询总条数
	 * 
	 * @author yangsheng
	 */
	@RequestMapping("/qryAllUserCount")
	@ResponseBody
	public Result<Integer> qryAllUserCount() {
		int customerAllCount = userSV.qryUserCount();
		return Result.success(customerAllCount);
	}
	
	/**
	 * 查询消息提醒总条数
	 * 
	 * @author yangsheng
	 */
	@RequestMapping("/qryAllTipsCount")
	@ResponseBody
	public Result<Integer> qryAllTipsCount() {
		int customerAllCount = userSV.qryAllTipsCount();
		return Result.success(customerAllCount);
	}
	
	/**
	 * 查询消息提醒信息
	 * 
	 * @author yangsheng
	 */
	@RequestMapping("/qryAllTips")
	@ResponseBody
	public Result<Map<String,Object>> qryAllTips() {
		Map<String,Object> rtnMap = new HashMap(); 
		List<Contact> contactList = userSV.qryAllTips();
		int customerAllCount = userSV.qryAllTipsCount();
		rtnMap.put("contactList", contactList);
		rtnMap.put("customerAllCount", customerAllCount);
		return Result.success(rtnMap);
	}
}
