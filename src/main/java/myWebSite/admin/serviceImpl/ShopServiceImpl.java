package myWebSite.admin.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import myWebSite.admin.dao.ShopDao;
import myWebSite.admin.entity.Shop;
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

	/*@Transactional(rollbackFor = Exception.class)*/
	public Integer addShop(String uploadImage,String price,String name,String firstSelect,String secondSelect,String threeSelect,String description) {
		
		String img[] = uploadImage.split("=_=");
		Shop shop = new Shop();
		shop.setDescription(description);
		shop.setPrice(price);
		if(img.length>0){
			shop.setShopInfoImage(img[0]);
		}
		shop.setShopInfoName(name);
		if(!threeSelect.equals("-1")){
			shop.setShopCategoryId(Integer.parseInt(threeSelect));
		}
		if(secondSelect.equals("-1")){
			shop.setShopCategoryId(Integer.parseInt(secondSelect));
		}
		if(firstSelect.equals("-1")){
			shop.setShopCategoryId(Integer.parseInt(firstSelect));
		}
		Integer id = shopDao.insertShop(shop);
		if(id == 1){
			Map<String,Object> result = shopDao.findByName(name);
			System.out.println(result.get("shop_info_id"));
		}
		
		
		
		return id;
	}

	@Override
	public Integer editShop(String id, String pId, String name, Integer level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findById(String id) {
		// TODO Auto-generated method stub
		return shopDao.findById(id);
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

	@Override
	public int[] shopExcelIn(Map<String, Object> map) {
		return shopDao.shopExcelIn(map);
	}
		

}
