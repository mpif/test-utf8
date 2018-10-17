package com.codefans.snmp;

import java.io.IOException;
import java.util.Vector;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SNMP_oper {
	public static void main(String[] args) {
		try {
			Address targetAddress = GenericAddress.parse("udp:10.0.0.50/161");
			TransportMapping transport = new DefaultUdpTransportMapping();
			Snmp snmp = new Snmp(transport);
			transport.listen();// 监听

			CommunityTarget target = new CommunityTarget();
			target.setCommunity(new OctetString("public"));// 设置共同体名
			target.setAddress(targetAddress);// 设置目标Agent地址
			target.setRetries(2);// 重试次数
			target.setTimeout(5000);// 超时设置
			target.setVersion(1);// 版本

			PDU request = new PDU();
			request.setType(PDU.GET);// 操作类型GET
			// request.add(new VariableBinding(new
			// OID(".1.3.6.1.2.1.1.1.0")));//OID_sysDescr , new
			// OctetString("sysDescr")
			// request.add(new VariableBinding(new
			// OID(".1.3.6.1.2.1.1.2.0")));//OID_sysObjectID , new
			// OctetString("sysObjectID")
			// request.add(new VariableBinding(new
			// OID(".1.3.6.1.2.1.1.3.0")));//OID_sysUpTime , new
			// OctetString("sysUpTime")
			// request.add(new VariableBinding(new
			// OID(".1.3.6.1.2.1.1.4.0")));// , new OctetString("sysContact")
			// request.add(new VariableBinding(new
			// OID(".1.3.6.1.2.1.1.5.0")));//, new OctetString("sysName")
			// request.add(new VariableBinding(new
			// OID(".1.3.6.1.2.1.1.6.0")));//, new OctetString("sysLocation")
			// request.add(new VariableBinding(new
			// OID(".1.3.6.1.2.1.1.7.0")));//, new OctetString("sysServices")
			// request.add(new VariableBinding(new
			// OID(".1.3.6.1.2.1.1.8.0")));//, new
			// OctetString("sysORLastChange")
			// request.add(new VariableBinding(new
			// OID(".1.3.6.1.2.1.1.4.0")));//, new OctetString("sysORTable")

			// Host Resource of MIB OID,
			// This is the MIB module HOST-RESOURCES-V2-MIB from Standards /
			// RFCs.
			// MIB HOST-RESOURCES-V2-MIB
			// hrStorage
			request.add(new VariableBinding(new OID(".1.3.6.1.2.1.25.0")));// host
			request.add(new VariableBinding(new OID(".1.3.6.1.2.1.25.1.0")));// hrSystem
			request.add(new VariableBinding(new OID(".1.3.6.1.2.1.25.2.0")));// hrStorage
			request.add(new VariableBinding(new OID(".1.3.6.1.2.1.25.2.1.0")));// hrStorageTypes
			request.add(new VariableBinding(new OID(".1.3.6.1.2.1.25.2.1.1.0")));// hrStorageOther
			request.add(new VariableBinding(new OID(".1.3.6.1.2.1.25.2.1.2.0")));// hrStorageRam
			request.add(new VariableBinding(new OID(".1.3.6.1.2.1.25.2.1.3.0")));// hrStorageVirtualMemory
			request.add(new VariableBinding(new OID(".1.3.6.1.2.1.25.2.1.4.0")));// hrStorageFixedDisk
			request.add(new VariableBinding(new OID(".1.3.6.1.2.1.25.2.1.5.0")));// hrStorageRemovableDisk
			request.add(new VariableBinding(new OID(".1.3.6.1.2.1.25.2.1.6.0")));// hrStorageFloppyDisk
			request.add(new VariableBinding(new OID(".1.3.6.1.2.1.25.2.1.7.0")));// hrStorageCompactDisc
			request.add(new VariableBinding(new OID(".1.3.6.1.2.1.25.2.1.8.0")));// hrStorageRamDisk
			// request.add(new VariableBinding(new
			// OID(".1.3.6.1.2.1.25.2.1.5.0")));//
			// request.add(new VariableBinding(new
			// OID(".1.3.6.1.2.1.25.2.1.5.0")));//
			// request.add(new VariableBinding(new
			// OID(".1.3.6.1.2.1.25.2.1.5.0")));//
			// request.add(new VariableBinding(new
			// OID(".1.3.6.1.2.1.25.2.1.5.0")));//

			System.out.println("Request UDP:" + request);

			ResponseEvent respEvt = snmp.send(request, target);

			// 读取得到的绑定变量
			if (respEvt != null && respEvt.getResponse() != null) {
				Vector<VariableBinding> revBindings = (Vector<VariableBinding>) respEvt.getResponse()
						.getVariableBindings();
				for (int i = 0; i < revBindings.size(); i++) {
					VariableBinding vbs = revBindings.elementAt(i);
					System.out.println(vbs.getOid() + ": " + vbs.getVariable());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
