package com.codefans.i18n;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ResourceBundleUtil {

	private int n = 0;
	private int refreshFrequence = 5;

	public void scheduleTask() {
		final Timer timer = new Timer();
		final TimerTask job = new TimerTask() {
			public void run() {
				try {
					if (n == 1) {
						// Locale locale = new Locale("zh_CN");
						// ResourceBundle br =
						// ResourceBundle.getBundle("com/messagesolution/i18n/message",
						// locale);
						ResourceBundle br = getBundle("zh_CN");
						String down = br.getString("eas.down");
						System.out.println("down: " + down);
					} else {
						// Locale locale = new Locale("en_US");
						// Locale locale = new Locale("en_us");
						// ResourceBundle br =
						// ResourceBundle.getBundle("com/messagesolution/i18n/message",
						// locale);
						ResourceBundle br = getBundle("en_US");
						String down = br.getString("eas.down");
						System.out.println("down: " + down);
					}
					n++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		Thread jobThread = new Thread(new Runnable() {
			@Override
			public void run() {
				timer.scheduleAtFixedRate(job, 0, refreshFrequence * 1000);
			}
		});

		jobThread.start();
	}

	public String baseName = "com/messagesolution/i18n/message";
	public String ZH_CN = "zh_CN";
	public String EN_US = "en_US";

	public ResourceBundle getBundle(String local) {
		// Locale locale = this.getLocale(local);
		// this method can not be suitable for every situation
		Locale locale = new Locale(local);
		return ResourceBundle.getBundle(baseName, locale);
	}

	public Locale getLocale(String lang) {
		Locale locale = Locale.getDefault();
		if (ZH_CN.equalsIgnoreCase(lang)) {
			locale = Locale.CHINA;
		} else if (EN_US.equalsIgnoreCase(lang)) {
			locale = Locale.US;
		}
		return locale;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Locale locale = new Locale("zh", "CN");
		// Locale locale = new Locale("en", "US");
		// Locale locale = new Locale("zh_CN");
		// Locale locale = new Locale("en_US");
		// ResourceBundle br =
		// ResourceBundle.getBundle("com/messagesolution/i18n/message", locale);
		// String down = br.getString("eas.down");
		// System.out.println("down: " + down);
		//
		// locale = new Locale("zh_CN");
		// br = ResourceBundle.getBundle("com/messagesolution/i18n/message",
		// locale);
		// down = br.getString("eas.down");
		// System.out.println("down: " + down);
		//
		// String name = Locale.getDefault().getDisplayName();
		// System.out.println(name);

		ResourceBundleUtil resourceBundleUtil = new ResourceBundleUtil();
		resourceBundleUtil.scheduleTask();

	}

}
