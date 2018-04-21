package resources;

public class Customer {

	private float timeOfArrival;
	private float requestedTime;
	
	public Customer(float toa, float rt) {
		timeOfArrival = toa;
		requestedTime = rt;
	}
	
	public float getArrival() {
		return timeOfArrival;
	}
	
	public float getRequest() {
		return requestedTime;
	}
	
}
