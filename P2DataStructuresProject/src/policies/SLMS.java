package policies;

import java.util.ArrayList;
import java.util.PriorityQueue;

import resources.Customer;
import resources.Server;

/**
 * 
 * @author Hector Montes Martinez
 * 841-14-4960
 * 
 * Policy that allows multiple servers but only has one line.
 *
 */

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
	}
	
	//Method that runs the simulation of the current policy.
	private void runSim() {
		
		while((!pQueue.isEmpty() || currentTime <= nextEvent) && currentTime < 1000) {
			
			verifyCompletedTask();
			verifyServiceStart();
			
			currentTime++;
		}		
	}	
	
	//Method that verifies if the servers completed attending the custumers.
	private void verifyCompletedTask() {
		for(Server s: serverList) {
			if(s.isAttending() && currentTime == s.getCustomer().getTimeOfEnd()) {
				attendedCustomers.add(s.getCustomer());
				s.finishedAttending();
			}
		}
	}
	
	//Method that makes the customers start taking their service according to their arrival time.
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
	
	
	
	//Method that returns the results from the current policy in a string.
	public String getValues() {
		String str = "SLMS " + numberOfServers + ": " + nextEvent + " " + String.format("%.2f", getAverageWaitingTime()) + " 0.00"   ;
		return str;
	}
	
	//Method that calculates the average waiting time of the customers.
	private float getAverageWaitingTime() {		
		float vtr = 0;		
		for(Customer c: attendedCustomers) {
			vtr += c.getWaitTime();
		}		
		vtr = vtr/attendedCustomers.size();		
		return vtr;
	}
	
	
}
