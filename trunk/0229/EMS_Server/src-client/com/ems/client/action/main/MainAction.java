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
			result.add(new MenuItemVO("05", "打印证明", false));
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
				result.add(new MenuItemVO("0501", "在学证明", "ems.biz.certificate.studentship.Studentship", "在学证明"));
				result.add(new MenuItemVO("0502", "成绩证明", "ems.biz.certificate.transcript.Transcript", "成绩证明"));
				result.add(new MenuItemVO("0503", "预毕业证明", "ems.biz.certificate.freshgraduate.FreshGraduate", "预毕业证明"));
				result.add(new MenuItemVO("0504", "毕业证明", "ems.biz.certificate.graduate.Graduate", "毕业证明"));
				result.add(new MenuItemVO("0505", "就读证明", "ems.biz.certificate.study.Study", "就读证明"));
			}
		}
		
//		if ("root".equals(id)) { // StringUtils.isEmpty(id) ||
//			result.add(new MenuItemVO("00", "我的", false));
//			result.add(new MenuItemVO("01", "人员管理", false));
//			result.add(new MenuItemVO("02", "基础信息管理", false));
//			result.add(new MenuItemVO("03", "教务管理", false));
////			result.add(new MenuItemVO("04", "打印证明", false));
//		} else {
//			if ("00".equals(id)) {
//				result.add(new MenuItemVO("0001", "我的资料", true));
//				result.add(new MenuItemVO("0002", "我的课表", true));
//				result.add(new MenuItemVO("0003", "我的考勤", true));
//				result.add(new MenuItemVO("0004", "我的成绩", true));
//			} 
//			else if ("01".equals(id)) {
//				result.add(new MenuItemVO("0101", "管理员", true));
//				result.add(new MenuItemVO("0102", "教务", true));
//				result.add(new MenuItemVO("0103", "财务", true));
//				result.add(new MenuItemVO("0104", "招生", true));
//				result.add(new MenuItemVO("0105", "教师", true));
//				result.add(new MenuItemVO("0106", "辅导员", true));
//				result.add(new MenuItemVO("0107", "学生", true));
//			} 
//			else if ("02".equals(id)) {
//				result.add(new MenuItemVO("0201", "年级管理", true));
//				result.add(new MenuItemVO("0202", "学期管理", true));
//				result.add(new MenuItemVO("0203", "班级管理", true));
//				result.add(new MenuItemVO("0204", "专业管理", true));
//				result.add(new MenuItemVO("0205", "课程管理", true));
//				result.add(new MenuItemVO("0206", "教室管理", true));
//				result.add(new MenuItemVO("0207", "教材管理", true));
//			}
//			else if ("03".equals(id)) {
//				result.add(new MenuItemVO("0301", "报名管理", true));
//				result.add(new MenuItemVO("0302", "考勤管理", true));
//				result.add(new MenuItemVO("0303", "成绩管理", false));
//				result.add(new MenuItemVO("0304", "排课管理", false));
//				result.add(new MenuItemVO("0305", "证明管理", false));
//			}
//			else if ("0303".equals(id)) {
//				result.add(new MenuItemVO("030301", "选修课成绩", true));
//				result.add(new MenuItemVO("030302", "必修课成绩", true));
//			}
//			else if ("0304".equals(id)) {
//				result.add(new MenuItemVO("030401", "排课计划", true));
//			}
//			else if ("0305".equals(id)) {
//				result.add(new MenuItemVO("030501", "成绩证明", true));
//				result.add(new MenuItemVO("030502", "在学证明", true));
//				result.add(new MenuItemVO("030503", "预毕业证明", true));
//				result.add(new MenuItemVO("030504", "毕业证明", true));
//				result.add(new MenuItemVO("030505", "就读证明", true));
//			}
//		}
		return result;
	}
}