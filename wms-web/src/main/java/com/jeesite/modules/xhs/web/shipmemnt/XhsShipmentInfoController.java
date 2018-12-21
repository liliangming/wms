/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.web.shipmemnt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.xhs.entity.shipmemnt.XhsShipmentInfo;
import com.jeesite.modules.xhs.service.shipmemnt.XhsShipmentInfoService;

/**
 * 出货信息Controller
 * @author liliangming
 * @version 2018-12-18
 */
@Controller
@RequestMapping(value = "${adminPath}/xhs/shipmemnt/xhsShipmentInfo")
public class XhsShipmentInfoController extends BaseController {

	@Autowired
	private XhsShipmentInfoService xhsShipmentInfoService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public XhsShipmentInfo get(String id, boolean isNewRecord) {
		return xhsShipmentInfoService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("xhs:shipmemnt:xhsShipmentInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(XhsShipmentInfo xhsShipmentInfo, Model model) {
		model.addAttribute("xhsShipmentInfo", xhsShipmentInfo);
		return "modules/xhs/shipmemnt/xhsShipmentInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("xhs:shipmemnt:xhsShipmentInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<XhsShipmentInfo> listData(XhsShipmentInfo xhsShipmentInfo, HttpServletRequest request, HttpServletResponse response) {
		xhsShipmentInfo.setPage(new Page<>(request, response));
		Page<XhsShipmentInfo> page = xhsShipmentInfoService.findPage(xhsShipmentInfo); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("xhs:shipmemnt:xhsShipmentInfo:view")
	@RequestMapping(value = "form")
	public String form(XhsShipmentInfo xhsShipmentInfo, Model model) {
		model.addAttribute("xhsShipmentInfo", xhsShipmentInfo);
		return "modules/xhs/shipmemnt/xhsShipmentInfoForm";
	}

	/**
	 * 保存出货信息
	 */
	@RequiresPermissions("xhs:shipmemnt:xhsShipmentInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated XhsShipmentInfo xhsShipmentInfo) {
//		if(xhsShipmentInfo.getWarehousePosition() != null && xhsShipmentInfo.getWarehousePosition().getId() != null)
//		{
//			String[] splits = xhsShipmentInfo.getWarehousePosition().getId().split(",");
//			if( splits.length > 0 )
//			{
//				XhsWarehouseInfo warehouseInfo = new XhsWarehouseInfo();
//				warehouseInfo.setId(splits[0]);
//				xhsShipmentInfo.setWarehouseInfo(warehouseInfo);
//			}
//			
//			if( splits.length > 1 )
//			{
//				xhsShipmentInfo.getWarehousePosition().setId(splits[1]);
//			}
//		}
		
		xhsShipmentInfoService.save(xhsShipmentInfo);
		return renderResult(Global.TRUE, text("保存出货信息成功！"));
	}
	
	/**
	 * 删除出货信息
	 */
	@RequiresPermissions("xhs:shipmemnt:xhsShipmentInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(XhsShipmentInfo xhsShipmentInfo) {
		xhsShipmentInfoService.delete(xhsShipmentInfo);
		return renderResult(Global.TRUE, text("删除出货信息成功！"));
	}
	
}