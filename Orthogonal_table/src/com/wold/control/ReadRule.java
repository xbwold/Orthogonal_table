package com.wold.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wold.pojo.Rule;

public class ReadRule {
	private String rulestr;

	public String getRulestr() {
		return rulestr;
	}

	private Map<Integer, Integer> map;
	private Boolean special = false; // 是否有超过10的水平
	private List<Rule> listrule;

	public List<Rule> getListrule() {
		return listrule;
	}

	public Boolean getSpecial() {
		return special;
	}

	public Map<Integer, Integer> getMap() {
		return map;
	}

	/**
	 * 分析str串的正则表规则
	 * 
	 * @param str
	 */
	public void analysisStr(String str) {
		String[] a = str.split("\n");
		listrule = new ArrayList<Rule>();
		for (String string : a) {
			String[] b = string.split(":|,");
			int i = 0;
			Rule rule = new Rule();
			List<String> list1 = new ArrayList<String>();
			for (String string2 : b) {
				if (i == 0) {
					rule.setName(string2);
				} else {
					list1.add(string2);
				}
				i++;
			}
			rule.setList(list1);
			listrule.add(rule);
		}
		Collections.sort(listrule);
		map = new HashMap<>();
		for (Rule r : listrule) {
			int key = r.getN();
			if (map.containsKey(key)) {
				map.put(key, map.get(key) + 1);
			} else {
				map.put(key, 1);
			}
		}
		rulestr = "";
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			String string3 = entry.getKey() + "\\^" + entry.getValue();
			rulestr += string3 + " ";
			if (entry.getKey() > 10) {
				special = true;
			}
		}
	}
}
