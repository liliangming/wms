/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.service.warehouseinput;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.xhs.dao.warehouseinput.XhsWarehouseInputDao;
import com.jeesite.modules.xhs.entity.warehouseinput.XhsWarehouseInput;

/**
 * 入库登记Service
 * 
 * @author liliangming
 * @version 2018-12-19
 */
@Service
@Transactional(readOnly = true)
public class XhsWarehouseInputService extends CrudService<XhsWarehouseInputDao, XhsWarehouseInput> {

	/**
	 * 获取单条数据
	 * 
	 * @param xhsWarehouseInput
	 * @return
	 */
	@Override
	public XhsWarehouseInput get(XhsWarehouseInput xhsWarehouseInput) {
		return super.get(xhsWarehouseInput);
	}

	/**
	 * 查询分页数据
	 * 
	 * @param xhsWarehouseInput 查询条件
	 * @param                   xhsWarehouseInput.page 分页对象
	 * @return
	 */
	@Override
	public Page<XhsWarehouseInput> findPage(XhsWarehouseInput xhsWarehouseInput) {
		return super.findPage(xhsWarehouseInput);
	}

	/**
	 * 保存数据（插入或更新）
	 * 
	 * @param xhsWarehouseInput
	 */
	@Override
	@Transactional(readOnly = false)
	public void save(XhsWarehouseInput xhsWarehouseInput) {
		super.save(xhsWarehouseInput);
	}

	/**
	 * 更新状态
	 * 
	 * @param xhsWarehouseInput
	 */
	@Override
	@Transactional(readOnly = false)
	public void updateStatus(XhsWarehouseInput xhsWarehouseInput) {
		super.updateStatus(xhsWarehouseInput);
	}

	/**
	 * 删除数据
	 * 
	 * @param xhsWarehouseInput
	 */
	@Override
	@Transactional(readOnly = false)
	public void delete(XhsWarehouseInput xhsWarehouseInput) {
		super.delete(xhsWarehouseInput);
	}

}