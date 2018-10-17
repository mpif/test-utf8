package com.codefans.task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Properties;
import java.util.Scanner;

public class EnvironmentJumper {

	private static String netName = "";
	
	public EnvironmentJumper() {
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String type = "";
		
		type = "product";
//		type = "dev";
//		type = "test";
//		args = new String[]{type, "无线网络连接"};
//		args = new String[]{type};
		if(args.length == 1) {
			type = args[0];
		} else if(args.length == 2) {
			type = args[0];
			netName = args[1];
//			System.out.println("type:[" + type + "], netName:[" + netName + "]");
		} else {
			System.out.println("Usage: EnvironmentJumper [product|dev|test]");
			System.exit(0);
		}
		
		EnvironmentJumper jumper = new EnvironmentJumper();
		
		if("product".equalsIgnoreCase(type)) {
			jumper.product();
		} else if("dev".equalsIgnoreCase(type)) {
			jumper.dev();
		} else if("test".equalsIgnoreCase(type)) {
			jumper.test();
		}
		
		
	}
	
	public void product() {
		try {
			
			String productHostsContent = this.getProductHosts();
			changeHosts(productHostsContent);
			System.out.println("成功切换生产环境hosts!");
			
			String resetDnsCommand = "netsh interface ip set dns \"以太网\" dhcp";
//			String resetDnsCommand = "netsh interface ip set dns \"本地连接\" source=static addr=192.168.2.1";
//			String resetDnsCommand = "netsh interface ip set dns \"本地连接\" static 192.168.2.1";
//			String resetDnsCommand = "netsh interface ip set dns \"" + netName + "\" dhcp";
			String resultOfChangeDNS = changeDns(resetDnsCommand);
			if(resultOfChangeDNS != null && resultOfChangeDNS.trim().length() > 0) {
				System.out.println("result of changeDNS:" + resultOfChangeDNS);
			} else {
				System.out.println("成功切换生产环境DNS!");
			}
			resetDnsCommand = "netsh interface ip set dns \"WLAN\" dhcp";
//			resetDnsCommand = "netsh interface ip set dns \"无线网络连接\" source=static addr=192.168.2.1";
//			resetDnsCommand = "netsh interface ip set dns \"无线网络连接\" static 192.168.2.1";
//			resetDnsCommand = "netsh interface ip set dns \"" + netName + "\" dhcp";
			resultOfChangeDNS = changeDns(resetDnsCommand);
			if(resultOfChangeDNS != null && resultOfChangeDNS.trim().length() > 0) {
				System.out.println("result of changeDNS:" + resultOfChangeDNS);
			} else {
				System.out.println("成功切换生产环境DNS!");
			}
			resetDnsCommand = "netsh interface ip set dns \"SECOO VPN 连接\" dhcp";
//			resetDnsCommand = "netsh interface ip set dns \"SECOO VPN 连接\" source=static addr=192.168.2.1";
//			resetDnsCommand = "netsh interface ip set dns \"SECOO VPN 连接\" static 192.168.2.1";
			resultOfChangeDNS = changeDns(resetDnsCommand);
			if(resultOfChangeDNS != null && resultOfChangeDNS.trim().length() > 0) {
				System.out.println("result of changeDNS:" + resultOfChangeDNS);
			} else {
				System.out.println("成功切换生产环境DNS!");
			}
			
			flushDns();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void dev() {
		try {
			
			String devHostsContent = this.getDevHosts();
			changeHosts(devHostsContent);
			System.out.println("成功切换开发环境hosts!");
			
			String resetDnsCommand = "netsh interface ip set dns \"以太网\" dhcp";
//			String resetDnsCommand = "netsh interface ip set dns \"本地连接\" source=static addr=192.168.2.1";
//			String resetDnsCommand = "netsh interface ip set dns \"本地连接\" static 192.168.2.1";
//			String resetDnsCommand = "netsh interface ip set dns \"" + netName + "\" dhcp";
			String resultOfChangeDNS = changeDns(resetDnsCommand);
			if(resultOfChangeDNS != null && resultOfChangeDNS.trim().length() > 0) {
				System.out.println("result of changeDNS:" + resultOfChangeDNS);
			} else {
				System.out.println("成功切换开发环境DNS!");
			}
			resetDnsCommand = "netsh interface ip set dns \"WLAN\" dhcp";
//			resetDnsCommand = "netsh interface ip set dns \"无线网络连接\" source=static addr=192.168.2.1";
//			resetDnsCommand = "netsh interface ip set dns \"无线网络连接\" static 192.168.2.1";
//			resetDnsCommand = "netsh interface ip set dns \"" + netName + "\" dhcp";
			resultOfChangeDNS = changeDns(resetDnsCommand);
			if(resultOfChangeDNS != null && resultOfChangeDNS.trim().length() > 0) {
				System.out.println("result of changeDNS:" + resultOfChangeDNS);
			} else {
				System.out.println("成功切换开发环境DNS!");
			}
			resetDnsCommand = "netsh interface ip set dns \"SECOO VPN 连接\" dhcp";
//			resetDnsCommand = "netsh interface ip set dns \"SECOO VPN 连接\" source=static addr=192.168.2.1";
//			resetDnsCommand = "netsh interface ip set dns \"SECOO VPN 连接\" static 192.168.2.1";
			resultOfChangeDNS = changeDns(resetDnsCommand);
			if(resultOfChangeDNS != null && resultOfChangeDNS.trim().length() > 0) {
				System.out.println("result of changeDNS:" + resultOfChangeDNS);
			} else {
				System.out.println("成功切换开发环境DNS!");
			}
			String resultOfFlushDNS = flushDns();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void test() {
		try {
			
			String testHostsContent = this.getProductHosts();
			changeHosts(testHostsContent);
			System.out.println("成功切换测试环境hosts!");
			
//			String dnsSettingCommand = "netsh interface ip set dns \"本地连接\" static 10.185.240.85 10.185.240.86";
			String dns = this.getTestDNS();
//			String dnsSettingCommand = "netsh interface ip set dns \"本地连接\" static " + dns;
			String dnsSettingCommand = "netsh interface ip set dns \"以太网\" static " + dns;
//			String dnsSettingCommand = "netsh interface ip set dns \"" + netName + "\" static " + dns;
			String resultOfChangeDNS = changeDns(dnsSettingCommand);
			if(resultOfChangeDNS != null && resultOfChangeDNS.trim().length() > 0) {
				System.out.println("result of changeDNS:" + resultOfChangeDNS);
			} else {
				System.out.println("成功切换测试环境DNS!");
			}
//			dnsSettingCommand = "netsh interface ip set dns \"本地连接\" static 10.185.240.85 10.185.240.86";
			dns = this.getTestDNS();
//			dnsSettingCommand = "netsh interface ip set dns \"无线网络连接\" static " + dns;
			dnsSettingCommand = "netsh interface ip set dns \"WLAN\" static " + dns;
//			dnsSettingCommand = "netsh interface ip set dns \"" + netName + "\" static " + dns;
			resultOfChangeDNS = changeDns(dnsSettingCommand);
			if(resultOfChangeDNS != null && resultOfChangeDNS.trim().length() > 0) {
				System.out.println("result of changeDNS:" + resultOfChangeDNS);
			} else {
				System.out.println("成功切换测试环境DNS!");
			}
			
			dnsSettingCommand = "netsh interface ip set dns \"SECOO VPN 连接\" static " + dns;
			resultOfChangeDNS = changeDns(dnsSettingCommand);
			if(resultOfChangeDNS != null && resultOfChangeDNS.trim().length() > 0) {
				System.out.println("result of changeDNS:" + resultOfChangeDNS);
			} else {
				System.out.println("成功切换测试环境DNS!");
			}
			
			flushDns();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void changeHosts(String content) throws IOException {
		File hosts = new File("C:/Windows/System32/drivers/etc/hosts");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(hosts)));
		bw.write(content);
		bw.flush();
		bw.close();
	}
	
	public String changeDns(String changeDnsCommand) {
		String result = execute(changeDnsCommand);
//		if(result != null && result.trim().length() > 0) {
//			System.out.println("result of changeDNS:" + result);
//		}
		return result;
	}
	
	public String flushDns() {
		String changeDnsCommand = "ipconfig /flushdns";
		String result = execute(changeDnsCommand);
//		if(result != null && result.trim().length() > 0) {
//			System.out.println("result of flushDNS:" + result);
//		}
		return result;
	}
	
	public String getProductHosts() {
		
		StringBuffer sb = new StringBuffer();
//		sb.append("# Product Hosts").append("\r\n");
//		sb.append("127.0.0.1       localhost  host01 host02").append("\r\n");

		sb.append(this.getStrFromFile("productAndTestHosts.conf"));
		
		return sb.toString();
	}
	
	public String getDevHosts() {
		
		StringBuffer sb = new StringBuffer();
//		sb.append("# Dev Hosts").append("\r\n");
//		sb.append("ip host01 host02").append("\r\n");

		sb.append(this.getStrFromFile("devHosts.conf"));
		
		return sb.toString();
	}
	
	public String getTestDNS() {
		StringBuffer sb = new StringBuffer();
		
		try {
			InputStream is = EnvironmentJumper.class.getResourceAsStream("testDns.conf");
			Properties prop = new Properties();
			prop.load(is);
			Object obj = prop.getProperty("dns");
			sb.append(String.valueOf(obj));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	public String getStrFromFile(String fileName) {
		InputStream is = EnvironmentJumper.class.getResourceAsStream(fileName);
		return getStrFromFile(is);
	}
	
	public String getStrFromFile(InputStream fileStream) {
		StringBuffer sb = new StringBuffer();
		Scanner sc = new Scanner(fileStream);
		while(sc.hasNext()) {
			sb.append(sc.nextLine()).append("\r\n");
		}
		return sb.toString();
	}
	
	public String execute(String command) {
		StringBuffer result = new StringBuffer();
		InputStream is = null;
		BufferedReader br = null;
		String tmp = "";
		try {
			
			Process proc = Runtime.getRuntime().exec(command);
			is = proc.getInputStream();
			if(is == null) {
				is = proc.getErrorStream();
			}
			br = new BufferedReader(new InputStreamReader(is, "GBK"));
			while((tmp = br.readLine()) != null) {
				if(tmp.trim().length() > 0) {
					result.append(tmp).append("\r\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result.toString();
	}
	
}

//windows命令行设置IP与DNS
//http://blog.csdn.net/cashey1991/article/details/7038876
//	@1.设置动态IP(DHCP自动获取IP)
//
//	netsh interface ip set address "本地连接" dhcp  
//	@2.设置指定的IP，此处以设置本机IP为10.16.15.226，子网掩码为255.255.255.0，网关IP为10.16.15.1为例
//
//	netsh interface ip set address "本地连接" static 10.16.15.226 255.255.255.0 10.16.15.1  
//	@3.设置动态DNS(DHCP自动获取DNS)
//
//	netsh interface ip set dns "本地连接" dhcp  
//	@4.设置指定的DNS，此处以设置DNS为210.45.240.10为例 #####################上文链接的文章中，这条命令没有static，会报错
//	netsh interface ip set dns "本地连接" static 210.45.240.10  