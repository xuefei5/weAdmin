package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.Customer;
import com.result.CodeMsg;
import com.result.Result;
import com.service.interfaces.ICustomerSV;


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
	 */
	@RequestMapping(value = "/addCustomer")
	@ResponseBody
	public Result<CodeMsg> addCustomer(Customer Customer) {

		if (iCustSV.addCustomer(Customer)) {
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

		int id = Integer.parseInt(request.getParameter("id"));
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

}
