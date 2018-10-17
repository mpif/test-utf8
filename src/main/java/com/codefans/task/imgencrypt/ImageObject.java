package com.codefans.task.imgencrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class ImageObject extends RandomAccessFileByteIoBase {

	public static String PREFIX = "PREFIX";
	public static String SUFFIX = "SUFFIX";
	public static String firstMidPart = "firstMidPart";
	public static String secondMidPart = "secondMidPart";

	private String sourceFilePath;
	private String destFilePath;

	public RandomAccessFile raf = null;
	public InputStream sourceStream;
	public OutputStream out;
	public long sourceLength = 0;

	public ImageObject(RandomAccessFile raf) {
		this.raf = raf;
	}

	@Deprecated
	public ImageObject(RandomAccessFile raf, InputStream sourceStream, long sourceLength) {
		this.raf = raf;
		this.sourceStream = sourceStream;
		this.sourceLength = sourceLength;
	}

	public ImageObject(String sourceFilePath, String destFilePath) {

		if (sourceFilePath == null || sourceFilePath.trim().length() == 0) {
			throw new IllegalArgumentException("sourceFilePath can not be empty!");
		}

		if (destFilePath == null || destFilePath.trim().length() == 0) {
			throw new IllegalArgumentException("destFilePath can not be empty!");
		}

		this.sourceFilePath = sourceFilePath;
		this.destFilePath = destFilePath;

	}

	@Deprecated
	public ImageObject(RandomAccessFile raf, OutputStream out, long encryptFileLength) {
		this.raf = raf;
		this.out = out;
		try {
			raf.seek(encryptFileLength - 8);
			this.sourceLength = raf.readLong();
			raf.seek(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeSelf() {
		try {

			File sourceFile = new File(sourceFilePath);
			long length = sourceFile.length();
			this.raf = new RandomAccessFile(destFilePath, "rw");
			;
			this.sourceStream = new FileInputStream(sourceFile);
			;
			this.sourceLength = length;

			this.writeString(raf, ImageObject.PREFIX);

			// String filePath = "";
			this.writeInt(raf, sourceFilePath.length());
			this.writeString(raf, sourceFilePath);

			if (sourceLength >= 25) {
				int n = 0;
				byte[] bytes = new byte[1024];

				long firstLen = this.getFirstMidPartOffset();
				long oneLen = firstLen;
				if (firstLen > 1024) {
					bytes = new byte[1024];
				} else {
					bytes = new byte[(int) firstLen];
				}

				boolean lastTime = false;
				while ((n = sourceStream.read(bytes)) != -1) {
					raf.write(bytes, 0, n);
					if (!lastTime) {
						firstLen -= n;
						if (firstLen > 1024) {
							bytes = new byte[1024];
						} else {
							bytes = new byte[(int) firstLen];
							lastTime = true;
						}
					} else {
						break;
					}
				}
				this.writeString(raf, ImageObject.firstMidPart);

				long secondLen = this.getSecondMidPartOffset() - this.getFirstMidPartOffset();
				long twoLen = secondLen;
				if (secondLen > 1024) {
					bytes = new byte[1024];
				} else {
					bytes = new byte[(int) secondLen];
				}
				lastTime = false;
				while ((n = sourceStream.read(bytes)) != -1) {
					raf.write(bytes, 0, n);
					if (!lastTime) {
						secondLen -= n;
						if (secondLen > 1024) {
							bytes = new byte[1024];
						} else {
							bytes = new byte[(int) secondLen];
							lastTime = true;
						}
					} else {
						break;
					}
				}
				this.writeString(raf, ImageObject.secondMidPart);

				long thirdLen = sourceLength - this.getSecondMidPartOffset();
				long threeLen = thirdLen;
				if (thirdLen > 1024) {
					bytes = new byte[1024];
				} else {
					bytes = new byte[(int) thirdLen];
				}
				lastTime = false;
				while ((n = sourceStream.read(bytes)) != -1) {
					raf.write(bytes, 0, n);
					if (!lastTime) {
						thirdLen -= n;
						if (thirdLen > 1024) {
							bytes = new byte[1024];
						} else {
							bytes = new byte[(int) thirdLen];
							lastTime = true;
						}
					} else {
						break;
					}
				}

				System.out.println("sourceLength:[" + sourceLength + "]");
				System.out.println("firstLen:[" + firstLen + "]");
				System.out.println("secondLen:[" + secondLen + "]");
				System.out.println("thirdLen:[" + thirdLen + "]");
				System.out.println("firstLen:[" + oneLen + "]");
				System.out.println("secondLen:[" + twoLen + "]");
				System.out.println("thirdLen:[" + threeLen + "]");
				System.out.println("(firstLen + secondLen + thirdLen):[" + (oneLen + twoLen + threeLen) + "]");
				System.out.println(sourceLength == (oneLen + twoLen + threeLen));

			} else {
				int n = 0;
				byte[] bytes = new byte[1024];
				while ((n = sourceStream.read(bytes)) != -1) {
					raf.write(bytes, 0, n);
				}
			}

			// if(sourceLength >= 25) {
			// this.writeString(raf, ImageObject.secondMidPart);
			// }

			this.writeString(raf, ImageObject.SUFFIX);

			raf.writeLong(sourceLength);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (sourceStream != null) {
					sourceStream.close();
					sourceStream = null;
				}
				if (raf != null) {
					raf.close();
					raf = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void readSelf() {
		try {

			File sourceFile = new File(sourceFilePath);
			long encryptFileLength = sourceFile.length();

			this.raf = new RandomAccessFile(sourceFile, "r");
			// this.out = new FileOutputStream(destFilePath);
			try {
				raf.seek(encryptFileLength - 8);
				this.sourceLength = raf.readLong();
				raf.seek(0);
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("Prefix:[" + this.readString(raf, ImageObject.PREFIX.length()) + "]");

			int sourcePathLen = this.readInt(raf);
			String oriFilePath = this.readString(raf, sourcePathLen);
			System.out.println("original file path:[" + oriFilePath + "]");

			if (destFilePath == null || destFilePath.trim().length() == 0) {
				destFilePath = oriFilePath;
			} else {
				destFilePath = destFilePath + File.separator
						+ oriFilePath.substring(oriFilePath.lastIndexOf(File.separator) + 1);
			}
			this.out = new FileOutputStream(destFilePath);

			if (sourceLength >= 25) {
				int n = 0;
				byte[] bytes = new byte[1024];

				long firstLen = this.getFirstMidPartOffset();
				long oneLen = firstLen;
				if (firstLen > 1024) {
					bytes = new byte[1024];
				} else {
					bytes = new byte[(int) firstLen];
				}

				boolean lastTime = false;
				while ((n = raf.read(bytes)) != -1) {
					out.write(bytes, 0, n);
					if (!lastTime) {
						firstLen -= n;
						if (firstLen > 1024) {
							bytes = new byte[1024];
						} else {
							bytes = new byte[(int) firstLen];
							lastTime = true;
						}
					} else {
						break;
					}
				}
				System.out.println(
						"firstMidPartString:[" + this.readString(raf, ImageObject.firstMidPart.length()) + "]");

				long secondLen = this.getSecondMidPartOffset() - this.getFirstMidPartOffset();
				long twoLen = secondLen;
				if (secondLen > 1024) {
					bytes = new byte[1024];
				} else {
					bytes = new byte[(int) secondLen];
				}
				lastTime = false;
				while ((n = raf.read(bytes)) != -1) {
					out.write(bytes, 0, n);
					if (!lastTime) {
						secondLen -= n;
						if (secondLen > 1024) {
							bytes = new byte[1024];
						} else {
							bytes = new byte[(int) secondLen];
							lastTime = true;
						}
					} else {
						break;
					}
				}
				System.out.println(
						"secondMidPartString:[" + this.readString(raf, ImageObject.secondMidPart.length()) + "]");

				long thirdLen = sourceLength - this.getSecondMidPartOffset();
				long threeLen = thirdLen;
				if (thirdLen > 1024) {
					bytes = new byte[1024];
				} else {
					bytes = new byte[(int) thirdLen];
				}
				lastTime = false;
				while ((n = raf.read(bytes)) != -1) {
					out.write(bytes, 0, n);
					if (!lastTime) {
						thirdLen -= n;
						if (thirdLen > 1024) {
							bytes = new byte[1024];
						} else {
							bytes = new byte[(int) thirdLen];
							lastTime = true;
						}
					} else {
						break;
					}
				}

				System.out.println("sourceLength:[" + sourceLength + "]");
				System.out.println("firstLen:[" + firstLen + "]");
				System.out.println("secondLen:[" + secondLen + "]");
				System.out.println("thirdLen:[" + thirdLen + "]");
				System.out.println("firstLen:[" + oneLen + "]");
				System.out.println("secondLen:[" + twoLen + "]");
				System.out.println("thirdLen:[" + threeLen + "]");
				System.out.println("(firstLen + secondLen + thirdLen):[" + (oneLen + twoLen + threeLen) + "]");
				System.out.println(sourceLength == (oneLen + twoLen + threeLen));

			} else {
				int n = 0;
				byte[] bytes = new byte[(int) sourceLength];
				while ((n = raf.read(bytes)) != -1) {
					out.write(bytes, 0, n);
				}
			}

			System.out.println("Suffix:[" + this.readString(raf, ImageObject.SUFFIX.length()) + "]");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (sourceStream != null) {
					sourceStream.close();
					sourceStream = null;
				}
				if (raf != null) {
					raf.close();
					raf = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public long getFirstMidPartOffset() {
		return this.getMidPartOffset(ImageObject.firstMidPart);
		// return ObjectHeader.PREFIX.length() +
		// this.getMidPartOffset(ObjectHeader.firstMidPart);
	}

	public long getSecondMidPartOffset() {
		return this.getMidPartOffset(ImageObject.secondMidPart);
		// return ObjectHeader.PREFIX.length() +
		// ObjectHeader.firstMidPart.length() +
		// this.getMidPartOffset(ObjectHeader.secondMidPart);
	}

	public long getMidPartOffset(String midPart) {
		long offset = 0;

		if (ImageObject.firstMidPart.equals(midPart)) {
			if (sourceLength < 25) {
				offset = 0;
			} else {
				long mid = 0;
				if (sourceLength % 2 == 0) {
					mid = sourceLength / 2;
				} else {
					mid = (1 + sourceLength) / 2;
				}
				long firstMid = 0;
				if (mid % 2 == 0) {
					firstMid = mid / 2;
				} else {
					firstMid = (1 + mid) / 2;
				}
				offset = firstMid + 5;
			}
		} else if (ImageObject.secondMidPart.equals(midPart)) {
			if (sourceLength < 25) {
				offset = sourceLength;
			} else {
				long mid = 0;
				if (sourceLength % 2 == 0) {
					mid = sourceLength / 2;
				} else {
					mid = (1 + sourceLength) / 2;
				}
				long secondMid = 0;
				if ((sourceLength - mid) % 2 == 0) {
					secondMid = mid + (sourceLength - mid) / 2;
				} else {
					secondMid = mid + (sourceLength - mid + 1) / 2;
				}
				offset = secondMid + 5;
			}
		}
		return offset;
	}

}
