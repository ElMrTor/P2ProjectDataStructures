package resources;

public class Customer implements Comparable<Customer> {

	private int timeOfArrival;
	private int requestedTime;
	private int timeFinished;
	private int waitTime;
	
	public Customer(int toa, int rt) {
		timeOfArrival = toa;
		requestedTime = rt;
		timeFinished = 0;
		waitTime = 0;
	}
	
	public int getArrival() {
		return timeOfArrival;
	}
	
	public int getRequest() {
		return requestedTime;
	}
	public void setFinishedTime(int time) {
		timeFinished = time + requestedTime;
	}
	
	public void setWaitTime(int time) {
		if(time - getArrival() <= 0)
			waitTime = 0;
		else
		waitTime = time - getArrival();
	}
	
	public int getTimeOfEnd() {
		return timeFinished;
	}
	
	public int getWaitTime() {
		return waitTime;
	}
	
	

	@Override
	public int compareTo(Customer otherCustomer) {
		// TODO Auto-generated method stub
		//TODO verify method
		return Integer.compare(timeOfArrival, otherCustomer.getArrival());
//		return Integer.compare((int)otherCustomer.getArrival(), (int) timeOfArrival);

	}
	
}
