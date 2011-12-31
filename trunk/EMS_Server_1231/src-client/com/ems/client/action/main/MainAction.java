package com.ems.client.action.main;

import java.util.ArrayList;
import java.util.List;

import com.ems.client.action.main.vo.MenuItemVO;
import com.ems.system.client.DirectAction;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

@ActionScope(scope=Scope.APPLICATION)
public class MainAction extends DirectAction {

	@DirectMethod
	public List<MenuItemVO> getMenu(String id) {
		List<MenuItemVO> result = new ArrayList<MenuItemVO>();

		if ("root".equals(id)) { // StringUtils.isEmpty(id) ||
			result.add(new MenuItemVO("00", "实例", false));
			result.add(new MenuItemVO("01", "学籍管理", false));
			result.add(new MenuItemVO("02", "师资管理", false));
			result.add(new MenuItemVO("03", "教室管理", false));
			result.add(new MenuItemVO("04", "教材管理", false));
			result.add(new MenuItemVO("05", "网上报名", false));
		} else {
			if ("00".equals(id)) {
				result.add(new MenuItemVO("0001", "测试节点1", false));
				result.add(new MenuItemVO("0002", "实例-表格", "ems.biz.samples.xgridsample.XGridSample", "XGrid实例"));
				result.add(new MenuItemVO("0003", "实例-表单", "ems.biz.samples.formsample.FormSample", "Form实例"));
				result.add(new MenuItemVO("0004", "实例-对话框", "ems.biz.samples.dialogsample.DialogSample", "Dialog实例"));
				result.add(new MenuItemVO("0005", "CRUD实例", "ems.biz.samples.crud.User", "CRUD实例"));
			}
			if ("0001".equals(id)) {
				result.add(new MenuItemVO("000101", "测试节点1.1", "ems.biz.samples.dialogsample.DialogSample", "测试节点1.1Title"));
				result.add(new MenuItemVO("000102", "测试节点1.2", "ems.biz.samples.dialogsample.DialogSample", "测试节点1.2Title"));
			}
			if ("05".equals(id)) {
				result.add(new MenuItemVO("0501", "网上报名", "ems.biz.applyonline.apply.Apply", "网上报名"));
				result.add(new MenuItemVO("0502", "查看报名信息", "ems.biz.applyonline.query.Query", "查看报名信息"));
			}
		}
		return result;
	}
}