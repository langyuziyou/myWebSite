package myWebSite.admin.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LoanPayType implements RowMapper<LoanPayType>, Serializable{
	
	private static final long serialVersionUID = -8823504831198719837L;  
	  
    private Integer loanPayTypeId;  
  
    private String name;  
	public Integer getLoanPayTypeId() {
		return loanPayTypeId;
	}
	public void setLoanPayTypeId(Integer loanPayTypeId) {
		this.loanPayTypeId = loanPayTypeId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public LoanPayType mapRow(ResultSet rs, int rowNum) throws SQLException {
		LoanPayType loanPayType = new LoanPayType();
		loanPayType.setLoanPayTypeId(rs.getInt("loan_pay_type_id"));
		loanPayType.setName(rs.getString("name"));
		return loanPayType;
	}

}
