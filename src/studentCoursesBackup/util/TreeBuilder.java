package studentCoursesBackup.util;

import studentCoursesBackup.myTree.Node;
public class TreeBuilder {
	private Node root;
	
	public TreeBuilder() {
		root = null;
	}
	
	public void insert(int id, String courseName){
		root = insertRec(root,id,courseName);
	}
	public Node search(int id){
		return searchRec(root, id);
	}
	private Node searchRec(Node root, int id){
		if(root == null || root.getId() == id){
			return root;
		}
		if(root.getId() > id){
			return searchRec(root.getLeft(), id);
		}
		else
			return searchRec(root.getRight(), id);
		
	}
	
	public void print(){
		inOrderPrintRec(root);
	}

	private void inOrderPrintRec(Node node) {
		if(node==null){
			return;
		}
		
			inOrderPrintRec(node.getLeft());
			System.out.println(node.getId());
			inOrderPrintRec(node.getRight());
	}

	private Node insertRec(Node root,int id, String courseName) {
		if(root == null){
			root = new Node(id,courseName);
			return root;
		}
		
		if(id < root.getId()){
			root.setLeft(insertRec(root.getLeft(),id, courseName));
		}
		else if(id > root.getId()){
			root.setRight(insertRec(root.getRight(),id,courseName));
		}
		return root;
	}

}
