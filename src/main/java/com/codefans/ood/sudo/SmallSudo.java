package com.codefans.ood.sudo;

import java.util.List;

public class SmallSudo {
//	private List<Integer> datas = new ArrayList<Integer>();
	private List<Integer> datas;
	private SmallSudoRule smallSudoRule;
	
	public boolean checkRule() {
		return smallSudoRule.match(datas);
	}
	
}
