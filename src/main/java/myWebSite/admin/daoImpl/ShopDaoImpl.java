package myWebSite.admin.daoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import myWebSite.admin.dao.ShopDao;
import myWebSite.admin.entity.Shop;

@Repository
public class ShopDaoImpl extends CommonDaoImpl implements ShopDao {

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
		baseSql.append(" LEFT JOIN shop_category  ON shop_category.`shop_category_id` = shop_info.`shop_category_id`  ");
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
			sql.append(" AND shop_info.`shop_category_id` = ? ");
			paramList.add(firstSelect);
			
			if (!shopName.equals("")) {
				sql.append(" AND  shop_info_name = ?");
				paramList.add("%" + shopName + "%");
			}
			
			sql.append(" union ");
			sql.append(baseSql.toString());
			sql.append(" AND shop_category.`parent_id` = ? ");
			paramList.add(firstSelect);
			
			if (!shopName.equals("")) {
				sql.append(" AND  shop_info_name = ?");
				paramList.add("%" + shopName + "%");
			}
			
			sql.append(" union ");
			sql.append(baseSql.toString());
			sql.append(" AND shop_category.`parent_id` IN (SELECT shop_category_id FROM `shop_category`  b3 WHERE b3.parent_id = ?)  ");
			paramList.add(firstSelect);
			
			if (!shopName.equals("")) {
				sql.append(" AND  shop_info_name = ?");
				paramList.add("%" + shopName + "%");
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
			sql.append(" AND shop_info.`shop_category_id` = ? ");
			paramList.add(secondSelect);
			
			
			if (!shopName.equals("")) {
				sql.append(" AND  shop_info_name = ?");
				paramList.add("%" + shopName + "%");
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
			sql.append(" AND shop_info.`shop_category_id` = ? ");
			paramList.add(threeSelect);
			
			
			if (!shopName.equals("")) {
				sql.append(" AND  shop_info_name = ?");
				paramList.add("%" + shopName + "%");
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

}
