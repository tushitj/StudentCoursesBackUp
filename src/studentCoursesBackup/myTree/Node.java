package studentCoursesBackup.myTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Node class that is a structure to save in the tree that implements Observer, Subject, Cloneable and Comparable Interface.
 * Class has two pointers to left and right nodes.
 * The node saves buId and a HashSet of courses.
 * I have used hashset for courses because it is fast and efficient to find
 * a course of a buId and also duplicate courses are handled by hashset.
 * @author tushitjain
 *
 */
public class Node implements ObserverI, SubjectI, Cloneable, Comparable<Node> {
	private List<ObserverI> observerList = new ArrayList<>();
	private Node left;
	private Node right;
	private int id;
	private HashSet<String> courses;

	/**
	 * Constructor of Node class
	 * @param idIn : buId
	 * @param courseIn: courseName
	 */
	public Node(int idIn, String courseIn) {
		setLeft(setRight(null));
		this.setId(idIn);
		addCourse(courseIn);
	}

	/**
	 * A function that registers the observer.
	 */
	@Override
	public void subscribeObserver(ObserverI observer) {
		observerList.add(observer);

	}
	
	/**
	 * A function that UnSubscribe the observer.
	 */
	@Override
	public void unsubscribeObserver(ObserverI observerI) {
		observerList.remove(observerI);

	}

	/**
	 * A function that notifies the observers to pull the data.
	 */
	@Override
	public void notifyAllObservers(Operation op, String course) {
		for (ObserverI o : observerList) {
			o.update(op, course);
		}

	}

	/**
	 * Update function that adds or remove the course from the node.
	 */
	@Override
	public void update(Operation op, String course) {
		switch (op) {
		case INSERT:
			addCourse(course);
			break;
		case DELETE:
			removeCourse(course);
			break;
		}
	}

	/**
	 * A function that adds the course to the node for a particular buId.
	 * @param course: the courseName
	 */
	public void addCourse(String course) {
		if(null == courses)
			courses = new HashSet<>();
		if(course.charAt(0) > 'K'){
			//Do Nothing
		}
		else{
			courses.add(course);
		}
		

	}

	/**
	 * Removes the course for a particular node
	 * @param course: courseName to be removed if it exists.
	 */
	public void removeCourse(String course) {
		if(null != courses)
			courses.remove(course);

	}

	/**
	 * Method returns the Id of the node
	 */
	@Override
	public int hashCode() {
		return getId();
	}
	/**
	 * Clone method that creates a deep copy of a node.
	 */
	@Override
	public Node clone() {
		Node clone = null;
		try {
			clone = (Node) super.clone();
			clone.setId(this.getId());
			clone.courses = new HashSet<>(this.courses);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}	
	
	/**
	 * Method overridden from Comparable interface to compare two nodes.
	 */
	@Override
	public int compareTo(Node o) {
		if (getId() < o.getId()) {
			return -1;
		} else if (getId() > o.getId()) {
			return 1;
		}
		return 0;
	}

	/**
	 * Returns the id of the node
	 * @return BuId
	 */
	public int getId() {
		return id;
	}

	/**
	 * sets the buID
	 * @param id buID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the left node of the current node.
	 * @return : left Node
	 */
	public Node getLeft() {
		return left;
	}

	/**
	 * sets the left of the node to a new node
	 * @param left: new Node
	 */
	public void setLeft(Node left) {
		this.left = left;
	}

	/**
	 * gets the right node of the current node
	 * @return
	 */
	public Node getRight() {
		return right;
	}

	/**
	 * sets the right of the current node to new node and return it.
	 * @param right : new Node
	 * @return: right node
	 */
	public Node setRight(Node right) {
		this.right = right;
		return right;
	}
	/**
	 * toString method to get the details of the particular Node.
	 */
	@Override
	public String toString(){
		return String.format("The id is %d list : %s", getId() ,courses.size() > 0 ? beautifyStringList(courses.toString()) :"--No course enrolled--");
	}
	/**
	 * Auxiliary method to make look good a string
	 * @param str : String to look formatted
	 * @return : formatted string
	 */
	private String beautifyStringList(String str){
		return str.substring(1,str.length()-1);	
	}

	/**
	 * returns the string of courses from hashSet courses.
	 * @return A string of all courses.
	 */
	public String getCourses() {
		StringBuilder sbr = new StringBuilder();
		for(String str : courses){
			sbr.append(str);
			sbr.append(" ");
		}
		return sbr.toString();
	}

}
