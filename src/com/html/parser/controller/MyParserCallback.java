package com.html.parser.controller;

import java.util.Enumeration;
import java.util.Stack;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

import com.html.parser.pojo.Attribute;
import com.html.parser.pojo.MyDocument;
import com.html.parser.pojo.MyElement;

/**
 * SWING의 HTMLEditorKit.ParserCallback을 상속받아 
 * HTMLEditorKit.Parser에서 InputBuffer처리시 각 값에 대한 처리문을 정의하고 있음
 * 
 * @author JohnKoo
 *
 */
public class MyParserCallback extends HTMLEditorKit.ParserCallback {

	private Stack<MyElement> stack = new Stack<>();
	private MyDocument myDocument = new MyDocument();

	/**
	 * starting tag와 closing tag가 따로 존재하는 일반적인 html tag
	 * 예) <body> .... </body>
	 */
	@Override
	public void handleStartTag(Tag t, MutableAttributeSet a, int pos) {
			
		// currentElem 초기화
		MyElement currentElem = new MyElement();
		currentElem.setElementType(MyElement.Type.NORMAL);
		
		// tag종류 저장
		currentElem.setTagName(t);
		Enumeration attributeNames = a.getAttributeNames();
		
		// attribute 처리
		// id, class, href, img
		while(attributeNames.hasMoreElements()){
			Object attr = attributeNames.nextElement();
			String attrName = attr.toString();
			Attribute newAttr = new Attribute(attrName, a.getAttribute(attr).toString());
			currentElem.setNewAttribute(newAttr);
			
			// attr의 엘레먼트를 받아서 
			// private sortAttrbute(Object obj) 메소드를 통해서
			// 해당하는 도큐먼트 프로퍼티에 저장됨
			this.assignElementByAttr(newAttr, currentElem);			
		}
		
		// 만약 부모 엘러먼트가 존재한다면
		// currentElem을 부모엘레먼트의 자식요소로 저장
		if(!stack.empty()){
			MyElement prevElem = stack.peek();
			prevElem.setNewChild(currentElem);
			currentElem.setParentNode(prevElem);
		}
		
		// tag별로 document에 정리
		this.assignElementByTag(t.toString(), currentElem);
		
		// 스택에 푸시
		stack.push(currentElem);
	}

	/**
	 *  closing tag가 starting tag에 같이 명시된 tag element
	 *  예) <img src="clouds.jpg" align="bottom" />
	 */
	@Override
	public void handleSimpleTag(Tag t, MutableAttributeSet a, int pos) {
		
		// currentElem 초기화
		MyElement currentElem = new MyElement();
		currentElem.setElementType(MyElement.Type.SIMPLE);
		currentElem.setTagName(t);
		
		// attributes 저장
		Enumeration attributeNames = a.getAttributeNames();
		
		while(attributeNames.hasMoreElements()){
			Object attr = attributeNames.nextElement();
			String attrName = attr.toString();
			
			Attribute newAttr = new Attribute(attrName, a.getAttribute(attr).toString());
			currentElem.setNewAttribute(newAttr);
			
			// attr의 엘레먼트를 받아서 
			// private assignElementByAttr(Object obj) 메소드를 통해서
			// 해당하는 도큐먼트 프로퍼티에 저장됨
			this.assignElementByAttr(newAttr, currentElem);						
		}
		
		// 만약 부모 엘러먼트가 존재한다면
		// currentElem을 부모엘레먼트의 자식요소로 저장
		if(!stack.empty()){
			MyElement prevElem = stack.peek();
			prevElem.setNewChild(currentElem);
			currentElem.setParentNode(prevElem);
		}
		
		// tag별로 document에 정리
		this.assignElementByTag(t.toString(), currentElem);
		
	}
	
	/**
	 * tag가 가진 데이터 정보를 처리
	 */
	@Override
	public void handleText(char[] data, int pos) {			
		MyElement currentElem = stack.pop();
		currentElem.setText(new String(data));
		stack.push(currentElem);
	}

	/**
	 * 현재 처리하는 tag가 script일 경우
	 * comment로 처리되는 값을 받아 script tag의 data로 저장한다
	 */
	@Override
	public void handleComment(char[] data, int pos) {
		MyElement currentElem = stack.peek();
		
		if(currentElem.getTagName().equals(HTML.Tag.SCRIPT)){
			currentElem.setText(new String(data));
		}
	}
	
	/**
	 * 현재 stack에 저장된 최종 Element 객체를 pop 한다
	 */
	@Override
	public void handleEndTag(Tag t, int pos) {
		stack.pop();		
	}

	@Override
	public void handleError(String errorMsg, int pos) {
		// do nothing
	}
	
	@Override
	public void handleEndOfLineString(String eol) {
		// do nothing
	}

	/************************************************
	 * 패스받은 엘레먼트를 tag, class, id, attribute 별로 	*
	 * Document object에 입력하기 위한 메소드들			*
	 ************************************************/
	
	private void assignElementByAttr(Attribute attr, MyElement elem) {
		
		this.myDocument.setMyElementByAttribute(attr.getValue(), elem);
		
		if(attr.getStringKey().equals("id")){
			this.myDocument.setMyElementById(attr.getValue(), elem);
		} else if(attr.getStringKey().equals("class")){
			this.myDocument.setMyElementByClass(attr.getValue(), elem);
		}
			
	}
	
	
	private void assignElementByTag(String tagKey, MyElement elem){
		this.myDocument.setMyElementByTag(tagKey, elem);
	}
	
	
	public MyDocument getMyDocument() {
		return myDocument;
	}
	
	
}
