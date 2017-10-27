package myWebSite.admin.daoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import myWebSite.admin.dao.ShopDao;
import myWebSite.admin.entity.Shop;
import myWebSite.admin.entity.UploadImg;
import myWebSite.admin.tools.DateUtil;

@Repository
public class ShopDaoImpl extends CommonDaoImpl implements ShopDao {

	private static final Logger LOGGER = Logger.getLogger(ShopDaoImpl.class);


	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;



	@Override
	public Shop getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shop> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(Shop entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> shopList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT shop_info.*,shop_category.`shop_category_name` FROM shop_info   ");
		sql.append(" LEFT JOIN shop_category  ON shop_category.`shop_category_id` = shop_info.`shop_category_id`  ");
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString());
		return list;
	}

	@Override
	public Map<String, Object> shopList(Map<String, Object> paraList) {
		Integer page = Integer.parseInt(paraList.get("page").toString());
		Integer pageSize = Integer.parseInt(paraList.get("pageSize").toString());
		// conditions
		String shopName = paraList.get("shopName").toString();
		String price1 = paraList.get("price1").toString();
		String price2 = paraList.get("price2").toString();
		
		String firstSelect = paraList.get("firstSelect").toString();
		String secondSelect = paraList.get("secondSelect").toString();
		String threeSelect = paraList.get("threeSelect").toString();
		/**
		 * 查询条件用 0： 查询所有 1：查询选中的1级分类 2：查询选中的2级分类 3：查询选中的3级分类
		 */
		int condition = 0;
		if (!firstSelect.equals("-1")) {
			condition = 1;
		}
		if (!secondSelect.equals("-1")) {
			condition = 2;
		}
		if (!threeSelect.equals("-1")) {
			condition = 3;
		}

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Object> paramList = new ArrayList<Object>();
		
		StringBuffer baseSql = new StringBuffer();
		baseSql.append(" SELECT shop_info.*,shop_category.`shop_category_name` FROM shop_info   ");
		baseSql.append(" LEFT JOIN shop_category  ON shop_category.`shop_category_id` = shop_info.`first_shop_category_id`  ");
		baseSql.append(" where 1=1 ");
		
		StringBuffer sql = new StringBuffer();
		sql.append(baseSql.toString());
		int count = 0;
		int pageCount = 1;// 记录总页数

		switch (condition) {

		/***********************************************************************************************************************
		 * condition 0 : 一级：全选，二级全选，三级 全选 过滤关键字
		 **************************************************************************************************************************/
		case 0:
			if (!shopName.equals("")) {
				sql.append(" AND  shop_info_name Like ?");
				paramList.add("%" + shopName + "%");
			}
			
			if(!price1.equals("")){
				sql.append(" AND  price >= ? ");
				paramList.add(price1);
			}
			
			if(!price2.equals("")){
				sql.append(" AND  price <= ? ");
				paramList.add(price2);
			}
			

			// 查询总数
			count = count(sql.toString(), paramList.toArray());

			sql.append(" ORDER BY shop_info.`create_time` DESC LIMIT ?,? ");
			paramList.add((page - 1) * pageSize);
			paramList.add(pageSize);
			// 查询分页

			break;

		/**********************************************************************************************************
		 * condition 1: 一级分类被选择 过滤 关键字
		 **********************************************************************************************************/
		case 1:
			sql.append(" AND shop_info.`first_shop_category_id` = ? ");
			paramList.add(firstSelect);
			
			if (!shopName.equals("")) {
				sql.append(" AND  shop_info_name like ?");
				paramList.add("%" + shopName + "%");
			}
			
			if(!price1.equals("")){
				sql.append(" AND  price >= ? ");
				paramList.add(price1);
			}
			
			if(!price2.equals("")){
				sql.append(" AND  price <= ? ");
				paramList.add(price2);
			}
			
			
			sql.append(" union ");
			sql.append(baseSql.toString());
			sql.append(" AND shop_category.`parent_id` = ? ");
			paramList.add(firstSelect);
			
			if (!shopName.equals("")) {
				sql.append(" AND  shop_info_name like ?");
				paramList.add("%" + shopName + "%");
			}
			if(!price1.equals("")){
				sql.append(" AND  price >= ? ");
				paramList.add(price1);
			}
			
			if(!price2.equals("")){
				sql.append(" AND  price <= ? ");
				paramList.add(price2);
			}
			
			
			sql.append(" union ");
			sql.append(baseSql.toString());
			sql.append(" AND shop_category.`parent_id` IN (SELECT shop_category_id FROM `shop_category`  b3 WHERE b3.parent_id = ?)  ");
			paramList.add(firstSelect);
			
			if (!shopName.equals("")) {
				sql.append(" AND  shop_info_name like ?");
				paramList.add("%" + shopName + "%");
			}
			
			if(!price1.equals("")){
				sql.append(" AND  price >= ? ");
				paramList.add(price1);
			}
			
			if(!price2.equals("")){
				sql.append(" AND  price <= ? ");
				paramList.add(price2);
			}
			

			// 查询总数
			count = count(sql.toString(), paramList.toArray());

			sql.append(" ORDER BY `create_time` DESC LIMIT ?,? ");
			paramList.add((page - 1) * pageSize);
			paramList.add(pageSize);
			break;

		/***************************************************************************************************************
		 * condition 2: 选择了 二级分类 过滤 关键字
		 **************************************************************************************************************/
		case 2:
			sql.append(" AND shop_info.`second_shop_category_id` = ? ");
			paramList.add(secondSelect);
			
			
			if (!shopName.equals("")) {
				sql.append(" AND  shop_info_name like ?");
				paramList.add("%" + shopName + "%");
			}
			
			if(!price1.equals("")){
				sql.append(" AND  price >= ? ");
				paramList.add(price1);
			}
			
			if(!price2.equals("")){
				sql.append(" AND  price <= ? ");
				paramList.add(price2);
			}
			
			

			sql.append(" union ");
			sql.append(baseSql.toString());
			sql.append(" AND shop_category.`parent_id` = ? ");
			paramList.add(secondSelect);
			
			if (!shopName.equals("")) {
				sql.append(" AND  shop_info_name like ?");
				paramList.add("%" + shopName + "%");
			}
			
			if(!price1.equals("")){
				sql.append(" AND  price >= ? ");
				paramList.add(price1);
			}
			
			if(!price2.equals("")){
				sql.append(" AND  price <= ? ");
				paramList.add(price2);
			}
			
			

			// 查询总数
			count = count(sql.toString(), paramList.toArray());

			sql.append(" ORDER BY `create_time` DESC LIMIT ?,? ");
			paramList.add((page - 1) * pageSize);
			paramList.add(pageSize);
			break;

		/***************************************************************************************************************
		 * condition 3: 选择了3级分类 过滤 关键字
		 **************************************************************************************************************/
		case 3:
			sql.append(" AND shop_info.`three_shop_category_id` = ? ");
			paramList.add(threeSelect);
			
			
			if (!shopName.equals("")) {
				sql.append(" AND  shop_info_name like ?");
				paramList.add("%" + shopName + "%");
			}
			
			if(!price1.equals("")){
				sql.append(" AND  price >= ? ");
				paramList.add(price1);
			}
			
			if(!price2.equals("")){
				sql.append(" AND  price <= ? ");
				paramList.add(price2);
			}
			
			

			// 查询总数
			count = count(sql.toString(), paramList.toArray());

			sql.append(" ORDER BY `create_time` DESC LIMIT ?,? ");
			paramList.add((page - 1) * pageSize);
			paramList.add(pageSize);
			break;

		case 4:
			break;

		}

		list = jdbcTemplate.queryForList(sql.toString(), paramList.toArray());
		
		/****
		 * 计算出 总页数
		 */
		if (count % pageSize == 0) {
			pageCount = count / pageSize;
		} else {
			pageCount = count / pageSize + 1;
		}

		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		result.put("count", count);
		result.put("pageCount", pageCount);
		return result;
	}

	@Override
	public int[] shopExcelIn(Map<String, Object> map) {
		int [] result = null;
		Gson g = new Gson();
	try {
		String list = map.get("sb").toString();
		String currentUser = map.get("user").toString();// userName
		/***************************************************************
		 * 前台传入的 json 数据 已经封装成 ProductExcel
		 **************************************************************/
		List<Shop> shopList = g.fromJson(list, new TypeToken<List<Shop>>() {
		}.getType());
		
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		StringBuffer sb = new StringBuffer();
		sb.append("  INSERT INTO `shop_info`  ");
		sb.append(" (shop_info_name,shop_info_image,price,first_shop_category_id,second_shop_category_id,three_shop_category_id,create_time,create_by,description,from_type,from_web) ");
		sb.append(" VALUES(?,?,?,?,?,?,?,?,?) ");
			for(Shop p:shopList){
					batchArgs.add(new Object[] { p.getShopInfoName(),p.getShopInfoImage(),p.getPrice(),p.getFirstShopCategoryId(),0,0,DateUtil.getDateTime(),currentUser,p.getDescription(),1});
			}
			result = jdbcTemplate.batchUpdate(sb.toString(), batchArgs);
		} catch (Exception e) {
			LOGGER.error(e.toString());
		}
		return result;
	}

	@Override
	public Integer insertShop(Shop p) {
		StringBuffer sb = new StringBuffer();
		Integer result = null;
		try{
			sb.append("  INSERT INTO `shop_info`  ");
			sb.append(" (shop_info_name,shop_info_image,price,first_shop_category_id,second_shop_category_id,three_shop_category_id,create_time,create_by,description,from_type,category_name) ");
			sb.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?) ");
			result = jdbcTemplate.update(sb.toString(),new Object[]{ p.getShopInfoName(),p.getShopInfoImage(),p.getPrice(),p.getFirstShopCategoryId(),p.getSecondShopCategoryId(),p.getThreeShopCategoryId(),DateUtil.getDateTime(),"admin",p.getDescription(),2,p.getCategoryName()});
		}catch(Exception e){
			LOGGER.error(e.toString());
		}
		return result;
	}

	@Override
	public Map<String, Object> findByName(String name) {
		String sql = " select * from shop_info where shop_info_name = ? ";
		Map<String, Object> result = jdbcTemplate.queryForMap(sql,new Object[] { name });
		return result;
	}
	
	public Map<String, Object> findById(String id) {
		String sql = " select * from shop_info where shop_info_id = ? ";
		Map<String, Object> result = jdbcTemplate.queryForMap(sql,new Object[] { id });
		return result;
	}

	@Override
	public int findByNameCount(String name) {
		String sql = " select * from shop_info where shop_info_name = ? ";
		return count(sql.toString(),name);
	}

	@Override
	public void insertImg(String id, List<UploadImg> list) {
		
		try{
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		StringBuffer sb = new StringBuffer();
		sb.append("  INSERT INTO `shop_img`  ");
		sb.append(" (shop_info_id,shop_info_image) ");
		sb.append(" VALUES(?,?) ");
			for(UploadImg p:list){
					batchArgs.add(new Object[] { id,p.getSrc()});
			}
			jdbcTemplate.batchUpdate(sb.toString(), batchArgs);
		} catch (Exception e) {
			LOGGER.error(e.toString());
		}
	}

	@Override
	public List<Map<String, Object>> imgByShopId(String id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = " select * from shop_img where shop_info_id = ? ";
		list = jdbcTemplate.queryForList(sql,new Object[]{id});
		return list;
	}

	@Override
	public Integer deleteImg(String id, String imgId) {
		String sql = " delete from shop_img where shop_img_id = ? ";
		Integer result = 0;
		try{
			result = jdbcTemplate.update(sql,new Object[]{imgId});
		}catch(Exception e){
			LOGGER.error(e.toString());
		}
		return result;
	}

	@Override
	public void editShop(Shop p) {
		StringBuffer sb = new StringBuffer();
		try{
			sb.append("  update `shop_info` set  shop_info_name = ? ,shop_info_image = ? ,price =? ,first_shop_category_id =? ,second_shop_category_id = ? , three_shop_category_id = ? ,description = ? ,category_name =?  where shop_info_id = ? ");
			jdbcTemplate.update(sb.toString(),new Object[]{ p.getShopInfoName(),p.getShopInfoImage(),p.getPrice(),p.getFirstShopCategoryId(),p.getSecondShopCategoryId(),p.getThreeShopCategoryId(),p.getDescription(),p.getCategoryName(),p.getShopInfoId()});
		}catch(Exception e){
			LOGGER.error(e.toString());
		}
	
	}

	@Override
	public void deleteImgByShopId(String id) {
		String sql = " delete from shop_img where shop_info_id = ? ";
		try{
			jdbcTemplate.update(sql,new Object[]{id});
		}catch(Exception e){
			LOGGER.error(e.toString());
		}
	
	}

	@Override
	public Integer delShop(String id) {
		String sql = " delete from shop_info where shop_info_id = ? ";
		Integer result = 0;
		try{
			result = jdbcTemplate.update(sql,new Object[]{id});
		}catch(Exception e){
			LOGGER.error(e.toString());
		}
		return result;
	
	}

}
