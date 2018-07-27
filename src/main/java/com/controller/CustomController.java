package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.bean.Customer;
import com.result.CodeMsg;
import com.result.Result;
import com.service.interfaces.ICustomerSV;
import com.utils.CommonUtil;


@Controller
@RequestMapping("/cust")
public class CustomController extends BaseController{
	
	// 日志输出
	private static final transient Logger logger = Logger
			.getLogger(CustomController.class);
	
	@Autowired
	ICustomerSV iCustSV;

	/**
	 * 分页查询客户
	 * 
	 * @author xuefei
	 */
	@RequestMapping("/qryCustomerByPageNum")
	@ResponseBody
	public Result<List<Customer>> qryCustomerByPageNum(HttpServletRequest request) {

		int pageNum = Integer
				.parseInt(null == request.getParameter("pageNum") ? "1"
						: request.getParameter("pageNum"));
		List<Customer> CustomerList = iCustSV.qryCustomerByPageNum(pageNum);
		return Result.success(CustomerList);
	}

	/**
	 * 查询所有客户
	 * 
	 * @author xuefei
	 */
	@RequestMapping("/qryAllCustomer")
	@ResponseBody
	public Result<List<Customer>> qryAllCustomer() {
		List<Customer> CustomerList = iCustSV.qryAllCustomer();
		logger.info(CustomerList);
		return Result.success(CustomerList);
	}

	/**
	 * 客户页面
	 * 
	 * @author xuefei
	 * */
	@RequestMapping(value = "/customer")
	public String Customer() {
		return "customer";
	}

	/**
	 * 增加客户页面
	 * 
	 * @author xuefei
	 * */
	@RequestMapping(value = "/addCustomerHtml")
	public String addCustomerHtml() {
		return "addCustomer";
	}

	/**
	 * 客户添加
	 * 
	 * @author xuefei
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/addCustomer",method = RequestMethod.POST)
	@ResponseBody
	public Result<CodeMsg> addCustomer(Customer customer,HttpServletRequest req) throws ParseException {
		//对上传的文件进行处理
		List<MultipartFile> files = ((MultipartHttpServletRequest) req).getFiles("headFile");
		//进入文件处理代码段
		if(null!=files&&files.size()>=0){
			
		}
			
		//添加时间--获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String addTime = df.format(new Date());
		customer.setAddTime(addTime);
		
		//出身日期--格式转换
		String birthday = CommonUtil.fomatDate(customer.getBirthday(), "MM/dd/yyyy", "yyyy-MM-dd HH:mm:ss");
		customer.setBirthday(birthday);

		if (iCustSV.addCustomer(customer)) {
			logger.info("客户添加成功");
			return Result.success(CodeMsg.CUSTOMER_ADD_SUCCESS);
		} else {
			logger.info("客户添加失败");
			return Result.error(CodeMsg.CUSTOMER_ADD_FAIL);
		}
	}

	/**
	 * 客户删除
	 * 
	 * @author xuefei
	 */
	@RequestMapping(value = "/deleteCustomer")
	@ResponseBody
	public Result<CodeMsg> deleteCustomer(HttpServletRequest request) {
		JSONObject jsonObj = super.getInputObject(request);
		int id = Integer.parseInt(jsonObj.getString("id"));
		if (iCustSV.deleteCustomer(id)) {
			logger.info("客户删除成功");
			return Result.success(CodeMsg.CUSTOMER_DELETE_SUCCESS);
		} else {
			logger.info("客户删除失败");
			return Result.error(CodeMsg.CUSTOMER_DELETE_FAIL);
		}
	}

	/**
	 * 客户更新
	 * 
	 * @author xuefei
	 */
	@RequestMapping(value = "/updateCustomer")
	@ResponseBody
	public Result<CodeMsg> updateCustomer(Customer Customer) {

		if (iCustSV.updateCustomer(Customer)) {
			logger.info("客户更新成功");
			return Result.success(CodeMsg.CUSTOMER_UPDATE_SUCCESS);
		} else {
			logger.info("客户更新失败");
			return Result.error(CodeMsg.CUSTOMER_UPDATE_FAIL);
		}
	}

	/**
	 * 姓名查询客户
	 * 
	 * @author xuefei
	 */
	@RequestMapping(value = "/qryByName")
	@ResponseBody
	public Result<List<Customer>> qryByName(HttpServletRequest request) {

		String name = request.getParameter("name");
		List<Customer> CustomerList = iCustSV.qryByName(name);

		return Result.success(CustomerList);
	}
	
	/**
	 * 姓名查询客户
	 * 
	 * @author xuefei
	 */
	@RequestMapping(value = "/qryCustomerById")
	@ResponseBody
	public Result<Customer> qryCustomerById(HttpServletRequest request) {
		JSONObject jsonObj = super.getInputObject(request);
		int id = Integer.parseInt(jsonObj.getString("id"));
		Customer Customer = iCustSV.qryById(id);
		return Result.success(Customer);
	}

}
