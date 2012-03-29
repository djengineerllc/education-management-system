package com.ems.client.action.main;

import java.util.ArrayList;
import java.util.List;

import com.ems.client.action.login.LoginAction;
import com.ems.common.model.vo.LoginInfoVO;
import com.ems.system.acl.bs.IUserBS;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.MenuItemVO;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

@ActionScope(scope=Scope.APPLICATION)
public class MainAction extends DirectAction {
	
	private IUserBS userBS = this.getBean("userBS", IUserBS.class);

	@DirectMethod
	public List<MenuItemVO> getMenu(Integer menuId) {
		LoginInfoVO loginInfoVO = this.getAction(LoginAction.class).getLoginInfo();
		
		List<MenuItemVO> menu = userBS.findMenuList(loginInfoVO.getUserId(), menuId);
		
		return menu;
//		loginInfoVO.getUserId()

//		if ("root".equals(id)) { // StringUtils.isEmpty(id) ||
//			result.add(new MenuItemVO("00", "实例", false));
//			//result.add(new MenuItemVO("01", "学籍管理", false));
//			//result.add(new MenuItemVO("02", "师资管理", false));
//			//result.add(new MenuItemVO("03", "教室管理", false));
//			//result.add(new MenuItemVO("04", "教材管理", false));
//			result.add(new MenuItemVO("03", "排课管理", false));
//			result.add(new MenuItemVO("05", "基础信息管理", false));
//			result.add(new MenuItemVO("06", "打印证明", false));
//		} else {
//			if ("00".equals(id)) {
//				result.add(new MenuItemVO("0001", "测试节点1", false));
//				result.add(new MenuItemVO("0002", "实例-表格", "ems.biz.samples.xgridsample.XGridSample", "XGrid实例"));
//				result.add(new MenuItemVO("0003", "实例-表单", "ems.biz.samples.formsample.FormSample", "Form实例"));
//				result.add(new MenuItemVO("0004", "实例-对话框", "ems.biz.samples.dialogsample.DialogSample", "Dialog实例"));
//				result.add(new MenuItemVO("0005", "CRUD实例", "ems.biz.samples.crud.User", "CRUD实例"));
//			}
//			if ("0001".equals(id)) {
//				result.add(new MenuItemVO("000101", "测试节点1.1", "ems.biz.samples.dialogsample.DialogSample", "测试节点1.1Title"));
//				result.add(new MenuItemVO("000102", "测试节点1.2", "ems.biz.samples.dialogsample.DialogSample", "测试节点1.2Title"));
//			}
//			if ("03".equals(id)) {
//				result.add(new MenuItemVO("0301", "排课(一览表)", "ems.biz.syllabus.syllabusplan.SyllabusPlan", "排课(一览表)"));
//				result.add(new MenuItemVO("0302", "教师分表", "ems.biz.syllabus.syllabusbyteacher.SyllabusByTeacher", "教师分表"));
//				result.add(new MenuItemVO("0303", "班级分表", "ems.biz.syllabus.syllabusbyclass.SyllabusByClass", "班级分表"));
//				result.add(new MenuItemVO("0304", "课程分表", "ems.biz.syllabus.syllabusbycourse.SyllabusByCourse", "课程分表"));
//			}
//			if ("05".equals(id)) {
//				result.add(new MenuItemVO("0501", "用户管理", "ems.biz.basicInfo.gradeManager.Grade", "用户管理"));
//				result.add(new MenuItemVO("0502", "年级管理", "ems.biz.basicInfo.gradeManager.Grade", "年级管理"));
//				result.add(new MenuItemVO("0503", "学期管理", "ems.biz.basicInfo.termManager.Term", "学期管理"));
//				result.add(new MenuItemVO("0504", "班级管理", "ems.biz.basicInfo.classManager.Class", "班级管理"));
//				result.add(new MenuItemVO("0505", "项目管理", "ems.biz.basicInfo.projectManager.Project", "项目管理"));
//				result.add(new MenuItemVO("0506", "专业管理", "ems.biz.basicInfo.professManager.Profess", "专业管理"));
//				result.add(new MenuItemVO("0507", "课程管理", "ems.biz.basicInfo.subjectManager.Subject", "课程管理"));
//				result.add(new MenuItemVO("0508", "教室管理", "ems.biz.basicInfo.roomManager.Room", "教室管理"));
//				result.add(new MenuItemVO("0509", "教材管理", "ems.biz.basicInfo.bookManager.Book", "教材管理"));
//			}
//			else if ("06".equals(id)) {
//				result.add(new MenuItemVO("0601", "成绩证明", "ems.biz.certificate.transcript.Transcript", "成绩证明"));
//				result.add(new MenuItemVO("0602", "在学证明", "ems.biz.certificate.studentship.Studentship", "在学证明"));
//				result.add(new MenuItemVO("0603", "预毕业证明", "ems.biz.certificate.freshgraduate.FreshGraduate", "预毕业证明"));
//				result.add(new MenuItemVO("0604", "毕业证明", "ems.biz.certificate.graduate.Graduate", "毕业证明"));
//				result.add(new MenuItemVO("0605", "就读证明", "ems.biz.certificate.study.Study", "就读证明"));
//			}
//		}
//		
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
	}
}