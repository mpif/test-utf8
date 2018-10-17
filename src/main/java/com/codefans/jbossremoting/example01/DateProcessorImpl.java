package com.codefans.jbossremoting.example01;

import java.text.DateFormat;
import java.util.Date;

public class DateProcessorImpl implements DateProcessor {

	public String formatDate(Date dateToConvert) {
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
		return dateFormat.format(dateToConvert);
	}

}
