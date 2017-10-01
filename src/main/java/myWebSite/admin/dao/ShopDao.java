package myWebSite.admin.dao;

import java.util.List;
import java.util.Map;

import myWebSite.admin.entity.Shop;
import myWebSite.admin.entity.ShopCategory;

public interface ShopDao extends GenericDao<Shop,Integer>{

	List<Map<String, Object>> shopList();

	Map<String, Object> shopList(Map<String, Object> paraList);

}
