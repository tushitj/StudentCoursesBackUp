package studentCoursesBackup.myTree;

public interface SubjectI {
	void subscribeObserver(ObserverI observerI);
	void notifyAllObservers(ObserverI.Operation op,String course);
	void unsubscribeObserver(ObserverI observerI);
}
