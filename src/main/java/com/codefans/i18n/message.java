package com.codefans.i18n;

import java.util.ListResourceBundle;

public class message extends ListResourceBundle {

	private final Object myData[][] = { { "username", "{0},UserName" }, { "eas.down", "eas down haha" },
			{ "password", "{0},PassWord" } };

	@Override
	protected Object[][] getContents() {
		return myData;
	}

}
