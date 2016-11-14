package com.html.parser.pojo;

import java.util.ArrayList;

/**
 * MyDocument객체의 MyElement 결과값이 복수일 경우
 * MyElements 클래스의 객체로 담아 리턴한다
 * 
 * 결과값내에서 또 결과값을 분류해낼 수 있는 메소드를 제공한다
 * 
 */
public class MyElements extends ArrayList<MyElement> {
	public MyElements(){
		super();
	}
	
	/**
	 *  ID로 MyElement 리턴
	 */

	public MyElement selectID(String idName){
		
		for(MyElement e : this){
			if(e.hasAttr(idName)){
				return e;
			}
		}
		
		return null;
	}
	
	/**
	 *  특정 어트리뷰트를 가진 엘레먼트 리턴
	 */
	public MyElements hasAttribute(String attr){
		MyElements result = new MyElements();
		
		for(MyElement e : this){
			if(e.hasAttr(attr)){
				result.add(e);
			}
		}
		
		return result;
	}
	
	/**
	 *  특정 어트리뷰트와 그 어트리뷰트의 특정 결과값을 가진 마이엘레먼트 리턴 
	 */
	public MyElements selectAttributeValue(String attr, String value){
		MyElements result = new MyElements();
		
		for(MyElement e : this){
			if(e.hasAttr(attr) && e.getAttrValue(attr).equals(value)){
				result.add(e);
			}
		}
		return result;
	}
	
	/**
	 * 첫번째 엘레먼트 리턴
	 */
	public MyElement first(){
		if(this.isEmpty()){
			return null;
		}
		
		return this.get(0);
	}
	
	/**
	 * 마지막 엘레먼트 리턴
	 */
	public MyElement last(){
		if(this.isEmpty()){
			return null;
		}
		
		return this.get(this.size()-1);
	}
}
