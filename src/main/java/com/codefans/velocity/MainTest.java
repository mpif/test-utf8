package com.codefans.velocity;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class MainTest {

	/**
	 * @return void
	 * @author caisz
	 */
	public static void main(String[] args) {
		/* first, get and initialize an engine */
		VelocityEngine ve = new VelocityEngine();
		ve.init();
		/* next, get the Template */
		Template t = ve.getTemplate("hello.vm");
		/* create a context and add data */
		VelocityContext context = new VelocityContext();
		context.put("name", "VerRan");
		context.put("site", "http://verran.iteye.com");
		/* now render the template into a StringWriter */
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		/* show the World */
		System.out.println(writer.toString());

	}

}
