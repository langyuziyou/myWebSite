package myWebSite.admin.dao;

import java.util.List;
import java.util.Map;

import myWebSite.admin.entity.Shop;
import myWebSite.admin.entity.ShopCategory;
import myWebSite.admin.entity.UploadImg;

public interface ShopDao extends GenericDao<Shop,Integer>{

	List<Map<String, Object>> shopList();

	Map<String, Object> shopList(Map<String, Object> paraList);

	int[] shopExcelIn(Map<String, Object> map);

	Integer insertShop(Shop shop);
	
	Map<String, Object> findByName(String name);

	Map<String, Object> findById(String id);

	int findByNameCount(String name);

	void insertImg(String id, List<UploadImg> uploadImgList);

	List<Map<String, Object>> imgByShopId(String id);

	Integer deleteImg(String id, String imgId);

	void editShop(Shop shop);

	void deleteImgByShopId(String id);

	Integer delShop(String id);

}
