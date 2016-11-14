package com.html.parser.util;

import static org.junit.Assert.*;

import javax.swing.text.html.HTML;

import org.junit.Test;

import com.html.parser.pojo.MyElement;
import com.html.parser.pojo.MyNode;

/**
 * 怨듯넻�쑝濡� �궗�슜�븯�뒗 Static Methods �쑀�떥 �겢�씪�뒪
 * 
 * @author JohnKoo
 *
 */
public final class MyParserUtil {

	/**
	 * String �깭洹몄씠由꾩쓣 HTML.Tag �긽�닔濡� 蹂��솚
	 */
	public static HTML.Tag changeStringToHtmlTagConst(String tagStr){
		HTML.Tag tag = HTML.getTag(tagStr);
		return tag;
	}
	
	/**
	 * String �뼱�듃由щ럭�듃瑜� HTML.Attribute �긽�닔濡� 蹂��솚
	 */
	public static HTML.Attribute changeStringToHtmlAttributeConst(String attrStr){
		HTML.Attribute attr = HTML.getAttributeKey(attrStr);
		return attr;
	}
	
	/**
	 * Object 瑜� HTML.Attribute String�쑝濡� 蹂��솚
	 */
	public static String changeAttrObjectToString(Object obj){
		if(obj instanceof HTML.Attribute){
			return obj.toString();
		} else {
			return "NNNN";
		}
	}
	
	/**
	 * This method takes MyNode object and transfer it to MyElement by casting
	 */
	public static MyElement changeMyNodeToMyElement(MyNode node){
		return (MyElement) node;
	}
	
	
	/**
	 * Method Tests
	 */

}
