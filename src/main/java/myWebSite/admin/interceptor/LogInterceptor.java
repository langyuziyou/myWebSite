package myWebSite.admin.interceptor;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import myWebSite.admin.entity.SysUser;




/**
 * 日志拦截器
 *@ClassName: LogInterceptor  
 *@Description:  
 *@Author:liulicai  
 *@Since:liulicai  
 *@Version:1.1.0  
 *@date:2016年6月2日
 */
public class LogInterceptor implements HandlerInterceptor {

	Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		SysUser user = (SysUser) request.getSession().getAttribute("user");
		if (user != null) {
			return true;
		}

		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		
//		String currentParam = request.getParameter("currentUrl");
//		if(StringUtil.isNotEmpty(currentParam)){
//			request.getSession().setAttribute("currentParam", currentParam); //请求参数
//		}
		
		url = url.substring(1, url.length());
		Map<String, String[]> param = request.getParameterMap();

		request.getSession().setAttribute("ex_url", "/"+url); // 拦截请求的地址
//		request.getSession().setAttribute("ex_p", new Gson().toJson(param)); // 拦截请求的参数
		logger.info("请求的路径：" + url + "\n 请求的参数：" + param);

		request.getRequestDispatcher("/WEB-INF/sys/login.jsp").forward(request, response);
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

}
