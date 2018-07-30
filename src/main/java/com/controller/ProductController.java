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
import com.bean.Product;
import com.result.CodeMsg;
import com.result.Result;
import com.service.interfaces.IProductSV;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

	// 日志输出
	private static final transient Logger logger = Logger.getLogger(ProductController.class);
			
	@Autowired
	IProductSV productSV;

	/**
	 * 商品添加
	 * @author yangsheng
	 */
	@RequestMapping(value = "/addProduct")
	@ResponseBody
	public Result<CodeMsg> addProduct(HttpServletRequest request) {
		
		String inputStr = super.getInputString(request);
		Product Product = JSON.parseObject(inputStr, new TypeReference<Product>() {});
		
		if (productSV.addProduct(Product)) {
			return Result.success(CodeMsg.PRODUCT_ADD_SUCCESS);
		} else {
			return Result.error(CodeMsg.PRODUCT_ADD_FAIL);
		}
	}

	/**
	 * 商品删除
	 * @author yangsheng
	 */
	@RequestMapping(value = "/deleteProduct")
	@ResponseBody
	public Result<CodeMsg> deleteProduct(HttpServletRequest request) {
		
		JSONObject jsonObj = super.getInputObject(request);
		int id = Integer.parseInt(jsonObj.getString("id"));
		if (productSV.deleteProduct(id)) {
			return Result.success(CodeMsg.PRODUCT_DELETE_SUCCESS);
		} else {
			return Result.error(CodeMsg.PRODUCT_DELETE_FAIL);
		}
	}

	/**
	 * 商品更新
	 * @author yangsheng
	 */
	@RequestMapping(value = "/updateProduct",method = RequestMethod.POST)
	@ResponseBody
	public Result<CodeMsg> updateProduct(HttpServletRequest request) {
		
		String inputStr = super.getInputString(request);
		Product Product = JSON.parseObject(inputStr, new TypeReference<Product>() {});
		
		if (productSV.updateProduct(Product)) {
			return Result.success(CodeMsg.PRODUCT_UPDATE_SUCCESS);
		} else {
			return Result.error(CodeMsg.PRODUCT_UPDATE_FAIL);
		}
	}
	
	/**
	 * 分页查询商品
	 * @author yangsheng
	 */
	@RequestMapping("/qryProductByPageNum")
	@ResponseBody
	public Result<Map<String,Object>> qryProductByPageNum(HttpServletRequest request) {

		JSONObject jsonObj = super.getInputObject(request);
		int startPage = Integer.parseInt(jsonObj.getString("startPage"));
		int endPage = Integer.parseInt(jsonObj.getString("endPage"));
		
		Map<String,Object> mapToClient = new HashMap<String,Object>();
		List<Product> productList = productSV.qryProductByPageNum(startPage,endPage);
		mapToClient.put("productList", productList);
		return Result.success(mapToClient);
	}
	
	/**
	 * 根据商品关键词分页查询商品
	 * @author yangsheng
	 */
	@RequestMapping("/qryProductByNamePageNum")
	@ResponseBody
	public Result<Map<String,Object>> qryProductByNamePageNum(HttpServletRequest request) {

		JSONObject jsonObj = super.getInputObject(request);
		int startPage = Integer.parseInt(jsonObj.getString("startPage"));
		int endPage = Integer.parseInt(jsonObj.getString("endPage"));
		String name = null == jsonObj.getString("name")?"":jsonObj.getString("name");
		
		Map<String,Object> mapToClient = new HashMap<String,Object>();
		List<Product> productList = productSV.qryProductByNamePageNum(name,startPage,endPage);
		mapToClient.put("productList", productList);
		return Result.success(mapToClient);
	}
	
	/**
	 * 查询商品总条数
	 * @author yangsheng
	 */
	@RequestMapping("/qryAllProductCount")
	@ResponseBody
	public Result<Integer> qryAllProductCount() {
		int productCount = productSV.qryProductCount();
		return Result.success(productCount);
	}
	
	/**
	 * 根据关键词查询商品总条数
	 * @author yangsheng
	 */
	@RequestMapping("/qryProductByNameCount")
	@ResponseBody
	public Result<Integer> qryProductByNameCount(String name) {
		int productCount = productSV.qryProductByNameCount(name);
		return Result.success(productCount);
	}
	
	/**
	 * 商品购买
	 * @author yangsheng
	 */
	@RequestMapping("/purchaseProduct")
	@ResponseBody
	public Result<CodeMsg> purchaseProduct(HttpServletRequest request) {
		JSONObject jsonObj = super.getInputObject(request);
		
		if (productSV.purchaseProduct(jsonObj)) {
			return Result.success(CodeMsg.PRODUCT_PURCHASE_SUCCESS);
		} else {
			return Result.error(CodeMsg.PRODUCT_PURCHASE_FAIL);
		}
	}
}