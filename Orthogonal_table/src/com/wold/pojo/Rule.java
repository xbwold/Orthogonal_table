package com.wold.pojo;

import java.util.List;

public class Rule implements  Comparable<Rule>{
	private String name;
	private List<String> list;
	private int n;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
		n = list.size();
	}

	@Override
	public String toString() {
		return "Rule [name=" + name + ", list=" + list + ", n=" + n + "]";
	}

	public int getN() {
		return n;
	}

	@Override
	public int compareTo(Rule o) {
		return this.list.size()-o.list.size();
	}
}
