/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.web.address;

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
import com.jeesite.modules.xhs.entity.address.XhsReceivingAddress;
import com.jeesite.modules.xhs.service.address.XhsReceivingAddressService;

/**
 * 收货地址信息Controller
 * @author liliangming
 * @version 2018-12-18
 */
@Controller
@RequestMapping(value = "${adminPath}/xhs/address/xhsReceivingAddress")
public class XhsReceivingAddressController extends BaseController {

	@Autowired
	private XhsReceivingAddressService xhsReceivingAddressService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public XhsReceivingAddress get(String id, boolean isNewRecord) {
		return xhsReceivingAddressService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("xhs:address:xhsReceivingAddress:view")
	@RequestMapping(value = {"list", ""})
	public String list(XhsReceivingAddress xhsReceivingAddress, Model model) {
		model.addAttribute("xhsReceivingAddress", xhsReceivingAddress);
		return "modules/xhs/address/xhsReceivingAddressList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("xhs:address:xhsReceivingAddress:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<XhsReceivingAddress> listData(XhsReceivingAddress xhsReceivingAddress, HttpServletRequest request, HttpServletResponse response) {
		xhsReceivingAddress.setPage(new Page<>(request, response));
		Page<XhsReceivingAddress> page = xhsReceivingAddressService.findPage(xhsReceivingAddress); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("xhs:address:xhsReceivingAddress:view")
	@RequestMapping(value = "form")
	public String form(XhsReceivingAddress xhsReceivingAddress, Model model) {
		model.addAttribute("xhsReceivingAddress", xhsReceivingAddress);
		return "modules/xhs/address/xhsReceivingAddressForm";
	}

	/**
	 * 保存收货地址信息
	 */
	@RequiresPermissions("xhs:address:xhsReceivingAddress:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated XhsReceivingAddress xhsReceivingAddress) {
		xhsReceivingAddressService.save(xhsReceivingAddress);
		return renderResult(Global.TRUE, text("保存收货地址信息成功！"));
	}
	
	/**
	 * 删除收货地址信息
	 */
	@RequiresPermissions("xhs:address:xhsReceivingAddress:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(XhsReceivingAddress xhsReceivingAddress) {
		xhsReceivingAddressService.delete(xhsReceivingAddress);
		return renderResult(Global.TRUE, text("删除收货地址信息成功！"));
	}
	
}