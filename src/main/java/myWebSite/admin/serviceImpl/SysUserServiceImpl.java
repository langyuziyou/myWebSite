package myWebSite.admin.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myWebSite.admin.dao.LoanPayTypeDao;
import myWebSite.admin.dao.SysUserDao;
import myWebSite.admin.entity.SysUser;
import myWebSite.admin.service.SysUserService;


@Service("sysUserService")  
public class SysUserServiceImpl implements SysUserService{
	
	
	@Autowired  
    private SysUserDao sysUserDao;  

	@Override
	public SysUser getById(Integer id) {
		return sysUserDao.getById(id);
	}

	@Override
	public List<SysUser> findAll() {
		return sysUserDao.findAll();
	}

	@Override
	public Integer save(SysUser sysUser) {
		return sysUserDao.save(sysUser);
	}

	@Override
	public SysUser getByLogin(String username, String password) {
		return sysUserDao.getByLogin(username, password);
	}
	

}
