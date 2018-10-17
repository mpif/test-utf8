package com.codefans.thread.fileread;

public class TestVisitFileWriter implements Runnable {
	private String filename;
	private VisitFile vf;

	public TestVisitFileWriter() {
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setVf(VisitFile vf) {
		this.vf = vf;
	}

	public VisitFile getVisitFile() {
		return this.vf;
	}

	public void run() {
		int count = 0;
		while (count++ < 100) {
			String data1[] = new String[] { "zxx", "swq", "lsf" };
			String data2[] = new String[] { "张孝祥", "孙卫琴", "罗时飞" };
			if (count % 2 == 0)
				vf.setDateToFile(filename, data1);
			else
				vf.setDateToFile(filename, data2);
		}
	}

}
