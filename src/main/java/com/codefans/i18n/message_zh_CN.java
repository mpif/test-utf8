package com.codefans.i18n;

import java.util.ListResourceBundle;

public class message_zh_CN extends ListResourceBundle {

	private final Object myData[][] = { { "username:fjdfd", "{0},姓名" }, { "eas.down", "系统挂了" },
			{ "password", "{0},密码" } };

	@Override
	protected Object[][] getContents() {
		return myData;
	}

}
