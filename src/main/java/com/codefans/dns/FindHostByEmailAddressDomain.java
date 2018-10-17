package com.codefans.dns;

import org.xbill.DNS.Lookup;
import org.xbill.DNS.MXRecord;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

/*
 * @Author: Sean
 * @Time: 2015-05-20 11:02:01
 */
public class FindHostByEmailAddressDomain {

	/**
	 * @param args
	 * @throws Throwable
	 */
	public static void main(String[] args) throws Throwable {
		FindHostByEmailAddressDomain fhbead = new FindHostByEmailAddressDomain();
		fhbead.find();
	}

	public void find() throws Throwable {

		// String domain = "messagesolution.cn";
		// String domain = "exchange2013.com";
		// String domain = "win2010.com";
		String domain = "exchange2010a.com";
		// String domain = "163.com";
		// String domain = "sohu.com";
		// String domain = "sina.com.cn";
		// String domain = "qq.com";

		Record[] records = new Lookup(domain, Type.MX).run();
		if (records != null) {
			for (int i = 0; i < records.length; i++) {
				MXRecord mx = (MXRecord) records[i];
				System.out.println("Host " + mx.getTarget() + " has preference " + mx.getPriority());
			}
		}
	}

}
