package com.wold.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class FileRead {
	private String rulestr;
	private String ruletable;

	public String getRuletable() {
		return ruletable;
	}

	private Boolean have;

	public Boolean getHave() {
		return have;
	}

	public String getRulestr() {
		return rulestr;
	}

	/**
	 * Æ¥ÅäÕý½»±í
	 * 
	 * @throws IOException
	 */
	public void query(String str) throws IOException {
		String regex = ".*" + str.toString() + "\\s+n.*";
		BufferedReader br = new BufferedReader(new FileReader("1.txt"));
		String next = null;
		have = false;
		while ((next = br.readLine()) != null) {
			if (next.contains("n")) {
				if (next.matches(regex)) {
					have = true;
					rulestr = next;
					String[] a=rulestr.split("=");
					int n=Integer.valueOf(a[1]);
					ruletable="";
					while(n>0) {
						next = br.readLine();
						ruletable=ruletable+next;
						if(n>1) {
							ruletable=ruletable+"\n";
						}
						n--;
					}
				}
			}
		}
		br.close();
	}
}
