package myWebSite.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import myWebSite.admin.service.CategoryService;
import myWebSite.admin.tools.AjaxJson;

/**
 * 商品 类别相关
 *
 * @author yzj
 * @version 2.0 2017年9月28日 上午10:20:52
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {

	private static final Logger LOGGER = Logger.getLogger(CategoryController.class);

	@Autowired
	private CategoryService categoryService;

	/**
	 * 列表
	 * 
	 * @author yzj
	 * @version 2.0 2017年9月28日 上午10:10:14
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView categoryList(HttpServletRequest request) {
		LOGGER.info(" categoryList ");

		List<Map<String, Object>> list = categoryService.categoryList();
		System.out.println(list);

		request.setAttribute("list", list);

		return new ModelAndView("/sys/category/categoryList");
	}

	/**
	 * 
	 * @author yzj
	 * @version 2.0 2017年9月28日 下午5:01:08
	 * 
	 * @param req
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getListByParentId")
	@ResponseBody
	public AjaxJson getList(HttpServletRequest req, String id, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
			List<Map<String, Object>> list = categoryService.getListByParentId(id);
			Map<String, Object> map = new HashMap<>();
			map.put("rs", list);
			j.setAttributes(map);
		} catch (Exception e) {
			LOGGER.error("根据父ID获取菜单异常: " + e.getMessage());

			j.setMsg(e.toString());
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 新增 分类
	 * 
	 * @author yzj
	 * @version 2.0 2017年9月29日 下午4:30:24
	 * 
	 * @param req
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addCategory")
	@ResponseBody
	public AjaxJson addCategory(HttpServletRequest req, String pId, String name, Integer level, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
			Integer result = categoryService.addCategory(pId, name, level);
			if (result == -1) {
				LOGGER.info(" 新增失败，已经存在相同的名称 ");
				j.setMsg(" 新增失败，已经存在相同的名称  ");
				j.setSuccess(false);
				return j;
			} else {
				LOGGER.info(" 新增成功 Id =  " + result);
				j.setMsg(" 新增成功 Id =  " + result);
			}
		} catch (Exception e) {
			LOGGER.error("新增失败 : " + e.getMessage());
			
			j.setMsg(e.toString());
			j.setSuccess(false);
		}
		return j;
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
		Map<String, Object> result = categoryService.findById(id);
		request.setAttribute("parentId", result.get("parentId"));
		request.setAttribute("name", result.get("shop_category_name"));
		request.setAttribute("level", result.get("level"));

		return new ModelAndView("/sys/category/categoryEdit");
	}

	/**
	 * 编辑
	 * 
	 * @author yzj
	 * @version 2.0 2017年9月30日 上午10:18:20
	 * 
	 * @param req
	 * @param param
	 * @param id
	 * @param parentId
	 * @param name
	 * @param level
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/editCategory")
	@ResponseBody
	public AjaxJson editCategory(HttpServletRequest req, String param, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		System.out.println(param);
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map = gson.fromJson(param, map.getClass());
		System.out.println(map.get("name"));

		try {
			Integer result = categoryService.editCategory(map.get("id").toString(), map.get("parentId").toString(), map.get("name").toString(), Integer.parseInt(map.get("level").toString()));
			LOGGER.info(" 编辑 成功 Id =  " + result);
			j.setMsg(" 编辑 成功 Id =  " + result);
		} catch (Exception e) {
			LOGGER.error("编辑失败 : " + e.getMessage());

			j.setMsg(e.toString());
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * pre add
	 * 
	 * @author yzj
	 * @version 2.0 2017年9月30日 上午10:30:21
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/preAdd/id/{id}")
	public ModelAndView preAdd(HttpServletRequest request, @PathVariable("id") String id) {
		LOGGER.info(" preAdd ");
		request.setAttribute("id", id);
		Map<String, Object> result = categoryService.findById(id);
		request.setAttribute("parentId", result.get("parentId"));
		request.setAttribute("name", result.get("shop_category_name"));
		request.setAttribute("level", result.get("level"));

		return new ModelAndView("/sys/category/categoryAdd");
	}

	/**
	 * ' 新增
	 * 
	 * @author yzj
	 * @version 2.0 2017年9月30日 上午10:32:51
	 * 
	 * @param req
	 * @param param
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addCategoryChild")
	@ResponseBody
	public AjaxJson addCategoryChild(HttpServletRequest req, String param, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		System.out.println(param);
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map = gson.fromJson(param, map.getClass());
		System.out.println(map.get("name"));
		Integer level = Integer.parseInt(map.get("level").toString()) + 1;
		try {
			Integer result = categoryService.addCategory(map.get("parentId").toString(), map.get("name").toString(), level);
			if (result == -1) {
				LOGGER.info(" 新增失败，已经存在相同的名称 ");
				j.setMsg(" 新增失败，已经存在相同的名称  ");
				j.setSuccess(false);
				return j;
			} else {
				LOGGER.info(" 新增成功 Id =  " + result);
				j.setMsg(" 新增成功 Id =  " + result);
			}

		} catch (Exception e) {
			LOGGER.error("新增失败 : " + e.getMessage());
			j.setMsg(e.toString());
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 删除
	 * 
	 * @author yzj
	 * @version 2.0 2017年9月30日 上午10:44:41
	 * 
	 * @param req
	 * @param param
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delCategory")
	@ResponseBody
	public AjaxJson delCategory(HttpServletRequest req, String id, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
			Integer result = categoryService.delCategory(id);
			if (result == -2) {
				String str = "删除 失败 ,已经被商品引用";
				LOGGER.error(str);
				j.setMsg(str);
				j.setSuccess(false);
				return j;
			}
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

	/**
	 * 
	 * @author yzj
	 * @version 2.0 2017年9月30日 下午2:30:59
	 * 
	 * @param req
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/loadCategorySecond")
	@ResponseBody
	public AjaxJson loadCategorySecond(HttpServletRequest req, String id, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
			List<Map<String, Object>> list = categoryService.findByPid(id);
			Map<String, Object> map = new HashMap<>();
			map.put("rs", list);
			j.setAttributes(map);
		} catch (Exception e) {
			LOGGER.error("获取异常: " + e.getMessage());

			j.setMsg(e.toString());
			j.setSuccess(false);
		}
		return j;
	}

}
