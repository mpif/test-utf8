package com.codefans.java.jar;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JarUtil util = new JarUtil();
		util.print();
	}

	public void print() {
		try {
			// String jarStr = "D:\\dev\\RB-2.1.x-gx\\dist\\lib\\eas.jar";
			String jarStr = "C:\\Users\\Administrator\\Downloads\\eas_MAINTAIN-2.1.15-fx_r39292_t20131022-0558.jar";

			File jarFile = new File(jarStr);
			JarFile jf = new JarFile(jarFile);
			Enumeration<JarEntry> e = jf.entries();
			InputStream in = null;
			while (e.hasMoreElements()) {
				JarEntry entry = e.nextElement();
				String name = entry.getName();

				if (name.endsWith("classes.fingerprint"))// this file was added
															// to eas.jar since
															// ant build
				{
					System.out.println(name);
					// in = new BufferedInputStream( jf.getInputStream( entry )
					// );
					// break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
