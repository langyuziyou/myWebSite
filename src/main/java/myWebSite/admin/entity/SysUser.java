package myWebSite.admin.entity;

import java.sql.*;
import org.springframework.jdbc.core.RowMapper;
import java.io.Serializable;


/**
 * sys_user 实体类 Wed Jul 06 10:19:11 CST 2016 lvzonghai
 */

public class SysUser implements RowMapper<SysUser>, Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer sysUserId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 组ID 
	 */
	private Integer groupId;
	
	

	
	public Integer getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	

	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	@Override
	public SysUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		SysUser sysUser = new SysUser();
		sysUser.setSysUserId(rs.getInt("SYS_USER_ID"));
		sysUser.setUserName(rs.getString("USER_NAME"));
		sysUser.setPassword(rs.getString("PASSWORD"));
		sysUser.setGroupId(rs.getInt("GROUP_ID"));
		return sysUser;
	}
	
}
