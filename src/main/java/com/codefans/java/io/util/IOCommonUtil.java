package com.codefans.java.io.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOCommonUtil {

	public void closeResource(InputStream is, OutputStream os) {
		try {
			if (is != null) {
				is.close();
				is = null;
			}
			if (os != null) {
				os.flush();
				os.close();
				os = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
