/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.service.product;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.xhs.entity.product.XhsProductInfo;
import com.jeesite.modules.xhs.dao.product.XhsProductInfoDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 产品信息Service
 * @author liliangming
 * @version 2018-12-19
 */
@Service
@Transactional(readOnly=true)
public class XhsProductInfoService extends CrudService<XhsProductInfoDao, XhsProductInfo> {
	
	/**
	 * 获取单条数据
	 * @param xhsProductInfo
	 * @return
	 */
	@Override
	public XhsProductInfo get(XhsProductInfo xhsProductInfo) {
		return super.get(xhsProductInfo);
	}
	
	/**
	 * 查询分页数据
	 * @param xhsProductInfo 查询条件
	 * @param xhsProductInfo.page 分页对象
	 * @return
	 */
	@Override
	public Page<XhsProductInfo> findPage(XhsProductInfo xhsProductInfo) {
		return super.findPage(xhsProductInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param xhsProductInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(XhsProductInfo xhsProductInfo) {
		super.save(xhsProductInfo);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(xhsProductInfo.getId(), "xhsProductInfo_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(xhsProductInfo.getId(), "xhsProductInfo_file");
	}
	
	/**
	 * 更新状态
	 * @param xhsProductInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(XhsProductInfo xhsProductInfo) {
		super.updateStatus(xhsProductInfo);
	}
	
	/**
	 * 删除数据
	 * @param xhsProductInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(XhsProductInfo xhsProductInfo) {
		super.delete(xhsProductInfo);
	}
	
}