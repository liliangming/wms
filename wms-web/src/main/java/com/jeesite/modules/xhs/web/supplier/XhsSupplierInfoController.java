/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.web.supplier;

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
import com.jeesite.modules.xhs.entity.supplier.XhsSupplierInfo;
import com.jeesite.modules.xhs.service.supplier.XhsSupplierInfoService;

/**
 * 供应商信息Controller
 * @author liliangming
 * @version 2018-12-14
 */
@Controller
@RequestMapping(value = "${adminPath}/xhs/supplier/xhsSupplierInfo")
public class XhsSupplierInfoController extends BaseController {

	@Autowired
	private XhsSupplierInfoService xhsSupplierInfoService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public XhsSupplierInfo get(String id, boolean isNewRecord) {
		return xhsSupplierInfoService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("xhs:supplier:xhsSupplierInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(XhsSupplierInfo xhsSupplierInfo, Model model) {
		model.addAttribute("xhsSupplierInfo", xhsSupplierInfo);
		return "modules/xhs/supplier/xhsSupplierInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("xhs:supplier:xhsSupplierInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<XhsSupplierInfo> listData(XhsSupplierInfo xhsSupplierInfo, HttpServletRequest request, HttpServletResponse response) {
		xhsSupplierInfo.setPage(new Page<>(request, response));
		Page<XhsSupplierInfo> page = xhsSupplierInfoService.findPage(xhsSupplierInfo); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("xhs:supplier:xhsSupplierInfo:view")
	@RequestMapping(value = "form")
	public String form(XhsSupplierInfo xhsSupplierInfo, Model model) {
		model.addAttribute("xhsSupplierInfo", xhsSupplierInfo);
		return "modules/xhs/supplier/xhsSupplierInfoForm";
	}

	/**
	 * 保存供应商信息
	 */
	@RequiresPermissions("xhs:supplier:xhsSupplierInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated XhsSupplierInfo xhsSupplierInfo) {
		xhsSupplierInfoService.save(xhsSupplierInfo);
		return renderResult(Global.TRUE, text("保存供应商信息成功！"));
	}
	
	/**
	 * 删除供应商信息
	 */
	@RequiresPermissions("xhs:supplier:xhsSupplierInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(XhsSupplierInfo xhsSupplierInfo) {
		xhsSupplierInfoService.delete(xhsSupplierInfo);
		return renderResult(Global.TRUE, text("删除供应商信息成功！"));
	}
	
	/**
	 * 选择供应商对话框
	 */
	@RequestMapping(value = "supplierSelect")
	public String productSelect(XhsSupplierInfo xhsSupplierInfo, String selectData, Model model) {
		String selectDataJson = EncodeUtils.decodeUrl(selectData);
		if (JsonMapper.fromJson(selectDataJson, Map.class) != null) {
			model.addAttribute("selectData", selectDataJson);
		}
		model.addAttribute("supplier", xhsSupplierInfo);
		return "modules/xhs/supplier/supplierSelect";
	}
}