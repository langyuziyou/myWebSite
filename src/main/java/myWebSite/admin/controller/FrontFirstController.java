package myWebSite.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import myWebSite.admin.tools.StringUtil;


/****
 * 
 *
 * @author yzj
 * @version 2.0 2017年9月25日 下午3:57:48
 *
 */

@Scope("prototype")
@Controller
@RequestMapping("/front")
public class FrontFirstController extends BaseController{
	
	private static final Logger LOGGER = Logger  
            .getLogger(FrontFirstController.class);  
	
	
	
	/**
	 * 首页入口
	 * @author yzj
	 * @version 2.0 2017年9月25日 下午3:59:17
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/reStart")
	public ModelAndView reStart(HttpServletRequest request) {

		return new ModelAndView("/front/reStart");
	}
	
	
	/**
	 * 详情 页 
	 * @author yzj
	 * @version 2.0 2017年9月25日 下午3:59:31
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/detail/lipin/{lipin}")
	public ModelAndView detail(HttpServletRequest request,@PathVariable("lipin") String lipin) {
		LOGGER.info("查询礼品: "+lipin);
		return new ModelAndView("/front/detail");
	}
	
	
	/****
	 * 搜索 
	 * @author yzj
	 * @version 2.0 2017年9月26日 上午10:00:33
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/search")
	public ModelAndView search(HttpServletRequest request) {
		LOGGER.info("高级搜索开始: ");
		
		Integer page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : PAGE_NO;
		Integer pageSize = request.getParameter("pageSize") != null ? Integer.parseInt(request.getParameter("pageSize")) : PAGE_SIZE;
		String name = request.getParameter("name") != null ? request.getParameter("name") : "";
		
		return new ModelAndView("/front/search");
	}

}
