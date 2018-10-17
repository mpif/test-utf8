package com.codefans.snmp;

import java.io.IOException;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class MainTest {

	/**
	 * @author caisz
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// 设定CommunityTarget
			CommunityTarget myTarget = new CommunityTarget();
			// Address deviceAdd = GenericAddress.parse("udp:10.5.24.37/161");
			Address deviceAdd = GenericAddress.parse("udp:10.0.0.50/5006");
			myTarget.setAddress(deviceAdd);
			myTarget.setCommunity(new OctetString("public"));
			myTarget.setRetries(2);
			myTarget.setTimeout(5 * 60);
			myTarget.setVersion(SnmpConstants.version2c);// org.snmp4j.mp.*;
			// 设定采取的协议
			TransportMapping transport1 = new DefaultUdpTransportMapping();
			Snmp protocol = new Snmp(transport1);
			transport1.listen();
			// 获取mib
			PDU request = new PDU();
			String oidstr = "1.3.6.1.2.1.4.20";
			VariableBinding var = new VariableBinding(new OID(oidstr));
			request.add(var);
			request.setType(PDU.GETNEXT);
			ResponseEvent responseEvent = protocol.send(request, myTarget);
			PDU response = responseEvent.getResponse();
			// 输出
			if (response != null) {
				System.out.println("request.size()=" + request.size());
				System.out.println("response.size()=" + response.size());
				VariableBinding vb = response.get(0);
				System.out.println(vb);
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return;
		}
		test();
	}

	public static void test() {
		try {
			Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
			CommunityTarget target = new CommunityTarget();// agent对象
			target.setCommunity(new OctetString("chenxuan"));// 设置共同体名,没发现设置RWCommnity的方
			// 法,大概只能设一个.
			target.setVersion(SnmpConstants.version2c);// 设置版本
			target.setAddress(new UdpAddress("127.0.0.1/161"));// 设置IP地址和端口号,这里竟然用'/'来分
			// 隔,当初确实没有料到,JDOC的说明等于没有.
			target.setRetries(1); // 设置重试次数
			target.setTimeout(5000); // 设置超时

			snmp.listen(); // 监听
			PDU request = new PDU(); // new request PDU包
			// set pud type and set oid
			request.setType(PDU.GET); // 设置PDU类型,
			request.add(new VariableBinding(new OID(".1.3.6.1.2.1.1.1.0"))); // OID添加
			request.add(new VariableBinding(new OID(".1.3.6.1.2.1.1.2.0")));
			System.out.println("request UDP:" + request);// 请求包内内容输出,
			PDU response = null;// 定义response包
			ResponseEvent responseEvent = snmp.send(request, target); // 发出request
																		// PDU
			// 接收response PDU
			response = responseEvent.getResponse();

			// response PDU包解析
			if (response != null) {
				if (response.getErrorIndex() == response.noError && response.getErrorStatus() == response.noError) {
					System.out.println("no error.");
					String pause = responseEvent.getResponse().getVariableBindings().toString();
					String getvalue = pause.substring(pause.indexOf("= ") + 2, pause.indexOf(']'));
					String oid = pause.substring(pause.indexOf("VBS[") + 2, pause.indexOf("=") - 1);
					System.out.println(oid + "::");
					System.out.println(response);

				} else {
					System.out.println("get error:" + response.getErrorStatusText());
				}

			} else {
				System.out.println("get response error");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
