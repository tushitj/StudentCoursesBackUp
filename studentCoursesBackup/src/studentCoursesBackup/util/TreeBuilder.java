package studentCoursesBackup.util;

import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.myTree.ObserverI;
import studentCoursesBackup.myTree.ObserverI.Operation;

/**
 * TreeBuilder class that creates the node and enters it into the tree. We are using BST.
 * @author tushitjain
 *
 */
public class TreeBuilder {
	private Node root;
	private TreeBuilder backupTree1;
	private TreeBuilder backupTree2;
	
	/**
	 * returns a backUpTree1
	 * @return: Treebuilder Type
	 */
	public TreeBuilder getBackupTree1() {
		return backupTree1;
	}
	
	/**
	 * sets the backuptree1. Initializes it when called from main
	 * @param backupTree1
	 */
	public void setBackupTree1(TreeBuilder backupTree1) {
		this.backupTree1 = backupTree1;
	}

	/**
	 * sets the backuptree2. Initializes it when called from main
	 * @param backupTree2
	 */
	public void setBackupTree2(TreeBuilder backupTree2) {
		this.backupTree2 = backupTree2;
	}


	/**
	 * returns instance of backUpTree2
	 * @return backupTree2
	 */
	public TreeBuilder getBackupTree2() {
		return backupTree2;
	}

	
	/**
	 * Constructor of tree Builder that initializes root to null.
	 */
	public TreeBuilder() {
		root = null;
	}
	
	/**
	 * Function that inserts the course into the node of Id
	 * @param id : buId
	 * @param course: courseName
	 * @return String value that displays message for successful course addition
	 */
	public String insertCourse(int id, String course){
		Node node;
		if(course != null && id>999 && id<10000){
			if((node = searchNode(id)) !=null){
				node.addCourse(course);
				node.notifyAll(Operation.INSERT, course);
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

	/**
	 * function that is used to call insert function recursively
	 * @param node
	 */
	private void insertNodeIntoTree(Node node) {
		root = insert(root,node);	
	}
	
	/**
	 * inserts the new node at the appropriate place in the tree
	 * @param curr : the node on which we have to traverse
	 * @param node : the node to be inserted
	 * @return inserted node
	 */
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

	/**
	 * search the node and returns it if found
	 * @param id : buId used to search the node
	 * @return Node
	 */
	private Node searchNode(int id) {
		Node node = searchRec(root,id);
		return node;
	}

	/**
	 * Recursive function to search the node called from search
	 * @param node : current node
	 * @param id: the id for which node is to be searched
	 * @return : the node if it is found
	 */
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
	
	/**
	 * Function to remove the course from the node
	 * @param id : buID to search the node
	 * @param courseName: Course to remove from node
	 * @return String that message the successful remove of course.
	 */
	public String removeCourseFromNode(int id,String courseName){
		Node node = searchNode(id);
		if(node == null){
			return null;
		}
		else{
			node.removeCourse(courseName);
			node.notifyAll(Operation.DELETE, courseName);
		}
		return "Course removed Successfully";
	}
	
	/**
	 * Print function to print Inorder nodes of the tree
	 * @param result
	 */
	public void print(Results result){
		printInOrder(result,root);
	}
	
	/**
	 * Recursive function that prints nodes of the tree inOrder.
	 * @param result: result object that stores the output in it
	 * @param node: Node that has to be printed in result object
	 */
	public void printInOrder(Results result , Node node){
		if(node!=null){
			
			printInOrder(result, node.getLeft());
			result.storeNewResult(node.toString());
			printInOrder(result, node.getRight());
			
		}
	}


}
