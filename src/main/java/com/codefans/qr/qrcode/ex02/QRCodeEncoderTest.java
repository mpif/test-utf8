package com.codefans.qr.qrcode.ex02;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class QRCodeEncoderTest {
	public static void main(String[] args) throws Exception {
		QRCodeEncoderTest encoder = new QRCodeEncoderTest();
		String[] encodeStrs = new String[] { "http://mail.qq.com/cgi-bin/d?k=fgoiyIOF" };
		encoder.encode(encodeStrs);
	}

	public void encode(String[] encodeStrs) {
		try {

			if (encodeStrs != null) {
				String testString = "";
				for (int k = 0; k < encodeStrs.length; k++) {
					Qrcode qrcode = new Qrcode();
					qrcode.setQrcodeErrorCorrect('M');
					qrcode.setQrcodeEncodeMode('B');
					qrcode.setQrcodeVersion(7);

					// String testString = "_-+^%$#@!~`=&)*(";
					// String testString =
					// "http://mail.qq.com/cgi-bin/d?k=fgoiyIOF";
					testString = encodeStrs[k];

					byte[] d = testString.getBytes("GBK");

					BufferedImage bi = new BufferedImage(139, 139, BufferedImage.TYPE_INT_RGB);

					// createGraphics
					Graphics2D g = bi.createGraphics();

					// set background
					g.setBackground(Color.WHITE);
					g.clearRect(0, 0, 139, 139);

					g.setColor(Color.BLACK);

					if (d.length > 0 && d.length < 123) {
						boolean[][] b = qrcode.calQrcode(d);

						for (int i = 0; i < b.length; i++) {

							for (int j = 0; j < b.length; j++) {
								if (b[j][i]) {
									g.fillRect(j * 3 + 2, i * 3 + 2, 3, 3);
								}
							}

						}
					}

					g.dispose();
					bi.flush();

					String FilePath = "";
					if (encodeStrs.length == 1) {
						FilePath = "C:/TestQRCode.png";
					} else {
						FilePath = "C:/TestQRCode" + k + ".png";
					}

					File f = new File(FilePath);

					ImageIO.write(bi, "png", f);
					System.out.println("doned!");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
