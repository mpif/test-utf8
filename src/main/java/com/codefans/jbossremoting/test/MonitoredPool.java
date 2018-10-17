/**
 * 
 */
package com.codefans.jbossremoting.test;

import java.util.Map;

/**
 * @author luping
 *
 */
public interface MonitoredPool {
	public Map<String, GeneralMO> getMonitors() throws KeyNotFoundExcepton;
}
