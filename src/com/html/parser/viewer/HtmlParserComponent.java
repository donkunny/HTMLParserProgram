/**
 * Html Parser controller(메소드)와 Document 객체를 이용
 * HTML Source를 parsing 및 JTree 생성
 * UI view 제공을 위한 Component(tree) 및 Panel 객체 생성
 * 
 * @author Donghyuk Kim
 */

package com.html.parser.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.html.parser.controller.MyHtmlParser;
import com.html.parser.pojo.MyDocument;
import com.html.parser.pojo.MyElement;
import com.html.parser.pojo.MyElements;

public class HtmlParserComponent {

	private JPanel panel = new JPanel();		// Components 를 모두 담은 panel 객체
	private JPanel urlPanel = new JPanel();	// url명 + textArea
	private JTextField urltxt = new JTextField(650); 
	private JLabel urlLabel = new JLabel("URL 명: ");
	private int xpos, ypos, xpos1, ypos1;
	public HtmlParserComponent() {
		panel.setLayout(new BorderLayout());
	
		urlPanel.setLayout(new FlowLayout(FlowLayout.LEFT ,5, 5));
		urlPanel.add(urlLabel);	urlPanel.add(urltxt);
		
		panel.add(urlPanel, BorderLayout.NORTH);		
		panel.setSize(1000, 800);
		ypos = panel.getHeight();
		xpos = panel.getWidth();
				
		urltxt.addActionListener(new ActionListener() {		// textArea에 url 입력 후 엔터를 치면 ActionListener 메소드 호출
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel warningLabel = new JLabel();	// url 호출 성공: "Parsing Success", url 호출 실패: "잘못된 url입니다"
				JPanel westPanel = new JPanel(); 		// tag, attritube, id, class 수 출력 panel
				DefaultMutableTreeNode root = new DefaultMutableTreeNode("HTML");	// root node
				JTree 	tree = new JTree(root);
				
				try {
					System.out.println(panel.getWidth());
					westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));		
					
					warningLabel.setForeground(Color.BLACK);
					warningLabel.setText(e.getActionCommand() +" Parsing Success!!!");
					
					// MyHtmlParser 및 MyDocument 객체 호출
					MyHtmlParser parser = new MyHtmlParser();
					MyDocument doc = parser.parse(e.getActionCommand());
					MyElements elems = doc.selectTag("html").get(0).children();
					
					panel.removeAll(); 	// Listener 메소드 호출 할 때마다 panel의 모든 component 삭제 
					
					TreeMaker(elems, root);	// tree node를 생성하는 메소드 호출
					tree = new JTree(root);
					tree.expandPath(new TreePath(root.getPath()));	// tree node 펼쳐서 출력
				
					// westPanel에 들어갈 label : url명, tag수, attribute수, id수, class수
					JLabel urlNumberLabel = new JLabel("URL명: "+ e.getActionCommand());
					JLabel tagNumberLabel = new JLabel("TAG수: " + doc.getSizeOfTagMap());
					JLabel atrributeNumberLabel = new JLabel("Attribute수: " + doc.getSizeOfAttrMap());
					JLabel idNumberLabel = new JLabel("Id수: " + doc.getSizeOfIdMap());
					JLabel classNumberLabel = new JLabel("Class수: " + doc.getNumberOfKeysInClassMap());
					
					westPanel.add(urlNumberLabel);
					westPanel.add(tagNumberLabel);
					westPanel.add(atrributeNumberLabel);
					westPanel.add(idNumberLabel);
					westPanel.add(classNumberLabel);
					
					panel.add(urlPanel, BorderLayout.NORTH);		
					panel.add(warningLabel, BorderLayout.SOUTH);		
					panel.add(tree, BorderLayout.CENTER);
					panel.add(westPanel, BorderLayout.WEST);
					
					// listener 호출할 때마다 revalidate(), repaint() 호출
					panel.revalidate();
					panel.repaint();

				} catch (Exception ex) {	 	// 사용자가 url을 잘못입력하였을 경우 경고 메시지 출력
					ex.printStackTrace();
					panel.removeAll();
					
					// 경고창 - 다시입력: 되돌아가기, 종료: 프로그램종료
					String[] str = {"다시 입력", "종료"};
					JOptionPane pane = new JOptionPane("잘못된 url입니다. 다시 입력하세요.",  JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, str, str[0]);
				//	int ypos =  (int)(panel.getHeight()-panel.getY())/2 - (int)(pane.getHeight()/2);
				//	int xpos = (int)((panel.getWidth()-panel.getX())/2) - (int)(pane.getWidth()/2);
					JDialog d = pane.createDialog(panel, "경고창");
					xpos = (int)((xpos-panel.getY())/2) - (int)(pane.getWidth()/2);
					ypos = (int)((ypos-panel.getY())/2) - (int)(pane.getHeight()/2);

					d.setLocation(xpos, ypos);
					d.setVisible(true);
					if(pane.getValue().equals("종료"))
						System.exit(0);
					
					warningLabel.setForeground(Color.RED);
					warningLabel.setText("잘못된 url입니다. 다시 입력하세요.");
					
					panel.add(urlPanel, BorderLayout.NORTH);
					panel.add(warningLabel, BorderLayout.SOUTH);
								
					panel.revalidate();
					panel.repaint();
				}
			}
			
			/**
			 * Elements 객체와 root node를 받아 recursive function을 통해
			 * Tree node 생성 메소드
			 */
			private void TreeMaker(MyElements elems, DefaultMutableTreeNode node){
				DefaultMutableTreeNode childnode;

				if(elems == null){	// element가 없을 경우 리턴
					return;
				}
				
				for(MyElement e : elems){		
					
					childnode = new DefaultMutableTreeNode("<"+e.getTagName() + ">");
					node.add(childnode);	// node에 childnode 추가
					
					if(e.getAttrs() != null)	// childnode에 attribute(key, value) 추가
						for (String key : e.getAttrs().getAttrMap().keySet()){
							childnode.add(new DefaultMutableTreeNode(key + "=\"" + e.getAttrValue(key) + "\""));
						}
					if (e.getText() != null){	// childnode에 text data 추가
						childnode.add(new DefaultMutableTreeNode("TEXT: " + e.getText()));
						
					}
					if (e.hasChildren()) {
						elems = e.children();				// elements 안에 있는 child node(자식 태그) 저장
						TreeMaker(elems, childnode);	// recursive함수 호출
					}
				}
			}
		});
	}

	/**
	 * Panel 객체를 리턴
	 */
	public JPanel getPanel() {
		return panel;
	}
}
