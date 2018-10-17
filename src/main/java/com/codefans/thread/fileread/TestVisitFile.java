package com.codefans.thread.fileread;

public class TestVisitFile {
	public static void main(String[] args) {
		String filename = "xjs";
		VisitFile vf = new VisitFile();
		TestVisitFileWriter tfw = new TestVisitFileWriter();
		TestVisitFileReader tfr = new TestVisitFileReader();
		tfw.setFilename(filename);
		tfw.setVf(vf);
		tfr.setFilename(filename);
		tfr.setVf(vf);
		new Thread(tfw).start();
		new Thread(tfr).start();
	}

}
