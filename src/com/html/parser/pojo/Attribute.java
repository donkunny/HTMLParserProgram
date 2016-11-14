package com.html.parser.pojo;

/**
 * Tag의 어트리뷰트를 객체화하는 클래스
 * 
 * @author JohnKoo
 *
 */
public class Attribute {
	
	private String attr;
	private String value;
	
	public Attribute(String attr, String value){
		this.attr = attr;
		this.value = value;
	}
	
	public String getAttr() {
		return attr;
	}
	public void setAttr(String attr) {
		this.attr = attr;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getStringKey(){
		return attr.toString();
	}

	@Override
	public String toString() {
		return " " + attr.toString() + "=" + "\"" + value + "\"";
	}

	
}
