/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.web.customer;

import java.util.Map;

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

import com.jeesite.common.codec.EncodeUtils;
import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.mapper.JsonMapper;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.xhs.entity.customer.XhsCustomerInfo;
import com.jeesite.modules.xhs.entity.product.XhsProductInfo;
import com.jeesite.modules.xhs.service.customer.XhsCustomerInfoService;

/**
 * 客户信息Controller
 * @author liliangming
 * @version 2018-12-14
 */
@Controller
@RequestMapping(value = "${adminPath}/xhs/customer/xhsCustomerInfo")
public class XhsCustomerInfoController extends BaseController {

	@Autowired
	private XhsCustomerInfoService xhsCustomerInfoService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public XhsCustomerInfo get(String id, boolean isNewRecord) {
		return xhsCustomerInfoService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("xhs:customer:xhsCustomerInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(XhsCustomerInfo xhsCustomerInfo, Model model) {
		model.addAttribute("xhsCustomerInfo", xhsCustomerInfo);
		return "modules/xhs/customer/xhsCustomerInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("xhs:customer:xhsCustomerInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<XhsCustomerInfo> listData(XhsCustomerInfo xhsCustomerInfo, HttpServletRequest request, HttpServletResponse response) {
		xhsCustomerInfo.setPage(new Page<>(request, response));
		Page<XhsCustomerInfo> page = xhsCustomerInfoService.findPage(xhsCustomerInfo); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("xhs:customer:xhsCustomerInfo:view")
	@RequestMapping(value = "form")
	public String form(XhsCustomerInfo xhsCustomerInfo, Model model) {
		model.addAttribute("xhsCustomerInfo", xhsCustomerInfo);
		return "modules/xhs/customer/xhsCustomerInfoForm";
	}

	/**
	 * 保存客户信息
	 */
	@RequiresPermissions("xhs:customer:xhsCustomerInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated XhsCustomerInfo xhsCustomerInfo) {
		xhsCustomerInfoService.save(xhsCustomerInfo);
		return renderResult(Global.TRUE, text("保存客户信息成功！"));
	}
	
	/**
	 * 删除客户信息
	 */
	@RequiresPermissions("xhs:customer:xhsCustomerInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(XhsCustomerInfo xhsCustomerInfo) {
		xhsCustomerInfoService.delete(xhsCustomerInfo);
		return renderResult(Global.TRUE, text("删除客户信息成功！"));
	}
	
	/**
	 * 选择客户对话框
	 */
	@RequestMapping(value = "customerSelect")
	public String productSelect(XhsCustomerInfo xhsCustomerInfo, String selectData, Model model) {
		String selectDataJson = EncodeUtils.decodeUrl(selectData);
		if (JsonMapper.fromJson(selectDataJson, Map.class) != null){
			model.addAttribute("selectData", selectDataJson);
		}
		model.addAttribute("customer", xhsCustomerInfo);
		return "modules/xhs/customer/customerSelect";
	}
}