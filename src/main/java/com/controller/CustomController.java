package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import com.bean.Customer;
import com.bean.Order;
import com.result.CodeMsg;
import com.result.Result;
import com.service.interfaces.ICustomerSV;
import com.utils.CommonUtil;
import com.utils.LocalConstants;
import com.utils.RooFtpUtils;


@Controller
@RequestMapping("/cust")
public class CustomController extends BaseController{
	
	// 日志输出
	private static final transient Logger logger = Logger
			.getLogger(CustomController.class);
	
	@Autowired
	ICustomerSV iCustSV;
	@Autowired
	OrderController orderController;

	/**
	 * 分页查询客户
	 * 
	 * @author xuefei
	 */
	@RequestMapping("/qryCustomerByPageNum")
	@ResponseBody
	public Result<Map<String,Object>> qryCustomerByPageNum(HttpServletRequest request) {
		JSONObject jsonObj = super.getInputObject(request);
		int startPage = Integer.parseInt(jsonObj.getString("startPage"));
		int endPage = Integer.parseInt(jsonObj.getString("endPage"));
		//定义返回前端的map
		Map<String,Object> mapToClient = new HashMap<String,Object>();

		List<Customer> CustomerList = iCustSV.qryCustomerByPageNum(startPage,endPage);
		//封装客户信息
		mapToClient.put("customerList", CustomerList);
		return Result.success(mapToClient);
	}
	
	/**
	 * 查询总条数
	 * 
	 * @author xuefei
	 */
	@RequestMapping("/qryAllCustomerCount")
	@ResponseBody
	public Result<Integer> qryAllCustomerCount() {
		int customerAllCount = iCustSV.getCustomerAllCount();
		logger.info("客户信息总条数为："+customerAllCount);
		return Result.success(customerAllCount);
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
	 * @throws IOException 
	 */
	@RequestMapping(value = "/addCustomer",method = RequestMethod.POST)
	@ResponseBody
	public Result<CodeMsg> addCustomer(Customer customer,HttpServletRequest req) {
		try{
		//上传到服务器的文件名
		String fileNameToUpload = "";
		//对上传的文件进行处理
		List<MultipartFile> files = ((MultipartHttpServletRequest) req).getFiles("headFile");
		//进入文件处理代码段
		if(null!=files&&files.size()>0){
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String fileTime = df.format(new Date());
			//文件大小
			int fileSize = (int) files.get(0).getSize();
			if(fileSize>LocalConstants.CONST_SET.FILE_MAX_SIZE){
				throw new Exception("文件最大允许上传4M,请重新选择!");
			}
			//文件名
			String fileName = files.get(0).getOriginalFilename();
			//文件名:时间+客户名+后缀名
			fileNameToUpload = fileTime+customer.getName()+fileName.substring(fileName.lastIndexOf("."));

	        try {
	            InputStream inputStream = files.get(0).getInputStream();
	            RooFtpUtils.pushToFtp(inputStream, fileNameToUpload);
	        } catch (Exception e) {
	        	
	        }
		}
		
		customer.setImgRef(fileNameToUpload);
			
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
		
		}catch(Exception e){
			e.printStackTrace();
			if(null==e.getMessage()){
				return Result.error(CodeMsg.CUSTOMER_ADD_FAIL_EXCEPTION);
			}
			return Result.error(new CodeMsg(101004, e.getMessage()));
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
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/updateCustomer")
	@ResponseBody
	public Result<CodeMsg> updateCustomer(Customer customer) throws ParseException {
		
		//出身日期--格式转换
		String birthday = CommonUtil.fomatDate(customer.getBirthday(), "MM/dd/yyyy", "yyyy-MM-dd HH:mm:ss");
		customer.setBirthday(birthday);

		if (iCustSV.updateCustomer(customer)) {
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
	 * Id查询客户
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
	
	/**
	 * Id查询客户
	 * 
	 * @author xuefei
	 */
	@RequestMapping(value = "/qryCustAndOrderByCustId")
	@ResponseBody
	public Result<Map<String,Object>> qryCustAndOrderByCustId(HttpServletRequest request) {
		JSONObject jsonObj = super.getInputObject(request);
		//根据客户ID查询客户信息
		int id = Integer.parseInt(jsonObj.getString("id"));
		Customer customer = iCustSV.qryById(id);
		//根据客户信息ID查询订单信息
		List<Order> orders = orderController.qryOrderByPageNumForIndoor(id,"0","10");
		
		//封装出参
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("customer", customer);
		result.put("orderList", orders);
		
		return Result.success(result);
	}

}
