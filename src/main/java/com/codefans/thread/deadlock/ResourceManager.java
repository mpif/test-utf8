package com.codefans.thread.deadlock;

/**
 * 
 * 
 * 项目名称：ThreadDeadLock 类名称：ResourceManager 类描述：资源管理类，对资源数据操作的接口 创建人：Defonds
 * 创建时间：2010-1-26 下午02:04:59 修改人：Defonds 修改时间：2010-1-26 下午02:04:59 修改备注：
 * 
 * @version
 *
 */
public class ResourceManager {

	/**
	 * 管理的两个资源
	 */
	private Resource resourceA = new Resource();
	private Resource resourceB = new Resource();

	/**
	 * 创建一个新的实例 ResourceManager.
	 */
	public ResourceManager() {
		this.resourceA.setValue(0);
		this.resourceB.setValue(0);
	}

	/**
	 * 资源的读取
	 */
	public int read() {
		synchronized (this.resourceA) {
			System.out.println(Thread.currentThread().getName() + "线程拿到了资源 resourceA 的对象锁");
			synchronized (resourceB) {
				System.out.println(Thread.currentThread().getName() + "线程拿到了资源 resourceB 的对象锁");
				return this.resourceA.getValue() + this.resourceB.getValue();
			}
		}
	}

	/**
	 * 资源的改写
	 */
	public void write(int a, int b) {
		synchronized (this.resourceB) {
			System.out.println(Thread.currentThread().getName() + "线程拿到了资源 resourceB 的对象锁");
			synchronized (this.resourceA) {
				System.out.println(Thread.currentThread().getName() + "线程拿到了资源 resourceA 的对象锁");
				this.resourceA.setValue(a);
				this.resourceB.setValue(b);
			}
		}
	}
}