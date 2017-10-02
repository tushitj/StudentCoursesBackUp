package studentCoursesBackup.util;

import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.myTree.ObserverI;
import studentCoursesBackup.myTree.ObserverI.Operation;

public class TreeBuilder {
	private Node root;
	private TreeBuilder backupTree1;
	private TreeBuilder backupTree2;
	
	public TreeBuilder getBackupTree1() {
		return backupTree1;
	}
	

	public void setBackupTree1(TreeBuilder backupTree1) {
		this.backupTree1 = backupTree1;
	}


	public void setBackupTree2(TreeBuilder backupTree2) {
		this.backupTree2 = backupTree2;
	}


	public TreeBuilder getBackupTree2() {
		return backupTree2;
	}

	

	public TreeBuilder() {
		root = null;
	}
	
	public String insertCourse(int id, String course){
		Node node;
		if(course != null && id>999 && id<10000){
			if((node = searchNode(id)) !=null){
				node.addCourse(course);
				node.notifyAllObservers(Operation.INSERT, course);
			}else{
				node = new Node(id,course);
				insertNodeIntoTree(node);
				try{
					ObserverI nodeBackup1 = node.clone();
					backupTree1.insertNodeIntoTree((Node)nodeBackup1);
					node.subscribeObserver(nodeBackup1);
				}catch(Exception e){
					System.err.println("Error occured while cloning backupNode1");
				}
				try{
					ObserverI nodeBackup2 = node.clone();
					backupTree2.insertNodeIntoTree((Node)nodeBackup2);
					node.subscribeObserver(nodeBackup2);
				}catch(Exception e){
					System.err.println("Error occured while cloning backupNode2");
				}
				
			}
		}
		return "Course added successfuly";
		
	}

	private void insertNodeIntoTree(Node node) {
		root = insert(root,node);	
	}

	private Node insert(Node curr, Node node) {
		if(curr==null){
			curr = node;
			return curr;
		}
		else if(curr.getId() > node.getId()){
			 curr.setLeft(insert(curr.getLeft(),node));
		}
		else if(curr.getId() < node.getId()){
			 curr.setRight(insert(curr.getRight(),node));
		}
		return curr;
	}

	private Node searchNode(int id) {
		Node node = searchRec(root,id);
		return node;
	}

	private Node searchRec(Node node, int id) {
		Node found = null;
		if(node == null || node.getId() == id){
			return node;
		}
		else if(node.getId() > id){
			found = searchRec(node.getLeft(), id);
		}
		else if(node.getId() < id){
			found = searchRec(node.getRight(), id);
		}
		return found;
	}
	
	public String removeCourseFromNode(int id,String courseName){
		Node node = searchNode(id);
		if(node == null){
			return null;
		}
		else{
			node.removeCourse(courseName);
			node.notifyAllObservers(Operation.DELETE, courseName);
		}
		return "Course removed Successfully";
	}
	
	public void print(Results result){
		printInOrder(result,root);
	}
	
	public void printInOrder(Results result , Node node){
		if(node!=null){
			
			printInOrder(result, node.getLeft());
			result.storeNewResult(node.toString());
			printInOrder(result, node.getRight());
			
		}
	}


}
