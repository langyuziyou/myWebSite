package myWebSite.admin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import myWebSite.admin.entity.ImageData;
import myWebSite.admin.entity.Shop;
import myWebSite.admin.service.CategoryService;
import myWebSite.admin.service.ShopService;
import myWebSite.admin.tools.AjaxJson;
import myWebSite.admin.tools.DateUtil;
import myWebSite.admin.tools.ExcelUtil;

/****
 * 商品 相关
 *
 * @author yzj
 * @version 2.0 2017年9月28日 上午10:21:18
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/shop")
public class ShopController extends BaseController {

	private static final Logger LOGGER = Logger.getLogger(ShopController.class);

	@Autowired
	private ShopService shopService;

	@Autowired
	private CategoryService categoryService;

	/**
	 * 列表
	 * 
	 * @author yzj
	 * @version 2.0 2017年9月28日 上午10:18:35
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(HttpServletRequest request) {
		Map<String, Object> paraList = new HashMap<String, Object>();

		int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
		int pageSize = request.getParameter("pageSize") != null ? Integer.parseInt(request.getParameter("pageSize")) : PAGE_SIZE;

		String shopName = request.getParameter("shopName") != null ? request.getParameter("shopName") : "";
		String price1 = request.getParameter("price1") != null ? request.getParameter("price1") : "";
		String price2 = request.getParameter("price2") != null ? request.getParameter("price2") : "";
		
		String firstSelect = request.getParameter("firstSelect") != null ? request.getParameter("firstSelect") : "-1";
		String secondSelect = request.getParameter("secondSelect") != null ? request.getParameter("secondSelect") : "-1";
		String threeSelect = request.getParameter("threeSelect") != null ? request.getParameter("threeSelect") : "-1";

		// 分页数据请求
		paraList.put("page", page);
		paraList.put("pageSize", pageSize);

		paraList.put("shopName", shopName);
		paraList.put("price1", price1);
		paraList.put("price2", price2);
		
		paraList.put("firstSelect", firstSelect);
		paraList.put("secondSelect", secondSelect);
		paraList.put("threeSelect", threeSelect);

		Map<String, Object> map = shopService.shopList(paraList);
		System.out.println(map);
		request.setAttribute("list", map.get("list"));
		request.setAttribute("allCount", map.get("count"));
		request.setAttribute("pageCount", map.get("pageCount"));
		request.setAttribute("page", page);
		request.setAttribute("pageSize", pageSize);

		request.setAttribute("shopName", shopName);
		request.setAttribute("price1", price1);
		request.setAttribute("price2", price2);

		request.setAttribute("firstSelect", firstSelect);
		request.setAttribute("secondSelect", secondSelect);
		request.setAttribute("threeSelect", threeSelect);
		
		
		/// basePath 
		String basePath = request.getSession().getServletContext().getContextPath();
		request.setAttribute("basePath", basePath);//

		/**
		 * categoryList
		 */
		List<Map<String, Object>> categoryFirstList = loadCategoryFirst(request);
		request.setAttribute("categoryFirstList", categoryFirstList);

		// 获取 二级分类
		if (!firstSelect.equals("-1")) {
			List<Map<String, Object>> secondSelectList = categoryService.findByPid(firstSelect);
			request.setAttribute("secondSelectList", secondSelectList);// 二级分类数据
		}
		// 获取三级分类
		if (!secondSelect.equals("-1")) {
			List<Map<String, Object>> threeSelectList = categoryService.findByPid(secondSelect);
			request.setAttribute("threeSelectList", threeSelectList);//

		}

		return new ModelAndView("/sys/shop/shopList");
	}
	
	
	
	
	@RequestMapping(value = "/delShop")
	@ResponseBody
	public AjaxJson delShop(HttpServletRequest req, String id, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
			Integer result = shopService.delShop(id);
			if (result == -1) {
				LOGGER.error("删除 失败 ,存在子菜单 ");
				j.setMsg("删除 失败 ,存在子菜单  ");
				j.setSuccess(false);
				return j;
			}
			LOGGER.info(" 删除 成功 Id =  " + result);
			j.setMsg(" 删除 成功 Id =  " + result);
		} catch (Exception e) {
			LOGGER.error("删除 失败 : " + e.getMessage());

			j.setMsg(e.toString());
			j.setSuccess(false);
		}
		return j;
	}

	/***
	 * 准备新增
	 * 
	 * @author yzj
	 * @version 2.0 2017年10月1日 上午9:37:49
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/preAdd")
	public ModelAndView preAdd(HttpServletRequest request) {
		LOGGER.info(" preAdd ");
		/**
		 * categoryList
		 */
		List<Map<String, Object>> categoryFirstList = loadCategoryFirst(request);
		request.setAttribute("categoryFirstList", categoryFirstList);

		return new ModelAndView("/sys/shop/shopAdd");
	}
	
	
	/***
	 * 准备编辑
	 * 
	 * @author yzj
	 * @version 2.0 2017年9月30日 上午9:40:30
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/preEdit/id/{id}")
	public ModelAndView preEdit(HttpServletRequest request, @PathVariable("id") String id) {
		LOGGER.info(" preEdit ");
		request.setAttribute("id", id);
		Map<String, Object> result = shopService.findById(id);
		request.setAttribute("result", result);
		
		//分类 信息
		String categoryId1 = result.get("first_shop_category_id").toString();
		String categoryId2 = result.get("second_shop_category_id").toString();
		String categoryId3 = result.get("three_shop_category_id").toString();

		// 如果是 1级分类 就查询一级分类就好
		List<Map<String, Object>> categoryFirstList = loadCategoryFirst(request);
		request.setAttribute("categoryFirstList", categoryFirstList);
		
		request.setAttribute("firstSelect", categoryId1);
		request.setAttribute("secondSelect", categoryId2);
		request.setAttribute("threeSelect", categoryId3);
		
		if(!categoryId2.equals("-1")){
			List<Map<String, Object>> secondSelectList = categoryService.findByPid(categoryId1);
			request.setAttribute("secondSelectList", secondSelectList);// 二级分类数据

		}
		if(!categoryId3.equals("-1"))	{
			List<Map<String, Object>> secondSelectListx = categoryService.findByPid(categoryId1);
			request.setAttribute("secondSelectList", secondSelectListx);// 二级分类数据
			List<Map<String, Object>> threeSelectList = categoryService.findByPid(categoryId2);
			request.setAttribute("threeSelectList", threeSelectList);// 三级
		}	
		
		// 图片
		List<Map<String,Object>> imgList = shopService.imgByShopId(id);
		request.setAttribute("imgList", imgList);

		return new ModelAndView("/sys/shop/shopEdit");
	}
	
	
	/**
	 * 编辑
	 * @author yzj
	 * @version 2.0 2017年10月11日 下午3:17:43
	 * 
	 * @param req
	 * @param uploadImage
	 * @param price
	 * @param name
	 * @param firstSelect
	 * @param secondSelect
	 * @param threeSelect
	 * @param firstSelectVal
	 * @param secondSelectVal
	 * @param threeSelectVal
	 * @param description
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/editShop")
	@ResponseBody
	public AjaxJson editShop(HttpServletRequest req,String uploadImage,String id, String price,String name,String firstSelect,String secondSelect,
			String threeSelect,String firstSelectVal,String secondSelectVal,String threeSelectVal,String description, HttpServletResponse response) {
			AjaxJson j = new AjaxJson();		


			Integer result = shopService.editShop(uploadImage,id,price,name,firstSelect,secondSelect,threeSelect,firstSelectVal,secondSelectVal,threeSelectVal,description);
			if(result == 1){
				String str = "编辑 成功";
				LOGGER.info(str);
				j.setMsg(str);
			}else if(result == -1){
				String str = "编辑 失败";
				LOGGER.info(str);
				j.setMsg(str);
				j.setSuccess(false);
			}
			else
			{
				String str = "编辑 失败";
				LOGGER.info(str);
				j.setMsg(str);
				j.setSuccess(false);
			}
		return j;
	}
	
	
	/**
	 * 新增 
	 * @author yzj
	 * @version 2.0 2017年10月2日 下午12:10:04
	 * 
	 * @param req
	 * @param pId
	 * @param name
	 * @param level
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addShop")
	@ResponseBody
	public AjaxJson addShop(HttpServletRequest req,String uploadImage, String price,String name,String firstSelect,String secondSelect,
			String threeSelect,String firstSelectVal,String secondSelectVal,String threeSelectVal,String description, HttpServletResponse response) {
			AjaxJson j = new AjaxJson();		


			Integer result = shopService.addShop(uploadImage,price,name,firstSelect,secondSelect,threeSelect,firstSelectVal,secondSelectVal,threeSelectVal,description);
			if(result == 1){
				LOGGER.info(" 新增 成功 ");
				j.setMsg(" 新增 成功  ");
			}else if(result == -1){
				LOGGER.info(" 新增 失败,已经存在相同的名称 ");
				j.setMsg(" 新增 失败,已经存在相同的名称 ");
				j.setSuccess(false);
			}
			else
			{
				LOGGER.info(" 新增 失败 ");
				j.setMsg(" 新增 失败  ");
				j.setSuccess(false);
			}
		return j;
	}
	
	
	
	@RequestMapping(value = "/deleteImg")
	@ResponseBody
	public AjaxJson deleteImg(HttpServletRequest req,String id,String imgId, HttpServletResponse response) {
			AjaxJson j = new AjaxJson();		


			Integer result = shopService.deleteImg(id,imgId);
			if(result == 1){
				String str = "删除 成功";
				LOGGER.info(str);
				j.setMsg(str);
			}else if(result == -1){
				String str = "删除失败";
				LOGGER.info(str);
				j.setMsg(str);
				j.setSuccess(false);
			}
			else
			{
				String str = "删除 失败";
				LOGGER.info(str);
				j.setMsg(str);
				j.setSuccess(false);
			}
		return j;
	}
	

	/**
	 * 
	 * method: 单图片上传
	 *
	 * @param
	 * @return
	 * @author yzj
	 * @date 2016年8月18日 上午10:38:07
	 * @exception @version
	 *                1.0.0
	 */
	@RequestMapping(value = "/fileUpload")
	@ResponseBody
	public AjaxJson fileUpload(MultipartHttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		Gson gson = new Gson();
		String vo = "";
		JsonObject json = null;
		StringBuffer sb = new StringBuffer();
		try {
			String result = SaveFileFromInputStream(request);
			sb.append(result);
			j.setMsg(sb.toString());
		} catch (Exception e)  {
			vo = "{}";
			LOGGER.error("图片上传异常: " + e.getMessage());
			e.printStackTrace();
			j.setMsg("上传异常");
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 保存文件
	 * 
	 * @param stream
	 * @param path
	 * @param filename
	 * @throws IOException
	 */
	public String SaveFileFromInputStream(MultipartHttpServletRequest request) throws IOException {
		StringBuffer sb = new StringBuffer();
		
		boolean up = true;
		String basePath = request.getSession().getServletContext().getRealPath("shop/upload/imgs");
		String fileAdd = basePath + "/" + DateUtil.getDate();

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		for (Iterator<?> it = multipartRequest.getFileNames(); it.hasNext();) {
			String key = (String) it.next();
			MultipartFile mulfile = multipartRequest.getFile(key);
			String fileName = mulfile.getOriginalFilename();
			InputStream stream = mulfile.getInputStream();

			File file1 = new File(fileAdd);
			if (!file1.exists() && !file1.isDirectory()) {
				file1.mkdir();
			}

			// 扫描文件夹下是否存在相同文件
			File f = new File(fileAdd + "/" + fileName);
			if (f.exists()) {
				sb.append("0shop\\upload\\imgs\\"+ DateUtil.getDate()+"\\" +  fileName);
				up = false;
			}
			
			if (up) {
			FileOutputStream fs = new FileOutputStream(fileAdd + "/" + fileName);
			byte[] buffer = new byte[1024 * 1024];
			int bytesum = 0;
			int byteread = 0;
			while ((byteread = stream.read(buffer)) != -1) {
				bytesum += byteread;
				fs.write(buffer, 0, byteread);
				fs.flush();
			}
			fs.close();
			stream.close();
			sb.append("1shop\\upload\\imgs\\"+ DateUtil.getDate()+"\\" +  fileName);
			}
		}
		return sb.toString();

	}
	
	/**
	 * 商品信息 导入 id
	 * @author yzj
	 * @version 2.0 2017年9月7日 下午4:52:29
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "excelInId")
	@ResponseBody
	public AjaxJson excelInId(MultipartHttpServletRequest request, HttpServletResponse response) {
		AjaxJson ajax = new AjaxJson();
		ajax.setMsg("访问失败");
		ajax.setSuccess(false);
		String category = request.getParameter("category");

		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			for (Iterator<?> it = multipartRequest.getFileNames(); it.hasNext();) {
				String key = (String) it.next();
				MultipartFile mulfile = multipartRequest.getFile(key);
				ajax = this.importShopExcel(new ExcelUtil(mulfile.getInputStream(), ""),category);
			}
		} catch (Exception e) {
			ajax.setSuccess(false);
		}
		return ajax;
	}	
	
	
	/***
	 * 
	 * @author yzj
	 * @version 2.0 2017年9月7日 下午4:53:22
	 * 
	 * @param ex
	 * @return
	 * @throws Exception
	 */
	private AjaxJson importShopExcel(ExcelUtil ex,String category){
		AjaxJson ajax = new AjaxJson();
		Gson gson = new Gson();
		List<List<String>> read = new ArrayList<>();
		List<Shop> list = new ArrayList<>();
		System.out.println(list.size());
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		try {
			read = ex.read(0, 1, ex.getRowCount(0) - 1);
			
			for(List<String> readChild:read){
				if(readChild.get(0)!=null){
						sb.append("{\"shopInfoName\":" + "\"" + readChild.get(0) + "\"");
						sb.append(",\"shopInfoImage\":"  + "\"" + readChild.get(1)  + "\"");
						sb.append(",\"price\":"  + "\"" + readChild.get(2)  + "\"");
						sb.append(",\"shopCategoryId\":"  + "\"" + category  + "\"");
						sb.append(",\"description\":"  + "\"" + readChild.get(3).replace("\"", "\'")  + "\"");
						sb.append(",\"fromWeb\":"  + "\"" + category  + "\"");
						sb.append("},");
				}
			}
			
			if (sb.length() > 2) {
				sb.deleteCharAt(sb.length() - 1); // 删除多余的逗号

			}
			sb.append("]");
			System.out.println(sb);
			

			Map<String, Object> paraList = new HashMap<String, Object>();
			paraList.put("sb", sb.toString());
			paraList.put("user", "admin");
			int[] strList = shopService.shopExcelIn(paraList);
			
			System.out.println(strList);

			
		} catch (Exception e) {
			ajax.setSuccess(false);
			ajax.setMsg(e.toString());
		}
		return ajax;
	}
	
	
	/**
	 * 商品信息 导入 
	 * @author yzj
	 * @version 2.0 2017年9月7日 下午4:52:29
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "excelIn")
	@ResponseBody
	public AjaxJson excelIn(MultipartHttpServletRequest request, HttpServletResponse response) {
		AjaxJson ajax = new AjaxJson();
		ajax.setMsg("访问失败");
		ajax.setSuccess(false);

		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			for (Iterator<?> it = multipartRequest.getFileNames(); it.hasNext();) {
				String key = (String) it.next();
				MultipartFile mulfile = multipartRequest.getFile(key);
				ajax = this.importShopExcel(new ExcelUtil(mulfile.getInputStream(), ""));
			}
		} catch (Exception e) {
			ajax.setSuccess(false);
		}
		return ajax;
	}	
	
	
	/***
	 * 
	 * @author yzj
	 * @version 2.0 2017年9月7日 下午4:53:22
	 * 
	 * @param ex
	 * @return
	 * @throws Exception
	 */
	private AjaxJson importShopExcel(ExcelUtil ex){
		AjaxJson ajax = new AjaxJson();
		Gson gson = new Gson();
		List<List<String>> read = new ArrayList<>();
		List<Shop> list = new ArrayList<>();
		System.out.println(list.size());
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		try {
			read = ex.read(0, 1, ex.getRowCount(0) - 1);
			
			for(List<String> readChild:read){
				if(readChild.get(0)!=null){
					

//						sb.append("{\"brand\":" + "\"" + readChild.get(0)==null?" ":readChild.get(0) + "\"");
//						sb.append(",\"productCode\":"  + "\"" + readChild.get(1)==null?" ":readChild.get(1)  + "\"");
//						sb.append(",\"color\":"  + "\"" + readChild.get(2)==null?" ":readChild.get(2)  + "\"");
//						sb.append(",\"taxNo\":"  + "\"" + readChild.get(3)==null?" ":readChild.get(3)  + "\"");
//						sb.append(",\"chnName\":"  + "\"" + readChild.get(4)==null?" ":readChild.get(4)  + "\"");
//						sb.append(",\"detail\":"  + "\"" + readChild.get(5)==null?" ":readChild.get(5) + "\"");
//						sb.append("},");
						
//						System.out.println(readChild.get(0));
					
						System.out.println(readChild.get(4).replace("\"", "\'") );
						
						sb.append("{\"shopInfoName\":" + "\"" + readChild.get(0) + "\"");
						sb.append(",\"shopInfoImage\":"  + "\"" + readChild.get(1)  + "\"");
						sb.append(",\"price\":"  + "\"" + readChild.get(2)  + "\"");
						sb.append(",\"shopCategoryId\":"  + "\"" + readChild.get(3)  + "\"");
						sb.append(",\"description\":"  + "\"" + readChild.get(4).replace("\"", "\'")  + "\"");
						sb.append("},");
			
					
				}
			}
			
			if (sb.length() > 2) {
				sb.deleteCharAt(sb.length() - 1); // 删除多余的逗号

			}
			sb.append("]");
			System.out.println(sb);
			

			Map<String, Object> paraList = new HashMap<String, Object>();
			paraList.put("sb", sb.toString());
			paraList.put("user", "admin");
			int[] strList = shopService.shopExcelIn(paraList);
			
			System.out.println(strList);

			
		} catch (Exception e) {
			ajax.setSuccess(false);
			ajax.setMsg(e.toString());
		}
		return ajax;
	}
	

}
