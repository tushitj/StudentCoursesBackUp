package studentCoursesBackup.myTree;

/**
 * Observer Interface. These are the methods to be implemented by each of the Observer Node.
 * @author tushitjain
 *
 */
public interface ObserverI {
	enum Operation {INSERT, DELETE};
	void update(Operation op, String course);
}
