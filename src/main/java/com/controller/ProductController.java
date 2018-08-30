package com.controller;

import java.io.File;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bean.Product;
import com.bean.ProductCart;
import com.bean.User;
import com.result.CodeMsg;
import com.result.Result;
import com.service.interfaces.IProductSV;
import com.utils.LocalConstants;

@Controller
@RequestMapping("/prod")
public class ProductController extends BaseController {

	// 日志输出
	private static final transient Logger logger = Logger.getLogger(ProductController.class);
			
	@Autowired
	IProductSV productSV;
	
	/**
	 * 商品页面
	 * 
	 * @author yangsheng
	 * */
	@RequestMapping(value = "/product")
	public String Customer() {
		return "product";
	}

	/**
	 * 商品添加购物车 
	 * @author yangsheng
	 */
	@RequestMapping(value = "/addProdToCart")
	@ResponseBody
	public Result<CodeMsg> addProdToCart(HttpServletRequest request) {
		
		JSONObject inputObj = super.getInputObject(request);
		int productId = Integer.parseInt(inputObj.getString("id"));
		
		if (productSV.addProdToCart(productId)) {
			return Result.success(CodeMsg.PRODUCT_ADDTOCART_SUCCESS);
		} else {
			return Result.error(CodeMsg.PRODUCT_ADDTOCART_FAIL);
		}
	}
	
	/**
	 * 商品添加
	 * @author yangsheng
	 */
	@RequestMapping(value = "/addProduct")
	@ResponseBody
	public Result<CodeMsg> addProduct(Product product,HttpServletRequest request) {
		//String inputStr = super.getInputString(request);
		//Product Product = JSON.parseObject(inputStr, new TypeReference<Product>() {});
		try{
			//上传到服务器的文件名
			String fileNameToUpload = "";
			//上传服务器文件地址
			String ftpFilePath = "";
			//对上传的文件进行处理
			List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("headFile");
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
				//文件名:时间+商品名+后缀名
				fileNameToUpload = fileTime+product.getName()+fileName.substring(fileName.lastIndexOf("."));

				ftpFilePath = LocalConstants.CONST_SET.FILE_UPLOAD_PATH + fileNameToUpload;
				File dest = new File(ftpFilePath);
		        // 检测是否存在目录
		        if (!dest.getParentFile().exists()) {
		            dest.getParentFile().mkdirs();
		        }
		        //上传
		        files.get(0).transferTo(dest);
			}
			product.setImgRef(LocalConstants.CONST_SET.SERV_IP + "/" + fileNameToUpload);
			if (productSV.addProduct(product)) {
				return Result.success(CodeMsg.PRODUCT_ADD_SUCCESS);
			} else {
				return Result.error(CodeMsg.PRODUCT_ADD_FAIL);
			}
		}catch(Exception e) {
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
	 * 商品购物车删除
	 * @author yangsheng
	 */
	@RequestMapping(value = "/deleteProdCart")
	@ResponseBody
	public Result<CodeMsg> deleteProdCart(HttpServletRequest request) {
		
		JSONObject jsonObj = super.getInputObject(request);
		int id = Integer.parseInt(jsonObj.getString("id"));
		if (productSV.deleteProductCartByProdId(id)) {
			return Result.success(CodeMsg.PRODUCT_CART_DELETE_SUCCESS);
		} else {
			return Result.error(CodeMsg.PRODUCT_CART_DELETE_FAIL);
		}
	}
	
	/**
	 * 商品更新
	 * @author yangsheng
	 */
	@RequestMapping(value = "/updateProduct",method = RequestMethod.POST)
	@ResponseBody
	public Result<CodeMsg> updateProduct(Product product,HttpServletRequest request) {

		/*String inputStr = super.getInputString(request);
		Product Product = JSON.parseObject(inputStr, new TypeReference<Product>() {});
		
		if (productSV.updateProduct(Product)) {
			return Result.success(CodeMsg.PRODUCT_UPDATE_SUCCESS);
		} else {
			return Result.error(CodeMsg.PRODUCT_UPDATE_FAIL);
		}*/
		try{
			//上传到服务器的文件名
			String fileNameToUpload = "";
			//上传服务器文件地址
			String ftpFilePath = "";
			//对上传的文件进行处理
			List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("headFile");
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
				//文件名:时间+商品名+后缀名
				fileNameToUpload = fileTime+product.getName()+fileName.substring(fileName.lastIndexOf("."));

				ftpFilePath = LocalConstants.CONST_SET.FILE_UPLOAD_PATH + fileNameToUpload;
				File dest = new File(ftpFilePath);
		        // 检测是否存在目录
		        if (!dest.getParentFile().exists()) {
		            dest.getParentFile().mkdirs();
		        }
		        //上传
		        files.get(0).transferTo(dest);
		        
		        product.setImgRef(LocalConstants.CONST_SET.SERV_IP + "/" + fileNameToUpload);
			}else {
				product.setImgRef(productSV.qryProductById(product.getId()).getImgRef());
			}
			if (productSV.updateProduct(product)) {
				return Result.success(CodeMsg.PRODUCT_UPDATE_SUCCESS);
			} else {
				return Result.error(CodeMsg.PRODUCT_UPDATE_FAIL);
			}
		}catch(Exception e) {
			return Result.error(CodeMsg.PRODUCT_ADD_FAIL);
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
	
	/**
	 * id查询商品
	 * 
	 * @author yangsheng
	 */
	@RequestMapping(value = "/qryProductById")
	@ResponseBody
	public Result<Product> qryProductById(HttpServletRequest request) {

		JSONObject jsonObj = super.getInputObject(request);
		int id = Integer.parseInt(jsonObj.getString("id"));

		return Result.success(productSV.qryProductById(id));
	}
	
	/**
	 * 查询所有购物车商品
	 * @author yangsheng
	 */
	@RequestMapping("/qryAllProdCart")
	@ResponseBody
	public Result<Map<String,Object>> qryAllProdCart() {
		
		Map<String,Object> mapToClient = new HashMap<String,Object>();
		List<ProductCart> productCartList = productSV.qryAllProductCart();
		logger.info(productCartList);
		mapToClient.put("productCartList", productCartList);
		return Result.success(mapToClient);
	}

}
