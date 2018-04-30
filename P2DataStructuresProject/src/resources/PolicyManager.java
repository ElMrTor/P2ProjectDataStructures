package resources;

import java.util.ArrayList;
import java.util.List;

import policies.MLMS;
import policies.MLMSBLL;
import policies.MLMSBWT;
import policies.SLMS;

/**
 * 
 * @author Hector E. Montes Martinez
 * 841-14-4960
 *
 *Class that runs all policies and takes all the results and stores them in an ArrayList to be later printed to the corresponding file.
 *
 */


public class PolicyManager {

	private int[] arguments;
	private ArrayList<String> toWrite;
	private ArrayList<Customer> cList;
	
	
	
	public PolicyManager(int[] args, ArrayList<Customer> list) {
		
		arguments = args.clone();
		cList = list;		
		toWrite = new ArrayList<String>();
		toWrite.add("Number of customers: " + list.size());
		
	}
	
	//Method that collects the data from all policies and returns it as an array list.
	public ArrayList<String> processData() {
	
		for(int i = 0; i < arguments.length; i++) {
			SLMS serv = new SLMS(cList, arguments[i]);
			toWrite.add(serv.getValues());		
		}		
		
		for(int i = 0; i < arguments.length; i++) {
			MLMS serv = new MLMS(cList, arguments[i]);
			toWrite.add(serv.getValue());
		}
		
		for(int i = 0; i < arguments.length; i++) {
			MLMSBLL serv = new MLMSBLL(cList, arguments[i]);
			toWrite.add(serv.getValue());
		}
		
		for(int i = 0; i < arguments.length; i++) {
			MLMSBWT serv = new MLMSBWT(cList, arguments[i]);
			toWrite.add(serv.getValue());
		}
		
		
		return toWrite;	
	
	}
	
	
}
