package com.codefans.task;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * User: Sean
 * Date: 2015-4-9
 * Time: 下午5:01:41
 * 
 * 设置ip地址：
 * netsh interface ip set address "本地连接" static 192.168.5.36 255.255.255.0 192.168.5.1 auto
 * 添加ip地址：
 * netsh interface ip add address "本地连接" 192.168.5.36 255.255.255.0
 * 删除ip地址： 
 * netsh interface ip delete address "本地连接" 10.168.5.36
 * 
 */

public class IPOccupationMonitor {

	private static final String DESC = "Atheros AR8151 PCI-E Gigabit Ethernet Controller";
	private static final String MACADDR = "54-04-A6-F1-93-CC";

	private List<String> availableIpAddr = new ArrayList<String>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IPOccupationMonitor ipOccupy = new IPOccupationMonitor();
		ipOccupy.monitor();
	}

	public void monitor() {

		// empty
		checkIpOccupation();

		// checkAvailableIpAddrs();

		// empty
		setNewIpAddr();

		// empty
		checkInternetAvailable();

		// String command = "ipconfig";
		String command = "ping 10.0.0.16";
		// String command = "ping 10.0.0.18";
		// String command = "ping 10.0.0.68";
		// execute(command);

		// setDefaultIp();
		// setIpAddress("10.0.0.2");
		setIpAddress("10.0.0.16");
		// setIpAddress("10.0.0.18");
		// setIpAddress("10.0.0.26");

		// addIpAddress("10.0.0.26");
		// deleteIpAddress("10.0.0.16");
		// deleteIpAddress("10.0.0.18");

		command = "ipconfig";
		execute(command);

	}

	public void checkIpOccupation() {
		String command = "netsh interface ip set address \"本地连接\" static 10.0.0.50 255.255.255.0 10.0.0.1";
	}

	public void checkAvailableIpAddrs() {
		String ipPrefix = "10.0.0.";
		int ipMax = 100;
		String ip = "";
		String commandPrefix = "ping ";

		for (int i = 2; i <= ipMax; i++) {
			ip = ipPrefix + i;
			String command = commandPrefix + ip;
			if (this.checkIpAvailable(command)) {
				availableIpAddr.add(ip);
				System.out.println("ip:[" + ip + "], connection success");
			} else {
				System.out.println("ip:[" + ip + "], connection fail");
			}
		}

	}

	public boolean checkIpAvailable(String command) {
		boolean flag = false;
		String result = this.getExecuteCommandResult(command);
		if (result != null) {
			if (result.contains("字节=32 时间<1ms TTL=64") && result.contains("往返行程的估计时间(以毫秒为单位):")) {
				flag = true;
				// System.out.println("success");
			}
		}
		return flag;
	}

	public void setNewIpAddr() {

	}

	public void checkInternetAvailable() {

	}

	public void setDefaultIp() {
		this.setIpAddress("10.0.0.50");
	}

	public void setIpAddress(String ip) {
		String command = "netsh interface ip set address \"本地连接\" static " + ip + " 255.255.255.0 10.0.0.1";
		String result = this.getExecuteCommandResult(command);
		System.out.println(result);
	}

	public void addIpAddress(String ip) {
		String command = "netsh interface ip add address \"本地连接\" " + ip + " 255.255.255.0 10.0.0.1";
		String result = this.getExecuteCommandResult(command);
		System.out.println(result);
	}

	public void deleteIpAddress(String ip) {
		String command = "netsh interface ip delete address \"本地连接\" " + ip + "";
		String result = this.getExecuteCommandResult(command);
		System.out.println(result);
	}

	public void execute(String command) {
		// String command = "netsh interface ip set address \"本地连接\" static
		// 10.0.0.2 255.255.255.0 10.0.0.1";
		String result = this.getExecuteCommandResult(command);
		System.out.println(result);
	}

	public String getExecuteCommandResult(String command) {
		StringBuffer result = new StringBuffer();
		InputStream is = null;
		BufferedReader br = null;
		String tmp = "";
		try {

			Process proc = Runtime.getRuntime().exec(command);
			is = proc.getInputStream();
			if (is == null) {
				is = proc.getErrorStream();
			}
			br = new BufferedReader(new InputStreamReader(is, "GBK"));
			while ((tmp = br.readLine()) != null) {
				if (tmp.trim().length() > 0) {
					result.append(tmp).append("\r\n");
					// System.out.println(tmp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.toString();
	}

}
