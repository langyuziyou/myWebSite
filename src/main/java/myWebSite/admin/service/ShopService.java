package myWebSite.admin.service;

import java.util.List;
import java.util.Map;

public interface ShopService {
	
	List<Map<String, Object>> shopList();

	List<Map<String, Object>> getListById(String id);

	Integer addShop(String uploadImage,String price,String name,String firstSelect,String secondSelect,String threeSelect,String firstSelectVal,String secondSelectVal,String threeSelectVal,String description);

	Integer editShop(String id, String pId, String name, Integer level);

	Map<String, Object> findById(String id);

	Integer delShop(String id);

	Map<String, Object> shopList(Map<String, Object> paraList);

	int[] shopExcelIn(Map<String, Object> paraList);

	List<Map<String, Object>> imgByShopId(String id);

	Integer deleteImg(String id, String imgId);
	
	Integer editShop(String uploadImage, String id, String price, String name, String firstSelect, String secondSelect, String threeSelect, String firstSelectVal, String secondSelectVal, String threeSelectVal, String description);

}
