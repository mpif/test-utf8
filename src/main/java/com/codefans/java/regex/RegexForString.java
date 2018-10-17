package com.codefans.java.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 正则表达式字符串处理类
 * @author: caishengzhi
 * @date: 2017年1月10日下午3:21:41
 */
public class RegexForString {

	@Test
	public void test() {
		replaceSpecialChar();
	}
	
	public void replaceSpecialChar() {
		String regex = "\\?";
		String str = "";
//		str = "select * from t_order limit ?,?";
		str = "select * from t_order where id=? and age=? and source=1";
		String[] arr = new String[]{"1","5"};
		String result = replaceSpecialChar(regex, str, arr);
		System.out.println(result);
	}
	
	/**
	 * 用数组arr的元素依次替换str字符串中匹配到的子串
	 * @param regex
	 * @param str
	 * @param arr
	 * @return
	 */
	private String replaceSpecialChar(String regex, String str,Object[] arr){
		int index = 0;
		StringBuffer sb = new StringBuffer();
		Matcher m=Pattern.compile(regex).matcher(str);
		String tmp = "";
		while(m.find()) {
			tmp = String.valueOf(arr[index++]);
			if(isInteger(tmp)) {
				m.appendReplacement(sb, tmp);
			} else {
				m.appendReplacement(sb, "'" + tmp + "'");
			}
		}
		m.appendTail(sb); 
		return sb.toString();
	}
	
	/*
	  * 判断是否为整数 
	  * @param str 传入的字符串 
	  * @return 是整数返回true,否则返回false 
	*/
	public static boolean isInteger(String str) {  
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
	    return pattern.matcher(str).matches();  
	}
	
	protected String replaceString(String regex, String source, String replaceString){
		StringBuffer sb = new StringBuffer();
		Matcher m=Pattern.compile(regex).matcher(source);
		while(m.find()) {
			m.appendReplacement(sb, replaceString);
		}
		m.appendTail(sb); 
		return sb.toString();
	}
	
}
