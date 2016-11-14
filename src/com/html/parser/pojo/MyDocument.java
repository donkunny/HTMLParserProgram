package com.html.parser.pojo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.html.HTML;

/**
 * SWING에서 제공하는 SAX구조의 파서에서
 * DOM구조의 MyDocument 객체로 저장한다.
 * 
 * id, class, tag를 통한 검색이 가능하기 위해서
 * 각각의 hashMap 프로퍼티를 가지고 있음.
 * 
 * @author JohnKoo
 *
 */
public class MyDocument {

	// id 저장소
	private HashMap<String, MyElement> idMap;
	// tag 저장소
	private HashMap<String, MyElements> tagMap;
	// class 저장소
	private HashMap<String, MyElements> classMap;
	// attribute 저장소
	private HashMap<String, MyElements> attrMap;
	
	private String url;
	
	// 기본생성자
	public MyDocument() {
		idMap = new HashMap<>();
		tagMap = new HashMap<>();
		classMap = new HashMap<>();
		attrMap = new HashMap<>();
	}

	/**
	 * 세부데이터 세터 메소드
	 */
	public void setMyElementById(String idValue, MyElement elem){
		idMap.put(idValue, elem);
	}
	
	public void setMyElementByTag(String t, MyElement elem){
//		System.out.println("Tag is " + elem.toString());
		
		if(tagMap.containsKey(t)){
			tagMap.get(t).add(elem);
		} else {
			MyElements newList = new MyElements();
			newList.add(elem);
			tagMap.put(t, newList);
		}
	}
	
	public void setMyElementByClass(String cls, MyElement elem){
		
		if(this.classMap.containsKey(cls)){
			classMap.get(cls).add(elem);
		} else {
			MyElements newList = new MyElements();
			newList.add(elem);
			classMap.put(cls, newList);
		}
	}
	
	public void setMyElementByAttribute(String attr, MyElement elem){
		if(this.attrMap.containsKey(attr)){
			attrMap.get(attr).add(elem);
		} else {
			MyElements newList = new MyElements();
			newList.add(elem);
			attrMap.put(attr, newList);
		}
	}
	
	
	/**
	 * 세부데이터 게터 메소드
	 */	
	public MyElement selectId(String id){
		return idMap.get(id);
	}
	
	public MyElements selectClass(String className){
		return this.classMap.get(className);
	}
	
	public MyElements selectTag(String tagName){
		return this.tagMap.get(tagName);
	}
	
	/**
	 * 사이즈 계산 메소드
	 */
	public int getSizeOfIdMap(){
		return idMap.size();
	}
	
	public int getSizeOfTagMap(){
		return tagMap.size();
	}
	
	public int getNumberOfKeysInClassMap(){
		return classMap.size();
	}
	
	public int getSizeOfAttrMap(){
		return attrMap.size();
	}
	
	/**
	 * 프로퍼티 게터와 세터
	 */
	public HashMap<String, MyElement> getIdMap() {
		return idMap;
	}

	public HashMap<String, MyElements> getTagMap() {
		return tagMap;
	}

	public HashMap<String, MyElements> getClassMap() {
		return classMap;
	}

	public HashMap<String, MyElements> getAttrMap() {
		return attrMap;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	
	
	
}
