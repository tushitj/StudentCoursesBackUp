package studentCoursesBackup.myTree;

import java.util.ArrayList;
import java.util.List;

public class Node implements ObserverI, SubjectI, Cloneable {
	private List<ObserverI> observerList = new ArrayList<>();
	private Node left;
	private Node right;
	private int id;
	private List<String> courses;
	public Node(int idIn , String courseIn){
		left = right = null;
		courses = new ArrayList<String>();
		this.id = idIn;
		courses.add(courseIn);
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
	public void notifyAllObservers(Operation op , String course) {
		for(ObserverI o : observerList){
			o.update(op,course);
		}
		
	}
	
	@Override
	public void update(Operation op ,String course) {
		switch(op){
		case INSERT: addCourse(course);
					break;
		case DELETE: removeCourse(course);
		}
	}
	private void addCourse(String course) {
		courses.add(course);
		
	}
	private void removeCourse(String course) {
		courses.remove(course);
		
	}
}
