package com.test.classinit;

public class SystemPropertyProvider {

	private String conf_path;
	private String root_path;

	public SystemPropertyProvider() {
		conf_path = "/eas/conf";
		root_path = "/opt/msol";
		System.out.println("root_path: " + root_path);
	}

	public String getConfPath() {
		return conf_path;
	}

	private ServerProperties properties = new ServerProperties(getConfPath(), "bbb");

	public static void main(String[] args) {
		SystemPropertyProvider spp = new SystemPropertyProvider();

		System.out.println(SystemPropertyProvider.class.getName());// com.test.classinit.SystemPropertyProvider
		System.out.println(SystemPropertyProvider.class.getName());// com.test.classinit.SystemPropertyProvider
		System.out.println(SystemPropertyProvider.class.getName());// com.test.classinit.SystemPropertyProvider
	}
}

class ServerProperties {

	public ServerProperties(String str, String str2) {
		System.out.println("str: " + str + ", str2: " + str2);
	}

}
