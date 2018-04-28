package resources;

import java.util.ArrayList;
import java.util.List;

import policies.SLMS;

public class PolicyManager {

	private int[] arguments;
	private ArrayList<String> toWrite;
	private ArrayList<Customer> cList;
	
	
	
	public PolicyManager(int[] args, ArrayList<Customer> list) {
		
		arguments = args.clone();
		cList = list;		
		toWrite = new ArrayList<String>();
		toWrite.add("Number of customers: " + list.size());
//		toWrite = "Number of Customers: " + list.size();
		
	}
	
	public ArrayList<String> processData() {
	
		for(int i = 0; i < arguments.length; i++) {
			System.out.println("policymanager is running" + arguments[i]);
			SLMS serv = new SLMS(cList, arguments[i]);
			toWrite.add(serv.getValues());
//			toWrite = toWrite.concat("\n");
//			toWrite = toWrite.concat(serv.getValues());			
		}
		
		for(int i = 0; i < arguments.length; i++) {
//			TODO Hacer lo mismo que arriba con cada policy			
		}
		
		for(int i = 0; i < arguments.length; i++) {
//			TODO Hacer lo mismo que arriba con todos los policies para conseguir el string
		}
		
		for(int i = 0; i < arguments.length; i++) {
//			TODO Hacer lo mismo que arriba para conseguir el string
		}
		
//		TODO Devolver el string creado con todas las estrategias
		System.out.println("It ran");
		System.out.println(toWrite);
		return toWrite;	
	
	}
	
	
}
