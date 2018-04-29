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
	private static ArrayList<Customer> customerList;
	private static boolean correctFormat;
	private static String correctOutput = "Number of customers: ";
	private static int[] argumentsArr;
	
	public OutputWriter(int[] args){
		argumentsArr = args;
		correctFileNames = new ArrayList<String>();
		customerList = new ArrayList<Customer>();
		correctFormat = false;
	}
	
	public static void verifyInputFile(String fileName) {
		try {
			ArrayList<Integer> list = new ArrayList<Integer>();
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
					
					if(!sc.hasNextInt()) {
						
						counter = 1;
						break;
					}					
					//verify
					int current = sc.nextInt();
					list.add(current);
					
					counter++;					
				}
				
				 if(counter%2 != 0) 
					writeIncorrectCase(fileName);
				
				//TODOelimate all the prints
				//Print for testing
				 else { 
					System.out.println("File is correct.");
					correctFormat = true;
					//Adding the info the a customer list
					for(int i = 0; i<list.size(); i = i+2) {
						customerList.add(new Customer(list.get(i),list.get(i+1)));
					}
					
					correctOutput = correctOutput.concat( ""+customerList.size());
					PolicyManager manager = new PolicyManager(argumentsArr, customerList);
					//Verify if it works.
					writeToFileCase(fileName, manager.processData());
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
			//TODO erase
			e.printStackTrace();

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
			//TODO erase
			e.printStackTrace();
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
			//TODO erase
			e.printStackTrace();
		}
	}
	
	public static void writeToFileCase(String fileName, ArrayList<String> toPrint) {
		try {
			String newOut = changeToOutput(fileName);
			Path p = Paths.get(newOut);
			File f = new File(newOut);
			if(!f.exists())
				Files.createFile(p);
			PrintWriter pw = new PrintWriter(newOut);
			
			
			for(String s: toPrint) {
				pw.println(s);
			}
		
			pw.close();
		
		}
		catch(Exception e) {
			//TODO fix the catch exception
			System.out.println(e);
			System.out.println("**********Error en el writeToFileCase**************");
		//TODO erase
			e.printStackTrace();
		
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
	
	public ArrayList<Customer> getCustomerList(){
		return customerList;
	}
	
	public boolean isCorrectFormat() {
		return correctFormat;
	}
	
	
	
}
