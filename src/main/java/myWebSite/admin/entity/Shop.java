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
	 * 1级分类ID 
	 */
	private Integer firstShopCategoryId;
	/**
	 * 2级分类ID 
	 */
	private Integer secondShopCategoryId;
	/**
	 * 3级分类ID 
	 */
	private Integer threeShopCategoryId;
	
	private String categoryName;
	
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
	
	
	
	private Integer fromType;
	
	private String price;
	 
	
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


	



	public Integer getFirstShopCategoryId() {
		return firstShopCategoryId;
	}



	public void setFirstShopCategoryId(Integer firstShopCategoryId) {
		this.firstShopCategoryId = firstShopCategoryId;
	}



	public Integer getSecondShopCategoryId() {
		return secondShopCategoryId;
	}



	public void setSecondShopCategoryId(Integer secondShopCategoryId) {
		this.secondShopCategoryId = secondShopCategoryId;
	}



	public Integer getThreeShopCategoryId() {
		return threeShopCategoryId;
	}



	public void setThreeShopCategoryId(Integer threeShopCategoryId) {
		this.threeShopCategoryId = threeShopCategoryId;
	}

	

	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	
	


	public Integer getFromType() {
		return fromType;
	}



	public void setFromType(Integer fromType) {
		this.fromType = fromType;
	}

	
	


	public String getPrice() {
		return price;
	}



	public void setPrice(String price) {
		this.price = price;
	}



	@Override
	public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
		Shop shop = new Shop();
		shop.setShopInfoId(rs.getInt("shop_info_id"));
		shop.setShopInfoName(rs.getString("shop_info_name"));
		shop.setShopInfoImage(rs.getString("shop_info_image"));
		shop.setFirstShopCategoryId(rs.getInt("first_shop_category_id"));
		shop.setSecondShopCategoryId(rs.getInt("second_shop_category_id"));
		shop.setThreeShopCategoryId(rs.getInt("three_shop_category_id"));
		shop.setCategoryName(rs.getString("category_name"));
		shop.setCreateTime(rs.getString("create_time"));
		shop.setCreateBy(rs.getString("create_by"));
		shop.setIsShow(rs.getInt("is_show"));
		shop.setDescription(rs.getString("description"));
		shop.setFromType(rs.getInt("from_type"));
		shop.setPrice(rs.getString("price"));
		return shop;
	}

}

