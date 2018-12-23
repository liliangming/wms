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
import com.jeesite.modules.xhs.dao.stock.XhsProductStockDao;
import com.jeesite.modules.xhs.entity.intostock.XhsIntostockInfo;
import com.jeesite.modules.xhs.entity.intostock.XhsIntostockProduct;
import com.jeesite.modules.xhs.entity.product.XhsProductInfo;
import com.jeesite.modules.xhs.entity.stock.XhsProductStock;
import com.jeesite.modules.xhs.entity.warehouse.XhsWarehouseTree;

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
	private XhsIntostockProductDao xhsIntostockProductDao;

	@Autowired
	private XhsProductStockDao xhsProductStockDao;

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
		// String quantity = "(SELECT sum(ifnull(quantity,0)) FROM
		// xhs_intostock_product WHERE intostock_id=a.id) AS \"quantity\", "
		// + "(SELECT count(distinct product_id) FROM xhs_intostock_product
		// WHERE intostock_id=a.id) AS \"productNum\"";
		// xhsIntostockInfo.getSqlMap().add("quantity", quantity);

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

				XhsProductStock xhsProductStock = new XhsProductStock();
				xhsProductStock.setProduct(new XhsProductInfo(xhsIntostockProduct.getProductId()));
				xhsProductStock.setWarehouse(new XhsWarehouseTree(xhsIntostockInfo.getWarehouse().getTreeCode()));
				xhsProductStock.setQuantity(xhsIntostockProduct.getQuantity());
				// xhsProductStock.setCreateBy(xhsIntostockInfo.getCreateBy());
				// xhsProductStock.setUpdateBy(xhsIntostockInfo.getUpdateBy());
				xhsProductStock.preInsert();
				xhsProductStockDao.addXhsProductStock(xhsProductStock);
			} else {
				xhsIntostockProductDao.delete(xhsIntostockProduct);

				XhsProductStock xhsProductStock = new XhsProductStock();
				xhsProductStock.setProduct(new XhsProductInfo(xhsIntostockProduct.getProductId()));
				xhsProductStock.setWarehouse(new XhsWarehouseTree(xhsIntostockInfo.getWarehouse().getTreeCode()));
				xhsProductStock.setQuantity(
						(xhsIntostockProduct.getQuantity() == null ? 0 : xhsIntostockProduct.getQuantity()) * -1);
				// xhsProductStock.setUpdateBy(xhsIntostockInfo.getUpdateBy());
				xhsProductStock.preInsert();
				xhsProductStockDao.addXhsProductStock(xhsProductStock);
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

}