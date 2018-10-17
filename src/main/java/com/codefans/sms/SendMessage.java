package com.codefans.sms;

import org.smslib.AGateway;
import org.smslib.IOutboundMessageNotification;
import org.smslib.Library;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.Message.MessageEncodings;
import org.smslib.modem.SerialModemGateway;

public class SendMessage {
	Service srv;

	public void doIt() throws Exception {

		OutboundMessage msg;
		OutboundNotification outboundNotification = new OutboundNotification();
		System.out.println("Example: Send message from a serial gsm modem.");
		System.out.println(Library.getLibraryDescription());
		System.out.println("Version: " + Library.getLibraryVersion());
		srv = Service.getInstance();

		SerialModemGateway gateway = new SerialModemGateway("modem.com1", "COM1", 115200, "wavecom", "17254");
		gateway.setInbound(true);
		gateway.setOutbound(true);
		gateway.setSimPin("0000");
		// gateway.setOutboundNotification(outboundNotification);
		srv.addGateway(gateway);
		srv.startService();
		System.out.println("Modem Information:");
		System.out.println(" Manufacturer: " + gateway.getManufacturer());
		System.out.println(" Model: " + gateway.getModel());
		System.out.println(" Serial No: " + gateway.getSerialNo());
		System.out.println(" SIM IMSI: " + gateway.getImsi());
		System.out.println(" Signal Level: " + gateway.getSignalLevel() + "%");
		System.out.println(" Battery Level: " + gateway.getBatteryLevel() + "%");
		System.out.println();
		// Send a message synchronously.

		msg = new OutboundMessage("15010198727", "这个是用java发的中文短信!");// 手机号码，和短信内容
		msg.setEncoding(MessageEncodings.ENCUCS2);// 这句话是发中文短信必须的
		srv.sendMessage(msg);
		System.out.println(msg);
		System.out.println("Now Sleeping - Hit <enter> to terminate.");
		System.in.read();
		srv.stopService();
	}

	public class OutboundNotification implements IOutboundMessageNotification {
		public void process(String gatewayId, OutboundMessage msg) {
			System.out.println("Outbound handler called from Gateway: " + gatewayId);
			System.out.println(msg);
		}

		@Override
		public void process(AGateway arg0, OutboundMessage arg1) {
			// TODO Auto-generated method stub

		}
	}

	public static void main(String args[]) {
		SendMessage app = new SendMessage();
		try {
			app.doIt();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
