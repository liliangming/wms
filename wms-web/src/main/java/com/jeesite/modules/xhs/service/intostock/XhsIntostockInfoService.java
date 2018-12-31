/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.service.intostock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.xhs.dao.intostock.XhsIntostockInfoDao;
import com.jeesite.modules.xhs.dao.intostock.XhsIntostockProductDao;
import com.jeesite.modules.xhs.dao.stock.XhsProductStockDetailDao;
import com.jeesite.modules.xhs.entity.intostock.XhsIntostockInfo;
import com.jeesite.modules.xhs.entity.intostock.XhsIntostockProduct;
import com.jeesite.modules.xhs.entity.product.XhsProductInfo;
import com.jeesite.modules.xhs.entity.stock.XhsProductStockDetail;
import com.jeesite.modules.xhs.entity.warehouse.XhsWarehouseTree;
import com.jeesite.modules.xhs.exception.MyException;

/**
 * 入库登记信息Service
 * 
 * @author liliangming
 * @version 2018-12-23
 */
@Service
@Transactional(readOnly = true)
public class XhsIntostockInfoService extends CrudService<XhsIntostockInfoDao, XhsIntostockInfo> {

	@Autowired
	private XhsIntostockInfoDao xhsIntostockInfoDao;

	@Autowired
	private XhsIntostockProductDao xhsIntostockProductDao;

	@Autowired
	private XhsProductStockDetailDao xhsProductStockDetailDao;

	/**
	 * 获取单条数据
	 * 
	 * @param xhsIntostockInfo
	 * @return
	 */
	@Override
	public XhsIntostockInfo get(XhsIntostockInfo xhsIntostockInfo) {
		XhsIntostockInfo entity = super.get(xhsIntostockInfo);
		if (entity != null) {
			XhsIntostockProduct xhsIntostockProduct = new XhsIntostockProduct(entity);
			xhsIntostockProduct.setStatus(XhsIntostockProduct.STATUS_NORMAL);
			entity.setXhsIntostockProductList(xhsIntostockProductDao.findList(xhsIntostockProduct));
		}
		return entity;
	}

	/**
	 * 查询分页数据
	 * 
	 * @param xhsIntostockInfo
	 *            查询条件
	 * @param xhsIntostockInfo.page
	 *            分页对象
	 * @return
	 */
	@Override
	public Page<XhsIntostockInfo> findPage(XhsIntostockInfo xhsIntostockInfo) {
		return super.findPage(xhsIntostockInfo);
	}

	/**
	 * 保存数据（插入或更新）
	 * 
	 * @param xhsIntostockInfo
	 */
	@Override
	@Transactional(readOnly = false)
	public void save(XhsIntostockInfo xhsIntostockInfo) {
		List<XhsIntostockProduct> list = xhsIntostockInfo.getXhsIntostockProductList();
		xhsIntostockInfo.setProductNum(0L);
		xhsIntostockInfo.setQuantity(0L);
		xhsIntostockInfo.setInstockStatus("0");
		// 计算产品种数和总数
		if (list != null && !list.isEmpty()) {
			for (XhsIntostockProduct xhsIntostockProduct : list) {
				xhsIntostockInfo.setProductNum(xhsIntostockInfo.getProductNum() + 1);
				xhsIntostockInfo.setQuantity(xhsIntostockInfo.getQuantity()
						+ (xhsIntostockProduct.getQuantity() == null ? 0L : xhsIntostockProduct.getQuantity()));
			}
		}
		super.save(xhsIntostockInfo);

		// 保存 XhsIntostockInfo子表
		for (XhsIntostockProduct xhsIntostockProduct : xhsIntostockInfo.getXhsIntostockProductList()) {
			if (!XhsIntostockProduct.STATUS_DELETE.equals(xhsIntostockProduct.getStatus())) {
				xhsIntostockProduct.setIntostockId(xhsIntostockInfo);
				if (xhsIntostockProduct.getIsNewRecord()) {
					xhsIntostockProduct.preInsert();
					xhsIntostockProductDao.insert(xhsIntostockProduct);
				} else {
					xhsIntostockProduct.preUpdate();
					xhsIntostockProductDao.update(xhsIntostockProduct);
				}

				XhsProductStockDetail xhsProductStockDetail = new XhsProductStockDetail();
				xhsProductStockDetail.setProduct(new XhsProductInfo(xhsIntostockProduct.getProductId()));
				xhsProductStockDetail.setWarehouse(new XhsWarehouseTree(xhsIntostockInfo.getWarehouse().getTreeCode()));
				xhsProductStockDetail.setQuantity(xhsIntostockProduct.getQuantity());
				xhsProductStockDetail.preInsert();
				xhsProductStockDetailDao.addXhsProductStockDetail(xhsProductStockDetail);
			} else {
				xhsIntostockProductDao.delete(xhsIntostockProduct);

				XhsProductStockDetail xhsProductStockDetail = new XhsProductStockDetail();
				xhsProductStockDetail.setProduct(new XhsProductInfo(xhsIntostockProduct.getProductId()));
				xhsProductStockDetail.setWarehouse(new XhsWarehouseTree(xhsIntostockInfo.getWarehouse().getTreeCode()));
				xhsProductStockDetail.setQuantity(
						(xhsIntostockProduct.getQuantity() == null ? 0 : xhsIntostockProduct.getQuantity()) * -1);
				xhsProductStockDetail.preInsert();
				xhsProductStockDetailDao.addXhsProductStockDetail(xhsProductStockDetail);
			}
		}
	}

	/**
	 * 更新状态
	 * 
	 * @param xhsIntostockInfo
	 */
	@Override
	@Transactional(readOnly = false)
	public void updateStatus(XhsIntostockInfo xhsIntostockInfo) {
		super.updateStatus(xhsIntostockInfo);
	}

	/**
	 * 删除数据
	 * 
	 * @param xhsIntostockInfo
	 */
	@Override
	@Transactional(readOnly = false)
	public void delete(XhsIntostockInfo xhsIntostockInfo) {
		super.delete(xhsIntostockInfo);
		XhsIntostockProduct xhsIntostockProduct = new XhsIntostockProduct();
		xhsIntostockProduct.setIntostockId(xhsIntostockInfo);
		xhsIntostockProductDao.delete(xhsIntostockProduct);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void cancel(XhsIntostockInfo xhsIntostockInfo) throws Exception {
		String instockStatus = xhsIntostockInfo.getInstockStatus();
		xhsIntostockInfo = get(new XhsIntostockInfo(xhsIntostockInfo.getId()));
		if (xhsIntostockInfo == null) {
			throw new MyException("入库单不存在");
		}

		List<XhsIntostockProduct> list = xhsIntostockInfo.getXhsIntostockProductList();
		if (list != null && !list.isEmpty()) {
			Long quantity = 0L;
			XhsProductStockDetail ex = null;
			for (XhsIntostockProduct xhsIntostockProduct : list) {
				quantity = xhsProductStockDetailDao.getQuantityByProductIdAndWarehouseCode(
						xhsIntostockProduct.getProductId(), xhsIntostockInfo.getWarehouse().getTreeCode());
				if (quantity.compareTo(xhsIntostockProduct.getQuantity()) < 0) {
					throw new MyException("库存不足");
				}

				// 撤销的入库登记需要将库存扣除
				ex = new XhsProductStockDetail();
				ex.setProduct(new XhsProductInfo(xhsIntostockProduct.getProductId()));
				ex.setWarehouse(new XhsWarehouseTree(xhsIntostockInfo.getWarehouse().getTreeCode()));
				ex.setQuantity(xhsIntostockProduct.getQuantity() * -1);
				ex.preInsert();
				xhsProductStockDetailDao.addXhsProductStockDetail(ex);
			}
		}

		xhsIntostockInfoDao.updateIntostockStatus(xhsIntostockInfo.getId(), instockStatus,
				xhsIntostockInfo.getUpdateBy());
	}

}