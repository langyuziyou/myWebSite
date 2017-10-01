package myWebSite.admin.dao;


import myWebSite.admin.entity.SysUser;

public interface SysUserDao extends GenericDao<SysUser,Integer>{

	SysUser getByLogin(String name, String password);

}
