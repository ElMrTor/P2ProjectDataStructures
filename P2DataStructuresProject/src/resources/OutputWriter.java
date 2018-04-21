package resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class OutputWriter {

	private static ArrayList<String> correctFileNames;
	
	public OutputWriter(){
		correctFileNames = new ArrayList<String>();
	}
	
	public static void verifyInputFile(String fileName) {
		try {
			ArrayList<Float> list = new ArrayList<Float>();
			File f = new File(fileName);
			
			if(!f.exists()) {
				writeNotExistingCase(fileName);	
				
			}
			
			else {
				Scanner sc = new Scanner(f);
				int counter = 0;
				if(!sc.hasNext())
					counter = 1;
				while(sc.hasNext()) {					
					
					if(!sc.hasNextFloat()) {
						
						counter = 1;
						break;
					}					
					//verify
					Float current = sc.nextFloat();
					list.add(current);
					
					counter++;					
				}
				
				 if(counter%2 != 0) 
					writeIncorrectCase(fileName);
				
				//TODOelimate all the prints
				//Print for testing
				 else { 
					System.out.println("File is correct.");
					//Ends testing print
					correctFileNames.add(fileName);
					
					
				 }
				 System.out.println(list.toString());
				 sc.close();
			}
		}
		catch(Exception e) {
			System.out.println("Error identifying the input file");
			System.out.println(e);

		}		
		
		
		
	}
	
	public static void writeNotExistingCase(String fileName) {
		try {
			String newOut = changeToOutput(fileName);
			Path p = Paths.get(newOut);
			File f = new File(newOut);
			if(!f.exists())
			Files.createFile(p);
			PrintWriter pw = new PrintWriter(newOut);
			pw.write("Input file not found.");
			pw.close();
			
			//TODO prints
			System.out.println("El file no existe");
			//Ends test
			
		} catch (Exception e) {
			System.out.println("Error in static printing class: Not Existing Case");
			System.out.println(e);
		}
	}
	
	public static void writeIncorrectCase(String fileName) {
		try {
			String newOut = changeToOutput(fileName);
			Path p = Paths.get(newOut);
			File f = new File(newOut);
			if(!f.exists())
			Files.createFile(p);
			PrintWriter pw = new PrintWriter(newOut);
			pw.write("Input file does not meet the expected format or it is empty");
			pw.close();
			
			//TODO testing print
			System.out.println("Incorrect information or format.");
			
		}
		catch(Exception e) {
			System.out.println("Error in static printing class: Incorrect Case");
		}
	}
	
	//Still working on implementation
	public static void writeToFileCase(String fileName, String[] toPrint) {
		//TODO implement code
		try {
			String newOut = changeToOutput(fileName);
			Path p = Paths.get(newOut);
			File f = new File(newOut);
			if(!f.exists())
				Files.createFile(p);
			PrintWriter pw = new PrintWriter(newOut);
			
			for(int i = 0;i < toPrint.length; i++) {
				pw.println(toPrint[i]);
			} 	
		
			pw.close();
		
		}
		catch(Exception e) {
			//TODO
			System.out.println(e);
			System.out.println("**********Error en el writeToFileCase**************");
		}
	}

	private static String  changeToOutput(String inputName) {
		String str = inputName;
		str = str.replace("inputFiles/","outputFiles/");
		str = str.replace(".txt", "_OUT.txt");	

		return str;
	}
	
	public ArrayList<String> getFileList(){
		return correctFileNames;
	}
	
}
