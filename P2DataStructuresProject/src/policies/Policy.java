package policies;

import java.util.List;
import java.util.Queue;

import resources.Customer;
import resources.Server;

public abstract class Policy {

	private int currentTime;
	private int nextEvent;
	private int numberOfServers;
	private Server[] serverList;
	private Queue<Customer> pQueue;
	private List<Customer> attendedCustomers;
	
	private void runSim() {
		while((!pQueue.isEmpty() || currentTime <= nextEvent)) {
			verifyCompletedTask();
			doLineChange();
			verifyServiceStart();
		}
	}
	
	private void doLineChange() {}
	
	private void verifyCompletedTask() {}
	
	private void verifyServiceStart() {}
	
	
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
	
}
