package policies;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

import resources.Customer;

public class SLMS {

	private int currentTime;
	private int nextEvent;
	private int numberOfServers;
	private int[] allCustomerWaitingTimes;
	private Queue<Customer> customerQueue;
	private PriorityQueue<Customer> pQueue;	
	public SLMS(ArrayList<Customer> cList, int server) {

		pQueue = new PriorityQueue<Customer>();
		
		pQueue.addAll(cList);
		allCustomerWaitingTimes = new int[pQueue.size()];
		nextEvent = 0;
		currentTime = 0;
		numberOfServers = server;
	}
	
	private void runSim() {
		
		//TODO class is incomplete needs to be fully implemented.
		
		while(!pQueue.isEmpty() || currentTime >= nextEvent) {
			
			if(currentTime == pQueue.peek().getArrival()) {
				pQueue.peek().setWaitTime(currentTime - pQueue.peek().getArrival());
			}
			
			
			
			
			currentTime++;
		}
		
	}
	
	public void printList() {

		while(!pQueue.isEmpty())
			System.out.println("Arrival Time: " + pQueue.peek().getArrival() + " Requested Time: " + pQueue.remove().getRequest());

	}
	
}
