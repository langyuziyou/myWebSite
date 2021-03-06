CREATE TABLE `loan_pay_type` (
  `loan_pay_type_id` INT(16) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` VARCHAR(50) NOT NULL COMMENT '名称',
  PRIMARY KEY (`loan_pay_type_id`)
) ENGINE=INNODB AUTO_INCREMENT=1168 DEFAULT CHARSET=utf8 COMMENT='还款方式维护表'




/******** 用户 *************/
CREATE TABLE `sys_user` (
  `SYS_USER_ID` INT(16) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `USER_NAME` VARCHAR(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '用户名',
  `PASSWORD` VARCHAR(50) CHARACTER SET utf8 DEFAULT '' COMMENT '密码',
  `GROUP_ID` INT(16)  DEFAULT 0 COMMENT '组ID',
  PRIMARY KEY (`SYS_USER_ID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='用户表'


/*****************  商品  ************************/
CREATE TABLE `shop_info` (
  `shop_info_id` INT(16) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `shop_info_name` VARCHAR(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '商品名称',
  `shop_info_image` VARCHAR(200) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '主图',
  `price` DECIMAL(10,2)  COMMENT '价格',
  `first_shop_category_id` INT(16) NOT NULL DEFAULT 0 COMMENT '1级分类ID',
  `second_shop_category_id` INT(16) NOT NULL DEFAULT 0 COMMENT '2级分类ID',
  `three_shop_category_id` INT(16) NOT NULL DEFAULT 0 COMMENT '3级分类ID',
  `category_name` VARCHAR(100) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '分类名称',
  `create_time` VARCHAR(25) DEFAULT NULL COMMENT '创建时间',
  `create_by` VARCHAR(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `is_show` INT(1) NOT NULL DEFAULT 1 COMMENT '1:展示，2:隐藏',
  `description` TEXT COMMENT '商品介绍',
  `from_type` INT(1) NOT NULL DEFAULT 1 COMMENT '1:导入，2:新增',
  PRIMARY KEY (`shop_info_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='商品信息'

DROP TABLE `shop_info`

/*****************  分类  ************************/
CREATE TABLE `shop_category` (
  `shop_category_id` INT(16) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `shop_category_name` VARCHAR(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '分类名称',
  `parent_id` INT(16) NOT NULL DEFAULT 0 COMMENT '父级ID',
  `level` INT(1)  DEFAULT 1 COMMENT '等级',
  `description` VARCHAR(50) CHARACTER SET utf8  DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`shop_category_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表'

/*****************  图片表 第一张就是主图，保存时会排序 *****************************/
CREATE TABLE `shop_img` (
  `shop_img_id` INT(16) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `shop_info_id` INT(16) NOT NULL  COMMENT '商品ID',
  `shop_info_image` VARCHAR(200) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '图片路径',
  PRIMARY KEY (`shop_img_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='图片表'

DROP TABLE `shop_img`
 

/************** 一级分类 ******************/

SELECT shop_info.*,shop_category.`shop_category_name` FROM shop_info  
LEFT JOIN shop_category  ON shop_category.`shop_category_id` = shop_info.`first_shop_category_id` 
WHERE 1=1 
AND shop_info.`first_shop_category_id` = 1 


UNION 
SELECT shop_info.*,shop_category.`shop_category_name` AS shop_category_name FROM shop_info  
LEFT JOIN shop_category  ON shop_category.`shop_category_id` = shop_info.`second_shop_category_id` 
WHERE 1=1 
AND shop_category.`parent_id` = 1  

UNION
SELECT shop_info.*,shop_category.`shop_category_name`, FROM shop_info  
LEFT JOIN shop_category  ON shop_category.`shop_category_id` = shop_info.`three_shop_category_id` 
WHERE 1=1  
AND shop_category.`parent_id` IN (SELECT shop_category_id FROM `shop_category`  b3 WHERE b3.parent_id = 1) 

/************ 二级分类  *********************************/
SELECT shop_info.*,shop_category.`shop_category_name` FROM shop_info  
LEFT JOIN shop_category  ON shop_category.`shop_category_id` = shop_info.`shop_category_id` 

WHERE 1=1 
AND shop_info.`shop_category_id` = 2 
AND  shop_info_name LIKE '%1%'


UNION 
SELECT shop_info.*,shop_category.`shop_category_name` FROM shop_info  
LEFT JOIN shop_category  ON shop_category.`shop_category_id` = shop_info.`shop_category_id` 
WHERE 1=1 
AND shop_category.`parent_id` = 2  
AND  shop_info_name LIKE '%1%'


/******/
SELECT t1.shop_category_name name1, t2.shop_category_name name2, t3.shop_category_name name3, t1.shop_category_id categoryId1, t2.shop_category_id categoryId2, t3.shop_category_id categoryId3 
FROM shop_category AS t1 LEFT JOIN shop_category AS t2 ON t1.SHOP_CATEGORY_ID = t2.PARENT_ID LEFT JOIN shop_category AS t3 ON t2.SHOP_CATEGORY_ID = t3.PARENT_ID 
WHERE 1) t
