package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bean.Order;
import com.result.CodeMsg;
import com.result.Result;
import com.service.interfaces.IOrderSV;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

	private static final transient Logger logger = Logger.getLogger(OrderController.class);
			
	@Autowired
	IOrderSV orderSV;

	/**
	 * 分页查询客户订单
	 * @author yangsheng
	 */
	@RequestMapping("/qryOrderByPageNum")
	@ResponseBody
	public Result<Map<String,Object>> qryOrderByPageNum(HttpServletRequest request) {

		JSONObject jsonObj = super.getInputObject(request);
		int customerId = Integer.parseInt(jsonObj.getString("customerId"));
		int startPage = Integer.parseInt(jsonObj.getString("startPage"));
		int endPage = Integer.parseInt(jsonObj.getString("endPage"));
		
		Map<String,Object> mapToClient = new HashMap<String,Object>();
		List<Order> orderList = orderSV.qryOrderByPageNum(customerId,startPage,endPage);
		mapToClient.put("orderList", orderList);
		return Result.success(mapToClient);
	}
	
	/**
	 * 分页查询客户订单
	 * @author yangsheng
	 */
	@RequestMapping("/qryOrderByPageNumForIndoor")
	@ResponseBody
	public List<Order> qryOrderByPageNumForIndoor(int custId) {

		int customerId = custId;
		
		List<Order> orderList = orderSV.qryOrderByCustId(customerId);
		return orderList;
	}
	
	/**
	 * 根据订单Id查询订单
	 * @author xuefei
	 */
	@RequestMapping("/qryOrderInfoByOrderIdForIndoor")
	@ResponseBody
	public Order qryOrderInfoByOrderIdForIndoor(int orderId){
		Order order = orderSV.qryOrderInfoByOrderId(orderId);
		return order;
		
	}

	/**
	 * 订单添加
	 * @author yangsheng
	 */
	@RequestMapping(value = "/addOrder")
	@ResponseBody
	public Result<CodeMsg> addOrder(HttpServletRequest request) {

		String inputStr = super.getInputString(request);
		Order order = JSON.parseObject(inputStr, new TypeReference<Order>() {});
		
		if (orderSV.addOrder(order)) {
			return Result.success(CodeMsg.ORDER_ADD_SUCCESS);
		} else {
			return Result.error(CodeMsg.ORDER_ADD_FAIL);
		}
	}

	/**
	 * 订单删除
	 * @author yangsheng
	 */
	@RequestMapping(value = "/deleteOrder")
	@ResponseBody
	public Result<CodeMsg> deleteOrder(HttpServletRequest request) {
		
		JSONObject jsonObj = super.getInputObject(request);
		int id = Integer.parseInt(jsonObj.getString("id"));
		if (orderSV.deleteOrder(id)) {
			return Result.success(CodeMsg.ORDER_DELETE_SUCCESS);
		} else {
			return Result.error(CodeMsg.ORDER_DELETE_FAIL);
		}
	}
}
