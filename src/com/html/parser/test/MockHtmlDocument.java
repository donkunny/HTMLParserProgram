package com.html.parser.test;

public class MockHtmlDocument {

	private static StringBuffer sb;
	
//	<HTML>
//	<HEAD>
//	<TITLE>Your Title Here</TITLE>
//	</HEAD>
//	<BODY BGCOLOR="FFFFFF">
//	<CENTER><IMG SRC="clouds.jpg" ALIGN="BOTTOM"> </CENTER>
//	<HR>
//	<a href="http://somegreatsite.com">Link Name</a>
//	is a link to another nifty site
//	<H1>This is a Header</H1>
//	<H2>This is a Medium Header</H2>
//	Send me mail at <a href="mailto:support@yourcompany.com">
//	support@yourcompany.com</a>.
//	<P> This is a new paragraph!
//	<P> <B>This is a new paragraph!</B>
//	<BR> <B><I>This is a new sentence without a paragraph break, in bold italics.</I></B>
//	<HR>
//	</BODY>
//	</HTML>
	
	public static StringBuffer getMockHtml(){
		sb = new StringBuffer();
		
		appendln("<HTML>");
		appendln("<!-- this is comment line -->");
		appendln("<HEAD>");
		appendln("<TITLE>Your Title Here</TITLE>");
		appendln("<BODY BGCOLOR=\"FFFFFF\">");
		appendln("<CENTER ID=\"center\"><IMG SRC=\"clouds.jpg\" ALIGN=\"BOTTOM\"> </CENTER>");
		appendln("<HR>");
		appendln("<a href=\"http://somegreatsite.com\">Link Name</a>");
		appendln("is a link to another nifty site");
		appendln("<H1 class=\"myclass\">This is a Header</H1>");
		appendln("<H2>This is a Medium Header</H2>");
		appendln("Send me mail at <a href=\"mailto:support@yourcompany.com\">");
		appendln("support@yourcompany.com</a>.");
		appendln("<P> This is a new paragraph!");
		appendln("<P> <B>This is a new paragraph!</B>");
		appendln("<BR> <B><I>This is a new sentence without a paragraph break, in bold italics.</I></B>");
		appendln("<HR>");
		appendln("</BODY>");
		appendln("</HTML>");
		
		
		return sb;
	}
	
	private static void appendln(String str){
		sb.append(str);
		sb.append("\n");
	}
}
