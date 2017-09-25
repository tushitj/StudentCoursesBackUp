package studentCoursesBackup.myTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Node implements ObserverI, SubjectI, Cloneable, Comparable<Node> {
	private List<ObserverI> observerList = new ArrayList<>();
	private Node left;
	private Node right;
	private int id;
	private List<String> courses;

	public Node(int idIn, String courseIn) {
		left = right = null;
		this.id = idIn;
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
		courses.add(course);

	}

	private void removeCourse(String course) {
		if(null != courses)
			courses.remove(course);

	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	protected Object clone() {
		Node clone = null;
		try {
			clone = (Node) super.clone();
			clone.id = this.id;
			clone.courses = new ArrayList<>(this.courses);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}

	@Override
	public int compareTo(Node o) {
		if (id < o.id) {
			return -1;
		} else if (id > o.id) {
			return 1;
		}
		return 0;
	}

}
