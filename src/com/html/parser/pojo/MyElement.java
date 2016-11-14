package com.html.parser.pojo;

import javax.swing.text.html.HTML;

import com.html.parser.util.MyParserUtil;

/**
 * HTML Tag의 정보를 객체화한 클래스
 * MyNode 클래스를 상속받아 node간의 관계를 관리
 * 
 * HTML tag의 구조 <tagName attr="attrValue"...> text </tagName>
 * 
 * 주요 프로퍼티
 * 	1) 태그종류: HTML.Tag tagName
 * 	2) 태그 값: String text
 *  3) 태그형식: 
 * 
 * @author JohnKoo
 *
 */
public class MyElement extends MyNode {
	
	public static enum Type{
		NORMAL,
		SIMPLE
	}
	
	private Type elemType;	
	private HTML.Tag tagName;
	private String text;
	
	// 기본 생성자
	public MyElement() {
	}

		
	/**
	 * 프로퍼티 게터, 세터
	 * @return
	 */
	public HTML.Tag getTagName() {
		return tagName;
	}
	public void setTagName(HTML.Tag tagName) {
		this.tagName = tagName;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	public Type getElementType() {
		return elemType;
	}

	public void setElementType(Type elemType) {
		this.elemType = elemType;
	}
	
	/**
	 * HTML 형식으로 스트링 변환
	 */
	@Override
	public String toString() {
		// tag name
		StringBuffer sb = new StringBuffer("<" + tagName);
		
		// attribute list
		if(this.getAttrs() != null){
			sb.append(this.getAttrs().toString());
		}
		
		
		if(this.elemType == elemType.NORMAL){
			sb.append(">");
			if(this.children() != null){
				for(MyNode n: this.getChildren()){
					sb.append(MyParserUtil.changeMyNodeToMyElement(n).toString());
				}
			}
			if(text != null){
				sb.append(text);
			}
			sb.append("</" + tagName + ">");
		} else if (this.elemType == elemType.SIMPLE){
			sb.append("/>");
		} else {
			sb.append("ERRRRRROOOOOOORRRRRRR");
		}
		return sb.toString();
	}	
	
	
}
