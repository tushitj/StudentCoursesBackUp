package studentCoursesBackup.myTree;

interface ObserverI {
	enum Operation {INSERT, DELETE};
	void update(Operation op, String course);
}
