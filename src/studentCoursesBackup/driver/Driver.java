package studentCoursesBackup.driver;

import studentCoursesBackup.util.TreeBuilder;

public class Driver {
	public static void main(String[] args) {
		TreeBuilder tree1 = new TreeBuilder();
		tree1.insert(12,"A");
		tree1.insert(11, "B");
		tree1.insert(13, "c");
		tree1.insert(18, "d");
		tree1.insert(17, "e");
		tree1.insert(1, "f");
		tree1.insert(10, "h");
		tree1.insert(16, "g");
		
		tree1.print();
	}
	

}
