package studentCoursesBackup.myTree;

public interface ObserverI {
	enum Operation {INSERT, DELETE};
	void update(Operation op, String course);
}
