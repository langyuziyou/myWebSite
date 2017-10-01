package myWebSite.admin.controller;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import myWebSite.admin.entity.SysUser;
import myWebSite.admin.service.LoanPayTypeService;
import myWebSite.admin.service.SysUserService;
import myWebSite.admin.tools.CryptHelper;
import myWebSite.admin.tools.PasswordUtil;



@Scope("prototype")
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	
	private static final Logger LOGGER = Logger  
            .getLogger(LoginController.class);  
	
    @Autowired  
    private SysUserService sysUserService;
	
	/**
	 * 登录入口
	 * @author yzj
	 * @version 2.0 2017年9月25日 下午3:59:17
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/loginPage")
	public ModelAndView loginPage(HttpServletRequest request) {
		LOGGER.info(" loginPage ");
		return new ModelAndView("/sys/login");
	}
	
	
	/***
	 * 登录 
	 * @author yzj
	 * @version 2.0 2017年9月27日 上午10:38:07
	 * 
	 * @param request
	 * @param response
	 * @param username
	 * @param password
	 * @param setCookie
	 * @param cookieLogin
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, String username,
			String password, int setCookie, int cookieLogin){
		LOGGER.info(" 请求登录 :" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
//		 System.out.println("commonUserA:" + PasswordUtil.encrytPwd("123456", "commonUserA"));
//		 System.out.println("commonUserB:"+PasswordUtil.encrytPwd("123456", "commonUserB"));


		String url = "/sys/login";
		request.setAttribute("loginError", null);


		try {
			if (cookieLogin == 1) {
				password = CryptHelper.decrypt(password);
			}


			/**
			 * 如果开启记住我功能 ， 启用cookie
			 * 
			 * @author peter
			 * @date 2016-08-23 11:02:43
			 */
			if (setCookie == 1) {
				Cookie usernameCookies = new Cookie("username", username);
				Cookie passwordCookies = new Cookie("password", CryptHelper.encrypt(password));// 加密后的密码
				Cookie outCookies = new Cookie("outCookies", "0");// 登录时 out = 0
				/**
				 * loginWay 登陆方式0.正常登陆 1.记住我功能开启后cookie登陆
				 */
				Cookie loginWay = new Cookie("loginWay", "1");// 启用cookies 登陆

				// 设置cookie过期时间为24小时。
				usernameCookies.setMaxAge(60 * 60 * 24);
				passwordCookies.setMaxAge(60 * 60 * 24);
				outCookies.setMaxAge(60 * 60 * 24);
				loginWay.setMaxAge(60 * 60 * 24);
				response.addCookie(usernameCookies);
				response.addCookie(passwordCookies);
				response.addCookie(outCookies);
				response.addCookie(loginWay);
			}

			SysUser user = sysUserService.getByLogin(username,MD5(username+password));
			//session 
			request.getSession().setAttribute("user", user);

			// 项目路径
			String path = request.getContextPath();
			System.out.println(" path = " +path);
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			System.out.println(" basePath = " +basePath);
			request.getSession().setAttribute("basePath", basePath);

			// 保存登陆日志
			LOGGER.info(username + " 登录成功 ");
		} catch (Exception ex) {// 用户名没有找到
			LOGGER.error(ex.getMessage());
			ex.printStackTrace();
			/***
			 * 测试要求 用户名 密码登录失败后给个提示
			 * 
			 * @author peter
			 * @date 2016-08-22 14:47
			 */
			request.setAttribute("loginError", ex.toString());
			return new ModelAndView(url);
		}
		return new ModelAndView("redirect:/sys/loginPage");
	}
}