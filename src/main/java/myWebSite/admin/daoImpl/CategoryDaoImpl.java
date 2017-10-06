package myWebSite.admin.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import myWebSite.admin.dao.CategoryDao;
import myWebSite.admin.entity.ShopCategory;

@Repository
public class CategoryDaoImpl extends CommonDaoImpl implements CategoryDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	
	/**
	 * 当查询结果数为0时，会报异常，则默认报异常为没有结果
	 */
	public int count(String sql, Object... o) {
		String newSql = "SELECT COUNT(1) FROM ( " + sql + " ) t";
		try {
			if (o == null) {
				return jdbcTemplate.queryForObject(newSql, Integer.class);
			}
			return jdbcTemplate.queryForObject(newSql, o, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}

	public int count(String sql) {
		return count(sql, null);
	}

	@Override
	public ShopCategory getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShopCategory> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(ShopCategory entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Map<String, Object>> getListByParentId(String id) {
		String sql = " SELECT shop_category_id as id,shop_category_name,parent_id as parentId,level,description FROM shop_category where parent_id = ? ";
		List<Map<String, Object>> categoryList = jdbcTemplate.queryForList(sql,new Object[] { id });
		return categoryList;
	}

	@Override
	public List<Map<String, Object>> categoryList() {
		String sql = " SELECT shop_category_id as id,shop_category_name,parent_id as parentId,level,description   FROM shop_category  where level = 1 ";
		List<Map<String, Object>> categoryList = jdbcTemplate.queryForList(sql);
		return categoryList;
	}

	@Override
	public Integer addCategory(String pId, String name,Integer level) {
		 String sql = " INSERT INTO shop_category(shop_category_name, parent_id,level) VALUES(:name, :pId,:level) ";  
	      MapSqlParameterSource paramSource = new MapSqlParameterSource();  
	      paramSource.addValue("name", name); 
	      paramSource.addValue("pId", pId); 
	      paramSource.addValue("level", level); 
	      int result = namedParameterJdbcTemplate.update(sql, paramSource);  
	      return result;  
		
	}

	@Override
	public Integer editCategory(String id, String pId, String name, Integer level) {
		 String sql = " update shop_category set shop_category_name =:name,parent_id=:pId,level=:level where shop_category_id = :id ";  
	      MapSqlParameterSource paramSource = new MapSqlParameterSource();  
	      paramSource.addValue("name", name); 
	      paramSource.addValue("pId", pId); 
	      paramSource.addValue("level", level); 
	      paramSource.addValue("id", id); 
	      int result = namedParameterJdbcTemplate.update(sql, paramSource);  
	      return result;  
	}

	@Override
	public Map<String, Object> findById(String id) {
		String sql = " SELECT shop_category_id as id,shop_category_name,parent_id as parentId,level,description FROM shop_category where shop_category_id = ? ";
		Map<String, Object> result = jdbcTemplate.queryForMap(sql,new Object[] { id });
		return result;
	}

	@Override
	public Integer delCategory(String id) {
		 String sql = " delete from shop_category where shop_category_id = :id ";  
	      MapSqlParameterSource paramSource = new MapSqlParameterSource();
	      paramSource.addValue("id", id); 
	      int result = namedParameterJdbcTemplate.update(sql, paramSource);  
	      return result;  
	}

	@Override
	public Map<String, Object> findByName(String name, Integer level) {
		Map<String, Object> result;
		try{
			String sql = " SELECT shop_category_id as id,shop_category_name,parent_id as parentId,level,description FROM shop_category where shop_category_name = ?  and level = ? ";
			result = jdbcTemplate.queryForMap(sql,new Object[] { name,level });
	
		}
		catch(EmptyResultDataAccessException e){
			result = new HashMap<>();
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> findByPid(String id) {
		String sql = " SELECT shop_category_id as id,shop_category_name,parent_id as parentId,level,description   FROM shop_category  where parent_id = ? ";
		List<Map<String, Object>> categoryList = jdbcTemplate.queryForList(sql,new Object[]{id});
		return categoryList;
	}


}
