/**
 * 
 */
package com.codefans.jbossremoting.test;

import java.io.Serializable;

/**
 * @author luping
 *
 */
public class GeneralMO implements Serializable {
	private static final long serialVersionUID = 2941675985733829857L;
	private String id;
	private String numericId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumericId() {
		return numericId;
	}

	public void setNumericId(String numericId) {
		this.numericId = numericId;
	}

	public String getFatherId() {
		return id.substring(0, id.lastIndexOf('.'));
	}
}
