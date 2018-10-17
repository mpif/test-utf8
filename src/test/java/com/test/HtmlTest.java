package com.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class HtmlTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// URLEncoder encoder = URLEncoder.;
		String s = "";
		try {
			// s = URLEncoder.encode(getStr(), "utf-8");
			s = transferHtml(getStr());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(s);
	}

	private static String transferHtml(String source) {
		source = source.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		// .replaceAll("&apos;", "\'")
		// .replaceAll("&quot;", "\"")
		// .replaceAll("&nbsp;", " ")
		// .replaceAll("&copy;", "@")
		// .replaceAll("&reg;", "?");
		return source;
	}

	public static String getStr() {
		String str = "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"\r\n "
				+ "xmlns:w=\"urn:schemas-microsoft-com:office:word\" xmlns:m=\"http://schemas.microsoft.com/office/2004/12/omml\" "
				+ "xmlns=\"http://www.w3.org/TR/REC-html40\">"
				+ "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">"
				+ "<meta name=\"Generator\" content=\"Microsoft Word 15 (filtered medium)\"><style>"
				+ "<!--/* Font Definitions */@font-face     {font-family:宋体;         panose-1:2 1 6 0 3 1 1 1 1 1;}@font-face         "
				+ "{font-family:\"Cambria Math\"; panose-1:2 4 5 3 5 4 6 3 2 4;}@font-face         "
				+ "{font-family:Calibri; panose-1:2 15 5 2 2 2 4 3 2 4;}@font-face       "
				+ "{font-family:\"\\@宋体\";   panose-1:2 1 6 0 3 1 1 1 1 1;}@font-face  {font-family:微软雅黑;    "
				+ "panose-1:2 11 5 3 2 2 4 2 2 4;}@font-face       "
				+ "{font-family:\"\\@微软雅黑\";   panose-1:2 11 5 3 2 2 4 2 2 4;}@font-face    "
				+ "{font-family:Verdana;       panose-1:2 11 6 4 3 5 4 4 2 4;}" + "/* Style Definitions */"
				+ "p.MsoNormal, li.MsoNormal, div.MsoNormal         "
				+ "{margin:0cm;   margin-bottom:.0001pt; font-size:12.0pt;      font-family:宋体;}"
				+ "a:link, span.MsoHyperlink         "
				+ "{mso-style-priority:99;     color:blue;        text-decoration:underline;}"
				+ "a:visited, span.MsoHyperlinkFollowed         {mso-style-priority:99;     color:purple;    text-decoration:underline;}"
				+ "pre        {mso-style-priority:99;     mso-style-link:\"HTML 预设格式 Char\";    margin:0cm;    margin-bottom:.0001pt; font-size:12.0pt;      font-family:宋体;}"
				+ "span.EmailStyle17         {mso-style-type:personal;         font-family:\"Calibri\",\"sans-serif\";    color:#1F497D;}"
				+ "span.HTMLChar     {mso-style-name:\"HTML 预设格式 Char\";         mso-style-priority:99;       mso-style-link:\"HTML 预设格式\"; font-family:\"Courier New\";}";
		// "span.EmailStyle20 {mso-style-type:personal-reply;
		// font-family:"Calibri","sans-serif"; color:#1F497D;}.MsoChpDefault
		// {mso-style-type:export-only; font-size:10.0pt;}@page WordSection1
		// {size:612.0pt 792.0pt; margin:72.0pt 90.0pt 72.0pt
		// 90.0pt;}div.WordSection1 {page:WordSection1;}--></style><!--[if gte
		// mso 9]><xml><o:shapedefaults v:ext="edit" spidmax="1026"
		// /></xml><![endif]--><!--[if gte mso 9]><xml><o:shapelayout
		// v:ext="edit"><o:idmap v:ext="edit" data="1"
		// /></o:shapelayout></xml><![endif]--></head><body lang="ZH-CN"
		// link="blue" vlink="purple"><div class="WordSection1"><p
		// class="MsoNormal"><span
		// style="font-size:10.5pt;color:#1F497D">希凯，你好，</span><span
		// lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D"><o:p></o:p></span></p><p
		// class="MsoNormal"><span
		// style="font-size:10.5pt;color:#1F497D">我检查了一下，</span><span
		// lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D"><o:p></o:p></span></p><p
		// class="MsoNormal"><span lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D">AD</span><span
		// style="font-size:10.5pt;color:#1F497D">中</span><span lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D">wangjianxin1</span><span
		// style="font-size:10.5pt;color:#1F497D">已在产品本部电子车间群组中，且和</span><span
		// lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D">domino</span><span
		// style="font-size:10.5pt;color:#1F497D">上的</span><span lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D">wangjianxin1</span><span
		// style="font-size:10.5pt;color:#1F497D">对应；</span><span lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D"><o:p></o:p></span></p><p
		// class="MsoNormal"><span lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D">domino</span><span
		// style="font-size:10.5pt;color:#1F497D">中只有</span><span lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D">chazhaojun</span><span
		// style="font-size:10.5pt;color:#1F497D">账户，对应的人是产品本部电子车间</span><span
		// lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D">/</span><span
		// style="font-size:10.5pt;color:#1F497D">查昭君，没有</span><span
		// lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D">zhazhaojun</span><span
		// style="font-size:10.5pt;color:#1F497D">这个账户，</span><span lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D">AD</span><span
		// style="font-size:10.5pt;color:#1F497D">中有</span><span lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D">zhazhaojun</span><span
		// style="font-size:10.5pt;color:#1F497D">这个账户，对应的人是产品本部</span><span
		// lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D">/</span><span
		// style="font-size:10.5pt;color:#1F497D">电子车间</span><span lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D">/</span><span
		// style="font-size:10.5pt;color:#1F497D">查昭君，</span><span lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D">chazhaojun</span><span
		// style="font-size:10.5pt;color:#1F497D">这个账户是对比用户时没有找到</span><span
		// lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D">chazhaojun</span><span
		// style="font-size:10.5pt;color:#1F497D">（查昭君）时新建的，也放在了电子车间这个</span><span
		// lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D">ou</span><span
		// style="font-size:10.5pt;color:#1F497D">中。请核实查昭君这个人和</span><span
		// lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D">chazhaojun</span><span
		// style="font-size:10.5pt;color:#1F497D">、</span><span lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D">zhazhaojun</span><span
		// style="font-size:10.5pt;color:#1F497D">这两个账户的关系。谢谢</span><span
		// lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D"><o:p></o:p></span></p><p
		// class="MsoNormal"><span lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D"><o:p>&nbsp;</o:p></span></p><div><div
		// style="border:none;border-top:solid #E1E1E1 1.0pt;padding:3.0pt 0cm
		// 0cm 0cm"><p class="MsoNormal"><b><span
		// style="font-size:11.0pt;font-family:&quot;微软雅黑&quot;,&quot;sans-serif&quot;">发件人<span
		// lang="EN-US">:</span></span></b><span lang="EN-US"
		// style="font-size:11.0pt;font-family:&quot;微软雅黑&quot;,&quot;sans-serif&quot;">
		// yinxikai@nuctech.com [mailto:yinxikai@nuctech.com]<br></span><b><span
		// style="font-size:11.0pt;font-family:&quot;微软雅黑&quot;,&quot;sans-serif&quot;">发送时间<span
		// lang="EN-US">:</span></span></b><span lang="EN-US"
		// style="font-size:11.0pt;font-family:&quot;微软雅黑&quot;,&quot;sans-serif&quot;">
		// 2013</span><span
		// style="font-size:11.0pt;font-family:&quot;微软雅黑&quot;,&quot;sans-serif&quot;">年<span
		// lang="EN-US">4</span>月<span lang="EN-US">26</span>日<span
		// lang="EN-US"> 13:33<br></span><b>收件人<span
		// lang="EN-US">:</span></b><span lang="EN-US"> </span>刘国勋<span
		// lang="EN-US"><br></span><b>主题<span lang="EN-US">:</span></b><span
		// lang="EN-US"> </span>转发<span
		// lang="EN-US">:</span>你好，请修改“产品本部电子车间”群邮件里的<span
		// lang="EN-US">2</span>个地址，详见下。谢谢！<span
		// lang="EN-US"><o:p></o:p></span></span></p></div></div><p
		// class="MsoNormal"><span lang="EN-US"><o:p>&nbsp;</o:p></span></p><p
		// class="MsoNormal"><span
		// style="font-size:10.5pt;color:#1F497D">请协助修改</span><span lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D">ad</span><span
		// style="font-size:10.5pt;color:#1F497D">中对应组</span><span lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D"><o:p></o:p></span></p><p
		// class="MsoNormal"><span lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D"><o:p>&nbsp;</o:p></span></p><p
		// class="MsoNormal"
		// style="mso-margin-top-alt:auto;margin-bottom:12.0pt;text-align:justify;text-justify:inter-ideograph;background:white"><span
		// lang="EN-US"
		// style="font-size:10.0pt;font-family:&quot;微软雅黑&quot;,&quot;sans-serif&quot;;color:#1F497D">===================================<br></span><span
		// style="font-size:10.0pt;font-family:&quot;微软雅黑&quot;,&quot;sans-serif&quot;;color:#1F497D">同方威视技术股份有限公司
		// 信息化与经营核算部<span lang="EN-US"><br></span>尹希凯<span lang="EN-US">
		// Yinxikai<br><a href="Tel:(010)-">Tel:(010)-</a></span></span><span
		// lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;微软雅黑&quot;,&quot;sans-serif&quot;;color:#1F497D">
		// 831-86252</span><span lang="EN-US"
		// style="font-size:10.0pt;font-family:&quot;微软雅黑&quot;,&quot;sans-serif&quot;;color:#1F497D"><br><a
		// href="Tel:13810723400">Tel:13810723400</a><br>Mail:Yinxikai@nuctech.com<o:p></o:p></span></p><p
		// class="MsoNormal"><span lang="EN-US"
		// style="font-size:10.5pt;font-family:&quot;Calibri&quot;,&quot;sans-serif&quot;;color:#1F497D"><o:p>&nbsp;</o:p></span></p><p
		// class="MsoNormal"><b><span style="font-size:10.0pt">发件人<span
		// lang="EN-US">:</span></span></b><span lang="EN-US"
		// style="font-size:10.0pt"><a
		// href="mailto:wangfang@nuctech.com">wangfang@nuctech.com</a> [<a
		// href="mailto:wangfang@nuctech.com">mailto:wangfang@nuctech.com</a>]<br></span><b><span
		// style="font-size:10.0pt">发送时间<span
		// lang="EN-US">:</span></span></b><span lang="EN-US"
		// style="font-size:10.0pt"> 2013</span><span
		// style="font-size:10.0pt">年<span lang="EN-US">4</span>月<span
		// lang="EN-US">26</span>日<span lang="EN-US">
		// 11:39<br></span><b>收件人<span lang="EN-US">:</span></b><span
		// lang="EN-US"> <a
		// href="mailto:yinxikai@nuctech.com">yinxikai@nuctech.com</a><br></span><b>主题<span
		// lang="EN-US">:</span></b><span lang="EN-US">
		// </span>你好，请修改“产品本部电子车间”群邮件里的<span
		// lang="EN-US">2</span>个地址，详见下。谢谢！<span
		// lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal"><span
		// lang="EN-US"><o:p>&nbsp;</o:p></span></p><div><p
		// class="MsoNormal"><span lang="EN-US"
		// style="font-size:10.0pt;font-family:&quot;Verdana&quot;,&quot;sans-serif&quot;">wangjianxin/Nuctech&nbsp;</span><span
		// style="font-size:10.0pt">改为</span><span lang="EN-US"
		// style="font-size:10.0pt;font-family:&quot;Verdana&quot;,&quot;sans-serif&quot;">
		// wangjianxin1/Nuctech<br><br>chazhaojun/Nuctech&nbsp;&nbsp;
		// </span><span style="font-size:10.0pt">改为</span><span lang="EN-US"
		// style="font-size:10.0pt;font-family:&quot;Verdana&quot;,&quot;sans-serif&quot;">
		// zhazhaojun/Nuctech<br><br><br><br>*-* *-* *-* *-* *-* *-* *-* *-* *-*
		// *-* *-* *-* *-* *-* * *-* *-* *-* *-* *-* *-* *-*
		// *-*<br><br></span><span
		// style="font-size:10.0pt">同方威视技术股份有限公司</span><span lang="EN-US"
		// style="font-size:10.0pt;font-family:&quot;Verdana&quot;,&quot;sans-serif&quot;"><br><br></span><span
		// style="font-size:10.0pt">祝工作顺利</span><span
		// style="font-size:10.0pt;font-family:&quot;Verdana&quot;,&quot;sans-serif&quot;"></span><span
		// style="font-size:10.0pt">幸福快乐每一天！</span><span lang="EN-US"
		// style="font-size:10.0pt;font-family:&quot;Verdana&quot;,&quot;sans-serif&quot;"><br><br></span><span
		// style="font-size:10.0pt">电子车间</span><span
		// style="font-size:10.0pt;font-family:&quot;Verdana&quot;,&quot;sans-serif&quot;"></span><span
		// style="font-size:10.0pt">王</span><span
		// style="font-size:10.0pt;font-family:&quot;Verdana&quot;,&quot;sans-serif&quot;"></span><span
		// style="font-size:10.0pt">芳</span><span lang="EN-US"
		// style="font-size:10.0pt;font-family:&quot;Verdana&quot;,&quot;sans-serif&quot;"><br><br></span><span
		// style="font-size:10.0pt">电话：</span><span lang="EN-US"
		// style="font-size:10.0pt;font-family:&quot;Verdana&quot;,&quot;sans-serif&quot;">010-89096787<br></span><span
		// style="font-size:10.0pt">邮箱：</span><span
		// style="font-size:10.0pt;font-family:&quot;Verdana&quot;,&quot;sans-serif&quot;"><span
		// lang="EN-US"><a
		// href="mailto:wangfang@nuctech.com">wangfang@nuctech.com</a><br>*-*
		// *-* *-* *-* *-* *-* *-* *-* *-* *-* *-* *-* *-* *-* *-*
		// <o:p></o:p></span></span></p></div><p class="MsoNormal"><span
		// lang="EN-US">=<o:p></o:p></span></p><p class="MsoNormal"><span
		// lang="EN-US"><o:p>&nbsp;</o:p></span></p><pre><span
		// lang="EN-US"><o:p>&nbsp;</o:p></span></pre><pre><span
		// lang="EN-US">Powered&nbsp;by&nbsp;MessageSoft&nbsp;SMG&nbsp;<o:p></o:p></span></pre><pre><span
		// lang="EN-US">SPAM,&nbsp;virus-free&nbsp;and&nbsp;secure&nbsp;email&nbsp;<o:p></o:p></span></pre><pre><span
		// lang="EN-US"><a
		// href="http://www.messagesoft.net">http://www.messagesoft.net</a>&nbsp;<o:p></o:p></span></pre><pre><span
		// lang="EN-US"><o:p>&nbsp;</o:p></span></pre></div><br><pre>Powered&nbsp;by&nbsp;MessageSoft&nbsp;SMG&nbsp;SPAM,&nbsp;virus-free&nbsp;and&nbsp;secure&nbsp;email&nbsp;http://www.messagesoft.net&nbsp;</pre></body></html>";

		return str;
	}

}
