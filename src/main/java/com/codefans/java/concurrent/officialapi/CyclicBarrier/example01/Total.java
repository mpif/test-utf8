package com.codefans.java.concurrent.officialapi.CyclicBarrier.example01;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
 * User: Sean
 * Date: 2015-4-1
 * Time: 下午3:16:12
 * 
 * CyclicBarrier是一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点（common barrier point）。
 * 在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时CyclicBarrier很有用。因为该barrier在释放
 * 等待线程后可以重用，所以称它为循环的barrier。
 * 
 * CyclicBarrier(int);
 * CyclicBarrier(int, Runnable); //当await的数量到达了设定的数量后，执行该Runnable对象
 * await(); //通知barrier已经完成线程
 * reset(); //将屏障重置为其初始状态。
 * 
 * 【适用场景】
 *     我们需要统计全国的业务数据。其中各省的数据库是独立的，也就是说按省分库。并且统计的数据量很大，统计过程也比较慢。
 *  为了提高性能，快速计算。我们采取并发的方式，多个线程同时计算各省数据，每个省下面有用多线程，最后再汇总统计全国数据（各省数据的总和）。
 * 
 * 
 * 
 * 
 */

public class Total {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(3, new TotalTask());
		// 实际系统是查出所有省编码code的列表，然后循环，每个code生成一个线程
		new BillTask(barrier, "北京市").start();
		new BillTask(barrier, "上海市").start();
		new BillTask(barrier, "广西省").start();

		new BillTask(barrier, "天津市").start();
		new BillTask(barrier, "广东省").start();
		new BillTask(barrier, "福建省").start();

	}

}

class TotalTask implements Runnable { // 主任务：汇总任务 //当然了这里也可以再拆分为很多省份的任务
	public void run() {
		System.out.println("开始全国汇总");
	}
}

class BillTask extends Thread {
	private CyclicBarrier barrier;
	private String code; // 代码，按省代码分类，各省数据库独立

	BillTask(CyclicBarrier barrier, String code) {
		this.barrier = barrier;
		this.code = code;
	}

	public void run() {
		System.out.println("开始计算--" + code + "--数据!");
		System.out.println(code + "已经计算完成,并通知汇总Service! ");

		try {
			barrier.await(); // 通知barrier已经完成，最好放在final里面
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();

	}
}
