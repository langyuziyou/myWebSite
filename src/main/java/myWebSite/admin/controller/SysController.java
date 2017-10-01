package myWebSite.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 系统相关
 *
 * @author yzj
 * @version 2.0 2017年9月28日 上午10:21:40
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/sys")
public class SysController extends BaseController{
	
	private static final Logger LOGGER = Logger  
            .getLogger(SysController.class);  
	
	
	/**
	 * 
	 * @author yzj
	 * @version 2.0 2017年9月28日 上午9:59:45
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/welcome")
	public ModelAndView loginPage(HttpServletRequest request) {
		LOGGER.info(" welcome ");
		return new ModelAndView("/sys/welcome");
	}

}
