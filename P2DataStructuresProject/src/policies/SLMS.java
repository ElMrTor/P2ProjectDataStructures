package policies;

import java.util.ArrayList;
import java.util.PriorityQueue;

import resources.Customer;
import resources.Server;

public class SLMS {

	private int currentTime;
	private int nextEvent;
	private int numberOfServers;
	private Server[] serverList;
	private PriorityQueue<Customer> pQueue;
	private ArrayList<Customer> attendedCustomers;
	
	
	public SLMS(ArrayList<Customer> cList, int server) {

		pQueue = new PriorityQueue<Customer>();
		attendedCustomers = new ArrayList<Customer>();
		
		pQueue.addAll(cList);
		nextEvent = 0;
		currentTime = 0;
		numberOfServers = server;
		serverList = new Server[numberOfServers];
		for(int i = 0; i < numberOfServers; i++) {
			serverList[i] = new Server();
		}
		
		runSim();
		printResults();
	}
	
	private void runSim() {
		
		//TODO class is incomplete needs to be fully implemented.
		while((!pQueue.isEmpty() || currentTime <= nextEvent) && currentTime < 1000) {
			
			verifyCompletedTask();
			verifyServiceStart();
			
			currentTime++;
		}
		
		
	}
	
	//TODO Unneeded method
	public void printList() {

		while(!pQueue.isEmpty())
			System.out.println("Arrival Time: " + pQueue.peek().getArrival() + " Requested Time: " + pQueue.remove().getRequest());

	}
	
	private void verifyCompletedTask() {
		for(Server s: serverList) {
			if(s.isAttending() && currentTime == s.getCustomer().getTimeOfEnd()) {
				attendedCustomers.add(s.getCustomer());
				s.finishedAttending();
			}
		}
	}
	
	private void verifyServiceStart() {
		
		for(Server s: serverList) {
			if(!pQueue.isEmpty()) {
				if(!s.isAttending() && pQueue.peek().getArrival() <= currentTime) {
					s.setCustomer(pQueue.poll());
					s.getCustomer().setWaitTime(currentTime);
					s.getCustomer().setFinishedTime(currentTime);
					if(s.getCustomer().getTimeOfEnd() > nextEvent)
						nextEvent = s.getCustomer().getTimeOfEnd();
				}
			}
		}		
	}
	
	private void printResults() {
		System.out.println("Arrival Request End Wait");
		for(Customer c : attendedCustomers) {
			
			System.out.println(c.getArrival() + " " + c.getRequest() + " " + c.getTimeOfEnd() + " " + c.getWaitTime());
		}
//		attendedCustomers = null;
//		pQueue = null;
//		attendedCustomers = new ArrayList<Customer>();
//		pQueue = new PriorityQueue<Customer>();
		
	}
	
//	TODO Metodo que me va a dar el string de cada estrategia con su servers para juntarse al ultimo file 
	// Verificar la m para ver si se le da un procedimiento que de 0, pero en este caso siempre da 0.
	public String getValues() {
		String str = "SLMS " + numberOfServers + ": " + nextEvent + " " + String.format("%.2f", getAverageWaitingTime()) + " 0.00"   ;
		return str;
	}
	
	private float getAverageWaitingTime() {		
		float vtr = 0;		
		for(Customer c: attendedCustomers) {
			vtr += c.getWaitTime();
		}		
		vtr = vtr/attendedCustomers.size();		
		return vtr;
	}
	
	
}
