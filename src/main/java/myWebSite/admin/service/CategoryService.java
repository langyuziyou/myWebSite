package myWebSite.admin.service;

import java.util.List;
import java.util.Map;

import myWebSite.admin.entity.SysUser;

public interface CategoryService {
	
		List<Map<String, Object>> categoryList();

		List<Map<String, Object>> getListByParentId(String id);

		Integer addCategory(String pId, String name,Integer level);

		Integer editCategory(String id, String pId, String name, Integer level);

		Map<String, Object> findById(String id);

		Integer delCategory(String id);

		List<Map<String, Object>> findByPid(String id);



}
