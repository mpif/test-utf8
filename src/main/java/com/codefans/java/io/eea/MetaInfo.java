package com.codefans.java.io.eea;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 14-3-18 Time: 上午10:53
 * To change this template use File | Settings | File Templates.
 */
public interface MetaInfo extends Serializable {
	public boolean isCompressed();

	public void setCompressed(boolean compressed);

	public void writeSelf(OutputStream out) throws IOException;

	public Object readSelf(InputStream in) throws IOException;
}
