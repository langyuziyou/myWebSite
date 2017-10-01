package myWebSite.admin.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myWebSite.admin.dao.ShopDao;
import myWebSite.admin.service.ShopService;


@Service("shopService")
public class ShopServiceImpl implements ShopService{
	
	@Autowired
	private ShopDao shopDao;

	@Override
	public List<Map<String, Object>> shopList() {
		// TODO Auto-generated method stub
		return shopDao.shopList();
	}

	@Override
	public List<Map<String, Object>> getListById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer addShop(String pId, String name, Integer level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer editShop(String id, String pId, String name, Integer level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delShop(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> shopList(Map<String, Object> paraList) {
		return shopDao.shopList(paraList);
	}

}
