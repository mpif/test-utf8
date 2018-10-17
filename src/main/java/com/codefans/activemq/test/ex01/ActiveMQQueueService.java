package com.codefans.activemq.test.ex01;

import java.net.URI;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

/*
 * @Author: Sean
 * @Time: 2015-05-14 16:50:34
 */
public class ActiveMQQueueService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ActiveMQQueueService amqs = new ActiveMQQueueService();
		amqs.start();
	}

	public void start() {
		try {

			System.setProperty("activemq.base", "C:/activemqRootDir");

			System.out.println("AMQ  Service start......");
			String queueConf = "conf/activemq.xml";
			String confFilePath = "xbean:file:" + queueConf;// build/conf/activemq.xml
			// confFilePath = queueConf;

			System.out.println("AMQ Config File Path: " + confFilePath);
			URI brokerURI = new URI(confFilePath);
			final BrokerService amqService = BrokerFactory.createBroker(brokerURI);
			if (amqService != null) {
				amqService.setUseJmx(true);
				amqService.start();
				amqService.setUseShutdownHook(true);
			}
			System.out.println("AMQ  Service started successfully");

			// Runtime.getRuntime().addShutdownHook(new Thread() {
			// public void run()
			// {
			// try {
			// if (amqService != null)
			// {
			// System.out.println("begin call AMQ Service stopped amq broker
			// service.");
			// amqService.stop();
			// System.out.println("end call AMQ Service stopped amq broker
			// service.");
			// }
			// } catch (Exception e) {
			// System.out.println("stopped amq broker service." + e);
			// }
			// }
			// });
		} catch (Exception e) {
			System.out.println("AMQ Service have been stop!some errors occurred." + e);
		}
	}

}


