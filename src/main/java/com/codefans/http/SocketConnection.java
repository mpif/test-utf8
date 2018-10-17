package com.codefans.http;

// package com.messagesolution.exchangeews.soapservice;
//
// import java.io.BufferedInputStream;
// import java.io.BufferedOutputStream;
// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.io.OutputStream;
// import java.io.OutputStreamWriter;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.util.List;
// import java.util.Map;
//
// import javax.net.ssl.HttpsURLConnection;
// import javax.net.ssl.SSLContext;
// import javax.net.ssl.TrustManager;
//
// import org.apache.log4j.Logger;
//
// import com.messagesolution.exchangeews.ExchangeEWSConst;
//
//
//
// public class SocketConnection {
//
// /**
// * record log
// */
// private Logger logger = Logger.getLogger(SocketConnection.class);
//
// private static int socketConnectCount = 0;
//
// private static Object obj = new Object();
//
// private HttpURLConnection conn = null;
//
// private boolean isDebug = true;
//
// /**
// * set timeout for socket 30min
// */
// private static int defaultConnectTimeout = 1800000;
// /**
// * set default Read Timeout 5min
// */
// private static int defaultReadTimeout = 300000;
//
// private void addConnectCount() {
// synchronized(obj) {
// socketConnectCount++;
// debugPrint("SocketConnection: connection count is " + socketConnectCount);
// }
// }
// private void subConnectCount() {
// synchronized(obj) {
// socketConnectCount--;
// debugPrint("SocketConnection: connection count is " + socketConnectCount);
// }
// }
//
// public SocketMessage sendMessage(SocketMessage soapMessage) {
// if(soapMessage == null) {
// logger.error("sendMessage parameter is null");
// return null;
// }
//
// //addConnectCount();
//
// return connect(soapMessage);
// }
//
// public SocketMessage sendMessage(SocketMessage soapMessage, InputStream
// inputStream) {
// if(soapMessage == null) {
// logger.error("sendMessage parameter is null");
// return null;
// }
//
// //addConnectCount();
//
// return connect(soapMessage, inputStream);
// }
//
// /**
// * connect remote webservice
// * @param soapMessage
// * @return
// */
// private SocketMessage connect(SocketMessage soapMessage) {
// SocketMessage responseMessage = null;
//
// if(soapMessage.getHttpInfo().isUseSSL()) {
// responseMessage = httpsRequest(soapMessage);
// } else {
// responseMessage = httpRequest(soapMessage);
// }
// logger.debug("Http state:" + responseMessage.getResponseCode());
// return responseMessage;
// }
//
// private SocketMessage connect(SocketMessage soapMessage, InputStream
// inputStream) {
// SocketMessage responseMessage = null;
//
// if(soapMessage.getHttpInfo().isUseSSL()) {
// responseMessage = httpsRequest(soapMessage, inputStream);
// } else {
// responseMessage = httpRequest(soapMessage, inputStream);
// }
// logger.debug("Http state:" + responseMessage.getResponseCode());
// return responseMessage;
// }
//
// /**
// * send message use http
// * @param soapMessage
// * @return
// */
// @SuppressWarnings("unchecked")
// private SocketMessage httpRequest(SocketMessage soapMessage) {
// SocketMessage responseMessage = new SocketMessage();
// String endPointURL = soapMessage.getHttpInfo().getAccessEWSURL();
// Map<String, String> requestHeader = soapMessage.getRequestHeader();
// OutputStream outputStream = null;
// InputStream inputStream = null;
// BufferedOutputStream bos = null;
// BufferedInputStream bis = null;
// URL url = null;
// try {
// url = new URL(endPointURL);
// conn = (HttpURLConnection) url.openConnection();
// conn.setConnectTimeout(defaultConnectTimeout);
// conn.setReadTimeout(defaultReadTimeout);
// conn.setDoInput(true);
// conn.setDoOutput(true);
//
// for(Map.Entry<String, String> header : requestHeader.entrySet()) {
// conn.setRequestProperty(header.getKey(), header.getValue());
// }
//
// conn.setRequestMethod(soapMessage.getRequestMethod());
// conn.connect();
//
// outputStream = conn.getOutputStream();
// bos = new BufferedOutputStream(outputStream);
// bos.write(soapMessage.getRequestContent().getBytes(ExchangeEWSConst.UTF_8));
// bos.flush();
//
// responseMessage.setResponseCode(conn.getResponseCode());
// responseMessage.setResponseMessage(conn.getResponseMessage());
//
// //responseMessage.setUrlConnection(conn);
//
// String key = "";
// String value = "";
// for(Map.Entry<String, List<String>> entry :
// conn.getHeaderFields().entrySet()) {
// key = entry.getKey();
// for(int i=0; i<entry.getValue().size(); i++) {
// value += entry.getValue().get(i);
// //System.out.println(value);
// }
// responseMessage.addResponseHeader(key, value);
// key = "";
// value = "";
// }
// } catch (Exception ex) {
// responseMessage.setResponseCode(500);
// responseMessage.setResponseMessage("error:"+ex.getMessage());
// ex.printStackTrace();
// } finally {
// closeResource(inputStream, outputStream, bis, bos);
// }
// return responseMessage;
// }
//
// /**
// * send message use http
// * @param soapMessage
// * @return
// */
// @SuppressWarnings("unchecked")
// private SocketMessage httpRequest(SocketMessage soapMessage, InputStream
// inputStream) {
// SocketMessage responseMessage = new SocketMessage();
// String endPointURL = soapMessage.getHttpInfo().getAccessEWSURL();
// Map<String, String> requestHeader = soapMessage.getRequestHeader();
// OutputStream outputStream = null;
// BufferedOutputStream bos = null;
// BufferedInputStream bis = null;
// //HttpURLConnection conn = null;
// URL url = null;
// BufferedReader reader = null;
// BufferedWriter writer = null;
//
// try {
// url = new URL(endPointURL);
// conn = (HttpURLConnection) url.openConnection();
// conn.setConnectTimeout(defaultConnectTimeout);
// conn.setReadTimeout(defaultReadTimeout);
// conn.setDoInput(true);
// conn.setDoOutput(true);
//
// for(Map.Entry<String, String> header : requestHeader.entrySet()) {
// conn.setRequestProperty(header.getKey(), header.getValue());
// }
//
// conn.setRequestMethod(soapMessage.getRequestMethod());
// conn.connect();
//
// outputStream = conn.getOutputStream();
//// bos = new BufferedOutputStream(outputStream);
//// bis = new BufferedInputStream(inputStream);
//// byte[] buffer = new byte[4096];
//// int len;
//// while ((len = bis.read(buffer)) != -1) {
//// bos.write(buffer, 0, len);
//// }
//// bos.flush();
//
// //bufferedReader
// reader = new BufferedReader(new InputStreamReader(inputStream,
// ExchangeEWSConst.UTF_8));
// writer = new BufferedWriter(new OutputStreamWriter(outputStream,
// ExchangeEWSConst.UTF_8));
//
// String line = null;
// while((line=reader.readLine()) != null) {
// writer.write(line);
// writer.newLine();
// }
// writer.flush();
//
// responseMessage.setResponseCode(conn.getResponseCode());
// responseMessage.setResponseMessage(conn.getResponseMessage());
//
// //responseMessage.setUrlConnection(conn);
//
// String key = "";
// String value = "";
// for(Map.Entry<String, List<String>> entry :
// conn.getHeaderFields().entrySet()) {
// key = entry.getKey();
// for(int i=0; i<entry.getValue().size(); i++) {
// value += entry.getValue().get(i);
// //System.out.println(value);
// }
// responseMessage.addResponseHeader(key, value);
// key = "";
// value = "";
// }
// } catch (Exception ex) {
// responseMessage.setResponseCode(500);
// responseMessage.setResponseMessage("error");
// ex.printStackTrace();
// } finally {
// closeResource(inputStream, outputStream, bis, bos);
// closeResource(reader, writer);
// }
// return responseMessage;
// }
//
// /**
// * send message use https
// * @param soapMessage
// * @return SOAPMessage
// */
// @SuppressWarnings("unchecked")
// private SocketMessage httpsRequest(SocketMessage soapMessage) {
// SocketMessage responseMessage = new SocketMessage();
// String endPointURL = soapMessage.getHttpInfo().getAccessEWSURL();
// Map<String, String> requestHeader = soapMessage.getRequestHeader();
// InputStream inputStream = null;
// OutputStream outputStream = null;
// BufferedOutputStream bos = null;
// BufferedInputStream bis = null;
// HttpsURLConnection conn = null;
// try {
// SSLContext sc = SSLContext.getInstance("SSL", "SunJSSE");
// sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new
// java.security.SecureRandom());
// URL url = new URL(endPointURL);
// conn = (HttpsURLConnection) url.openConnection();
// conn.setSSLSocketFactory(sc.getSocketFactory());
// conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
// conn.setConnectTimeout(defaultConnectTimeout);
// conn.setReadTimeout(defaultReadTimeout);
// conn.setDoInput(true);
// conn.setDoOutput(true);
//
// for(Map.Entry<String, String> header : requestHeader.entrySet()) {
// //System.out.println(header.getKey() + ":" + header.getValue());
// conn.addRequestProperty(header.getKey(), header.getValue());
// }
//
// conn.setRequestMethod(soapMessage.getRequestMethod());
// conn.connect();
//
// outputStream = conn.getOutputStream();
// bos = new BufferedOutputStream(outputStream);
// bos.write(soapMessage.getRequestContent().getBytes(ExchangeEWSConst.UTF_8));
// bos.flush();
//
// responseMessage.setResponseCode(conn.getResponseCode());
// responseMessage.setResponseMessage(conn.getResponseMessage());
//
// //responseMessage.setUrlConnection(conn);
// this.conn = conn;
// String key = "";
// String value = "";
// for(Map.Entry<String, List<String>> entry :
// conn.getHeaderFields().entrySet()) {
// key = entry.getKey();
// for(int i=0; i<entry.getValue().size(); i++) {
// value += entry.getValue().get(i);
// }
// responseMessage.addResponseHeader(key, value);
// key = "";
// value = "";
// }
// } catch (Exception ex) {
// //responseMessage.setResponseCode(500);
// //responseMessage.setResponseMessage("error");
// ex.printStackTrace();
// logger.error("httpsRequest", ex);
// } finally {
// closeResource(inputStream, outputStream, bis, bos);
// }
// return responseMessage;
// }
//
// private SocketMessage httpsRequest(SocketMessage soapMessage, InputStream
// inputStream) {
// SocketMessage responseMessage = new SocketMessage();
// String endPointURL = soapMessage.getHttpInfo().getAccessEWSURL();
// Map<String, String> requestHeader = soapMessage.getRequestHeader();
// OutputStream outputStream = null;
// BufferedOutputStream bos = null;
// BufferedInputStream bis = null;
// BufferedReader reader = null;
// BufferedWriter writer = null;
// try {
// SSLContext sc = SSLContext.getInstance("SSL", "SunJSSE");
// sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new
// java.security.SecureRandom());
// URL url = new URL(endPointURL);
// HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
// conn.setSSLSocketFactory(sc.getSocketFactory());
// conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
// conn.setConnectTimeout(defaultConnectTimeout);
// conn.setReadTimeout(defaultReadTimeout);
// conn.setDoInput(true);
// conn.setDoOutput(true);
// for(Map.Entry<String, String> header : requestHeader.entrySet()) {
// //System.out.println(header.getKey() + ":" + header.getValue());
// conn.addRequestProperty(header.getKey(), header.getValue());
// }
//
// conn.setRequestMethod(soapMessage.getRequestMethod());
// conn.connect();
//
// outputStream = conn.getOutputStream();
//// bos = new BufferedOutputStream(outputStream);
//// bis = new BufferedInputStream(inputStream);
////
//// byte[] buffer = new byte[4096];
//// int len;
//// while ((len = bis.read(buffer)) > 0) {
//// bos.write(buffer, 0, len);
//// }
//// bos.flush();
//
// //bufferedReader
// reader = new BufferedReader(new InputStreamReader(inputStream,
// ExchangeEWSConst.UTF_8));
// writer = new BufferedWriter(new OutputStreamWriter(outputStream,
// ExchangeEWSConst.UTF_8));
//
// String line = null;
// while((line=reader.readLine()) != null) {
// writer.write(line);
// writer.newLine();
// }
// writer.flush();
//
// responseMessage.setResponseCode(conn.getResponseCode());
// responseMessage.setResponseMessage(conn.getResponseMessage());
//
// //responseMessage.setUrlConnection(conn);
// this.conn = conn;
// String key = "";
// String value = "";
// for(Map.Entry<String, List<String>> entry :
// conn.getHeaderFields().entrySet()) {
// key = entry.getKey();
// for(int i=0; i<entry.getValue().size(); i++) {
// value += entry.getValue().get(i);
// }
// responseMessage.addResponseHeader(key, value);
// key = "";
// value = "";
// }
// } catch (Exception ex) {
// //responseMessage.setResponseCode(500);
// //responseMessage.setResponseMessage("error");
// ex.printStackTrace();
// logger.error("httpsRequest", ex);
// } finally {
// closeResource(inputStream, outputStream, bis, bos);
// closeResource(reader, writer);
// }
// return responseMessage;
// }
//
// /**
// * close connect resource
// * @param is
// * @param os
// * @param bos
// */
// private void closeResource(InputStream is, OutputStream os,
// BufferedInputStream bis, BufferedOutputStream bos) {
// try {
// if(bos != null) {
// bos.close();
// bos = null;
// }
// if(bis != null) {
// bis.close();
// bis = null;
// }
// if(os != null) {
// os.close();
// os = null;
// }
// if(is != null) {
// is.close();
// is = null;
// }
// } catch (IOException ex) {
// ex.printStackTrace();
// }
// }
//
// private void closeResource(BufferedReader reader, BufferedWriter writer) {
// try {
// if(reader != null) {
// reader.close();
// reader = null;
// }
// if(writer != null) {
// writer.close();
// writer = null;
// }
// } catch (IOException ex) {
// ex.printStackTrace();
// }
// }
//
// public void closeSocket() {
// if(conn != null) {
// //subConnectCount();
// //logger.debug("===================conn.disconnect()===========================");
// conn.disconnect();
// conn = null;
// }
// }
//
// public HttpURLConnection getConnection() {
// return conn;
// }
//
// private void debugPrint(String debugLog) {
// if(isDebug) {
// System.out.println(debugLog);
// }
// }
// }
