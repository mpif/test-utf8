package com.codefans.dns;

/**
 * Created by IntelliJ IDEA. User: JackWang Date: 13-1-12
 */
public class MailHost implements java.lang.Comparable, java.io.Serializable {

	private static final long serialVersionUID = 2914841027514369058L;

	private String host;
	private int priority;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public int compareTo(Object o) {
		return this.priority - ((MailHost) o).getPriority();
	}

	public String toString() {
		return "[host:" + host + ",priority:" + priority + "]";
	}
}
