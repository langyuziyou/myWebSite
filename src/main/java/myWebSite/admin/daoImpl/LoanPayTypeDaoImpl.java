package myWebSite.admin.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import myWebSite.admin.dao.LoanPayTypeDao;
import myWebSite.admin.entity.LoanPayType;


@Repository
public class LoanPayTypeDaoImpl implements LoanPayTypeDao{
	
	@Autowired  
	private JdbcTemplate jdbcTemplate;  
	  
    @Autowired  
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;  
	  

	@Override
	public LoanPayType getById(Integer id) {
		String sql = " SELECT * FROM loan_pay_type WHERE loan_pay_type_id = ?";  
		LoanPayType loanPayType = jdbcTemplate.queryForObject(sql, new LoanPayType(),  
                new Object[] { id });  
        return loanPayType;  
	}

	@Override
	public List<LoanPayType> findAll() { 
        String sql = " SELECT * FROM loan_pay_type ";  
        List<LoanPayType> loanPayTypes = jdbcTemplate.query(sql, new LoanPayType());  
        return loanPayTypes;  
        }

	@Override
	public Integer save(LoanPayType entity) {  
        
      String sql = " INSERT INTO loan_pay_type(loan_pay_type_id, name) VALUES(:id, :name) ";  
      MapSqlParameterSource paramSource = new MapSqlParameterSource();  
      paramSource.addValue("id", entity.getLoanPayTypeId());  
      paramSource.addValue("name", entity.getName());  
      int result = namedParameterJdbcTemplate.update(sql, paramSource);  
      return result;  
      }

}
