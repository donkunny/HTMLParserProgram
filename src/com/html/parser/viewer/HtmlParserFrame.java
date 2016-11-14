/**
 * Component 객체를 받아 Frame에 저장하는 클래스
 * 
 * @author Donghyuk Kim
 */

package com.html.parser.viewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class HtmlParserFrame extends JFrame {
	
	public HtmlParserFrame(){
			
		this.setTitle("HTML Parser Program");
		this.setLayout(new BorderLayout());
		
		int h =ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED; // 수평 스크롤
		int v= ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;   // 수직 스크롤
		
		JScrollPane scroll=new JScrollPane(new HtmlParserComponent().getPanel(), v, h);
		this.add(scroll);		
		
		this.setSize(1000,800);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		
}