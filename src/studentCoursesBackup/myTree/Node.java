package studentCoursesBackup.myTree;

import java.util.ArrayList;
import java.util.List;

public class Node implements ObserverI, SubjectI, Cloneable, Comparable<Node> {
	private List<ObserverI> observerList = new ArrayList<>();
	private Node left;
	private Node right;
	private int id;
	private List<String> courses;

	public Node(int idIn, String courseIn) {
		setLeft(setRight(null));
		this.setId(idIn);
		addCourse(courseIn);
	}

	@Override
	public void subscribeObserver(ObserverI observerI) {
		observerList.add(observerI);

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

	private void addCourse(String course) {
		if(null == courses)
			courses = new ArrayList<>();
		if(course.charAt(0) > 'K'){
			//Do Nothing
		}
		else{
			courses.add(course);
		}
		

	}

	private void removeCourse(String course) {
		if(null != courses)
			courses.remove(course);

	}

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	protected Object clone() {
		Node clone = null;
		try {
			clone = (Node) super.clone();
			clone.setId(this.getId());
			clone.courses = new ArrayList<>(this.courses);
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

}
