/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.service.warehouse;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.TreeService;
import com.jeesite.modules.xhs.entity.warehouse.XhsWarehouseTree;
import com.jeesite.modules.xhs.dao.warehouse.XhsWarehouseTreeDao;

/**
 * 仓库信息Service
 * @author liliangming
 * @version 2018-12-19
 */
@Service
@Transactional(readOnly=true)
public class XhsWarehouseTreeService extends TreeService<XhsWarehouseTreeDao, XhsWarehouseTree> {
	
	/**
	 * 获取单条数据
	 * @param xhsWarehouseTree
	 * @return
	 */
	@Override
	public XhsWarehouseTree get(XhsWarehouseTree xhsWarehouseTree) {
		return super.get(xhsWarehouseTree);
	}
	
	/**
	 * 查询列表数据
	 * @param xhsWarehouseTree
	 * @return
	 */
	@Override
	public List<XhsWarehouseTree> findList(XhsWarehouseTree xhsWarehouseTree) {
		return super.findList(xhsWarehouseTree);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param xhsWarehouseTree
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(XhsWarehouseTree xhsWarehouseTree) {
		super.save(xhsWarehouseTree);
	}
	
	/**
	 * 更新状态
	 * @param xhsWarehouseTree
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(XhsWarehouseTree xhsWarehouseTree) {
		super.updateStatus(xhsWarehouseTree);
	}
	
	/**
	 * 删除数据
	 * @param xhsWarehouseTree
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(XhsWarehouseTree xhsWarehouseTree) {
		super.delete(xhsWarehouseTree);
	}
	
}