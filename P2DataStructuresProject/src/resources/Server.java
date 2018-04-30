package resources;

/**
 * 
 * @author Hector Montes Martinez
 *841-14-4960
 *
 *Class that represents a server and holds the current customer.
 *
 */

public class Server {

	private Customer customer;
	private boolean attending;
	
	public Server() {
		attending = false;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public boolean isAttending() {
		return attending;
	}
	
	public void changeAttend() {
		this.attending = !attending;
	}
	
	public void setCustomer(Customer c) {
		changeAttend();
		customer = c;
	}
	
	public void finishedAttending() {
		changeAttend();
		customer = null;
	}
	
	
}
