package com.ems.client.action;

import java.util.ArrayList;
import java.util.List;

import com.softwarementors.extjs.djn.config.annotations.DirectAction;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

@DirectAction(action="MainAction")
public class MainAction {
	public static class Node {
		public String id;
		public String text;
		public boolean leaf;

		public String textKey;
		public String moduleId;
		public String moduleName;
		public String moduleNameKey;
		public String moduleConfig;

		public Node() {
			super();
		}

		public Node(String id, String text, boolean leaf) {
			super();
			this.id = id;
			this.text = text;
			this.leaf = leaf;
		}

		public Node(String id, String text, boolean leaf, String textKey,
				String moduleId, String moduleName, String moduleNameKey,
				String moduleConfig) {
			super();
			this.id = id;
			this.text = text;
			this.leaf = leaf;
			this.textKey = textKey;
			this.moduleId = moduleId;
			this.moduleName = moduleName;
			this.moduleNameKey = moduleNameKey;
			this.moduleConfig = moduleConfig;
		}
	}

	@DirectMethod
	public List<Node> getMenu(String id) {
		List<Node> result = new ArrayList<Node>();

		if ("root".equals(id)) { // StringUtils.isEmpty(id) ||
			result.add(new Node("01", "学籍管理", false));
			result.add(new Node("02", "师资管理", false));
			result.add(new Node("03", "教室管理", false));
			result.add(new Node("04", "教材管理", false));
		} else {
			if ("01".equals(id)) {
				result.add(new Node("0101", "学籍管理0101", false));
				result.add(new Node("0102", "学籍管理0102", true, null,
						"ems.biz.test.Test", "学籍管理0102Title", null, null));
				result.add(new Node("0103", "学籍管理0103", true, null,
						"ems.biz.test.Test", "学籍管理0103Title", null, null));
			} else if ("0101".equals(id)) {
				result.add(new Node("010101", "学籍管理010101", true, null,
						"ems.biz.test.Test", "学籍管理010101Title", null, null));
			} else if ("02".equals(id)) {
				result.add(new Node("0201", "师资管理0201", true, null,
						"ems.biz.test.Test", null, null, null));
				result.add(new Node("0202", "师资管理0202", true, null,
						"ems.biz.test.Test", null, null, null));
				result.add(new Node("0203", "师资管理0203", true, null,
						"ems.biz.test.Test", null, null, null));
			}
		}
		return result;
	}
}