package studentCoursesBackup.myTree;

/**
 * Subject Interface. This defines the methods to be implemented by each node to act a service provider.
 * @author tushitjain
 *
 */
public interface SubjectI {
	void subscribeObserver(ObserverI observerI);
	void notifyAllObservers(ObserverI.Operation op,String course);
	void unsubscribeObserver(ObserverI observerI);
}
