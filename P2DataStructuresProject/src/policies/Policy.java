package policies;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import resources.Customer;
import resources.Server;

/**
 * 
 * @author Hector Montes Martinez
 * 841-14-4960
 * 
 * The super class from where the other policies come from. It holds variables that are useful for the policies with their getters and setters.
 *
 */

public abstract class Policy {

	private int currentTime;
	private int nextEvent = 0;
	private int numberOfServers;
	private Server[] serverList;
	private Queue<Customer> pQueue;
	private List<Customer> attendedCustomers;
	private boolean lineChange;
	private String policyName;
	private float averageAttendedCustomer;
	public LinkedList<Customer>[] serverListNumber;
	
	
	//Runs the current policy simulation.
	protected void runSim() {
		while((!pQueue.isEmpty() || currentTime <= nextEvent)) {
			verifyCompletedTask();
			if(lineChange) {
			doLineChange();
			}
			verifyServiceStart();
		}
	}
	
	//To be implemented for each policy.
	public void doLineChange() {}
	
	public void verifyCompletedTask() {
		for(Server s: serverList) {
			if(s.isAttending() && currentTime == s.getCustomer().getTimeOfEnd()) {
				attendedCustomers.add(s.getCustomer());
				s.finishedAttending();
			}
		}
	}
	
	//To be implemented by each policy.
	public void verifyServiceStart() {}
	
	//Returns the results from the current policy simulation in a string.
	public String getValue() {
		if(getServers() == 1)
			setAverageAttendedCustomer(0);
		String str = getPolicyName() + " " + getServers() + ": " + "" + getNextEvent() + " " + String.format("%.2f", getAverageWaitingTime()) + " " + String.format("%.2f", calculateAverageAttendedPersonBeforeAnother());
		return str;
	}
	
	//Calculates the avergae waiting time.
	public float getAverageWaitingTime() {
		float vtr = 0;
		for(Customer c: attendedCustomers) {
			vtr += c.getWaitTime();
		}
		vtr = vtr / attendedCustomers.size();
		return vtr;
	}
	
	//Calculates the average waiting time of customers attended before other customer who arrived before.
	public float calculateAverageAttendedPersonBeforeAnother() {
			
			float ntr = getAverageAttendedCustomer()/getAttendedCustomerList().size();			
			return ntr;
		}
	
	//Verify if any attendedCustomer is receiving service before someone who arrived earlier
		public void verifyIfCustomerIsAttendedBefore(Server s) {
			for(int i = 0; i<serverListNumber.length; i++) {
				if(!serverListNumber[i].isEmpty()) {
					if(s.getCustomer().getArrival() > serverListNumber[i].getFirst().getArrival())
						setAverageAttendedCustomer(getAverageAttendedCustomer() + 1);
				}
			}
		}
	
	//Getters and Setters
	public int getCurrentTime() {
		return currentTime;
	}
	
	public void setCurrentTime(int time) {
		currentTime = time;
	}
	
	public int getNextEvent() {
		return nextEvent;
	}
	public void setNextEvent(int time) {
		nextEvent = time;
	}
	
	public Server[] getServerList() {
		return serverList;
	}
	
	public void setServerList(Server[] list) {
		serverList = list;
	}
	
	public List<Customer> getAttendedCustomerList() {
		return attendedCustomers;
	}
	
	public void setAttendedCustomerList(List<Customer> list) {
		attendedCustomers = list;
	}
	
	public Queue<Customer> getQueue(){
		return pQueue;
	}
	
	public void setQueue(Queue<Customer> q) {
		pQueue = q;
	}
	
	public boolean canChangeLine() {
		return lineChange;
	}
	
	public void setLineChange(boolean b) {
		lineChange = b;
	}
	
	public void setServers(int n) {
		numberOfServers = n;
	}
	
	public int getServers() {
		return numberOfServers;
	}
	
	public void setPolicyName(String name) {
		policyName = name;
	}
	
	public String getPolicyName() {
		return policyName;
	}
	
	public void setAverageAttendedCustomer(float n) {
		averageAttendedCustomer = n;
	}
	
	public float getAverageAttendedCustomer() {
		return averageAttendedCustomer;
	}
	
}
