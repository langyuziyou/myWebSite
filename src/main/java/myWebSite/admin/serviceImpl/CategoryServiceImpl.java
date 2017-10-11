package myWebSite.admin.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myWebSite.admin.dao.CategoryDao;
import myWebSite.admin.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<Map<String, Object>> categoryList() {
		return categoryDao.categoryList();
	}

	@Override
	public List<Map<String, Object>> getListByParentId(String id) {
		return categoryDao.getListByParentId(id);
	}

	@Override
	public Integer addCategory(String pId, String name, Integer level) {
		Map<String, Object> map = categoryDao.findByName(name.trim(), level);
		if (map.isEmpty()) {
			return categoryDao.addCategory(pId, name.trim(), level);

		} else {
			return -1;
		}

	}

	@Override
	public Integer editCategory(String id, String pId, String name, Integer level) {
		return categoryDao.editCategory(id, pId, name.trim(), level);
	}

	@Override
	public Map<String, Object> findById(String id) {
		return categoryDao.findById(id);
	}

	/**
	 * 删除
	 */
	public Integer delCategory(String id) {
		Integer count = categoryDao.findShopById(id);
		if(count>0){
			return -2;
		}
		List<Map<String, Object>> list = categoryDao.getListByParentId(id);
		if (list.isEmpty()) {
			return categoryDao.delCategory(id);
		} else {
			return -1;
		}

	}

	@Override
	public List<Map<String, Object>> findByPid(String id) {
		return categoryDao.findByPid(id);
	}

}
