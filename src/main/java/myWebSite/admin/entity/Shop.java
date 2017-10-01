package myWebSite.admin.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Shop implements RowMapper<Shop>, Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer shopInfoId;
	/**
	 * 商品名
	 */
	private String shopInfoName;
	/**
	 * 商品主图
	 */
	private String shopInfoImage;
	/**
	 * 分类ID 
	 */
	private Integer shopCategoryId;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	
	/**
	 * 创建者
	 */
	private String createBy;
	
	/**
	 * 是否展示 1:展示，2:隐藏
	 */
	private Integer isShow;
	
	/**
	 * 描述 
	 * @author yzj
	 * @version 2.0 2017年9月30日 上午11:50:42
	 * 
	 * @return
	 */
	private String description;
	
	 
	
	public Integer getShopInfoId() {
		return shopInfoId;
	}



	public void setShopInfoId(Integer shopInfoId) {
		this.shopInfoId = shopInfoId;
	}



	public String getShopInfoName() {
		return shopInfoName;
	}



	public void setShopInfoName(String shopInfoName) {
		this.shopInfoName = shopInfoName;
	}



	public String getShopInfoImage() {
		return shopInfoImage;
	}



	public void setShopInfoImage(String shopInfoImage) {
		this.shopInfoImage = shopInfoImage;
	}



	public Integer getShopCategoryId() {
		return shopCategoryId;
	}



	public void setShopCategoryId(Integer shopCategoryId) {
		this.shopCategoryId = shopCategoryId;
	}



	public String getCreateTime() {
		return createTime;
	}



	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}



	public String getCreateBy() {
		return createBy;
	}



	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}



	public Integer getIsShow() {
		return isShow;
	}



	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
		Shop shop = new Shop();
		shop.setShopInfoId(rs.getInt("shop_info_id"));
		shop.setShopInfoName(rs.getString("shop_info_name"));
		shop.setShopInfoImage(rs.getString("shop_info_image"));
		shop.setShopCategoryId(rs.getInt("shop_category_id"));
		shop.setCreateTime(rs.getString("create_time"));
		shop.setCreateBy(rs.getString("create_by"));
		shop.setIsShow(rs.getInt("is_show"));
		shop.setDescription(rs.getString("description"));
		return shop;
	}

}

