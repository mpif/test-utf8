package com.codefans.qr.qrcode.ex02;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

public class QRCodeDecoderTest {

	public QRCodeDecoderTest() {
	}

	public static void main(String[] args) {

		QRCodeDecoderTest decoder = new QRCodeDecoderTest();
		String[] imageFiles = new String[] {
				// "C:/TestQRCode.png"
				// "C:/qr_images/firefox.jpg",
				"C:/Users/Administrator/Pictures/QQ图片20150915123023.jpg"
				// "C:/qr_images/QQBrowser.jpg"
		};
		decoder.decodes(imageFiles);

	}

	public void decodes(String[] imageFiles) {

		try {

			QRCodeDecoder decoder = new QRCodeDecoder();
			if (imageFiles != null) {
				BufferedImage image = null;
				File imageFile = null;
				for (int i = 0; i < imageFiles.length; i++) {
					imageFile = new File(imageFiles[i]);
					image = ImageIO.read(imageFile);
					String decodedData = new String(decoder.decode(new J2SEImage(image)), "GBK");
					System.out.println(decodedData);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

class J2SEImage implements QRCodeImage {
	BufferedImage image;

	public J2SEImage(BufferedImage image) {
		this.image = image;
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}

	public int getPixel(int x, int y) {
		return image.getRGB(x, y);
	}

}
