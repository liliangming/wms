/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.service.stock;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.xhs.dao.stock.XhsProductStockDao;
import com.jeesite.modules.xhs.entity.stock.XhsProductStock;

/**
 * 产品库存信息Service
 * @author liliangming
 * @version 2018-12-23
 */
@Service
@Transactional(readOnly=true)
public class XhsProductStockService extends CrudService<XhsProductStockDao, XhsProductStock> {
	
	/**
	 * 获取单条数据
	 * @param xhsProductStock
	 * @return
	 */
	@Override
	public XhsProductStock get(XhsProductStock xhsProductStock) {
		return super.get(xhsProductStock);
	}
	
	/**
	 * 查询分页数据
	 * @param xhsProductStock 查询条件
	 * @param xhsProductStock.page 分页对象
	 * @return
	 */
	@Override
	public Page<XhsProductStock> findPage(XhsProductStock xhsProductStock) {
		return super.findPage(xhsProductStock);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param xhsProductStock
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(XhsProductStock xhsProductStock) {
		super.save(xhsProductStock);
	}
	
	/**
	 * 更新状态
	 * @param xhsProductStock
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(XhsProductStock xhsProductStock) {
		super.updateStatus(xhsProductStock);
	}
	
	/**
	 * 删除数据
	 * @param xhsProductStock
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(XhsProductStock xhsProductStock) {
		super.delete(xhsProductStock);
	}
}