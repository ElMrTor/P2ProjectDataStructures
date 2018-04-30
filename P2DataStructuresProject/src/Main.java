import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import resources.Customer;
import resources.OutputWriter;


/**
 * 
 * @author Hector E. Montes Matrinez
 * 841-14-4960
 * 
 * Main class that reads the list of files and simulates eachs policy according to what is found on the files.
 *
 */


public class Main {

	static Scanner sc;
	static ArrayList<Customer> allCustomers;
	static int numberOfServers;
	static int[] servers;
	static OutputWriter oW;
	
	public static void main(String[] args) {
		
		//Stores the arguments in a array of integers.
		servers = new int[args.length];		
		for(int i = 0; i < args.length; i++) {
			//TODO do the loop with every server
			servers[i] = Integer.parseInt(args[i]);
			
		}
		
		//Class that handles the outputs of the files.
		oW = new OutputWriter(servers);

		//Reads the file with all lists and searches for them.
		try {
			File f = new File("inputFiles/dataFiles.txt");
			Scanner sc = new Scanner(f);
			sc.useDelimiter("[\r\n]");
			while(sc.hasNext())
				OutputWriter.verifyInputFile(changeToFolderPath(sc.next()));
			
			sc.close();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}		
	}
	
	
	//Changes the string so that the data files can be found.
	private static String changeToFolderPath(String fileName) {
		String str = "inputFiles/";
		return str.concat(fileName);
	}
	
}
