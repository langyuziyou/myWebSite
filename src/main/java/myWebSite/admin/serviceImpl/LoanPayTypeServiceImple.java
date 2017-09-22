package myWebSite.admin.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myWebSite.admin.dao.LoanPayTypeDao;
import myWebSite.admin.entity.LoanPayType;
import myWebSite.admin.service.LoanPayTypeService;

@Service("loanPayTypeService")  
public class LoanPayTypeServiceImple implements LoanPayTypeService{
	
	@Autowired  
    private LoanPayTypeDao loanPayTypeDao;  

	@Override
	public LoanPayType getById(Integer id) {
		return loanPayTypeDao.getById(id);
	}

	@Override
	public List<LoanPayType> findAll() {
		return loanPayTypeDao.findAll();
	}

	@Override
	public Integer save(LoanPayType loanPay) {
		return loanPayTypeDao.save(loanPay);
	}

}
