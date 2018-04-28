import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import policies.SLMS;
import resources.Customer;
import resources.OutputWriter;

public class Main {

	static Scanner sc;
	static ArrayList<Customer> allCustomers;
	static int numberOfServers;
	static int[] servers;
	static OutputWriter oW;
	
	public static void main(String[] args) {
		
//		oW = new OutputWriter();

		
		servers = new int[args.length];
		

		
		
		for(int i = 0; i < args.length; i++) {
			//TODO do the loop with every server
			servers[i] = Integer.parseInt(args[i]);
			
//			if(oW.isCorrectFormat()) {
//			SLMS line = new SLMS(oW.getCustomerList(), servers[i]);
//			}
			
		}
		
		oW = new OutputWriter(servers);
		OutputWriter.verifyInputFile("inputFiles/data_1.txt");


//		oW = new OutputWriter();
		//Testing OutputClass
		
		
		//Testing el reading de dataFiles
//		try {
//			File f = new File("inputFiles/dataFiles.txt");
//			Scanner sc = new Scanner(f);
//			sc.useDelimiter("[\r\n]");
//			while(sc.hasNext())
//				OutputWriter.verifyInputFile(changeToFolderPath(sc.next()));
//			
//			sc.close();
//			
//		}
//		catch(Exception e) {
//			System.out.println(e);
//		}
		
		
		
		
		//Caso 1 --> correct
//		OutputWriter.verifyInputFile("inputFiles/data_1.txt");
//		System.out.println("Caso 1");
//		
//		SLMS poly = new SLMS(oW.getCustomerList(), 2);
//		poly.printList();
//		
		
//		
//		//Caso 2 --> bad format letters
//		OutputWriter.verifyInputFile("inputFiles/data_2.txt");
//		System.out.println("Caso 2");
//		
//		//Caso 3 --> empty
//		OutputWriter.verifyInputFile("inputFiles/data_3.txt");
//		System.out.println("Caso 3");
//		
//		//Caso 4 --> correct
//		OutputWriter.verifyInputFile("inputFiles/data_4.txt");
//		System.out.println("Caso 4");
//		
//		//Caso 5 --> bad format, no esta completo
//		OutputWriter.verifyInputFile("inputFiles/data_5.txt");
//		System.out.println("Caso 5");
//		
//		//Caso 6 --> el file no existe
//		OutputWriter.verifyInputFile("inputFiles/data_6.txt");
//		System.out.println("Caso 6");
//
//		System.out.println(oW.getFileList().toString());
		
//		readFiles();
		
		
	}
	
	
	public static void readFiles() {
		allCustomers = new ArrayList<Customer>();
		File dataText = new File("inputFiles/dataFiles.txt");
		try {
			sc = new Scanner(dataText);
			sc.useDelimiter("[\n\r]");
			while(sc.hasNextLine()) {
				
				
				
				
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void validateFile(String filePath) {
		try {
			File current = new File(filePath);
			Scanner sc = new Scanner(current);
//			if(sc.hasNext()) ||  {
//				//tirar el file con
//			}
			
			
		}
		catch(Exception e) {
			//Tirar el file diciendo que no existe
		}
	}

	
	private static void writeFiles() {
		
	}
	
	private static String changeToFolderPath(String fileName) {
		String str = "inputFiles/";
		return str.concat(fileName);
	}
	
}
