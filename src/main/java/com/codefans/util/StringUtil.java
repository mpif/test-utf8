package com.codefans.util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.MimeUtility;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringUtil {
	public String filterChinese(String str) {
		StringBuilder sb = new StringBuilder("");
		if (str != null && str.startsWith("\"=?")) { // eg. String str =
														// "\"=?UTF-8?B?LuaZruWIqeeJuSDmsaQu5pmu5Yip54m5?=\"<tem.plate@new2010.local>";
			Pattern pattern = Pattern.compile("\"(.*?)\"");
			Matcher matcher = pattern.matcher(str);
			StringBuilder sbuilder = new StringBuilder();
			String temp = "";
			// try {
			while (matcher.find()) {
				temp = matcher.group(1);
				// sbuilder.append(MimeUtility.decodeText(temp));
			}
			// } catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
			// }
			int index = str.indexOf("?=\"");
			temp = str.substring(index + 3);
			sbuilder.append(temp);

			str = sbuilder.toString();
		}
		return sb.toString();
	}

	@Before
	public void before() {

	}

	@Test
	public void test() {
		// p(formatStr());

		// String str =
		// "��֧����Ϣ������Ա#���Ž�Ͷ֤ȯ<��֧����Ϣ������Ա#���Ž�Ͷ֤ȯ>,=?utf-8?B?5Lqk5piT57O757uf566h55CG57uEX+S/oeaBr+aKgOacr+mDqA==?=_���Ž�Ͷ֤ȯ(���Ӳ���)<����ϵͳ������_��Ϣ������_���Ž�Ͷ֤ȯ(���Ӳ���)>,=?utf-8?B?5r2Y5bu65LicPOa9mOW7uuS4nD4=?=,=?utf-8?B?56iL5pumPOeoi+abpj4=?=";
		// System.out.println(str);
		// System.out.println(str.replace("=?utf-8?B?5Lqk5piT57O757uf566h55CG57uEX+S/oeaBr+aKgOacr+mDqA==?=",
		// "����ϵͳ������_��Ϣ������"));
		// decodeSubStringByRegex(str);

		// String s =
		// "=?utf-8?B?5Lqk5piT57O757uf566h55CG57uEX+S/oeaBr+aKgOacr+mDqA==?=";
		// p(decodeSubString(s));

		// equals();

		// compareJarsFromString();

		setClassPath();

		System.out.println("aabbcc".split(";")[0]);
	}

	public void reduceClasspath() {
		String allJars = "C:/Program Files/MessageSolution/MEAHE/eas/lib/activation.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/activemq-all-5.1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/activemq-core-5.1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/activemq-web-5.1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ant-tomcat.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ant.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/antlr-2.7.6rc1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/aopalliance.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/asm-3.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/aspectjrt.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/aspectjweaver.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/axiom-api-1.2.7.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/axiom-impl-1.2.7.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/axis.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/axis2-adb-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/axis2-kernel-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/axis2_sharepoint_client.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/bcmail-jdk14-136.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/bcprov-jdk14-136.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/bfograph.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/bootstrap.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/c3p0-0.9.1.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/calendrica.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/cglib-nodep-2.1_3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/charset.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-beanutils.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-codec-1.3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-collections-3.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-configuration-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-dbcp.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-digester-1.7.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-discovery-0.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-fileupload-1.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-httpclient-3.0.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-httpclient-contrib.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-io-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-lang-2.3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-logging.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-pool-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-pool.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-vfs-manual.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/concurrent.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/core-3.1.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/DBPool_v4.8.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/diskpace.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/dnsjava-2.1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/dom.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/dom4j-1.5.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/dwr.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/dwr3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/eas.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ehcache-1.4.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/el-api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ews-1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/exchangeews2010.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/FastInfoset.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/fontbox-0.1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/FPLibrary.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/freemarker.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/geronimo-j2ee-management_1.0_spec-1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/geronimo-jms_1.1_spec-1.1.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/geronimo-jta_1.0.1B_spec-1.0.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/guice-1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/gwws.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/hessian-3.1.6.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/hibernate3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/htmlparser.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/icu4j-3_8.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/icu4j-charsets-3_8.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/iText-2.1.5.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/iTextAsian.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jakarta-oro-2.0.8.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jakarta-slide-webdavlib-2.1M1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jarsafe.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jasper-compiler.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jasper-el.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jasper-runtime.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jasper.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/javassist-3.9.0.GA.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaws-1.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jax-qname.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxb-api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxb-impl.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxb-libs.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxb-xjc.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxen-1.1.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxp-api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxrpc-api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxrpc-impl.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxrpc-spi.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxrpc.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jboss-common.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jboss-jmx.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jboss-remoting.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jboss-serialization.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jcifs-1.1.11.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jcommon-1.0.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jdom.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jdt.core_3.6.0.v_A58.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jempbox-0.2.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jetty-all-7.2.0.v20101020.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jfreechart-1.0.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jms.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/joda-time-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jrobin-1.5.9.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/json-lib-2.0-jdk15.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jsp-api-2.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jsr173_api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jstl.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jta-1.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/junit.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ldap.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ldapbp.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ldapjdk.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/log4j-1.2.15.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/lstnef.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/lucene-contrib.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/lucene-core.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/mail.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/mex-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/mysql-connector-java-3.1.10-bin.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/namespace.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/NCSO.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/neethi-2.0.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/nekohtml-1.9.9.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ognl.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ooxml-schemas-1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/openxml4j-1.0-beta.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/optional.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/oscore.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/OutsideInExport.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/pdfbox-0.7.3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/pell-multipart.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/poi-3.5-beta4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/poi-ooxml-3.5-beta4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/poi-scratchpad-3.5-beta4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/relaxngDatatype.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/resolver.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/rife-continuations.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/saaj-api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/saaj-impl.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/saaj.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/sax.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/serializer.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/servlet-api-2.5.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/slf4j-api-1.5.8.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/slf4j-simple-1.5.8.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/snmp4j-1.11.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/snmp4j-agent-1.4.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/spring-aop.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/spring-beans-2.5.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/spring-context-2.5.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/spring-context-support.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/spring-core-2.5.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/spring-jdbc.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/spring-orm-2.5.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/spring-tx.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/spring-web-2.5.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/spring-webmvc.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/standard.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/stax-api-1.0.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/tika-0.3-SNAPSHOT.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/tomcat-juli.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/trove.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/uninstaller.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/unrar0.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/utilities.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/webwork-2.2.7.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/webwork.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/woden-api-1.0M8.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/woden-impl-dom-1.0M8.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/wsdl4j-1.5.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/wstx-asl-3.2.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xalan.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xbean-spring-3.3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xercesImpl-2.8.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xercesImpl.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xercesSamples.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xml-apis.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xml-im-exporter1_0b1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xml-resolver-1.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xmlbeans-2.3.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/XmlSchema-1.4.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xsdlib.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xstream-1.2.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xwork.jar;";

	}

	public void setClassPath() {
		// String[] arr = new String[]{
		// "ant-tomcat.jar",
		// "aopalliance.jar",
		// "asm-3.1.jar",
		// "aspectjrt.jar",
		// "aspectjweaver.jar",
		// "bfograph.jar",
		// "bootstrap.jar",
		// "calendrica.jar",
		// "charset.jar",
		// "commons-configuration-1.4.jar",
		// "commons-dbcp.jar",
		// "commons-digester-1.7.jar",
		// "commons-httpclient-contrib.jar",
		// "commons-pool.jar",
		// "core-3.1.1.jar",
		// "jakarta-oro-2.0.8.jar",
		// "jakarta-slide-webdavlib-2.1M1.jar",
		// "jarsafe.jar",
		// "javassist-3.9.0.GA.jar",
		// "jempbox-0.2.0.jar",
		// "jms.jar",
		// "jrobin-1.5.9.1.jar",
		// "jta-1.1.jar",
		// "nekohtml-1.9.9.jar",
		// "ognl.jar",
		// "ooxml-schemas-1.0.jar",
		// "openxml4j-1.0-beta.jar",
		// "optional.jar",
		// "oscore.jar",
		// "pell-multipart.jar",
		// "resolver.jar",
		// "rife-continuations.jar",
		// "serializer.jar",
		// "slf4j-api-1.5.8.jar",
		// "slf4j-simple-1.5.8.jar",
		// "standard.jar",
		// "stax-api-1.0.1.jar",
		// "xercesImpl-2.8.1.jar",
		// "xercesImpl.jar",
		// "xercesSamples.jar",
		// "xml-apis.jar",
		// "xml-im-exporter1_0b1.jar",
		// "xwork.jar"
		// };

		String[] arr = null;
		String lib = "H:\\ewstool\\eas\\lib";
		getJarArray(lib);
		arr = jarNames.toArray(new String[0]);

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			sb.append("%LIB_PATH%/");
			sb.append(arr[i]);
			sb.append(";");
		}
		System.out.println(arr.length);
		System.out.println(sb.toString());

	}

	private List<String> jarNames = new ArrayList<String>();

	public void getJarArray(String path) {
		File p = new File(path);
		if (p.isDirectory()) {
			File[] files = p.listFiles();
			File file = null;
			for (int i = 0; i < files.length; i++) {
				file = files[i];
				if (file.isDirectory()) {
					if (!file.getName().equals("PreCompileJsp")) {
						getJarArray(path + File.separator + file.getName());
					}
				} else {
					jarNames.add(file.getName());
				}

			}
		}
	}

	public void compareJarsFromString() {
		// installer admin eas.ini
		// String jarStr01 = "C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/eas.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xmlbeans-2.3.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/mysql-connector-java-3.1.10-bin.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/DBPool_v4.8.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/joda-time-1.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/dom.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxb-api.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxb-impl.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxb-libs.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxb-xjc.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxp-api.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jax-qname.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/namespace.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/relaxngDatatype.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/sax.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xalan.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xsdlib.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/ldapjdk.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jdom.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jfreechart-1.0.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/activation.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jcifs-1.1.11.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/csdk.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/exchange.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/NCSO.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/Notes.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/ldapbp.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxrpc.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/saaj.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/wsdl4j-1.5.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/mail.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/ldap.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-codec-1.3.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-httpclient-3.0.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-collections-3.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-logging.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-fileupload-1.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-vfs-manual.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-lang-2.3.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-beanutils.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-discovery-0.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-io-1.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-codec.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-pool-1.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/ant.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/ehcache-1.4.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/guice-1.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/cglib-nodep-2.1_3.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xstream-1.2.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jboss-remoting.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/concurrent.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/trove.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jboss-common.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jboss-serialization.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/htmlparser.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/icu4j-3_8.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/icu4j-charsets-3_8.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/gwws.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/FastInfoset.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxrpc-api.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxrpc-spi.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxrpc-impl.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jsr173_api.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/saaj-api.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/saaj-impl.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/utilities.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/dom4j-1.5.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/hessian-3.1.6.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/poi-3.0.1-FINAL-20070705.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/poi-contrib-3.0.1-FINAL-20070705.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/poi-scratchpad-3.0.1-FINAL-20070705.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/pdfbox-0.7.3.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jboss-jmx.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/dwr.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/activemq-all-5.1.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/activemq-web-5.1.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/json-lib-2.0-jdk15.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jcommon-1.0.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/lucene-core.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/lucene-contrib.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/lstnef.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaws-1.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/iTextAsian.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/exchangeews2010.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxen-1.1.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/tomcat-juli.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/servlet-api-2.5.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jsp-api-2.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jdt.core_3.6.0.v_A58.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jasper-el.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jasper.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/el-api.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-fileupload-1.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/webwork.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jstl.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/log4j-1.2.15.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/c3p0-0.9.1.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/iText-2.1.5.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/iTextAsian.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/freemarker.jar;";
		String jarStr01 = "C:/Program Files/MessageSolution/MEAHE/eas/lib/eas.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xmlbeans-2.3.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/mysql-connector-java-3.1.10-bin.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/DBPool_v4.8.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/joda-time-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/dom.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxb-api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxb-impl.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxb-libs.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxb-xjc.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxp-api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jax-qname.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/namespace.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/relaxngDatatype.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/sax.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xalan.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xsdlib.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ldapjdk.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jdom.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jfreechart-1.0.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/activation.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jcifs-1.1.11.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/csdk.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/exchange.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/NCSO.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/Notes.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ldapbp.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/axis.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxrpc.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/saaj.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/wsdl4j-1.5.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/mail.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ldap.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-codec-1.3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-httpclient-3.0.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-collections-3.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-logging.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-fileupload-1.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-vfs-manual.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-lang-2.3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-beanutils.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-discovery-0.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-io-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-codec.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-pool-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ant.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ehcache-1.4.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/guice-1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/cglib-nodep-2.1_3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xstream-1.2.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jboss-remoting.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/concurrent.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/trove.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jboss-common.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jboss-serialization.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/htmlparser.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/icu4j-3_8.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/icu4j-charsets-3_8.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/gwws.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/FastInfoset.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxrpc-api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxrpc-spi.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxrpc-impl.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jsr173_api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/saaj-api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/saaj-impl.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/utilities.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/dom4j-1.5.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/hessian-3.1.6.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/poi-3.0.1-FINAL-20070705.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/poi-contrib-3.0.1-FINAL-20070705.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/poi-scratchpad-3.0.1-FINAL-20070705.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/pdfbox-0.7.3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jboss-jmx.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/dwr.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/activemq-all-5.1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/activemq-web-5.1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/json-lib-2.0-jdk15.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jcommon-1.0.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/lucene-core.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/lucene-contrib.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/lstnef.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaws-1.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/iTextAsian.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/exchangeews2010.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxen-1.1.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/tomcat-juli.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/servlet-api-2.5.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jsp-api-2.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jdt.core_3.6.0.v_A58.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jasper-el.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jasper.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/el-api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-fileupload-1.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/webwork.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jstl.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/log4j-1.2.15.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/c3p0-0.9.1.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/iText-2.1.5.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/iTextAsian.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/freemarker.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/antlr-2.7.6rc1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/axiom-api-1.2.7.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/axiom-impl-1.2.7.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/axis2-adb-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/axis2-kernel-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/geronimo-j2ee-management_1.0_spec-1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/geronimo-jms_1.1_spec-1.1.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/geronimo-jta_1.0.1B_spec-1.0.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/mex-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/neethi-2.0.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/woden-api-1.0M8.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/woden-impl-dom-1.0M8.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/wstx-asl-3.2.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xml-resolver-1.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/XmlSchema-1.4.2.jar;";
		// System.out.println(jarStr01.contains("antlr-2.7.6rc1.jar"));
		// System.out.println(jarStr01.contains("axiom-api-1.2.7.jar"));
		// System.out.println(jarStr01.contains("axiom-impl-1.2.7.jar"));
		// System.out.println(jarStr01.contains("axis2-adb-1.4.jar"));
		// System.out.println(jarStr01.contains("axis2-kernel-1.4.jar"));
		// System.out.println(jarStr01.contains("geronimo-j2ee-management_1.0_spec-1.0.jar"));
		// System.out.println(jarStr01.contains("geronimo-jms_1.1_spec-1.1.1.jar"));
		// System.out.println(jarStr01.contains("geronimo-jta_1.0.1B_spec-1.0.1.jar"));
		// System.out.println(jarStr01.contains("mex-1.4.jar"));
		// System.out.println(jarStr01.contains("neethi-2.0.4.jar"));
		// System.out.println(jarStr01.contains("woden-api-1.0M8.jar"));
		// System.out.println(jarStr01.contains("woden-impl-dom-1.0M8.jar"));
		// System.out.println(jarStr01.contains("wstx-asl-3.2.4.jar"));
		// System.out.println(jarStr01.contains("xml-resolver-1.2.jar"));
		// System.out.println(jarStr01.contains("XmlSchema-1.4.2.jar"));

		// String axisJarStr =
		// "axis-ant.jar;axis.jar;commons-discovery-0.2.jar;commons-logging-1.0.4.jar;jaxrpc.jar;log4j-1.2.8.jar;saaj.jar;wsdl4j-1.5.1.jar;";
		// String[] axisJars = axisJarStr.split(";");
		// for(int i = 0; i < axisJars.length; i ++) {
		// if(!jarStr01.contains(axisJars[i])) {
		// System.out.println(axisJars[i]);
		// }
		// }

		// antlr-2.7.6rc1.jar
		// axiom-api-1.2.7.jar
		// axiom-impl-1.2.7.jar
		// axis2-adb-1.4.jar
		// axis2-kernel-1.4.jar
		//
		// geronimo-j2ee-management_1.0_spec-1.0.jar
		// geronimo-jms_1.1_spec-1.1.1.jar
		// geronimo-jta_1.0.1B_spec-1.0.1.jar
		// mex-1.4.jar
		// neethi-2.0.4.jar
		//
		// woden-api-1.0M8.jar
		// woden-impl-dom-1.0M8.jar
		// wstx-asl-3.2.4.jar
		// xml-resolver-1.2.jar
		// XmlSchema-1.4.2.jar

		// installer eas eas.ini
		// String jarStr001 = "C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/eas.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/mysql-connector-java-3.1.10-bin.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/sqljdbc.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/DBPool_v4.8.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/joda-time-1.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/dom.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxb-api.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxb-impl.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxb-libs.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxb-xjc.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/namespace.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/sax.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xalan.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/ldapjdk.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jdom.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jfreechart-1.0.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/activation.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jcifs-1.1.11.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/csdk.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/exchange.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/NCSO.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/Notes.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/ldapbp.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/dwr.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-codec-1.3.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-httpclient-3.0.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-collections-3.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-logging.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-fileupload-1.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-vfs-manual.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-lang-2.3.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-beanutils.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-discovery-0.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-io-1.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-codec.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/ant.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jakarta-oro-2.0.8.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-pool-1.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/poi-scratchpad-3.5-beta4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/poi-ooxml-3.5-beta4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/poi-3.5-beta4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/pdfbox-0.7.3.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/asm-3.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/bcmail-jdk14-136.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/bcprov-jdk14-136.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/fontbox-0.1.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jempbox-0.2.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/nekohtml-1.9.9.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/ooxml-schemas-1.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/openxml4j-1.0-beta.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/stax-api-1.0.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/tika-0.3-SNAPSHOT.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/log4j-1.2.15.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/ehcache-1.4.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/guice-1.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/cglib-nodep-2.1_3.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xstream-1.2.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jboss-remoting.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/concurrent.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/trove.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jboss-common.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jboss-serialization.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jboss-jmx.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/htmlparser.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/icu4j-3_8.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/icu4j-charsets-3_8.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/gwws.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/FastInfoset.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jax-qname.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxp-api.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxrpc-api.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxrpc-spi.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxrpc-impl.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jsr173_api.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/mail.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/relaxngDatatype.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/saaj-api.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/saaj-impl.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xsdlib.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/ldap.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/utilities.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxrpc.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/wsdl4j-1.5.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/activemq-core-5.1.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xbean-spring-3.3.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/geronimo-j2ee-management_1.0_spec-1.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/geronimo-jms_1.1_spec-1.1.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/geronimo-jta_1.0.1B_spec-1.0.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/spring-core-2.5.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/spring-beans-2.5.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/spring-context-2.5.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axiom-api-1.2.7.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axiom-impl-1.2.7.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-adb-1.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-kernel-1.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2_sharepoint_client.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxen-1.1.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/mex-1.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/neethi-2.0.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/woden-impl-dom-1.0M8.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/wstx-asl-3.2.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xercesImpl-2.8.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xml-resolver-1.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xmlbeans-2.3.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/XmlSchema-1.4.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/woden-api-1.0M8.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/FPLibrary.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/calendrica.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/core-3.1.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/diskpace.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/dom4j-1.5.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jetty-util-6.1.9.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/json-lib-2.0-jdk15.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/optional.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/pell-multipart.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/pell-multipart.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/iText-2.1.5.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/iTextAsian.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jcommon-1.0.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaws-1.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/unrar0.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/lucene-core.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/lucene-contrib.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/lstnef.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/exchangeews2010.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/tomcat-juli.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/servlet-api-2.5.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jsp-api-2.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jdt.core_3.6.0.v_A58.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jasper-el.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jasper.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/el-api.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-fileupload-1.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/webwork.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jstl.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/c3p0-0.9.1.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/dnsjava-2.1.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/freemarker.jar;";

		// System.out.println(jarStr001.contains("spring-beans-2.5.1.jar"));
		// System.out.println(jarStr001.contains("jakarta-oro-2.0.8.jar"));

		// all axis depend
		// String jarStr02 = "C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/activation-1.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/antlr-2.7.7.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/apache-mime4j-core-0.7.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axiom-api-1.2.13.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axiom-dom-1.2.13.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axiom-impl-1.2.13.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-adb-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-adb-codegen-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-ant-plugin-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-clustering-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-codegen-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-corba-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-fastinfoset-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-java2wsdl-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-jaxbri-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-jaxws-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-jibx-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-json-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-kernel-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-metadata-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-mtompolicy-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-saaj-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-soapmonitor-servlet-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-spring-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-transport-http-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-transport-local-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-xmlbeans-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/bcel-5.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-cli-1.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-codec-1.3.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-fileupload-1.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-httpclient-3.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-io-1.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-logging-1.1.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/geronimo-annotation_1.0_spec-1.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/geronimo-jaxws_2.2_spec-1.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/geronimo-jta_1.1_spec-1.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/geronimo-saaj_1.3_spec-1.0.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/geronimo-stax-api_1.0_spec-1.0.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/geronimo-ws-metadata_2.0_spec-1.1.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/httpcore-4.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jalopy-1.5rc3.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxb-api-2.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxb-impl-2.1.7.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxb-xjc-2.1.7.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxen-1.1.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxws-tools-2.1.3.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jettison-1.0-RC2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jibx-bind-1.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jibx-run-1.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jsr311-api-1.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/juli-6.0.16.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/log4j-1.2.15.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/mail-1.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/mex-1.6.2-impl.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/neethi-3.0.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/regexp-1.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/tribes-6.0.16.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/woden-api-1.0M9.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/woden-impl-commons-1.0M9.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/woden-impl-dom-1.0M9.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/wsdl4j-1.6.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/wstx-asl-3.2.9.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xalan-2.7.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xml-resolver-1.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xmlbeans-2.3.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/XmlSchema-1.4.7.jar;";

		// success jars
		// String jarStr02 = "C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/activation.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/activemq-core-5.1.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/ant.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/antlr-2.7.6rc1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/aopalliance.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/asm-3.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/aspectjrt.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/aspectjweaver.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axiom-api-1.2.7.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axiom-impl-1.2.7.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-adb-1.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2-kernel-1.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/axis2_sharepoint_client.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/bcmail-jdk14-136.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/bcprov-jdk14-136.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/bfograph.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/bloomBergBlog.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/bloomBergIb.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/bloomBergMsg.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/bootstrap.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/c3p0-0.9.1.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/calendrica.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/cglib-nodep-2.1_3.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/charset.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-beanutils.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-codec-1.3.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-collections-3.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-compress-1.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-configuration-1.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-dbcp.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-digester-1.7.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-discovery-0.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-fileupload-1.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-httpclient-3.0.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-httpclient-contrib.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-io-1.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-lang-2.3.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-logging.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-net-2.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-pool-1.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-pool.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/commons-vfs-manual.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/concurrent.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/DBPool_v4.8.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/diskpace.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/dnsjava-2.1.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/dom.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/dom4j-1.5.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/dwr.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/dwr3.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/eas.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/ehcache-1.4.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/el-api.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/ews-1.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/exchangeews2010.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/FastInfoset.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/fontbox-0.1.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/FPLibrary.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/freemarker.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/geronimo-j2ee-management_1.0_spec-1.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/geronimo-jms_1.1_spec-1.1.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/geronimo-jta_1.0.1B_spec-1.0.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/guice-1.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/gwws.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/hessian-3.1.6.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/hibernate3.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/htmlparser.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/icu4j-3_8.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/icu4j-charsets-3_8.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/iText-2.1.5.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/iTextAsian.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jakarta-oro-2.0.8.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jakarta-slide-webdavlib-2.1M1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jarsafe.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jasper-el.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jasper.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/javassist-3.9.0.GA.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaws-1.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jax-qname.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxb-api.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxb-impl.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxb-libs.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxb-xjc.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxen-1.1.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxp-api.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxrpc-api.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxrpc-impl.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxrpc-spi.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jaxrpc.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jboss-common.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jboss-jmx.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jboss-remoting.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jboss-serialization.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jcifs-1.1.11.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jcommon-1.0.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jdom.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jdt.core_3.6.0.v_A58.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jempbox-0.2.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jetty-all-7.2.0.v20101020.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jfreechart-1.0.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jms.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/joda-time-1.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jrobin-1.5.9.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/json-lib-2.0-jdk15.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jsp-api-2.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jsr173_api.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jstl.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/jta-1.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/junit.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/ldap.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/ldapbp.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/ldapjdk.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/log4j-1.2.15.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/lstnef.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/lucene-contrib.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/lucene-core.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/mail.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/mex-1.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/mysql-connector-java-3.1.10-bin.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/namespace.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/NCSO.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/neethi-2.0.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/nekohtml-1.9.9.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/ognl.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/ooxml-schemas-1.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/openxml4j-1.0-beta.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/optional.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/oscore.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/OutsideInExport.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/pdfbox-0.7.3.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/pell-multipart.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/pinyin4j-1.1.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/poi-3.5-beta4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/poi-ooxml-3.5-beta4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/poi-scratchpad-3.5-beta4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/relaxngDatatype.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/resolver.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/rife-continuations.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/saaj-api.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/saaj-impl.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/saaj.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/sax.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/serializer.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/servlet-api-2.5.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/slf4j-api-1.5.8.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/slf4j-simple-1.5.8.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/snmp4j-1.11.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/snmp4j-agent-1.4.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/spring-aop.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/spring-beans-2.5.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/spring-context-2.5.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/spring-context-support.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/spring-core-2.5.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/spring-jdbc.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/spring-orm-2.5.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/spring-tx.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/spring-web-2.5.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/spring-webmvc.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/standard.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/stax-api-1.0.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/tar.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/tika-0.3-SNAPSHOT.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/tomcat-juli.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/trove.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/uninstaller.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/unrar0.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/utilities.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/webwork-2.2.7.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/webwork.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/woden-api-1.0M8.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/woden-impl-dom-1.0M8.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/wsdl4j-1.5.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/wstx-asl-3.2.4.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xalan.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xbean-spring-3.3.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xercesImpl-2.8.1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xercesSamples.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xml-apis.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xml-im-exporter1_0b1.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xml-resolver-1.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xmlbeans-2.3.0.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/XmlSchema-1.4.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xsdlib.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xstream-1.2.2.jar;C:/Program
		// Files/MessageSolution/MEAHE/eas/lib/xwork.jar;";

		// installer all jars
		String jarStr02 = "C:/Program Files/MessageSolution/MEAHE/eas/lib/activation.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/activemq-all-5.1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/activemq-core-5.1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/activemq-web-5.1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ant-tomcat.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ant.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/antlr-2.7.6rc1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/aopalliance.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/asm-3.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/aspectjrt.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/aspectjweaver.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/axiom-api-1.2.7.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/axiom-impl-1.2.7.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/axis.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/axis2-adb-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/axis2-kernel-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/axis2_sharepoint_client.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/bcmail-jdk14-136.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/bcprov-jdk14-136.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/bfograph.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/bootstrap.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/c3p0-0.9.1.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/calendrica.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/cglib-nodep-2.1_3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/charset.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-beanutils.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-codec-1.3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-collections-3.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-configuration-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-dbcp.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-digester-1.7.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-discovery-0.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-fileupload-1.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-httpclient-3.0.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-httpclient-contrib.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-io-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-lang-2.3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-logging.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-pool-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-pool.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/commons-vfs-manual.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/concurrent.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/core-3.1.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/DBPool_v4.8.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/diskpace.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/dnsjava-2.1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/dom.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/dom4j-1.5.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/dwr.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/dwr3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/eas.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ehcache-1.4.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/el-api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ews-1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/exchangeews2010.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/FastInfoset.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/fontbox-0.1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/FPLibrary.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/freemarker.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/geronimo-j2ee-management_1.0_spec-1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/geronimo-jms_1.1_spec-1.1.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/geronimo-jta_1.0.1B_spec-1.0.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/guice-1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/gwws.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/hessian-3.1.6.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/hibernate3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/htmlparser.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/icu4j-3_8.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/icu4j-charsets-3_8.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/iText-2.1.5.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/iTextAsian.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jakarta-oro-2.0.8.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jakarta-slide-webdavlib-2.1M1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jarsafe.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jasper-compiler.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jasper-el.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jasper-runtime.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jasper.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/javassist-3.9.0.GA.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaws-1.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jax-qname.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxb-api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxb-impl.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxb-libs.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxb-xjc.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxen-1.1.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxp-api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxrpc-api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxrpc-impl.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxrpc-spi.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jaxrpc.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jboss-common.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jboss-jmx.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jboss-remoting.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jboss-serialization.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jcifs-1.1.11.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jcommon-1.0.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jdom.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jdt.core_3.6.0.v_A58.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jempbox-0.2.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jetty-all-7.2.0.v20101020.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jfreechart-1.0.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jms.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/joda-time-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jrobin-1.5.9.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/json-lib-2.0-jdk15.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jsp-api-2.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jsr173_api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jstl.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/jta-1.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/junit.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ldap.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ldapbp.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ldapjdk.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/log4j-1.2.15.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/lstnef.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/lucene-contrib.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/lucene-core.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/mail.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/mex-1.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/mysql-connector-java-3.1.10-bin.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/namespace.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/NCSO.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/neethi-2.0.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/nekohtml-1.9.9.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ognl.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/ooxml-schemas-1.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/openxml4j-1.0-beta.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/optional.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/oscore.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/OutsideInExport.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/pdfbox-0.7.3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/pell-multipart.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/poi-3.5-beta4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/poi-ooxml-3.5-beta4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/poi-scratchpad-3.5-beta4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/relaxngDatatype.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/resolver.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/rife-continuations.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/saaj-api.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/saaj-impl.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/saaj.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/sax.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/serializer.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/servlet-api-2.5.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/slf4j-api-1.5.8.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/slf4j-simple-1.5.8.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/snmp4j-1.11.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/snmp4j-agent-1.4.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/spring-aop.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/spring-beans-2.5.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/spring-context-2.5.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/spring-context-support.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/spring-core-2.5.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/spring-jdbc.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/spring-orm-2.5.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/spring-tx.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/spring-web-2.5.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/spring-webmvc.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/standard.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/stax-api-1.0.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/tika-0.3-SNAPSHOT.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/tomcat-juli.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/trove.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/uninstaller.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/unrar0.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/utilities.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/webwork-2.2.7.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/webwork.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/woden-api-1.0M8.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/woden-impl-dom-1.0M8.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/wsdl4j-1.5.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/wstx-asl-3.2.4.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xalan.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xbean-spring-3.3.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xercesImpl-2.8.1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xercesImpl.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xercesSamples.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xml-apis.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xml-im-exporter1_0b1.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xml-resolver-1.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xmlbeans-2.3.0.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/XmlSchema-1.4.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xsdlib.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xstream-1.2.2.jar;C:/Program Files/MessageSolution/MEAHE/eas/lib/xwork.jar;";

		String[] arr01 = jarStr01.split(";");
		String[] arr02 = jarStr02.split(";");

		String tmp = "";
		int count = 0;

		System.out.println("exists in jarStr01, but not exists in jarStr02:");
		for (int i = 0; i < arr01.length; i++) {
			tmp = arr01[i];
			if (!jarStr02.contains(tmp)) {
				count++;
				System.out.println(tmp.substring(tmp.lastIndexOf("/") + 1));
			}
		}
		System.out.println("total:[" + count + "]");
		count = 0;

		System.out.println("exists in jarStr02, but not exists in jarStr01:");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr02.length; i++) {
			tmp = arr02[i];
			if (!jarStr01.contains(tmp)) {
				count++;
				sb.append(tmp.substring(tmp.lastIndexOf("/") + 1)).append(";");
				System.out.println(tmp.substring(tmp.lastIndexOf("/") + 1));
			}
		}
		System.out.println(sb.toString());
		System.out.println("total:[" + count + "]");

	}

	public void deleteDouble() {
		List tableList = new ArrayList();
		tableList.add("hello");
		tableList.add("hell0");
		tableList.add("world");
		tableList.add("world");
		tableList.add(2);
		tableList.add(2);
		tableList.add(true);
		tableList.add(true);
		HashSet hs = new HashSet(tableList);
		// System.out.println(hs.toString());
		// System.out.println(tableList);
		Iterator i = hs.iterator();
		while (i.hasNext()) {
			Object temp = i.next();
			System.out.println(temp.toString());
		}
	}

	public void equals() {
		// String one =
		// "AQMkADJiNTNlOGVjLTZmYmMtNGZjZi04YjM3LWFhADViZjdlYjY4NWIARgAAA+dWEH6kOT1Mqx/tIZmhstQHACtGWf4DkHlGictQ765TZw8AAAIrUAAAAATjicgp1PVHsCgpyFCm0n8AAAK8/gAAAAESABAAtqWejycOXEG2yk7qzgoHLQ==";
		// String another =
		// "AQMkADJiNTNlOGVjLTZmYmMtNGZjZi04YjM3LWFhADViZjdlYjY4NWIARgAAA+dWEH6kOT1Mqx/tIZmhstQHACtGWf4DkHlGictQ765TZw8AAAIrUAAAAATjicgp1PVHsCgpyFCm0n8AAAK8/gAAAAESABAAqaMqy3MN60+/qtUMXB9Fig==";
		String one = "AQMkADJiNTNlOGVjLTZmYmMtNGZjZi04YjM3LWFhADViZjdlYjY4NWIARgAAA+dWEH6kOT1Mqx/tIZmhstQHACtGWf4DkHlGictQ765TZw8AAAIrUAAAAATjicgp1PVHsCgpyFCm0n8AAAK8/gAAAA==";
		String another = "AQMkADJiNTNlOGVjLTZmYmMtNGZjZi04YjM3LWFhADViZjdlYjY4NWIARgAAA+dWEH6kOT1Mqx/tIZmhstQHACtGWf4DkHlGictQ765TZw8AAAIrUAAAAATjicgp1PVHsCgpyFCm0n8AAAK8/gAAAA==";
		System.out.println(one.equals(another));
	}

	public int decodeSubString(String str) {
		char[] sourceArray = str.toCharArray();
		StringBuilder sb = new StringBuilder();

		String GBK_PREFIX = "=?GBK?B?";
		String UTF8_PREFIX = "=?utf-8?B?";
		int prefixLength = 0;
		String PREFIX = "";
		if (str.indexOf(GBK_PREFIX) >= 0) {
			PREFIX = GBK_PREFIX;
			prefixLength = GBK_PREFIX.length();
		} else if (str.indexOf(UTF8_PREFIX) >= 0) {
			PREFIX = UTF8_PREFIX;
			prefixLength = UTF8_PREFIX.length();
		}

		String SUFFIX = "?=";
		int sourceLen = str.length();
		char[] preArray = PREFIX.toCharArray();
		int index = 0;
		int subIndex = 0;
		for (int i = 0; i < sourceArray.length; i++) {
			char c = sourceArray[i];
			if (c == preArray[subIndex]) {
				if (subIndex == prefixLength - 1) {
					index = i - (prefixLength - 1);
					break;
				} else {
					subIndex++;
				}
			} else {
				subIndex = 0;
			}
		}

		return index;
	}

	public String decodeSubStringByRegex(String str) {

		String regex = "";
		try {
			if (str.indexOf("=?GBK?B?") >= 0) {
				regex = "=\\?GBK?B?(.*?)\\?=";
			} else if (str.indexOf("=?utf-8?B?") >= 0) {
				regex = "=\\?utf-8\\?B\\?(.*?)\\?=";
			}
			Pattern pattern = Pattern.compile(regex);
			Matcher match = pattern.matcher(str);
			Map<String, String> map = new HashMap<String, String>();
			String temp = "";
			while (match.find()) {
				temp = match.group();
				map.put(temp, MimeUtility.decodeText(temp));
				// System.out.println(temp);
				// System.out.println(MimeUtility.decodeText(temp));
			}

			Iterator<String> iter = map.keySet().iterator();
			String key = "";
			String value = "";
			while (iter.hasNext()) {
				key = iter.next();
				value = map.get(key);
				str = str.replace(key, value);
			}

			System.out.println(str);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	}

	public String findSubString(String source, String subString) {
		return null;
	}

	// "aaa", "bbb", "ccc" ---> \"aaa\", \"bbb\", \"ccc\"
	public String formatStr() {
		BufferedReader br = this.getBufferedReader();
		String temp = "", result = "";
		try {
			int num = 0;
			while ((temp = br.readLine()) != null) {
				num++;
				// p("temp: " + temp);
				// p("");
				result = temp.replaceAll("\"", "\\\\\"");
				// p("temp: " + temp);
			}
			p("����" + num + "��");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
					br = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// p("temp: " + temp);
		return result;
	}

	public BufferedReader getBufferedReader() {
		InputStream is = StringUtil.class.getResourceAsStream("str.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		return br;
	}

	@After
	public void after() {

	}

	public void p(Object o) {
		System.out.println(o);
	}
}
