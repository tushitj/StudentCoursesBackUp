package studentCoursesBackup.myTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Node implements ObserverI, SubjectI, Cloneable, Comparable<Node> {
	private List<ObserverI> observerList = new ArrayList<>();
	private Node left;
	private Node right;
	private int id;
	private HashSet<String> courses;

	public Node(int idIn, String courseIn) {
		setLeft(setRight(null));
		this.setId(idIn);
		addCourse(courseIn);
	}

	@Override
	public void subscribeObserver(ObserverI observer) {
		observerList.add(observer);

	}

	@Override
	public void unsubscribeObserver(ObserverI observerI) {
		observerList.remove(observerI);

	}

	@Override
	public void notifyAllObservers(Operation op, String course) {
		for (ObserverI o : observerList) {
			o.update(op, course);
		}

	}

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

	public void removeCourse(String course) {
		if(null != courses)
			courses.remove(course);

	}

	@Override
	public int hashCode() {
		return getId();
	}

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

	@Override
	public int compareTo(Node o) {
		if (getId() < o.getId()) {
			return -1;
		} else if (getId() > o.getId()) {
			return 1;
		}
		return 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public Node setRight(Node right) {
		this.right = right;
		return right;
	}
	
	@Override
	public String toString(){
		return String.format("The id is %d list : %s", getId() ,courses.size() > 0 ? beautifyStringList(courses.toString()) :"--No course enrolled--");
	}
	private String beautifyStringList(String str){
		return str.substring(1,str.length()-1);
		
	}

	public String getCourses() {
		StringBuilder sbr = new StringBuilder();
		for(String str : courses){
			sbr.append(str);
			sbr.append(" ");
		}
		return sbr.toString();
	}

}
