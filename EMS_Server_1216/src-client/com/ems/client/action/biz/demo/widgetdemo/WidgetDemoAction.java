package com.ems.client.action.biz.demo.widgetdemo;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.ems.client.action.biz.demo.widgetdemo.vo.TestQueryInfoVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class WidgetDemoAction {
	
	static class TestGridVO {
		private String userName;
		private String sex;
		private String birthday;
		private String email;
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
	}
	
	@DirectMethod
	public ExtPagingVO getUserInfo(JsonArray params) {
//		ExtGridVO gridVO = BeanUtils.toBeanFromJsonFirst(params, ExtGridVO.class);
		TestQueryInfoVO queryInfoVO = BeanUtils.toBeanFromJsonFirst(params, TestQueryInfoVO.class);
		System.out.println("================================");
		System.out.println(ToStringBuilder.reflectionToString(queryInfoVO));
		
//		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
//		Map<String, String> row1 = new HashMap<String, String>();
//		row1.put("b", "父亲");
//		
//		rows.add(row1);
//		Map<String, String> row2 = new HashMap<String, String>();
//		row2.put("b", "母亲");
//		rows.add(row2);
		
//		return rows;
		
		TestGridVO vo1 = new TestGridVO();
		vo1.userName = "小萌";
		vo1.sex = "1";
		vo1.birthday = "2011-10-10";
		vo1.email = "xx@163.com";
		
		TestGridVO vo2 = new TestGridVO();
		vo2.userName = "小乐";
		vo2.sex = "2";
		vo2.birthday = "2011-11-11";
		vo2.email = "xx@gmail.com";
		
		ExtPagingVO result = new ExtPagingVO();
		result.setId("userName");
		result.addItem(vo1).addItem(vo2);
//		result.setItems(rows);
		result.setTotalCount(35);
		
		return result;
		
//		throw new NullPointerException("空指针异常");
	}
}