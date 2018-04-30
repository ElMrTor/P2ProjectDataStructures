package resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Hector Montes Martinez
 * 841-14-4960
 * 
 * Class that writes and verifies the files to be used in each policy simulation.
 *
 */

public class OutputWriter {

	private static ArrayList<Customer> customerList;
	private static boolean correctFormat;
	private static String correctOutput = "Number of customers: ";
	private static int[] argumentsArr;
	
	public OutputWriter(int[] args){
		argumentsArr = args;
		customerList = new ArrayList<Customer>();
		correctFormat = false;
	}
	
	
	//Method that verifies the file given and decides if it is correct or not and gives the corresponding output to each case.
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
					int current = sc.nextInt();
					list.add(current);
					
					counter++;					
				}
				
				 if(counter%2 != 0) 
					writeIncorrectCase(fileName);
				
				
				 else { 
					correctFormat = true;
					for(int i = 0; i<list.size(); i = i+2) {
						customerList.add(new Customer(list.get(i),list.get(i+1)));
					}
					
					correctOutput = correctOutput.concat( ""+customerList.size());
					PolicyManager manager = new PolicyManager(argumentsArr, customerList);
					writeToFileCase(fileName, manager.processData());
					
					
				 }
				 sc.close();
			}
		}
		catch(Exception e) {			
			//Catch error
		}		
		
		
		
	}
	
	//Method that writes to output file the corresponding message if said file is not found.
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
			
		} catch (Exception e) {
			System.out.println("Error in static printing class: Not Existing Case");			
		}
	}
	
	//Method that writes to output file the corresponding message if said file has an incorrect format.
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
			
		}
		catch(Exception e) {
			System.out.println("Error in static printing class: Incorrect Case");			
		}
	}
	
	//Method that writes to output file the corresponding results from the file given.
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
		//Exception
		}
	}

	//Method that changes the filename so it can be written to the correct output folder.
	private static String  changeToOutput(String inputName) {
		String str = inputName;
		str = str.replace("inputFiles/","outputFiles/");
		str = str.replace(".txt", "_OUT.txt");	

		return str;
	}	
	
	
	//Getters 
	public ArrayList<Customer> getCustomerList(){
		return customerList;
	}
	
	public boolean isCorrectFormat() {
		return correctFormat;
	}
	
	
	
}
