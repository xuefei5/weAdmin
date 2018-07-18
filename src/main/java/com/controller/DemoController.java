package com.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.Test;
import com.result.CodeMsg;
import com.result.Result;
import com.service.TestService;


@Controller
@RequestMapping("/demo")
public class DemoController {
	
	//日志输出
	private static final transient Logger logger = Logger.getLogger(DemoController.class);
	
	@Autowired
	TestService testService;
	
	@Autowired
	Test test;
	
 	@RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
 	
 	
 	/*json 格式正确输出*/
 	@RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello() {
 		return Result.success("hello,imooc");
    }
 	
 	/*json 格式错误输出*/
 	@RequestMapping("/helloError")
    @ResponseBody
    public Result<String> helloError() {
 		return Result.error(CodeMsg.SERVER_ERROR);
    }
 	
 	/*html 格式输出*/
 	@RequestMapping("/thymeleaf")
    public String  thymeleaf(Model model) {
 		model.addAttribute("name", "Joshua");
 		return "hello";
    }
 	
 	/*数据库插入测试*/
 	@RequestMapping("/sqlTest")
    @ResponseBody
    public Result<String> sqlTest() {
 		testService.tx();
 		test.setId(0);
 		test.setName("yangsheng");
 		return Result.success(String.valueOf(test));
    } 	
	 	
	 	
}
