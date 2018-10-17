package com.test.regex;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class RegexTest {

	public static void main(String[] args) {
		RegexTest regexTest = new RegexTest();
//		regexTest.testNumeric();
		regexTest.stringReplaceTest();
	}

	/**
	 * 字符串查找，匹配，替换，正则无不能做，特别是灵活的运用子串匹配得到的变量值$1,$2，再进行二次处理能够达到很巧妙的效果。
	 * 
	[...] 位于括号之内的任意字符
	[^...] 不在括号之中的任意字符
	. 除了换行符之外的任意字符,等价于[^\n]
	\w 任何单字字符, 等价于[a-zA-Z0-9]
	\W 任何非单字字符,等价于[^a-zA-Z0-9]
	\s 任何空白符,等价于[\ t \ n \ r \ f \ v]
	\S 任何非空白符,等价于[^\ t \ n \ r \ f \ v]
	\d 任何数字,等价于[0-9]
	\D 除了数字之外的任何字符,等价于[^0-9]
	[\b] 一个退格直接量(特例)
	 
	{n, m} 匹配前一项至少n次,但是不能超过m次
	{n, } 匹配前一项n次,或者多次
	{n} 匹配前一项恰好n次
	? 匹配前一项0次或1次,也就是说前一项是可选的. 等价于 {0, 1}
	+ 匹配前一项1次或多次,等价于{1,}
	* 匹配前一项0次或多次.等价于{0,}
	 
	| 选择.匹配的要么是该符号左边的子表达式,要么它右边的子表达式
	(...) 分组.将几个项目分为一个单元.这个单元可由 *、+、？和|等符号使用,而且还可以记住和这个组匹配的字符以供此后引用使用
	\n 和第n个分组所匹配的字符相匹配.分组是括号中的子表达式(可能是嵌套的).分组号是从左到右计数的左括号数
	 
	^ 匹配的是字符的开头,在多行检索中,匹配的是一行的开头
	$ 匹配的是字符的结尾,在多行检索中,匹配的是一行的结尾
	\b 匹配的是一个词语的边界.简而言之就是位于字符\w 和 \w之间的位置(注意:[\b]匹配的是退格符)
	\B 匹配的是非词语的边界的字符
	
	参考：http://www.jb51.net/article/34154.htm
	
	 */
	public void stringReplaceTest() {
		//被替换关键字的的数据源 
	    Map<String,String> tokens = new HashMap<String,String>(); 
	    tokens.put("cat", "Garfield"); 
	    tokens.put("beverage", "coffee"); 

	    //匹配类似velocity规则的字符串 
	    String template = "${cat} really needs some ${beverage}."; 
	    //生成匹配模式的正则表达式 
	    String patternString = "\\$\\{(" + StringUtils.join(tokens.keySet(), "|") + ")\\}"; 

	    Pattern pattern = Pattern.compile(patternString); 
	    Matcher matcher = pattern.matcher(template); 

	    //两个方法：appendReplacement, appendTail 
	    StringBuffer sb = new StringBuffer(); 
	    while(matcher.find()) { 
	        matcher.appendReplacement(sb, tokens.get(matcher.group(1))); 
	    } 
	    matcher.appendTail(sb); 

	    //out: Garfield really needs some coffee. 
	    System.out.println(sb.toString()); 

	    //对于特殊含义字符"\","$"，使用Matcher.quoteReplacement消除特殊意义 
	    matcher.reset(); 
	    //out: cat really needs some beverage. 
	    System.out.println(matcher.replaceAll("$1")); 
	    //out: $1 really needs some $1. 
	    System.out.println(matcher.replaceAll(Matcher.quoteReplacement("$1"))); 

	    //到得邮箱的前缀名。插一句，其实验证邮箱的正则多种多样，根据自己的需求写对应的正则才是王道 
	    String emailPattern = "^([a-z0-9_\\.\\-\\+]+)@([\\da-z\\.\\-]+)\\.([a-z\\.]{2,6})$"; 
	    pattern = Pattern.compile(emailPattern); 
	    matcher = pattern.matcher("test@qq.com"); 
	    //验证是否邮箱 
	    System.out.println(matcher.find()); 
	    //得到@符号前的邮箱名  out: test 
	    System.out.println(matcher.replaceAll("$1")); 

	    //获得匹配值 
	    String temp = "<meta-data android:name=\"appid\" android:value=\"joy\"></meta-data>"; 
	    pattern = Pattern.compile("android:(name|value)=\"(.+?)\""); 
	    matcher = pattern.matcher(temp); 
	    while(matcher.find()) { 
	        //out: appid, joy 
	        System.out.println(matcher.group(2)); 
	    } 
	}
	
	public static void test() {
		String pattern = "\\d{4}-\\d{2}";
		String source = "2012-19";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(source);
		while (m.find()) {
			p(m.group());
		}

		p(null + "_" + null);
	}

	public void testNumeric() {
		p(isNumeric("-988"));
		p(isNumeric("988"));
		p(isNumeric(""));
		p(isNumeric(null));
	}
	
	public static void p(Object o) {
		System.out.println(o);
	}

	protected static boolean isNumeric(String data) {
		// Pattern pattern = Pattern.compile("[0-9]*");
		Pattern pattern = Pattern.compile("\\d*");
		Matcher isNum = pattern.matcher(data);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

}
