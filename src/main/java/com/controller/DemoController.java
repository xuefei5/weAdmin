package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bean.Test;
import com.bean.User;
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
 		return Result.success("hello,here");
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
	 	
	
 	/*mode解析数据，使用主题thymeleaf*/
 	@RequestMapping(value="/resolveByModel", method=RequestMethod.GET)
    public String resolveByModel(Model model) {
 		model.addAttribute("name", "薛飞");
 		
 		ArrayList arrayList = new ArrayList();
 		
 		JSONObject jsonObj1 = new JSONObject();
 		JSONObject jsonObj2 = new JSONObject();
 		
 		jsonObj1.put("name", "杨胜");
 		jsonObj1.put("age", "21");
 		
 		jsonObj2.put("name", "薛飞");
 		jsonObj2.put("age", "23");
 		
 		arrayList.add(jsonObj1);
 		arrayList.add(jsonObj2);
 		
 		model.addAttribute("arrayList", arrayList);
        return "htmlResoveByModel";
    }
	
 	/*跳转到ajax测试页面*/
 	@RequestMapping(value="/toHtmlTestOfAjax", method=RequestMethod.GET)
    public String toHtmlTestOfAjax(Model model) {
        return "htmlResolveByAjax";
    }
 	
 	/*html解析数据，使用ajax*/
 	@RequestMapping(value="/resolveByAjax", method=RequestMethod.POST)
 	@ResponseBody
    public Result<ArrayList> resolveByAjax() {
 		ArrayList arrayList = new ArrayList();
 		
 		JSONObject jsonObj1 = new JSONObject();
 		JSONObject jsonObj2 = new JSONObject();
 		
 		jsonObj1.put("name", "杨胜");
 		jsonObj1.put("age", "21");
 		
 		jsonObj2.put("name", "薛飞");
 		jsonObj2.put("age", "23");
 		
 		arrayList.add(jsonObj1);
 		arrayList.add(jsonObj2);
 		
 		logger.info(arrayList);
 		arrayList.size();
 		int [] k = null ;
 		int h = k.length;
 		return Result.success(arrayList);
    }
 	
 	
}
