package studentCoursesBackup.util;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	private PrintWriter out;
	private StringBuilder sb;
	
	public Results(){
		sb = new StringBuilder();
	}
	
	/**
	 * This function created the whole string to write to the file
	 * @param s: the string value to append to already created String
	 */
	public void storeNewResult(String s){
		sb.append(s);
		sb.append("\n");
	}
	
	/**
	 * this function writes the String value to console.
	 */
	@Override
	public void writeToStdout(String s) {
		System.out.println(sb);
	}

	/**
	 * Implemented function from FileDisplayInterface that writes the String to the File
	 */
	@Override
	public void writeToFile(String s) {
		File file;
		file = new File(s);
		if(!file.isDirectory() && file.exists()){
			file.delete();
		}
		try {
			out = new PrintWriter(new FileWriter(s, true), true);
			out.write(sb.toString());
		} catch (IOException e) {
			System.out.println("There was some error in creating the file");
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
	/**
	 * Overridden method toString to give class description
	 */
	@Override
	public String toString(){
		return "Result class implements FileDisplay and StdDisplay Interfaces";
	}
	

}