/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.dao.product;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.xhs.entity.product.XhsProductInfo;

/**
 * 产品信息DAO接口
 * @author liliangming
 * @version 2018-12-19
 */
@MyBatisDao
public interface XhsProductInfoDao extends CrudDao<XhsProductInfo> {
	
}