package com.codefans.thread.fileread;

public class TestVisitFileReader implements Runnable {
	private String filename;
	private VisitFile vf;

	public TestVisitFileReader() {
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
			Object results[] = vf.getQueryResult(filename);
		}
	}
}
