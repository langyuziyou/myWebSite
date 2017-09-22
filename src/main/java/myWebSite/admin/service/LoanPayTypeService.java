package myWebSite.admin.service;

import java.util.List;

import myWebSite.admin.entity.LoanPayType;

public interface LoanPayTypeService {
	 
	  
    // 通过Id查询UserInfo  
	LoanPayType getById(Integer id);  
  
    // 查询全部的UserInfo  
    List<LoanPayType> findAll();  
  
    // 添加UserInfo  
    Integer save(LoanPayType userInfo);  

}
