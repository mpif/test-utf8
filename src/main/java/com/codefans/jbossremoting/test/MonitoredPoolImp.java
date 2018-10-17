/**
 * 
 */
package com.codefans.jbossremoting.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author luping
 *
 */
public class MonitoredPoolImp implements MonitoredPool {

	Logger log = Logger.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.messagesolution.monitor.MonitoredPool#getMonitors()
	 */
	@Override
	public Map<String, GeneralMO> getMonitors() throws KeyNotFoundExcepton {
		Map<String, GeneralMO> mos = new HashMap<String, GeneralMO>();
		System.out.println("---------");
		return mos;
	}

	public MonitoredPoolImp() {
	}
}
