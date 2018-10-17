package com.codefans.util;

//jdk 6
//import sun.management.ManagementFactory;
//jdk 7
import sun.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class MemoryCpuTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

		long free = osmxb.getFreePhysicalMemorySize();
		long total = osmxb.getTotalPhysicalMemorySize();
		p("free: " + free);
		p("total: " + total);

		int kb = 1024;

		// 可使用内存
		long totalMemory = Runtime.getRuntime().totalMemory() / kb;
		// 剩余内存
		long freeMemory = Runtime.getRuntime().freeMemory() / kb;
		// 最大可使用内存
		long maxMemory = Runtime.getRuntime().maxMemory() / kb;

		// 操作系统
		String osName = System.getProperty("os.name");
		// 总的物理内存
		long totalMemorySize = osmxb.getTotalPhysicalMemorySize() / kb;
		// 剩余的物理内存
		long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize() / kb;
		// 已使用的物理内存
		long usedMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / kb;

		System.out.println("totalMemory of VM:[" + totalMemory + "]KB");
		System.out.println("totalMemory of Physical:[" + totalMemorySize + "]KB");
		System.out.println("freeMemory of VM:[" + freeMemory + "]KB");
		System.out.println("freeMemory of Physical:[" + freePhysicalMemorySize + "]KB");
		System.out.println("maxMemory of VM:[" + maxMemory + "]KB");
		System.out.println("usedMemory of Physical:[" + usedMemory + "]KB");

	}

	public static void p(Object o) {
		System.out.println(o);
	}
}
