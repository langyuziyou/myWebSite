package myWebSite.admin.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import myWebSite.admin.dao.ShopDao;
import myWebSite.admin.daoImpl.ShopDaoImpl;
import myWebSite.admin.entity.Shop;
import myWebSite.admin.entity.UploadImg;
import myWebSite.admin.service.ShopService;
import myWebSite.admin.tools.DateUtil;


@Service("shopService")
public class ShopServiceImpl implements ShopService{
	
	private static final Logger LOGGER = Logger.getLogger(ShopServiceImpl.class);


	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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

	@Transactional(rollbackFor = Exception.class)
	public Integer addShop(String uploadImage,String price,String name,String firstSelect,String secondSelect,String threeSelect,String firstSelectVal,String secondSelectVal,String threeSelectVal,String description) {
		
			StringBuffer sb = new StringBuffer();
			Gson g = new Gson();
			if(!firstSelectVal.trim().equals("请选择")){
				sb.append(firstSelectVal.trim());
			}
			
			if(!secondSelectVal.trim().equals("请选择")){
				sb.append(">"+secondSelectVal.trim());
			}
			
			if(!threeSelectVal.trim().equals("请选择")){
				sb.append(">"+threeSelectVal);
			}
			int count  = shopDao.findByNameCount(name);
			if(count>0){
				return -1;
			}
			/***************************************************************
			 * 前台传入的 json 数据 已经封装成 UploadImg
			 **************************************************************/
			List<UploadImg> uploadImgList = g.fromJson(uploadImage, new TypeToken<List<UploadImg>>() {
			}.getType());
			
			Shop shop = new Shop();
			shop.setDescription(description);
			shop.setPrice(price);
			if(uploadImgList.size()>0){
				shop.setShopInfoImage(uploadImgList.get(0).getSrc());
			}
			shop.setShopInfoName(name);
			shop.setFirstShopCategoryId(Integer.parseInt(firstSelect));
			shop.setSecondShopCategoryId(Integer.parseInt(secondSelect));
			shop.setThreeShopCategoryId(Integer.parseInt(threeSelect));
			shop.setCategoryName(sb.toString());
		
			try{
				Integer id = shopDao.insertShop(shop);
				if(id == 1){
					Map<String,Object> result = shopDao.findByName(name);
					// 处理图片
					shopDao.insertImg(result.get("shop_info_id").toString(),uploadImgList);
					System.out.println(result.get("shop_info_id"));
				}
				return id;
			}catch(Exception e){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
			return 0;
			
		

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
		return shopDao.delShop(id);
	}

	@Override
	public Map<String, Object> shopList(Map<String, Object> paraList) {
		return shopDao.shopList(paraList);
	}

	@Override
	public int[] shopExcelIn(Map<String, Object> map) {
		return shopDao.shopExcelIn(map);
	}

	@Override
	public List<Map<String, Object>> imgByShopId(String id) {
		return shopDao.imgByShopId(id);
	}

	@Override
	public Integer deleteImg(String id, String imgId) {
		return shopDao.deleteImg(id,imgId);
	}

	@Transactional(rollbackFor = Exception.class)
	public Integer editShop(String uploadImage, String id, String price, String name, String firstSelect, String secondSelect, String threeSelect, String firstSelectVal, String secondSelectVal, String threeSelectVal, String description) {
		
		StringBuffer sb = new StringBuffer();
		Gson g = new Gson();
		if(!firstSelectVal.trim().equals("请选择")){
			sb.append(firstSelectVal.trim());
		}
		
		if(!secondSelectVal.trim().equals("请选择")){
			sb.append(">"+secondSelectVal.trim());
		}
		
		if(!threeSelectVal.trim().equals("请选择")){
			sb.append(">"+threeSelectVal);
		}
		int count  = shopDao.findByNameCount(name);
		if(count>1){
			return -1;
		}
		/***************************************************************
		 * 前台传入的 json 数据 已经封装成 UploadImg
		 **************************************************************/
		List<UploadImg> uploadImgList = g.fromJson(uploadImage, new TypeToken<List<UploadImg>>() {
		}.getType());
		
		Shop shop = new Shop();
		shop.setDescription(description);
		shop.setPrice(price);
		if(uploadImgList.size()>0){
			shop.setShopInfoImage(uploadImgList.get(0).getSrc());
		}
		shop.setShopInfoName(name);
		shop.setFirstShopCategoryId(Integer.parseInt(firstSelect));
		shop.setSecondShopCategoryId(Integer.parseInt(secondSelect));
		shop.setThreeShopCategoryId(Integer.parseInt(threeSelect));
		shop.setCategoryName(sb.toString());
		shop.setShopInfoId(Integer.parseInt(id));
		
		try{
			shopDao.editShop(shop);
			shopDao.deleteImgByShopId(id);
			// 处理图片
			shopDao.insertImg(id,uploadImgList);
			
		}catch(Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return 0;
		}
		return 1;
		
	

}

	@Override
	public int[] shopListAdd(Map<String, Object> map) {
		int [] result = null;
		Gson g = new Gson();
	try {
		String currentUser = map.get("user").toString();// userName
		/***************************************************************
		 * 前台传入的 json 数据 已经封装成 ProductExcel
		 **************************************************************/
		List<Shop> shopList = (List<Shop>) map.get("shopList");
		
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		StringBuffer sb = new StringBuffer();
		sb.append("  INSERT INTO `shop_info`  ");
		sb.append(" (shop_info_name,shop_info_image,price,first_shop_category_id,second_shop_category_id,three_shop_category_id,create_time,create_by,description,from_type,from_web) ");
		sb.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?) ");
			for(Shop p:shopList){
					batchArgs.add(new Object[] { p.getShopInfoName(),p.getShopInfoImage(),p.getPrice(),p.getFirstShopCategoryId(),0,0,DateUtil.getDateTime(),currentUser,p.getDescription(),1,p.getFromWeb()});
			}
			result = jdbcTemplate.batchUpdate(sb.toString(), batchArgs);
		} catch (Exception e) {
			LOGGER.error(e.toString());
		}
		return result;
	}
		

}
