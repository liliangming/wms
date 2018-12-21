/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.web.product;

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
import com.jeesite.modules.xhs.entity.product.XhsProductInfo;
import com.jeesite.modules.xhs.service.product.XhsProductInfoService;

/**
 * 产品信息Controller
 * 
 * @author liliangming
 * @version 2018-12-19
 */
@Controller
@RequestMapping(value = "${adminPath}/xhs/product/xhsProductInfo")
public class XhsProductInfoController extends BaseController {

	@Autowired
	private XhsProductInfoService xhsProductInfoService;

	/**
	 * 获取数据
	 */
	@ModelAttribute
	public XhsProductInfo get(String id, boolean isNewRecord) {
		return xhsProductInfoService.get(id, isNewRecord);
	}

	/**
	 * 查询列表
	 */
	@RequiresPermissions("xhs:product:xhsProductInfo:view")
	@RequestMapping(value = { "list", "" })
	public String list(XhsProductInfo xhsProductInfo, Model model) {
		model.addAttribute("xhsProductInfo", xhsProductInfo);
		return "modules/xhs/product/xhsProductInfoList";
	}

	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("xhs:product:xhsProductInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<XhsProductInfo> listData(XhsProductInfo xhsProductInfo, HttpServletRequest request,
			HttpServletResponse response) {
		xhsProductInfo.setPage(new Page<>(request, response));
		Page<XhsProductInfo> page = xhsProductInfoService.findPage(xhsProductInfo);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("xhs:product:xhsProductInfo:view")
	@RequestMapping(value = "form")
	public String form(XhsProductInfo xhsProductInfo, Model model) {
		model.addAttribute("xhsProductInfo", xhsProductInfo);
		return "modules/xhs/product/xhsProductInfoForm";
	}

	/**
	 * 保存产品信息
	 */
	@RequiresPermissions("xhs:product:xhsProductInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated XhsProductInfo xhsProductInfo) {
		xhsProductInfoService.save(xhsProductInfo);
		return renderResult(Global.TRUE, text("保存产品信息成功！"));
	}

	/**
	 * 删除产品信息
	 */
	@RequiresPermissions("xhs:product:xhsProductInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(XhsProductInfo xhsProductInfo) {
		xhsProductInfoService.delete(xhsProductInfo);
		return renderResult(Global.TRUE, text("删除产品信息成功！"));
	}

	/**
	 * 选择产品对话框
	 */
	@RequestMapping(value = "productSelect")
	public String productSelect(XhsProductInfo xhsProductInfo, String selectData, Model model) {
		String selectDataJson = EncodeUtils.decodeUrl(selectData);
		if (JsonMapper.fromJson(selectDataJson, Map.class) != null) {
			model.addAttribute("selectData", selectDataJson);
		}
		model.addAttribute("product", xhsProductInfo);
		return "modules/xhs/product/productSelect";
	}

	/**
	 * 根据id获取产品信息
	 */
	@RequestMapping(value = "getProductById")
	public XhsProductInfo productSelect(XhsProductInfo xhsProductInfo) {
		return xhsProductInfoService.get(xhsProductInfo.getId());
	}

}