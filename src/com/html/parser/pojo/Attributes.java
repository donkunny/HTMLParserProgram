package com.html.parser.pojo;

import java.util.LinkedHashMap;

/**
 *
 * 어트리뷰트 Map 클래스
 * 
 * @author JohnKoo
 *
 */
public class Attributes {
	
	private LinkedHashMap<String, Attribute> attrMap;

	public Attributes() {
		attrMap = new LinkedHashMap<>();
	}
	
	public int length(){
		return attrMap.size();
	}
	
	public boolean containsAttribute(Attribute attr){
		if(attrMap == null){
			return false;
		}
		return attrMap.containsKey(attrMap);
	}
	
	public boolean containsAttrName(String attrName){
		return attrMap.containsKey(attrName);
	}
	
	public String getAttrValue(String attrName){
		if(this.containsAttrName(attrName)){
			return attrMap.get(attrName).getValue();
		}
		return null;
	}
	
	/**
	 * Initiate Attribute Object and save it to attrMap
	 * 
	 * @param key
	 * @param attr
	 */
	public void add(Attribute attr){
		attrMap.put(attr.getStringKey(), attr);
	}

	public LinkedHashMap<String, Attribute> getAttrMap() {
		return attrMap;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		for(String key : attrMap.keySet()){
			sb.append(attrMap.get(key).toString());
		}
		
		return sb.toString();
	}
	
	
}
