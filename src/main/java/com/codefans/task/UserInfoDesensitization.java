package com.codefans.task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.codefans.java.regex.RegexForString;

public class UserInfoDesensitization {

	public static void main(String[] args) {
		UserInfoDesensitization uid = new UserInfoDesensitization();
		uid.desensitiveFile();
	}
	
	public void desensitiveFile() {
		String filePath = "D:/tmp/sensitiveUserInfos.txt";
		InputStream is = null;
		try {
			is = new FileInputStream(filePath);
			Scanner sc = new Scanner(is);
			String line = "";
//			String patternString = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
//			String patternString = "^[1][3,4,5,7,8][0-9]{9}$";
//			String patternString = "^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[6-8])|(18[0-9]))\\d{8}$";
			String patternString = "([^0-9]|[ \\t\\n\\x0B\\f\\r]|^)((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[6-8])|(18[0-9]))\\d{8}([^0-9]|[ \\t\\n\\x0B\\f\\r]|$)";
//			String patternString = "1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}";
			Pattern p = Pattern.compile(patternString);          
			Matcher m = null;
			
//			line = "15010198727";
//			m=p.matcher(line); 
//			while(m.find()){  
//		    	System.out.println(m.group());           
//		    }
			RegexForString rfs = new RegexForString();
			
			while(sc.hasNextLine()) {
				line = sc.nextLine();
				System.out.println(replaceString(patternString, line, "用户电话"));
//				m=p.matcher(line); 
//				while(m.find()){  
//			    	System.out.println(m.group());           
//			    } 
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	private String replaceString(String regex, String source, String replaceString){
		StringBuffer sb = new StringBuffer();
		Matcher m=Pattern.compile(regex).matcher(source);
		String groupStr = "";
		String prefix = "";
		String suffix = "";
		while(m.find()) {
			groupStr = m.group();
			if(groupStr.length() == 11) {
				prefix = "";
				suffix = "";
			} else {
				prefix = groupStr.substring(0, 1);
				suffix = groupStr.substring(groupStr.length() - 1, groupStr.length());
			}
			m.appendReplacement(sb, prefix + replaceString + suffix);
		}
		m.appendTail(sb); 
		return sb.toString();
	}
	
}
