package myWebSite.admin.service;

import java.util.List;
import java.util.Map;

public interface ShopService {
	
	List<Map<String, Object>> shopList();

	List<Map<String, Object>> getListById(String id);

	Integer addShop(String pId, String name,Integer level);

	Integer editShop(String id, String pId, String name, Integer level);

	Map<String, Object> findById(String id);

	Integer delShop(String id);

	Map<String, Object> shopList(Map<String, Object> paraList);

}
