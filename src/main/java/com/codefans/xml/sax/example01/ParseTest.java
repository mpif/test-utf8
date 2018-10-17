package com.codefans.xml.sax.example01;

import java.io.InputStream;
import java.util.List;

public class ParseTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SaxParseService sax = new SaxParseService();
			InputStream input = ParseTest.class.getResourceAsStream("book.xml");
			List<Book> books = sax.getBooks(input);
			for (Book book : books) {
				System.out.println(book.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
