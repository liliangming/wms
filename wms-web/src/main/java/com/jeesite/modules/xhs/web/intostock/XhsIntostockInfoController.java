/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.xhs.web.intostock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.internal.util.StringHelper;
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
import com.jeesite.modules.xhs.entity.intostock.XhsIntostockInfo;
import com.jeesite.modules.xhs.exception.MyException;
import com.jeesite.modules.xhs.service.intostock.XhsIntostockInfoService;

/**
 * 入库登记信息Controller
 * 
 * @author liliangming
 * @version 2018-12-23
 */
@Controller
@RequestMapping(value = "${adminPath}/xhs/intostock/xhsIntostockInfo")
public class XhsIntostockInfoController extends BaseController {

	@Autowired
	private XhsIntostockInfoService xhsIntostockInfoService;

	/**
	 * 获取数据
	 */
	@ModelAttribute
	public XhsIntostockInfo get(String id, boolean isNewRecord) {
		return xhsIntostockInfoService.get(id, isNewRecord);
	}

	/**
	 * 查询列表
	 */
	@RequiresPermissions("xhs:intostock:xhsIntostockInfo:view")
	@RequestMapping(value = { "list", "" })
	public String list(XhsIntostockInfo xhsIntostockInfo, Model model) {
		model.addAttribute("xhsIntostockInfo", xhsIntostockInfo);
		return "modules/xhs/intostock/xhsIntostockInfoList";
	}

	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("xhs:intostock:xhsIntostockInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<XhsIntostockInfo> listData(XhsIntostockInfo xhsIntostockInfo, HttpServletRequest request,
			HttpServletResponse response) {
		xhsIntostockInfo.setPage(new Page<>(request, response));
		Page<XhsIntostockInfo> page = xhsIntostockInfoService.findPage(xhsIntostockInfo);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("xhs:intostock:xhsIntostockInfo:view")
	@RequestMapping(value = "form")
	public String form(XhsIntostockInfo xhsIntostockInfo, Model model) {
		model.addAttribute("xhsIntostockInfo", xhsIntostockInfo);
		return xhsIntostockInfo.getIsNewRecord() ? "modules/xhs/intostock/xhsIntostockInfoForm"
				: "modules/xhs/intostock/xhsIntostockInfoQuery";
	}

	/**
	 * 保存入库登记
	 */
	@RequiresPermissions("xhs:intostock:xhsIntostockInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated XhsIntostockInfo xhsIntostockInfo) {
		xhsIntostockInfoService.save(xhsIntostockInfo);
		return renderResult(Global.TRUE, text("保存入库登记成功！"));
	}

	/**
	 * 删除入库登记
	 */
	@RequiresPermissions("xhs:intostock:xhsIntostockInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(XhsIntostockInfo xhsIntostockInfo) {
		xhsIntostockInfoService.delete(xhsIntostockInfo);
		return renderResult(Global.TRUE, text("删除入库登记成功！"));
	}

	/**
	 * 撤销入库登记
	 */
	@RequiresPermissions("xhs:intostock:xhsIntostockInfo:edit")
	@RequestMapping(value = "cancel")
	@ResponseBody
	public String cancel(XhsIntostockInfo xhsIntostockInfo) {
		try {
			xhsIntostockInfoService.cancel(xhsIntostockInfo);
		} catch (MyException e) {
			return renderResult(Global.FALSE,
					text(StringHelper.isNullOrEmptyString(e.getMsg()) ? "发生未知错误！" : e.getMsg()));
		} catch (Exception e) {
			return renderResult(Global.FALSE,
					text(StringHelper.isNullOrEmptyString(e.getMessage()) ? "发生未知错误！" : e.getMessage()));
		}
		return renderResult(Global.TRUE, text("撤销入库登记成功！"));
	}
}