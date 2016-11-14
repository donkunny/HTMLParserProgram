package com.html.parser.pojo;

import java.util.ArrayList;
import java.util.List;

import com.html.parser.util.MyParserUtil;

public class MyNode {

	private MyNode parentNode;
	private List<MyNode> childNode;
	private Attributes attrs;
	
	
	/**
	 * 태그 어트리뷰트 세터
	 */
	public void setNewAttribute(Attribute attr){
		if(attrs == null){
			attrs = new Attributes();
		}
		attrs.add(attr);
	}
	
	public void setNewAttribute(String attrName, String attrValue){

		Attribute attr = new Attribute(attrName, attrValue);
		
		if(attrs == null){
			attrs = new Attributes();
		}
		attrs.add(attr);
	}
	
	/**
	 * 프로퍼티 검색
	 */
	public boolean hasAttr(String attrName){
		if(attrs == null){
			return false;
		}
		return this.attrs.containsAttrName(attrName);
	}
	
	public String getAttrValue(String attrName){
		return this.attrs.getAttrValue(attrName);
	}
	
	/**
	 * Child Node 접근 메소드
	 */
	public MyElements children(){
		
		if(this.childNode == null){
			return null;
		} 
		
		MyElements result = new MyElements();
		
		for(MyNode n : this.getChildren()){
			result.add(MyParserUtil.changeMyNodeToMyElement(n));
		}
		
		return result;
	}
	
	public MyElement selectChildById(String id){
		
		if(this.getChildren() == null){
			return null;
		} 
		
		for(MyNode n : this.getChildren()){
			if(n.attrs.containsAttrName("id") && n.attrs.getAttrValue("id").equals(id)){
				return MyParserUtil.changeMyNodeToMyElement(n);
			}
		}
		return null;
	}
	
	public MyElements selectChildrenByClass(String cls){
		
		if(this.getChildren() == null){
			return null;
		}
		
		MyElements result = new MyElements();
		
		for(MyNode n : this.getChildren()){
			if(n.attrs.containsAttrName("class") && n.attrs.getAttrValue("class").equals(cls)){
				result.add(MyParserUtil.changeMyNodeToMyElement(n));
			}
		}
		
		return result;
	}
	
	
	/**
	 * 게터와 세터
	 * @return
	 */
	public Attributes getAttrs() {
		return attrs;
	}

	public void setAttrs(Attributes attrs) {
		this.attrs = attrs;
	}

	public MyNode getParentNode() {
		return parentNode;
	}
	
	public void setParentNode(MyNode parentNode) {
		this.parentNode = parentNode;
	}
	
	public List<MyNode> getChildren() {
		return childNode;
	}
	
	public void setNewChild(MyNode child){
		if(this.childNode == null){
			this.childNode = new ArrayList<>();
		}
		childNode.add(child);
	}

	public boolean hasParent(){
		if(this.parentNode != null){
			return true;
		}
		return false;
	}
	
	public int getChildrenNum(){
		
		if(childNode == null){
			return 0;
		}
		return this.childNode.size();
	}
	
	// return first child node
	public MyElement firstChild(){
		if(childNode == null){
			return null;
		}
		return MyParserUtil.changeMyNodeToMyElement(childNode.get(0));
	}
	
	// return last child node
	public MyNode lastChild(){
		if(childNode == null){
			return null;
		}
		return MyParserUtil.changeMyNodeToMyElement(childNode.get(childNode.size()-1));
	}
	
	
	// return true if the node has children
	public boolean hasChildren(){
		if(this.getChildren() == null)
			return false;
		else 
			return true; 
	}
	
}
