/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.dao.intostock;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.xhs.entity.intostock.XhsIntostockInfo;

/**
 * 入库登记信息DAO接口
 * @author liliangming
 * @version 2018-12-23
 */
@MyBatisDao
public interface XhsIntostockInfoDao extends CrudDao<XhsIntostockInfo> {
	
}