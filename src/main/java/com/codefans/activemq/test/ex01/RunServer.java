package com.codefans.activemq.test.ex01;

import java.io.File;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.jmx.ManagementContext;

/*
 * @Author: Sean
 * @Time: 2015-06-17 07:33:57
 */
public class RunServer {
	// public static void main(String[] args) throws Exception {
	//
	// RunServer rs = new RunServer();
	//
	// 06 BrokerService broker = rs.startServer();
	//
	// 07 }
	//
	// 08
	//
	// 09 public BrokerService startServer() throws Exception{
	//
	// 10 // java代码调用activemq相关的类来构造并启动brokerService
	//
	// 11 BrokerService broker = new BrokerService();
	//
	// 12
	//
	// 13 // 以下是持久化的配置
	//
	// 14 // 持久化文件存储位置
	//
	// 15 File dataFilterDir = new File("targer/amq-in-action/kahadb");
	//
	// 16 KahaDBStore kaha = new KahaDBStore();
	//
	// 17 kaha.setDirectory(dataFilterDir);
	//
	// 18 // use a bigger journal file
	//
	// 19 kaha.setJournalMaxFileLength(1024*100);
	//
	// 20 // small batch means more frequent and smaller writes
	//
	// 21 kaha.setIndexWriteBatchSize(100);
	//
	// 22 // do the index write in a separate thread
	//
	// 23 kaha.setEnableIndexWriteAsync(true);
	//
	// 24
	//
	// 25 broker.setPersistenceAdapter(kaha);
	//
	// 26 // create a transport connector
	//
	// 27 broker.addConnector("tcp://localhost:61616");
	//
	// 28 broker.setUseJmx(true);
	//
	// 29 //broker.setDataDirectory("data/");
	//
	// 30
	//
	// 31
	//
	// 32 // 以下是ManagementContext的配置，从这个容器中可以取得消息队列中未执行的消息数、消费者数、出队数等等
	//
	// 33 // 设置ManagementContext
	//
	// 34 ManagementContext context = broker.getManagementContext();
	//
	// 35 context.setConnectorPort(2011);
	//
	// 36 context.setJmxDomainName("my-broker");
	//
	// 37 context.setConnectorPath("/jmxrmi");
	//
	// 38 broker.start();
	//
	// 39 System.in.read();
	//
	// 40 return broker;

	public static String jmxDomain = "jms-broker";
	public static int connectorPort = 2011;
	public static String connectorPath = "/jmxrmi";

	public static String queueName = "activemq_queue";

	/** 启动activeMQ服务 */
	public static void main(String[] args) throws Exception {
		// java代码调用activemq相关的类来构造并启动brokerService
		BrokerService broker = new BrokerService();

		// 以下是持久化的配置
		// 持久化文件存储位置
		// File dataFilterDir = new File("activemq/amq-in-action/kahadb");
		// KahaDBStore kaha = new KahaDBStore();
		// kaha.setDirectory(dataFilterDir);
		// // use a bigger journal file
		// kaha.setJournalMaxFileLength(1024*100);
		// // small batch means more frequent and smaller writes
		// kaha.setIndexWriteBatchSize(100);
		// // do the index write in a separate thread
		// kaha.setEnableIndexWriteAsync(true);
		//
		// broker.setPersistenceAdapter(kaha);
		// create a transport connector
		broker.addConnector("tcp://localhost:61616");
		broker.setUseJmx(true);

		// 以下是ManagementContext的配置，从这个容器中可以取得消息队列中未执行的消息数、消费者数、出队数等等
		// 设置ManagementContext
		ManagementContext context = broker.getManagementContext();
		context.setConnectorPort(connectorPort);
		context.setJmxDomainName(jmxDomain);
		context.setConnectorPath(connectorPath);
		broker.start();
	}
}
