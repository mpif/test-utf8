package com.codefans.xml;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class XMLGregorianCalendarTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GregorianCalendar nowGregorianCalendar = new GregorianCalendar();
		try {
			XMLGregorianCalendar xmlDatetime = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(nowGregorianCalendar);
			// XMLGregorianCalendar ->GregorianCalendar
			nowGregorianCalendar = xmlDatetime.toGregorianCalendar();
			// GregorianCalendar->String
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateTimeString = simpleDateFormat.format(nowGregorianCalendar.getTime());
			System.out.println("time: " + nowGregorianCalendar.getTime().toString());
			System.out.println("dateTimeString: " + dateTimeString);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
