package myWebSite.admin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import myWebSite.admin.service.CategoryService;
import myWebSite.admin.service.ShopService;
import myWebSite.admin.tools.AjaxJson;
import myWebSite.admin.tools.DateUtil;

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
		String firstSelect = request.getParameter("firstSelect") != null ? request.getParameter("firstSelect") : "-1";
		String secondSelect = request.getParameter("secondSelect") != null ? request.getParameter("secondSelect") : "-1";
		String threeSelect = request.getParameter("threeSelect") != null ? request.getParameter("threeSelect") : "-1";

		// 分页数据请求
		paraList.put("page", page);
		paraList.put("pageSize", pageSize);

		paraList.put("shopName", shopName);
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

		request.setAttribute("firstSelect", firstSelect);
		request.setAttribute("secondSelect", secondSelect);
		request.setAttribute("threeSelect", threeSelect);

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
				sb.append("0" + fileAdd + "/" + fileName );
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
	
	
	

}
