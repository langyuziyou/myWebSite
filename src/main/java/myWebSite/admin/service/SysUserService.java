package myWebSite.admin.service;

import java.util.List;

import myWebSite.admin.entity.SysUser;

public interface SysUserService {
		  
	    // 通过Id查询UserInfo  
		SysUser getById(Integer id);  
	  
	    // 查询全部的UserInfo  
	    List<SysUser> findAll();  
	  
	    // 添加UserInfo  
	    Integer save(SysUser sysUser);

		SysUser getByLogin(String username, String password);  



}
