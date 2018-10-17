package com.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class StringTest {

	public static void main(String[] args) {
		// test01();
		// test02();
		// test03();
		// test04();
		// test05();
		// test06();

		// System.out.println(test07());

		// test08();
		// test09();

		// test010();

		// test011();

		// test012();

		// test013();

		// test014();

//		System.out.println("12".compareToIgnoreCase("12"));
//		System.out.println("12".compareToIgnoreCase("34"));
//		System.out.println("345".compareToIgnoreCase("012"));
//
//		String dir = "D:\\dev\\RB-2.1.x-gx\\web\\messagesolutionTemp\\preAudit";
//		System.out.println(dir.length());

//		String sourceStr = "\"123,\",456";
		String sourceStr = "25454545";
		StringTest st = new StringTest();
		System.out.println(st.specialCharFilter(sourceStr));
		
		String sql = "";
//		sql = "select * from t_order limit ?,?";
//		sql = "select * from t_order where id=? and age=? and source=1";
		sql = "SELECT pcm.id, pc.id finance_category_id, pc.finance_category_code , pc.finance_category_name , pc.parent_id , pc.is_del , pcm.product_category_id, pcm.org_code, pcm.product_category_name , pcm.month, pcm.creator, pcm.create_date FROM fin_product_category_mapping pcm LEFT JOIN fin_product_category pc ON pc.id=pcm.finance_category_id WHERE 1=1 AND pc.is_del=0 and pcm.valid_date_begin >= ? and pcm.valid_date_end <= ? limit ?,?"; 
//		String[] arr = new String[]{"1","5"};
		Object[] arr = new Object[]{"2017-01-01 00:00:00","2017-01-31 23:59:59","0","10"};
		System.out.println(fillStringByArgs("\\?", sql, arr));
		
	}

	 private static String fillStringByArgs(String regex, String str,Object[] arr){
	        int index = 0;
//	        Matcher m=Pattern.compile("\\?").matcher(str);
//	        while(m.find()){
//	            str=str.replace(m.group(),arr[index++]);
//	        }
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
	        str = sb.toString();
	        
	        return str;
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
	
	protected String specialCharFilter(String sourceStr) {
		String[] specialChars = new String[]{"\"", "\'"};
		for(int i = 0; i < specialChars.length; i ++) {
			sourceStr = sourceStr.replaceAll(specialChars[i], "");
		}
		return sourceStr;
	}
	
	public static void test014() {
		String str01 = "00000000DCA740C8C042101AB4B908002B2FE18201000000000000002F4F3D4D455353414745534F4C5554494F4E2F4F553D45584348414E47452041444D494E4953545241544956452047524F5550202846594449424F484632335350444C54292F434E3D524543495049454E54532F434E3D4442454600";
		String str02 = "00000000DCA740C8C042101AB4B908002B2FE18201000000000000002F4F3D4D455353414745534F4C5554494F4E2F4F553D45584348414E47452041444D494E4953545241544956452047524F5550202846594449424F484632335350444C54292F434E3D524543495049454E54532F434E3D4442454600";

		System.out.println(str01.equals(str02));

		String str = "";
		String[] strs = StringUtils.splitByWholeSeparator(str, "_");
		// String[] strs = str.split("_");
		System.out.println(strs.length);
		long position = Long.parseLong(strs[3]);
		System.out.println(position);

	}

	public static void test013() {
		String header = "Content-Type:\r\n" + "Content-Disposition: attachment\r\n" +
				// " filename=MessageSolution_exchange_ews_for_restore.xml\r\n"
				// +
				"	filename=\"=?UTF-8?B?5p+l6K+i55So5oi3LnR4dA==?=\"\r\n" + "Content-Transfer-Encoding: base64\r\n"
				+ "attachment_size: 11465\r\n";

		String fileName = "";

		try {
			Scanner sc = new Scanner(header);
			String line = "";
			while (sc.hasNext()) {
				line = sc.nextLine().trim();
				if (line.startsWith("filename")) {
					fileName = line.substring(line.indexOf("=") + 1);
					System.out.println(fileName);
				}
			}

			if (fileName.indexOf("\"") == 0 && fileName.lastIndexOf("\"") == fileName.length() - 1) {
				fileName = fileName.substring(1, fileName.lastIndexOf("\""));
			}
			fileName = javax.mail.internet.MimeUtility.decodeText(fileName);

			System.out.println(fileName);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void test012() {
		// megre:
		// subject:
		// RE : 会话threadindex值测试
		// conversatioinId:
		// AAQkAGUyOTdkNzdjLTRiMzEtNDZjMy05NjZjLTUwZTFiYTljZjAyMgAQANs+AuvakUIvhoUwE+MO2Gc=
		// threadIndex:
		// Ac6Xxy5P2z4C69qRQi+GhTAT4w7YZwAACLEM
		// threadTopic:
		// 会话threadindex值测试
		// ConversationIndex:
		// Ac6Xxy5P2z4C69qRQi+GhTAT4w7YZwAACLEM
		// ConversationTopic
		// 会话threadindex值测试
		// InternetMessageId:
		// A4F4408D7019B64EBB18E64C77AA3AB007EA0E6E@vmwin208.win2010.com
		// inReplyTo:
		// 2D3F329E85EF9C4EB07062C3BC21AE4507EB37D8@vmwin208.win2010.com
		//
		// subject:
		// 会话threadindex值测试
		// conversatioinId:
		// AAQkAGUyOTdkNzdjLTRiMzEtNDZjMy05NjZjLTUwZTFiYTljZjAyMgAQANs+AuvakUIvhoUwE+MO2Gc=
		// threadIndex:
		//
		// threadTopic:
		//
		// ConversationIndex:
		// Ac6Xxy5P2z4C69qRQi+GhTAT4w7YZw==
		// ConversationTopic
		// 会话threadindex值测试
		// InternetMessageId:
		// 2D3F329E85EF9C4EB07062C3BC21AE4507EB37D8@vmwin208.win2010.com
		// inReplyTo:

		// String conversationId01 =
		// "AAQkAGUyOTdkNzdjLTRiMzEtNDZjMy05NjZjLTUwZTFiYTljZjAyMgAQANs+AuvakUIvhoUwE+MO2Gc=";
		// String conversationId02 =
		// "AAQkAGUyOTdkNzdjLTRiMzEtNDZjMy05NjZjLTUwZTFiYTljZjAyMgAQANs+AuvakUIvhoUwE+MO2Gc=";
		// System.out.println(conversationId01.equals(conversationId02));

		// lindong:
		// subject:
		// 会话threadindex值测试
		// conversatioinId:
		// AAQkADk4M2Y0NDc1LWI0ZDctNDIwNC05NjVjLWE3YjcwNWU0ZDhmNgAQANs+AuvakUIvhoUwE+MO2Gc=
		// threadIndex:
		// Ac6Xxy5P2z4C69qRQi+GhTAT4w7YZw==
		// threadTopic:
		// 会话threadindex值测试
		// ConversationIndex:
		// Ac6Xxy5P2z4C69qRQi+GhTAT4w7YZw==
		// ConversationTopic
		// 会话threadindex值测试
		// InternetMessageId:
		// 2D3F329E85EF9C4EB07062C3BC21AE4507EB37D8@vmwin208.win2010.com
		// inReplyTo:
		//
		//
		// subject:
		// RE : 会话threadindex值测试
		// conversatioinId:
		// AAQkADk4M2Y0NDc1LWI0ZDctNDIwNC05NjVjLWE3YjcwNWU0ZDhmNgAQANs+AuvakUIvhoUwE+MO2Gc=
		// threadIndex:
		//
		// threadTopic:
		//
		// ConversationIndex:
		// Ac6Xxy5P2z4C69qRQi+GhTAT4w7YZwAACLEM
		// ConversationTopic
		// 会话threadindex值测试
		// InternetMessageId:
		// A4F4408D7019B64EBB18E64C77AA3AB007EA0E6E@vmwin208.win2010.com
		// inReplyTo:
		// 2D3F329E85EF9C4EB07062C3BC21AE4507EB37D8@vmwin208.win2010.com
		// References:
		// 2D3F329E85EF9C4EB07062C3BC21AE4507EB37D8@vmwin208.win2010.com

		// String conversationId01 =
		// "AAQkADk4M2Y0NDc1LWI0ZDctNDIwNC05NjVjLWE3YjcwNWU0ZDhmNgAQANs+AuvakUIvhoUwE+MO2Gc=";
		// String conversationId02 =
		// "AAQkADk4M2Y0NDc1LWI0ZDctNDIwNC05NjVjLWE3YjcwNWU0ZDhmNgAQANs+AuvakUIvhoUwE+MO2Gc=";
		// System.out.println(conversationId01.equals(conversationId02));

		// thread index
		String originalThreadIndexMegre = "Ac6Xxy5P2z4C69qRQi+GhTAT4w7YZw==";
		String originalThreadIndexLin = "Ac6Xxy5P2z4C69qRQi+GhTAT4w7YZw==";
		System.out.println(originalThreadIndexMegre.equals(originalThreadIndexLin));

		String reThreadIndexMegre = "Ac6Xxy5P2z4C69qRQi+GhTAT4w7YZwAACLEM";
		String reThreadIndexLin = "Ac6Xxy5P2z4C69qRQi+GhTAT4w7YZwAACLEM";
		System.out.println(reThreadIndexMegre.equals(reThreadIndexLin));

	}

	public static void test011() {

		// 182
		// archived
		// AAMkAGZmZGZiNmY0LWMxM2UtNGE0ZC04NTcxLTY5ZjdmOTI2NWU3OAAuAAAAAAAXUBiCgjsxR7iMfY2/67cjAQDwBFW1BxJhQ6z+aeArOT/xAABLd0eAAAA=
		// AQAAABYAAADwBFW1BxJhQ6z+aeArOT/xAABLd2iC
		//
		// 182
		// archived
		// AAMkAGZmZGZiNmY0LWMxM2UtNGE0ZC04NTcxLTY5ZjdmOTI2NWU3OAAuAAAAAAAXUBiCgjsxR7iMfY2/67cjAQDwBFW1BxJhQ6z+aeArOT/xAABLd0eAAAA=
		// AQAAABYAAADwBFW1BxJhQ6z+aeArOT/xAABLd2iC

		// AAMkAGZmZGZiNmY0LWMxM2UtNGE0ZC04NTcxLTY5ZjdmOTI2NWU3OAAuAAAAAAAXUBiCgjsxR7iMfY2/67cjAQDwBFW1BxJhQ6z+aeArOT/xAABLd1ChAAA=
		// AQAAABYAAADwBFW1BxJhQ6z+aeArOT/xAABLd5k+

		// String ewsFolderid =
		// "AAMkAGZmZGZiNmY0LWMxM2UtNGE0ZC04NTcxLTY5ZjdmOTI2NWU3OAAuAAAAAAAXUBiCgjsxR7iMfY2/67cjAQDwBFW1BxJhQ6z+aeArOT/xAABLd0eAAAA=";
		//// String ewsFolderid_new =
		// "AAMkAGZmZGZiNmY0LWMxM2UtNGE0ZC04NTcxLTY5ZjdmOTI2NWU3OAAuAAAAAAAXUBiCgjsxR7iMfY2/67cjAQDwBFW1BxJhQ6z+aeArOT/xAABLd0eAAAA=";
		// String ewsFolderid_new =
		// "AAMkAGZmZGZiNmY0LWMxM2UtNGE0ZC04NTcxLTY5ZjdmOTI2NWU3OAAuAAAAAAAXUBiCgjsxR7iMfY2/67cjAQDwBFW1BxJhQ6z+aeArOT/xAABLd1ChAAA=";
		// System.out.println(ewsFolderid.equals(ewsFolderid_new));
		// String ewsfolderchangekey =
		// "AQAAABYAAADwBFW1BxJhQ6z+aeArOT/xAABLd2iC";
		// String ewsfolderchangekey_new =
		// "AQAAABYAAADwBFW1BxJhQ6z+aeArOT/xAABLd2iC";
		// System.out.println(ewsfolderchangekey.equals(ewsfolderchangekey_new));

		// reply
		// internetional message id:
		// <![CDATA[<87979A86A9E63C4C9A8CC0FA36060DB302F5A73D@WIN-EJ3SS55RSHU.exchange2010a.com>]]>
		//
		// altValue:
		// cb: 76 lpb:
		// 3C443546363445363934443230313434464235383432444544423231364134423730324143433838304057494E2D454A3353533535525348552E65786368616E676532303130612E636F6D3E
		//
		// forward
		// internetional message id:
		// <![CDATA[<D5F64E694D20144FB5842DEDB216A4B702ACC880@WIN-EJ3SS55RSHU.exchange2010a.com>]]>
		//
		// altValue:
		// cb: 76 lpb:
		// 3C443546363445363934443230313434464235383432444544423231364134423730324143433838304057494E2D454A3353533535525348552E65786368616E676532303130612E636F6D3E

		// String messageId01 =
		// "<![CDATA[<87979A86A9E63C4C9A8CC0FA36060DB302F5A73D@WIN-EJ3SS55RSHU.exchange2010a.com>]]>";
		// String messageId02 =
		// "<![CDATA[<D5F64E694D20144FB5842DEDB216A4B702ACC880@WIN-EJ3SS55RSHU.exchange2010a.com>]]>";
		// System.out.println(messageId01.equals(messageId02));
		//
		// String altValue01 = "cb: 76 lpb:
		// 3C443546363445363934443230313434464235383432444544423231364134423730324143433838304057494E2D454A3353533535525348552E65786368616E676532303130612E636F6D3E";
		// String altValue02 = "cb: 76 lpb:
		// 3C443546363445363934443230313434464235383432444544423231364134423730324143433838304057494E2D454A3353533535525348552E65786368616E676532303130612E636F6D3E";
		// System.out.println(altValue01.equals(altValue02));
		//
		// String altValue03 = "cb: 76 lpb:
		// 3C383739373941383641394536334334433941384343304641333630363044423330324635413736344057494E2D454A3353533535525348552E65786368616E676532303130612E636F6D3E";
		// String altValue04 = "cb: 76 lpb:
		// 3C383739373941383641394536334334433941384343304641333630363044423330324635413734464057494E2D454A3353533535525348552E65786368616E676532303130612E636F6D3E";
		//
		// System.out.println(altValue01.equals(altValue03));
		// System.out.println(altValue02.equals(altValue04));
		// System.out.println(altValue03.equals(altValue04));

		// --subject:
		// 邮件以及回复邮件 threadindex值是多少
		// --ConversationTopic:
		//
		// --ConversationIndex:
		//
		// --conversationId:
		// AAQkAGQwZjhmNWUxLWQxZGEtNDg3Mi1hZTA2LWIyNGRlMjljN2Y3MAAQAJ+ptt7dEUaitMmYdhuhB/M=
		// --theadIndex:
		// Ac6Hjphan6m23t0RRqK0yZh2G6EH8w==
		// --internetMessageId:
		// <0D58726B4061364BAAEB8AFAEAD3F54CF3A848A2@WIN-TSJZUR65S8T.new2010.local>
		// --inReplyTo
		//
		//
		// --subject:
		// RE: 邮件以及回复邮件 threadindex值是多少
		// --ConversationTopic:
		// 邮件以及回复邮件 threadindex值是多少
		// --ConversationIndex:
		// [1, -50, -121, -114, -104, 90, -97, -87, -74, -34, -35, 17, 70, -94,
		// -76, -55, -104, 118, 27, -95, 7, -13, 0, 0, 2, -36, -124]
		// --conversationId:
		// AAQkAGQwZjhmNWUxLWQxZGEtNDg3Mi1hZTA2LWIyNGRlMjljN2Y3MAAQAJ+ptt7dEUaitMmYdhuhB/M=
		// --theadIndex:
		//
		// --internetMessageId:
		// <0561C1E708B61B4FA3BC5C0FCE7E82BE1148CC54@WIN-TSJZUR65S8T.new2010.local>
		// --inReplyTo
		// <0D58726B4061364BAAEB8AFAEAD3F54CF3A848A2@WIN-TSJZUR65S8T.new2010.local>
		//
		//
		// --subject:
		// FW: 邮件以及回复邮件 threadindex值是多少
		// --ConversationTopic:
		// 邮件以及回复邮件 threadindex值是多少
		// --ConversationIndex:
		// [1, -50, -121, -114, -104, 90, -97, -87, -74, -34, -35, 17, 70, -94,
		// -76, -55, -104, 118, 27, -95, 7, -13, 0, 0, 6, -94, -42]
		// --conversationId:
		// AAQkAGQwZjhmNWUxLWQxZGEtNDg3Mi1hZTA2LWIyNGRlMjljN2Y3MAAQAJ+ptt7dEUaitMmYdhuhB/M=
		// --theadIndex:
		//
		// --internetMessageId:
		// <0561C1E708B61B4FA3BC5C0FCE7E82BE1148E45F@WIN-TSJZUR65S8T.new2010.local>
		// --inReplyTo
		// <0D58726B4061364BAAEB8AFAEAD3F54CF3A848A2@WIN-TSJZUR65S8T.new2010.local>

		String conversationId01 = "AAQkAGQwZjhmNWUxLWQxZGEtNDg3Mi1hZTA2LWIyNGRlMjljN2Y3MAAQAJ+ptt7dEUaitMmYdhuhB/M=";
		String conversationId02 = "AAQkAGQwZjhmNWUxLWQxZGEtNDg3Mi1hZTA2LWIyNGRlMjljN2Y3MAAQAJ+ptt7dEUaitMmYdhuhB/M=";
		String conversationId03 = "AAQkAGQwZjhmNWUxLWQxZGEtNDg3Mi1hZTA2LWIyNGRlMjljN2Y3MAAQAJ+ptt7dEUaitMmYdhuhB/M=";
		// System.out.println(conversationId01.equals(conversationId02) &&
		// conversationId01.equals(conversationId03) &&
		// conversationId02.equals(conversationId03));

		byte[] cIndexByte01 = new byte[] {};
		byte[] cIndexByte02 = new byte[] { 1, -50, -121, -114, -104, 90, -97, -87, -74, -34, -35, 17, 70, -94, -76, -55,
				-104, 118, 27, -95, 7, -13, 0, 0, 2, -36, -124 };
		byte[] cIndexByte03 = new byte[] { 1, -50, -121, -114, -104, 90, -97, -87, -74, -34, -35, 17, 70, -94, -76, -55,
				-104, 118, 27, -95, 7, -13, 0, 0, 6, -94, -42 };

		BASE64Encoder encoder = new BASE64Encoder();
		// String str01 = encoder.encode(cIndexByte01);
		String str02 = encoder.encode(cIndexByte02);
		String str03 = encoder.encode(cIndexByte03);

		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] bytes = decoder.decodeBuffer(str02);
			System.out.println(bytes);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// System.out.println(str01);
		System.out.println(str02);
		System.out.println(str03);

		String cid = "AAQkAGQwZjhmNWUxLWQxZGEtNDg3Mi1hZTA2LWIyNGRlMjljN2Y3MAAQAJ+ptt7dEUaitMmYdhuhB/M=";
		String cid02 = "AAQkAGQwZjhmNWUxLWQxZGEtNDg3Mi1hZTA2LWIyNGRlMjljN2Y3MAAQAJ+ptt7dEUaitMmYdhuhB/M=";
		// System.out.println(cid.equals(cid02));

		String str = "archive/20130724/0_1_20130724_147301/7dd01ea43c313b6a53a76ada0368b2b5";
		str = str.substring(0, str.lastIndexOf("/"));
		str = str.substring(str.lastIndexOf("/") + 1);
		System.out.println(str);

		System.out.println("0xe07".equalsIgnoreCase("0xe07"));

		// String s = "=?UTF-8?B?562U5aSNOiDlhbPkuo7pgq7ku7bkvJo=?=";
		String s = "超长字符串会被截断超长字符串会被";
		// String s = "=?UTF-8?B?562U5aSNOiDlhbPkuo7pgq7ku7bkvJo=?=
		// =?UTF-8?B?6K+d55qE56ys5Zub5bCB5rWL6K+V6YKu5Lu2?=";
		try {
			System.out.println(javax.mail.internet.MimeUtility.encodeText(s, "UTF-8", "B"));
			// System.out.println(javax.mail.internet.MimeUtility.decodeText(s));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	public static void test010() {
		String stub = "0000000038A1BB1005E5101AA1BB08002B2A56C20000454D534D44422E444C4C00000000000000001B55FA20AA6611CD9BC800AA002FC45A0C00000057494E2D54534A5A55523635533854002F6F3D4669727374204F7267616E697A6174696F6E2F6F753D45786368616E67652041646D696E6973747261746976652047726F7570202846594449424F484632335350444C54292F636E3D526563697069656E74732F636E3D662C6600";
		String rest = "0000000038A1BB1005E5101AA1BB08002B2A56C20000454D534D44422E444C4C00000000000000001B55FA20AA6611CD9BC800AA002FC45A0C00000057494E2D54534A5A55523635533854002F6F3D4669727374204F7267616E697A6174696F6E2F6F753D45786368616E67652041646D696E6973747261746976652047726F7570202846594449424F484632335350444C54292F636E3D526563697069656E74732F636E3D662C6600";
		System.out.println(stub.equals(rest));

		String ewsfolderid = "AAMkADgyYWE2NTYxLTM5MTAtNGIxMS1iOTFjLWEyYThhMWNhYzViYQAuAAAAAABeQpYosyiXTZN+HZFGQY8FAQBwoWLwOeCwRbOMY4QJZgK1AAAUaiQqAAA=";
		String ewsfolderchangekey = "AQAAABYAAABwoWLwOeCwRbOMY4QJZgK1AAAUaiQs";

		String ewsfolderid_Draft = "AAMkADgyYWE2NTYxLTM5MTAtNGIxMS1iOTFjLWEyYThhMWNhYzViYQAuAAAAAABeQpYosyiXTZN+HZFGQY8FAQBwoWLwOeCwRbOMY4QJZgK1AAAUaiQqAAA=";
		String ewsfolderchangekey_Draft = "AQAAABYAAABwoWLwOeCwRbOMY4QJZgK1AAAUaiQv";

		String ewsfolderid_aaab = "AAMkADgyYWE2NTYxLTM5MTAtNGIxMS1iOTFjLWEyYThhMWNhYzViYQAuAAAAAABeQpYosyiXTZN+HZFGQY8FAQBwoWLwOeCwRbOMY4QJZgK1AAAUaiQqAAA=";
		String ewsfolderchangekey_aaab = "AQAAABYAAABwoWLwOeCwRbOMY4QJZgK1AAAUaiQw";

		String recreate_ewsfolderid_aaa = "AAMkADgyYWE2NTYxLTM5MTAtNGIxMS1iOTFjLWEyYThhMWNhYzViYQAuAAAAAABeQpYosyiXTZN+HZFGQY8FAQBwoWLwOeCwRbOMY4QJZgK1AAAUaiQyAAA=";
		String recreate_ewsfolderchangekey_aaa = "AQAAABYAAABwoWLwOeCwRbOMY4QJZgK1AAAUaiQ0";

		System.out.println(ewsfolderid.equals(ewsfolderid_Draft));
		System.out.println(ewsfolderid.equals(ewsfolderid_aaab));
		System.out.println(ewsfolderchangekey.equals(ewsfolderchangekey_Draft));
		System.out.println(ewsfolderchangekey_Draft.equals(ewsfolderchangekey_aaab));

		System.out.println(ewsfolderid.equals(recreate_ewsfolderid_aaa));
		System.out.println(ewsfolderchangekey.equals(recreate_ewsfolderchangekey_aaa));

	}

	public static void test09() {
		Set<String> set = new HashSet<String>();
		set.add("111");
		set.add("222");
		for (String str : set) {
			System.out.println(str);
		}
	}

	public static void test08() {
		String str01 = "[[storeId=null; folderId=20121206; messageId=1000007_105_20121206_128092; sender=f@new2010.local; recipient=f@new2010.local; subject=转发: 附件丢失问题; size=95384; priority=Normal; flags=New; attachment=true; date=Thu Dec 06 14:55:59 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/Inbox/, assignsToIds=null, hashcode=59e12c9490b0b1eaf5168df20939ddb8}], [storeId=null; folderId=20121206; messageId=1000007_105_20121206_118596; sender=user1@new2010.local; recipient=f@new2010.local; subject=附件丢失问题; size=91214; priority=Normal; flags=New; attachment=true; date=Thu Dec 06 14:40:18 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/Inbox/, assignsToIds=null, hashcode=a919bac5da3aa850467541df4cd161b6}], [storeId=null; folderId=20121206; messageId=1000007_105_20121206_108049; sender=user1@new2010.local; recipient=f@new2010.local; subject=附件restore丢失问题; size=64505; priority=Normal; flags=New; attachment=true; date=Thu Dec 06 14:08:09 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/Inbox/, assignsToIds=null, hashcode=3822b8a8a8e9daf2f9fd43929fb27923}], [storeId=null; folderId=20121206; messageId=1000007_105_20121206_102400; sender=user1@new2010.local; recipient=user2@new2010.local; subject=转发: EEA[10.0.0.50] Archiving Unnormal Report; size=16620; priority=Normal; flags=New; attachment=false; date=Thu Dec 06 10:16:21 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/Inbox/test/aaaInbox/SMTP Event/, assignsToIds=null, hashcode=440aaac3239228bd00446345a3f7d6bc}], [storeId=null; folderId=20121129; messageId=1000007_105_20121129_106211; sender=user1@new2010.local; recipient=user2@new2010.local; subject=&#x1;&#x5;FW: ; size=3058146; priority=Normal; flags=New; attachment=true; date=Thu Nov 29 11:06:10 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/caitest/unarchived/, assignsToIds=null, hashcode=905a184ebaab19417ef82a2b6abf0327}], [storeId=null; folderId=20121129; messageId=1000007_105_20121129_102400; sender=user1@new2010.local; recipient=user2@new2010.local; subject=&#x1;&#x5;FW: ; size=7466; priority=Normal; flags=New; attachment=false; date=Thu Nov 29 10:12:10 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/caitest/unarchived/, assignsToIds=null, hashcode=950638fd6fcc39de55168ecb7c5c006f}], [storeId=null; folderId=20121128; messageId=1000007_105_20121128_106810; sender=xiao3@new2010.local; recipient=xiao3@new2010.local; subject=&#x1 &#x1; size=18192; priority=Normal; flags=New; attachment=false; date=Wed Nov 28 10:58:02 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/&#x1/@#$%^&/, assignsToIds=null, hashcode=3f3adaaefd24bd684c5f78026966b5eb}], [storeId=null; folderId=20121128; messageId=1000007_105_20121128_102400; sender=xiao3@new2010.local; recipient=xiao3@new2010.local; subject=&#x1; size=12616; priority=Normal; flags=New; attachment=false; date=Wed Nov 28 10:57:50 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/&#x1/, assignsToIds=null, hashcode=c5c1674f80744837c2434473b19d4423}], [storeId=null; folderId=20121125; messageId=1000007_105_20121125_102400; sender=no sender <>; recipient=system@unknownuser.com; subject=11 tips for safe social networking; size=7144; priority=Normal; flags=New; attachment=false; date=Sun Nov 25 00:00:00 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/RSS 源/Microsoft at Home/, assignsToIds=null, hashcode=745ad33e8bb177d4d316c9bf387ec21a}], [storeId=null; folderId=20121125; messageId=1000007_105_20121125_106579; sender=no sender <>; recipient=system@unknownuser.com; subject=6 ways to make sure your email gets read; size=7392; priority=Normal; flags=New; attachment=false; date=Sun Nov 25 00:00:00 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/RSS 源/Microsoft at Work/, assignsToIds=null, hashcode=45fdc3993968d367afd9b32a03b9aa5d}], [storeId=null; folderId=20121122; messageId=1000007_105_20121122_102400; sender=no sender <>; recipient=system@unknownuser.com; subject=Shop the Microsoft Store Black Friday through Cyber Monday and get free shipping*; size=7649; priority=Normal; flags=New; attachment=false; date=Thu Nov 22 00:00:00 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/RSS 源/Microsoft at Home/, assignsToIds=null, hashcode=66da749a33d58acbb2f0a96c622312a9}], [storeId=null; folderId=20121122; messageId=1000007_105_20121122_111166; sender=no sender <>; recipient=system@unknownuser.com; subject=Limited-time offer: Buy Office now, get the next version free*; size=7524; priority=Normal; flags=New; attachment=false; date=Thu Nov 22 00:00:00 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/RSS 源/Microsoft at Home/, assignsToIds=null, hashcode=d242700927e369e5360abef23d6761e8}], [storeId=null; folderId=20121122; messageId=1000007_105_20121122_106999; sender=no sender <>; recipient=system@unknownuser.com; subject=Click in and do more with Surface; size=6977; priority=Normal; flags=New; attachment=false; date=Thu Nov 22 00:00:00 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/RSS 源/Microsoft at Home/, assignsToIds=null, hashcode=48a2cff089b695460249047e0606de29}], [storeId=null; folderId=20121120; messageId=1000007_105_20121120_112701; sender=eeaadmin@new2010.local; recipient=xiao3@new2010.local; subject=submit email to superior; size=14602; priority=Normal; flags=New; attachment=false; date=Tue Nov 20 15:41:34 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/Bandeja de entrada/, assignsToIds=null, hashcode=baf89387dba56696950fe98c5aa67785}], [storeId=null; folderId=20121120; messageId=1000007_105_20121120_107710; sender=eeaadmin@new2010.local; recipient=xiao3@new2010.local; subject=submit email to superior; size=14790; priority=Normal; flags=New; attachment=false; date=Tue Nov 20 11:15:06 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/Bandeja de entrada/, assignsToIds=null, hashcode=be2a5a610d3d357a687d9910a5336327}], [storeId=null; folderId=20121120; messageId=1000007_105_20121120_102400; sender=eeaadmin@new2010.local; recipient=xiao3@new2010.local; subject=submit email to superior; size=17054; priority=Normal; flags=New; attachment=false; date=Tue Nov 20 11:13:51 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/Bandeja de entrada/, assignsToIds=null, hashcode=c2188a54be8c72829de48195753dd64b}], [storeId=null; folderId=20121116; messageId=1000007_105_20121116_102400; sender=no sender <>; recipient=system@unknownuser.com; subject=Shop the Microsoft Store for Xbox holiday savings; size=7139; priority=Normal; flags=New; attachment=false; date=Fri Nov 16 00:00:00 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/RSS 源/Microsoft at Home/, assignsToIds=null, hashcode=6d076b7cf1d39d6a6dfbdd5c1f418ee3}], [storeId=null; folderId=20121115; messageId=1000007_105_20121115_102400; sender=user2@new2010.local; recipient=user1@new2010.local; subject=Fw: Forwarded message from MessageSolution Enterprise Email Archiving system (EEA); size=408408; priority=Normal; flags=New; attachment=true; date=Thu Nov 15 15:51:47 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/caitest/unarchived/, assignsToIds=null, hashcode=4e6bfd28edeada28905bbdc7f5d91333}], [storeId=null; folderId=20121115; messageId=1000007_105_20121115_121729; sender=user2@new2010.local; recipient=user1@new2010.local; subject=Fw: Forwarded message from MessageSolution Enterprise Email Archiving system (EEA); size=414246; priority=Normal; flags=New; attachment=true; date=Thu Nov 15 15:50:36 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/caitest/unarchived/, assignsToIds=null, hashcode=aca29fc67a188da7eab10082a49bb66e}], [storeId=null; folderId=20121115; messageId=1000007_105_20121115_116895; sender=user2@new2010.local; recipient=user1@new2010.local; subject=Fw: Forwarded message from MessageSolution Enterprise Email Archiving system (EEA); size=17730; priority=Normal; flags=New; attachment=true; date=Thu Nov 15 15:46:27 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/caitest/unarchived/, assignsToIds=null, hashcode=18d69ccdb455188cf09c4fa050058c75}], [storeId=null; folderId=20121115; messageId=1000007_105_20121115_112063; sender=user2@new2010.local; recipient=user1@new2010.local; subject=Fw: Forwarded message from MessageSolution Enterprise Email Archiving system (EEA); size=16523; priority=No...";
		String str02 = "[[storeId=null; folderId=20121206; messageId=1000007_105_20121206_128092; sender=f@new2010.local; recipient=f@new2010.local; subject=转发: 附件丢失问题; size=95384; priority=Normal; flags=New; attachment=true; date=Thu Dec 06 14:55:59 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/Inbox/, assignsToIds=null, hashcode=59e12c9490b0b1eaf5168df20939ddb8}], [storeId=null; folderId=20121206; messageId=1000007_105_20121206_118596; sender=user1@new2010.local; recipient=f@new2010.local; subject=附件丢失问题; size=91214; priority=Normal; flags=New; attachment=true; date=Thu Dec 06 14:40:18 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/Inbox/, assignsToIds=null, hashcode=a919bac5da3aa850467541df4cd161b6}], [storeId=null; folderId=20121206; messageId=1000007_105_20121206_108049; sender=user1@new2010.local; recipient=f@new2010.local; subject=附件restore丢失问题; size=64505; priority=Normal; flags=New; attachment=true; date=Thu Dec 06 14:08:09 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/Inbox/, assignsToIds=null, hashcode=3822b8a8a8e9daf2f9fd43929fb27923}], [storeId=null; folderId=20121206; messageId=1000007_105_20121206_102400; sender=user1@new2010.local; recipient=user2@new2010.local; subject=转发: EEA[10.0.0.50] Archiving Unnormal Report; size=16620; priority=Normal; flags=New; attachment=false; date=Thu Dec 06 10:16:21 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/Inbox/test/aaaInbox/SMTP Event/, assignsToIds=null, hashcode=440aaac3239228bd00446345a3f7d6bc}], [storeId=null; folderId=20121129; messageId=1000007_105_20121129_106211; sender=user1@new2010.local; recipient=user2@new2010.local; subject=&#x1;&#x5;FW: ; size=3058146; priority=Normal; flags=New; attachment=true; date=Thu Nov 29 11:06:10 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/caitest/unarchived/, assignsToIds=null, hashcode=905a184ebaab19417ef82a2b6abf0327}], [storeId=null; folderId=20121129; messageId=1000007_105_20121129_102400; sender=user1@new2010.local; recipient=user2@new2010.local; subject=&#x1;&#x5;FW: ; size=7466; priority=Normal; flags=New; attachment=false; date=Thu Nov 29 10:12:10 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/caitest/unarchived/, assignsToIds=null, hashcode=950638fd6fcc39de55168ecb7c5c006f}], [storeId=null; folderId=20121128; messageId=1000007_105_20121128_106810; sender=xiao3@new2010.local; recipient=xiao3@new2010.local; subject=&#x1 &#x1; size=18192; priority=Normal; flags=New; attachment=false; date=Wed Nov 28 10:58:02 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/&#x1/@#$%^&/, assignsToIds=null, hashcode=3f3adaaefd24bd684c5f78026966b5eb}], [storeId=null; folderId=20121128; messageId=1000007_105_20121128_102400; sender=xiao3@new2010.local; recipient=xiao3@new2010.local; subject=&#x1; size=12616; priority=Normal; flags=New; attachment=false; date=Wed Nov 28 10:57:50 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/&#x1/, assignsToIds=null, hashcode=c5c1674f80744837c2434473b19d4423}], [storeId=null; folderId=20121125; messageId=1000007_105_20121125_102400; sender=no sender <>; recipient=system@unknownuser.com; subject=11 tips for safe social networking; size=7144; priority=Normal; flags=New; attachment=false; date=Sun Nov 25 00:00:00 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/RSS 源/Microsoft at Home/, assignsToIds=null, hashcode=745ad33e8bb177d4d316c9bf387ec21a}], [storeId=null; folderId=20121125; messageId=1000007_105_20121125_106579; sender=no sender <>; recipient=system@unknownuser.com; subject=6 ways to make sure your email gets read; size=7392; priority=Normal; flags=New; attachment=false; date=Sun Nov 25 00:00:00 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/RSS 源/Microsoft at Work/, assignsToIds=null, hashcode=45fdc3993968d367afd9b32a03b9aa5d}], [storeId=null; folderId=20121122; messageId=1000007_105_20121122_102400; sender=no sender <>; recipient=system@unknownuser.com; subject=Shop the Microsoft Store Black Friday through Cyber Monday and get free shipping*; size=7649; priority=Normal; flags=New; attachment=false; date=Thu Nov 22 00:00:00 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/RSS 源/Microsoft at Home/, assignsToIds=null, hashcode=66da749a33d58acbb2f0a96c622312a9}], [storeId=null; folderId=20121122; messageId=1000007_105_20121122_111166; sender=no sender <>; recipient=system@unknownuser.com; subject=Limited-time offer: Buy Office now, get the next version free*; size=7524; priority=Normal; flags=New; attachment=false; date=Thu Nov 22 00:00:00 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/RSS 源/Microsoft at Home/, assignsToIds=null, hashcode=d242700927e369e5360abef23d6761e8}], [storeId=null; folderId=20121122; messageId=1000007_105_20121122_106999; sender=no sender <>; recipient=system@unknownuser.com; subject=Click in and do more with Surface; size=6977; priority=Normal; flags=New; attachment=false; date=Thu Nov 22 00:00:00 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/RSS 源/Microsoft at Home/, assignsToIds=null, hashcode=48a2cff089b695460249047e0606de29}], [storeId=null; folderId=20121120; messageId=1000007_105_20121120_112701; sender=eeaadmin@new2010.local; recipient=xiao3@new2010.local; subject=submit email to superior; size=14602; priority=Normal; flags=New; attachment=false; date=Tue Nov 20 15:41:34 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/Bandeja de entrada/, assignsToIds=null, hashcode=baf89387dba56696950fe98c5aa67785}], [storeId=null; folderId=20121120; messageId=1000007_105_20121120_107710; sender=eeaadmin@new2010.local; recipient=xiao3@new2010.local; subject=submit email to superior; size=14790; priority=Normal; flags=New; attachment=false; date=Tue Nov 20 11:15:06 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/Bandeja de entrada/, assignsToIds=null, hashcode=be2a5a610d3d357a687d9910a5336327}], [storeId=null; folderId=20121120; messageId=1000007_105_20121120_102400; sender=eeaadmin@new2010.local; recipient=xiao3@new2010.local; subject=submit email to superior; size=17054; priority=Normal; flags=New; attachment=false; date=Tue Nov 20 11:13:51 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/Bandeja de entrada/, assignsToIds=null, hashcode=c2188a54be8c72829de48195753dd64b}], [storeId=null; folderId=20121116; messageId=1000007_105_20121116_102400; sender=no sender <>; recipient=system@unknownuser.com; subject=Shop the Microsoft Store for Xbox holiday savings; size=7139; priority=Normal; flags=New; attachment=false; date=Fri Nov 16 00:00:00 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/RSS 源/Microsoft at Home/, assignsToIds=null, hashcode=6d076b7cf1d39d6a6dfbdd5c1f418ee3}], [storeId=null; folderId=20121115; messageId=1000007_105_20121115_102400; sender=user2@new2010.local; recipient=user1@new2010.local; subject=Fw: Forwarded message from MessageSolution Enterprise Email Archiving system (EEA); size=408408; priority=Normal; flags=New; attachment=true; date=Thu Nov 15 15:51:47 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/caitest/unarchived/, assignsToIds=null, hashcode=4e6bfd28edeada28905bbdc7f5d91333}], [storeId=null; folderId=20121115; messageId=1000007_105_20121115_121729; sender=user2@new2010.local; recipient=user1@new2010.local; subject=Fw: Forwarded message from MessageSolution Enterprise Email Archiving system (EEA); size=414246; priority=Normal; flags=New; attachment=true; date=Thu Nov 15 15:50:36 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/caitest/unarchived/, assignsToIds=null, hashcode=aca29fc67a188da7eab10082a49bb66e}], [storeId=null; folderId=20121115; messageId=1000007_105_20121115_116895; sender=user2@new2010.local; recipient=user1@new2010.local; subject=Fw: Forwarded message from MessageSolution Enterprise Email Archiving system (EEA); size=17730; priority=Normal; flags=New; attachment=true; date=Thu Nov 15 15:46:27 CST 2012; attributes={remoteServerIP=null, mailtype=exchange-ews-message, remoteServerName=null, thread_index=null, owner_id=null, FOLDER_PATH=/caitest/unarchived/, assignsToIds=null, hashcode=18d69ccdb455188cf09c4fa050058c75}], [storeId=null; folderId=20121115; messageId=1000007_105_20121115_112063; sender=user2@new2010.local; recipient=user1@new2010.local; subject=Fw: Forwarded message from MessageSolution Enterprise Email Archiving system (EEA); size=16523; priority=No...";
		System.out.println(str01.equals(str02));
	}

	public static String test07() {
		String result = "";

		try {
			result = 100 / 20 + "";
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		} finally {
			System.out.println("before return!!");
		}
	}

	public static void test01() {
		String xml = "AAMkAGY0MDdlYjM3LWExZDUtNGNlMC1hZTg0LTU4ZWZkZGNkOGJiOQAuAAAAAAD/f8W+sY4gSYjY6DMcuFbCAQByOhVYqJoLSqCMpGPw4jLQAAAH0ZmWAAA=";
		String test = "AAMkAGY0MDdlYjM3LWExZDUtNGNlMC1hZTg0LTU4ZWZkZGNkOGJiOQAuAAAAAAD/f8W+sY4gSYjY6DMcuFbCAQByOhVYqJoLSqCMpGPw4jLQAAAA2+viAAA=";

		xml = "http://10.0.0.38:8080/webmial/ViewMessage.jp?operationType=emailSampling&storage=messagesolution&msgid=archive%2F20121122%2F0_1_20121122_311201%2Ff7fcf49711893a1fde055228b6164a68&sid=37&Mailstoreid=0_1_20121122_311201&isgroupmanager=false&&viewTag=true&remoteIP=";
		test = "http://10.0.0.38:8080/webmial/ViewMessage.jp?operationType=emailSampling&storage=messagesolution&msgid=archive%2F20121122%2F0_1_20121122_311201%2Ff7fcf49711893a1fde055228b6164a68&sid=37&Mailstoreid=0_1_20121122_311201&isgroupmanager=false&&viewTag=true&remoteIP=";

		System.out.println(xml.equals(test));

		String str = "GBKfdfdsfdsjfdsg";
		System.out.println(str.startsWith("GBK"));
		System.out.println(str.startsWith("gbk"));
	}

	public static void test02() {

		System.out.println((int) '.');

		System.out.println("?".equals("?"));

		System.out.println(" ???]z???}?]???}??u?</ns2:Body>".indexOf("?"));

		String ss = " ???]z???}?]???}??u?</ns2:Body>";
		System.out.println(ss.substring(ss.indexOf("</ns2:Body>")));

		// System.out.println(" ???]z???}?]???}??u?</ns2:Body>".replaceAll("?",
		// ""));
		// System.out.println(" ???]z???}?]???}??u?</ns2:Body>".replaceAll("
		// ???]z???}?]???}??u?", ""));

		String s = "gregoryr@shubertorg.com\r\n ???]z???}?]???}??u?</ns2:Body>\r\n<ns2:ExtendedProperty>";
		System.out.println(s.indexOf("?"));
		Scanner sc = new Scanner(s);
		String temp = "";
		while (sc.hasNextLine()) {
			temp = sc.nextLine();
			if (temp.indexOf("?]z") >= 0) {

				System.out.println(temp);
			}
		}
	}

	public static void test03() {

	}

	public static void test04() {

	}

	public static void test05() {

	}

	public static void test06() {

	}

}
