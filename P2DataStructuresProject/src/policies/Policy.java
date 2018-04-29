package policies;

import java.util.List;
import java.util.Queue;

import resources.Customer;
import resources.Server;

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
	
	protected void runSim() {
		while((!pQueue.isEmpty() || currentTime <= nextEvent)) {
			verifyCompletedTask();
			if(lineChange) {
			doLineChange();
			}
			verifyServiceStart();
		}
	}
	
	public void doLineChange() {}
	
	public void verifyCompletedTask() {
		for(Server s: serverList) {
			if(s.isAttending() && currentTime == s.getCustomer().getTimeOfEnd()) {
				attendedCustomers.add(s.getCustomer());
				s.finishedAttending();
				System.out.println("Finished Attending Customer");
			}
		}
	}
	
	public void verifyServiceStart() {
		
	}
	
	public String getValue() {
		System.out.println("GetValueStart" + getNextEvent());
		String str = getPolicyName() + " " + getServers() + ": " + "" + getNextEvent() + " " + String.format("%.2f", getAverageWaitingTime()) + " " + getAverageAttendedCustomer();
		System.out.println("GetValueENd");
		return str;
	}
	
	public float getAverageWaitingTime() {
		float vtr = 0;
		for(Customer c: attendedCustomers) {
			vtr =+ c.getWaitTime();
		}
		vtr = vtr / attendedCustomers.size();
		return vtr;
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
