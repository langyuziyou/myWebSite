package myWebSite.admin.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import myWebSite.admin.dao.LoanPayTypeDao;
import myWebSite.admin.dao.SysUserDao;
import myWebSite.admin.entity.LoanPayType;
import myWebSite.admin.entity.SysUser;

@Repository
public class SysUserDaoImpl implements SysUserDao{
	
	@Autowired  
	private JdbcTemplate jdbcTemplate;  
	  
    @Autowired  
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;  

	@Override
	public SysUser getById(Integer id) {
		String sql = " SELECT * FROM sys_user WHERE SYS_USER_ID = ? ";  
		SysUser sysUser = jdbcTemplate.queryForObject(sql, new SysUser(),  
                new Object[] { id });  
        return sysUser;  
	}
	
	
	@Override
	public List<SysUser> findAll() { 
        String sql = " SELECT * FROM sys_user ";  
        List<SysUser> SysUserList = jdbcTemplate.query(sql, new SysUser());  
        return SysUserList;  
        }

	@Override
	public Integer save(SysUser entity) {  
        
      String sql = " INSERT INTO sys_user(USER_NAME, PASSWORD,GROUP_ID) VALUES(:name, :password,:groupId) ";  
      MapSqlParameterSource paramSource = new MapSqlParameterSource();  
      paramSource.addValue("name", entity.getUserName());  
      paramSource.addValue("password", entity.getPassword());  
      paramSource.addValue("groupId", entity.getGroupId());  
      int result = namedParameterJdbcTemplate.update(sql, paramSource);  
      return result;  
      }
	
	/**
	 * 登录 用 
	 */
	public SysUser getByLogin(String name,String password) {
		String sql = " SELECT * FROM sys_user WHERE USER_NAME = ? AND PASSWORD = ? ";  
		SysUser sysUser = jdbcTemplate.queryForObject(sql, new SysUser(),  
                new Object[] { name,password });  
        return sysUser;  
	}

	


}
