package myWebSite.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import myWebSite.admin.entity.LoanPayType;
import myWebSite.admin.service.LoanPayTypeService;


@Scope("prototype")
@Controller
@RequestMapping("/ll")
public class LoanPayTypeController {
	private static final Logger LOGGER = Logger  
            .getLogger(LoanPayTypeController.class);  
	
	
    @Autowired  
    private LoanPayTypeService loanPayTypeService;  
  
    @RequestMapping("/showInfo/{userId}")  
    public String showLoanPayType(ModelMap modelMap, @PathVariable int id) {  
        LOGGER.info("查看用户：" + id);  
        LoanPayType userInfo = loanPayTypeService.getById(1);  
        modelMap.addAttribute("userInfo", userInfo);  
        return "/user/showInfo";  
    }  
  
    @RequestMapping("/showInfos")  
    public @ResponseBody List<LoanPayType> showUserInfos() {  
        LOGGER.info("json返回全部用户的信息");  
        List<LoanPayType> list = loanPayTypeService.findAll();  
        return list;  
    } 
    
    
    
	@RequestMapping(value = "/list")
	public ModelAndView list(HttpServletRequest request) {

		List<LoanPayType> list = loanPayTypeService.findAll();  
		request.setAttribute("list", list);
		
		return new ModelAndView("/list");
	}
	
	@RequestMapping(value = "/reStart")
	public ModelAndView reStart(HttpServletRequest request) {

		return new ModelAndView("/front/reStart");
	}

}
