package com.codefans.dns;

import org.xbill.DNS.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA. User: JackWang Date: 13-1-12
 */
public class DnsFinder {

	/**
	 *
	 * get MX record from DNS ,it will return NULL if we get nothing
	 *
	 * it is ordered by priority ,lowest preference = recommended server
	 *
	 * @param domain
	 * @return MailHost list,you can send mail to MailHost[0] if failed ,you can
	 *         try MailHost[1]...
	 */
	public List<MailHost> getMXRecord(String domain) {
		List<MailHost> results = null;
		try {
			Record[] records = new Lookup(domain, Type.MX).run();
			if (records == null || records.length == 0) {
				return null;
			}
			results = new ArrayList<MailHost>();
			for (Record record : records) {
				MXRecord mxRecord = (MXRecord) record;
				MailHost host = new MailHost();
				host.setHost(mxRecord.getTarget().toString());
				host.setPriority(mxRecord.getPriority());
				results.add(host);
			}
			// sort by priority , the lowest in front
			Collections.sort(results);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public static void main(String[] args) {
		DnsFinder f = new DnsFinder();

		// System.out.println(f.getMXRecord("exchange.com"));
		// System.out.println(f.getMXRecord("exchange07.com"));
		// System.out.println(f.getMXRecord("163.com"));
		// System.out.println(f.getMXRecord("sina.cn"));
		// System.out.println(f.getMXRecord("qq.com"));
		System.out.println(f.getMXRecord("messagesolution.cn"));
		// System.out.println(f.getMXRecord("win2010.com"));
		// System.out.println(f.getMXRecord("eea.win2010.com"));
		// System.out.println(f.getMXRecord("exchange2010a.com"));

	}

}
