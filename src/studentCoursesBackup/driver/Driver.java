package studentCoursesBackup.driver;

import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.util.Results;
import studentCoursesBackup.util.TreeBuilder;

public class Driver {
	public static boolean checkFile(String... fileArrs){
		boolean flag = true;
		for(String filePath : fileArrs)
			flag = flag && filePath !=null && filePath.trim().length() > 0 && !filePath.contains("${arg");
		return flag;
	}
	public static void main(String[] args) {
		int argLength = args.length;
		if(argLength!=5){
			System.err.println("Incorrect number of arguments. 2 files, input and output should be entered");
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
		if(checkFile(inputFile,deleteFile,outputFile1,outputFile2,outputFile3)){
			input = new FileProcessor(inputFile);
			delete = new FileProcessor(deleteFile);
			result.storeNewResult("Student Courses Backup");
			tree1 = new TreeBuilder();
			tree1.setBackupTree1(new TreeBuilder());
			tree1.setBackupTree2(new TreeBuilder());
			backup_tree1 = tree1.getBackupTree1();
			backup_tree2 = tree1.getBackupTree2();
			
			while((str = input.readLine())!= null){
				 String[] arr = str.split(":");
				 int buId = Integer.parseInt(arr[0]);
				 String courseName = arr[1];
				 String s = tree1.insertCourse(buId, courseName);
			}
			
			
		}
			
		}
	}
