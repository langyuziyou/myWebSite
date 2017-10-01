package myWebSite.admin.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ShopCategory implements RowMapper<ShopCategory>, Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer shopCategoryId;
	/**
	 * 商品名
	 */
	private String shopCategoryName;
	/**
	 * 父级ID 
	 */
	private Integer parentId;
	/**
	 * 等级
	 */
	private Integer level;
	
	/**
	 * 描述
	 */
	private String description;
	
	

	public Integer getShopCategoryId() {
		return shopCategoryId;
	}
	public void setShopCategoryId(Integer shopCategoryId) {
		this.shopCategoryId = shopCategoryId;
	}

	public String getShopCategoryName() {
		return shopCategoryName;
	}

	public void setShopCategoryName(String shopCategoryName) {
		this.shopCategoryName = shopCategoryName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public ShopCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(rs.getInt("shop_category_id"));
		shopCategory.setShopCategoryName(rs.getString("shop_category_name"));
		shopCategory.setParentId(rs.getInt("parent_id"));
		shopCategory.setLevel(rs.getInt("level"));
		shopCategory.setDescription(rs.getString("description"));
		return shopCategory;
	}

}
