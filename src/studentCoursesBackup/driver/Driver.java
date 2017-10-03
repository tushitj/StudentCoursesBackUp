package studentCoursesBackup.driver;

import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.util.Results;
import studentCoursesBackup.util.TreeBuilder;

/**
 * Driver class that runs the code.
 * It creates objects of TreeBuilder and Results and FileProcessor
 * @author tushitjain
 *
 */
public class Driver {
	/**
	 * A function that checks the valid file names
	 * @param fileArrs: array of string of filenames
	 * @return true if all the arguments are correct filenames
	 */
	public static boolean checkFile(String... fileArrs) {
		boolean flag = true;
		for (String filePath : fileArrs)
			flag = flag && filePath != null && filePath.trim().length() > 0 && !filePath.contains("${arg");
		return flag;
	}

	/**
	 * main function
	 * @param args: Command line arguments
	 */
	public static void main(String[] args) {
		int argLength = args.length;
		if (argLength != 5 || !checkFile(args)) {
			System.err.println("Incorrect number of arguments. 5 files, input and output should be entered");
			System.exit(1);
		}
		TreeBuilder tree1;
		TreeBuilder backup_tree1;
		TreeBuilder backup_tree2;

		String inputFile = args[0];
		String deleteFile = args[1];
		String outputFile1 = args[2];
		String outputFile2 = args[3];
		String outputFile3 = args[4];

		String str;

		FileProcessor input;
		FileProcessor delete;

		Results result = new Results();
		Results result1 = new Results();
		Results resultBackup1 = new Results();
		Results resultBackup2 = new Results();
		try {
			input = new FileProcessor(inputFile);
			delete = new FileProcessor(deleteFile);
			
			result.storeNewResult("Student Courses Backup");
			
			tree1 = new TreeBuilder();
			tree1.setBackupTree1(new TreeBuilder());
			tree1.setBackupTree2(new TreeBuilder());
			backup_tree1 = tree1.getBackupTree1();
			backup_tree2 = tree1.getBackupTree2();

			str = null;
			result.storeNewResult("Insert operation");
			while ((str = input.readLine()) != null) {
				String[] arr = str.split(":");
				int buId = Integer.parseInt(arr[0]);
				String courseName = arr[1];
				tree1.insertCourse(buId, courseName);
				result.storeNewResult(buId +" "+courseName);

			}

			str = null;
			result.storeNewResult("\n\nDelete operation");
			while ((str = delete.readLine()) != null) {
				String[] arr = str.split(":");
				int buId = Integer.parseInt(arr[0]);
				String courseName = arr[1];
				tree1.removeCourseFromNode(buId, courseName);
				result.storeNewResult(buId +" "+courseName);
			}

			result.writeToStdout("");
			tree1.print(result1);
			result1.writeToStdout("");
			result1.writeToFile(outputFile1);
			
			backup_tree1.print(resultBackup1);
			//resultBackup1.writeToStdout("");
			resultBackup1.writeToFile(outputFile2);
			
			backup_tree2.print(resultBackup2);
			//resultBackup2.writeToStdout("");
			resultBackup2.writeToFile(outputFile3);
		
		} catch (Exception ex) {
			System.out.println(ex);
			System.exit(1);
		}
	}
}