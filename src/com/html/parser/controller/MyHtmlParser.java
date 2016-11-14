package com.html.parser.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

import com.html.parser.pojo.MyDocument;

/**
 * URL 정보를 MyDocument객체로 파싱해 주는 클래스
 * HTML의 태그정보는 Node를 상속받는 MyElement 객체로 변환되어 
 * DOM트리 형태로 MyDocument객체를 리턴한다. 
 * 
 * @author JohnKoo
 *
 */
public class MyHtmlParser {	
	public MyHtmlParser(){}
	
	public MyDocument parse(String url) throws Exception{
		
		/**
		 * #1. URL에서 InputStream 생성후, BufferedReader생성
		 */
		URL target = new URL(this.urlChecker(url));
		URLConnection conn = target.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		/**
		 * #2. HTMLEditorKit 파서 초기화
		 */
		HTMLEditorKit.Parser parser = new ParserDelegator();
		MyParserCallback myCallback = new MyParserCallback();
		
		/**
		 * #3. SAX형 파싱
		 */
		parser.parse(br, myCallback, true);
		
		/**
		 * #4. MyDocument객체 출력
		 */
		MyDocument result = myCallback.getMyDocument();
		
		/**
		 * BufferedReader close
		 */
		br.close();
		
		return result;
	}
	
	/**
	 * url 스트링이 http://로 시작하는 지 확인하고
	 * www. 로 시작하는 경우 http://를 붙혀서 스트링값을 리턴한다
	 */
	private String urlChecker(String dirtyUrl){
		
		if(dirtyUrl.startsWith("http://")){
			return dirtyUrl;
		}
		
		return "http://" + dirtyUrl;
		
	}
}
