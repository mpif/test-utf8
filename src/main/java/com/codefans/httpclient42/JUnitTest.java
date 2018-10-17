package com.codefans.httpclient42;

// package com.messagesolution.httpclient42;
//
// import java.io.BufferedInputStream;
// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.security.KeyStore;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.regex.Matcher;
// import java.util.regex.Pattern;
//
// import org.apache.http.Consts;
// import org.apache.http.HttpEntity;
// import org.apache.http.HttpResponse;
// import org.apache.http.NameValuePair;
// import org.apache.http.client.HttpClient;
// import org.apache.http.client.entity.UrlEncodedFormEntity;
// import org.apache.http.client.methods.HttpGet;
// import org.apache.http.client.methods.HttpPost;
//// import org.apache.http.conn.scheme.Scheme;
//// import org.apache.http.conn.ssl.SSLSocketFactory;
// import org.apache.http.conn.scheme.Scheme;
// import org.apache.http.conn.ssl.SSLSocketFactory;
// import org.apache.http.impl.client.DefaultHttpClient;
// import org.apache.http.message.BasicNameValuePair;
// import org.apache.http.util.EntityUtils;
// import org.junit.After;
// import org.junit.Before;
// import org.junit.Test;
//
// public class JUnitTest {
//
// @Before
// public void before() {
//
// }
//
// @Test
// public void test() {
// Integer i = 11;
// Integer ii = new Integer(11);
//
//// p(i.equals(ii));
//
//
// testHttpsClient();
//
//// this.testHTTPClient();
//
// }
//
// public void testHttpsClient() {
// try {
// //获得httpclient对象
// HttpClient httpclient = new DefaultHttpClient();
// //获得密匙库
// KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
// FileInputStream instream = new FileInputStream(new File("C:/.keystore"));
// //密匙库的密码
// trustStore.load(instream, "12345678".toCharArray());
// //注册密匙库
// SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);
// //不校验域名
// socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
// Scheme sch = new Scheme("https", 800, socketFactory);
// httpclient.getConnectionManager().getSchemeRegistry().register(sch);
// //获得HttpGet对象
// HttpGet httpGet = null;
// httpGet = new
// HttpGet("https://10.0.0.70:8888/Login.jp?userName=administrator&password=admin");
// //发送请求
// HttpResponse response = httpclient.execute(httpGet);
//
// int code = response.getStatusLine().getStatusCode();
// System.out.println("code:" + code);
//
// //输出返回值
//// InputStream is = response.getEntity().getContent();
//// BufferedReader br = new BufferedReader(new InputStreamReader(is));
//// String line = "";
//// while((line = br.readLine())!=null){
//// System.out.println(line);
//// }
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
//
// public void testHTTPClient() {
// try {
// String url = "http://localhost:8080/Login.jp?ifCheck=true";
// String adminName = "administrator";
// String password = "admin";
// String result = "";
//
// DefaultHttpClient httpclient = new DefaultHttpClient();
//
// HttpPost httpost = new HttpPost(url.toString());
// //"http://localhost:8080/Login.jp?ifCheck=true"
//
// List <NameValuePair> nvps = new ArrayList <NameValuePair>();
// nvps.add(new BasicNameValuePair("userName", adminName));//�൱��<input
// type="text" name="userName">
// nvps.add(new BasicNameValuePair("password", password)); //�൱��<input
// type="text" name="password">
////
// httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
//
// HttpResponse response = httpclient.execute(httpost);
// HttpEntity entity = response.getEntity();
//
// p("response: " + response.toString());
//
// BufferedReader br = new BufferedReader(new
// InputStreamReader(entity.getContent()));
// String temp = "";
// temp = br.readLine();
// if(temp != null && !"".equals(temp)) {
// if(temp.equalsIgnoreCase("serviceAlive")) {
// result = temp;
// p("response content: " + temp);
//// System.out.println("service is alive");
// }
// }
//
// if(response.getStatusLine().getStatusCode() != 200) {
// result = "loginFail";
// }
// EntityUtils.consume(entity);
// httpclient.getConnectionManager().shutdown();
// }catch (Exception e) {
// e.printStackTrace();
// p(e.getMessage());
// }
// }
//
// private void currentIp() {
// DefaultHttpClient httpclient = new DefaultHttpClient();
// try {
// String URL = "http://iframe.ip138.com/city.asp";
// HttpPost httpost = new HttpPost(URL);
// HttpResponse response = httpclient.execute(httpost);
//
// System.out.println("Login form get: " + response.getStatusLine());
// System.out.println("response toString(): " + response.toString());
//
// HttpEntity entity = response.getEntity();
// InputStream is = entity.getContent();
//
// BufferedInputStream bis = new BufferedInputStream(is);
// int n = 0;
// byte[] b = new byte[1024];
// StringBuilder sb = new StringBuilder();
//
// while((n = bis.read(b)) != -1) {
// sb.append(new String(b, 0, b.length));
// }
//
// String content = sb.toString();
// System.out.println("content: " + sb.toString());
// Pattern p = Pattern.compile("[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}");
// Matcher matcher = p.matcher(content);
// while(matcher.find()) {
// System.out.println("ip: " + matcher.group());
// }
// } catch (Exception e) {
// e.printStackTrace();
// } finally {
// httpclient.getConnectionManager().shutdown();
// }
// }
//
// private void access() {
// DefaultHttpClient httpclient = new DefaultHttpClient();
// try {
// String URL =
// "http://localhost:8080/webmail/ProcessMessage.jp?operation=delete";
// String ids =
// "&id=archive/20120726/0_1_20120726_199990/2d8fc4b2d0f764dad7491b1f5b6bec75&id=archive/20120727/0_1_20120727_111865/a792492871b97808dce76c9465256216";
// String hiddenids =
// "&hiddenId=archive/20120726/0_1_20120726_199990/2d8fc4b2d0f764dad7491b1f5b6bec75/831&hiddenId=archive/20120727/0_1_20120727_111865/a792492871b97808dce76c9465256216/842";
//// URL =
// "http://localhost:8080/webmail/ProcessMessage.jp?id=archive/20120727/0_1_20120727_107305/b51874f0cc467709b03f7852dd540c98";
// URL = URL + ids + hiddenids + "&storage=messagesolution";
// HttpPost httpost = new HttpPost(URL);
// HttpResponse response = httpclient.execute(httpost);
//
//// System.out.println("Login form get: " + response.getStatusLine());
//
// } catch (Exception e) {
// e.printStackTrace();
// } finally {
// // When HttpClient instance is no longer needed,
// // shut down the connection manager to ensure
// // immediate deallocation of all system resources
// httpclient.getConnectionManager().shutdown();
// }
// }
//
// @After
// public void after() {
//
// }
//
// public void p(Object o) {
// System.out.println(o);
// }
// }
