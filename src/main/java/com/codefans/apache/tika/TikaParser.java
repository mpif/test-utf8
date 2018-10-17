package com.codefans.apache.tika;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.io.FileUtils;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.Detector;
import org.apache.tika.language.LanguageIdentifier;
import org.apache.tika.language.LanguageProfile;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeTypes;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.utils.ParseUtils;
import org.xml.sax.ContentHandler;

/*
 * @Author: Sean
 * @Time: 2015-07-02 16:24:27
 */
public class TikaParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TikaParser tikaParser = new TikaParser();
		tikaParser.parse();
	}

	public void parse() {
		OutputStream os = null;
		try {
			String filename = "D:\\360云盘\\MessageSolution\\项目\\事前审计（PreAudit）\\SMTP错误码建议解决方法.pdf";

			String outFile = "D:\\360云盘\\MessageSolution\\项目\\事前审计（PreAudit）\\SMTP错误码建议解决方法.pdf.txt";
			File outf = new File(outFile);
			// if(!outf.exists()) {
			// outf.createNewFile();
			// }

			MimeTypes mimeRegistry = TikaConfig.getDefaultConfig().getMimeRepository();

			System.out.println("Examining: [" + filename + "]");

			System.out.println("The MIME type (based on filename) is: [" + mimeRegistry.getMimeType(filename) + "]");

			System.out.println(
					"The MIME type (based on MAGIC) is: [" + mimeRegistry.getMimeType(new File(filename)) + "]");

			Detector mimeDetector = (Detector) mimeRegistry;
			System.out.println("The MIME type (based on the Detector interface) is: ["
					+ mimeDetector.detect(new File(filename).toURI().toURL().openStream(), new Metadata()) + "]");

			LanguageIdentifier lang = new LanguageIdentifier(
					new LanguageProfile(FileUtils.readFileToString(new File(filename))));

			System.out.println("The language of this content is: [" + lang.getLanguage() + "]");

			Parser parser = TikaConfig.getDefaultConfig()
					.getParser(MediaType.parse(mimeRegistry.getMimeType(filename).getName()));
			Metadata parsedMet = new Metadata();
			ContentHandler handler = new BodyContentHandler();
			parser.parse(new File(filename).toURI().toURL().openStream(), handler, parsedMet, new ParseContext());

			System.out.println("Parsed Metadata: ");
			System.out.println(parsedMet);
			// System.out.println("Parsed Text: ");
			// System.out.println(handler.toString());

			os = new FileOutputStream(outf);
			writeTo(os, handler.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
					os = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void writeTo(OutputStream os, String content) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
		bw.write(content);
		bw.flush();
	}

}
