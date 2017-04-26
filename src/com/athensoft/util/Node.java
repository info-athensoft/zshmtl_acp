package com.athensoft.util;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
 
public class Node {
	private String text;
	private String icon;
	private Map.Entry<String, Boolean> state;
	private final List<Node> children = new ArrayList<>();
	private final Node parent;
 
 public Node(Node parent) {
	 this.parent = parent;
 }
 
 public String getText() {
	 return text;
 }
 
 public void setText(String text) {
	 this.text = text;
 }
 
 public String getIcon() {
	 return icon;
 }
 
 public void setIcon(String icon) {
	 this.icon = icon;
 }
 
 public Entry<String, Boolean> getState() {
	return state;
}

public void setState(Entry<String, Boolean> state) {
	this.state = state;
}
 
 public List<Node> getChildren() {
	 return children;
 }
 
 public Node getParent() {
	 return parent;
 }
  
 public static Node addChild(Node parent, String text) {
	 Node node = new Node(parent);
	 node.setText(text);
	 parent.getChildren().add(node);
	 return node;
 }
 
 public static Node addChild(Node parent, String text, String icon) {
	 Node node = new Node(parent);
	 node.setText(text);
	 node.setIcon(icon);
	 parent.getChildren().add(node);
	 return node;
 }
 
 public static Node addChild(Node parent, String text, Entry<String, Boolean> state) {
	 Node node = new Node(parent);
	 node.setText(text);
	 node.setState(state);
	 parent.getChildren().add(node);
	 return node;
 }
 
 public static Node addChild(Node parent, String text, String icon, Entry<String, Boolean> state) {
	 Node node = new Node(parent);
	 node.setText(text);
	 node.setIcon(icon);
	 node.setState(state);
	 parent.getChildren().add(node);
	 return node;
 }
 
 public static StringBuffer buildJSTree(Node node, String appender) {
	 StringBuffer sb = new StringBuffer();
	 if (node.getText()!="root")
		 sb.append("\n" + appender + "{\"text\": \"" + node.getText() + "\"");
	 
	 if (node.getIcon() != null) {
		 sb.append(",\n" + appender + " \"icon\": \"" + node.getIcon() + "\"");
	 }
	 
	 if (node.getState() != null) {
		 sb.append(",\n" + appender + " \"state\": { \"" + node.getState().getKey() + "\" : " + node.getState().getValue() + " }");
	 }
	 
	 if (!node.getChildren().isEmpty()) {
		 if (node.getText()!="root")
			 sb.append(", \n"+appender + " \"children\": " );
		 sb.append("[");
	 }
		 
	 for (Node each : node.getChildren()) {
		 sb.append(buildJSTree(each, appender + appender));
		 sb.append("}," );
	 }
	 if (!node.getChildren().isEmpty()) {
		 sb.setLength(sb.length() - 1);
		 sb.append("\n" + appender + "]");
	 }
		 
	 return sb;
 }
  
  public static void main(String[] args) {
	  Node treeRootNode = new Node(null);
	  treeRootNode.setText("root");
	  // add child to root node 
	  Node parentNode = addChild(treeRootNode, "My Parent Node");
	  // add child to the child node created above
	  addChild(parentNode, "Initially selected", new AbstractMap.SimpleEntry<String, Boolean>("selected", true));
	  addChild(parentNode, "Custom Icon", "fa fa-warning icon-state-danger");
	  Node initiallyOpen = addChild(parentNode, "Initially open", "fa fa-folder icon-state-success", new AbstractMap.SimpleEntry<String, Boolean>("opened", true)); 
	  // add child to the child node created above
	  addChild(initiallyOpen, "Another node", "fa fa-file icon-state-warning");
	  
	  addChild(parentNode, "Another Custom Icon", "fa fa-warning icon-state-warning");
	  addChild(parentNode, "Disabled Node", "fa fa-check icon-state-success", new AbstractMap.SimpleEntry<String, Boolean>("disabled", true)); 	  
	  
	  Node subNodes = addChild(parentNode, "Sub Nodes", "fa fa-folder icon-state-danger"); 
	  // add child to the child node created above
	  addChild(subNodes, "Item 1", "fa fa-file icon-state-warning");
	  addChild(subNodes, "Item 2", "fa fa-file icon-state-success");
	  addChild(subNodes, "Item 3", "fa fa-file icon-state-default");
	  addChild(subNodes, "Item 4", "fa fa-file icon-state-danger");
	  addChild(subNodes, "Item 5", "fa fa-file icon-state-info");
	  
	  addChild(treeRootNode, "Another Node");
		  
	  System.out.println(buildJSTree(treeRootNode, "  "));
 
 }
 
}
