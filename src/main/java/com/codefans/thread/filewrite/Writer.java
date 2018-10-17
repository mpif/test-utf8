package com.codefans.thread.filewrite;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.codefans.util.UniqueStringGenerator;

public class Writer {

	private File tempFileDirectory = new File("");

	private File createTempFile(InputStream inputStream) {
		File tempFile = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			tempFile = File.createTempFile("getLargeAttachment-" + UniqueStringGenerator.getUniqueUUID(), ".eea",
					tempFileDirectory);
			if (tempFile == null) {
				return null;
			}

			bis = new BufferedInputStream(inputStream);
			bos = new BufferedOutputStream(new FileOutputStream(tempFile));

			byte[] buffer = new byte[4096];
			int len;
			while ((len = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			bos.flush();
		} catch (IOException ex) {
			deleteTempFile(tempFile);

			ex.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
					bis = null;
				}
				if (bos != null) {
					bos.close();
					bos = null;
				}
			} catch (Exception ex2) {
				ex2.printStackTrace();
			}
		}
		return tempFile;
	}

	private void deleteTempFile(File tempFile) {
		if (tempFile != null) {
			if (tempFile.exists()) {
				tempFile.delete();
				tempFile = null;
			}
		}
	}

}
