package policies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import resources.Customer;
import resources.Server;

public class MLMS extends Policy {
	
	public LinkedList<Customer> lLCustomer;
	
	
	public MLMS(ArrayList<Customer> cList, int server) {
		serverListNumber = new LinkedList[server];
		for(int i = 0; i < serverListNumber.length; i++) {
			serverListNumber[i] = new LinkedList<Customer>();
		}
		setPolicyName("MLMS");
		setQueue(new PriorityQueue<Customer>(cList));
		lLCustomer = new LinkedList<Customer>(getQueue());
		setQueue(null);
		setAttendedCustomerList(new ArrayList<Customer>());
		setLineChange(true);
		setCurrentTime(0);
		setNextEvent(0);
		setServers(server);
		setAverageAttendedCustomer(0);
		setServerList(new Server[getServers()]);
		for(int i = 0; i < getServers(); i++) {
			getServerList()[i] = new Server();
		}
		
		
		
		
		runSim();
		
//		System.out.println("The size of the attended customer list is: " + getAttendedCustomerList().size());
	
		for(Customer c: getAttendedCustomerList())
			System.out.println(c.getWaitTime());
		
	}
	
	@Override
	protected void runSim() {
		
		
		while(!areLinesEmpty() || getCurrentTime() <= getNextEvent() || !lLCustomer.isEmpty()) {
			verifyCompletedTask();
			if(canChangeLine()) {
				doLineChange();
			}
			verifyServiceStart();
			setCurrentTime(getCurrentTime() + 1);
			
			
		}
	}
	

	
	@Override
	public void doLineChange() {
		
	
		while(!lLCustomer.isEmpty() && lLCustomer.getFirst().getArrival() <= getCurrentTime() ) {
			//TODO erase
//			System.out.println("It entered the while loop");
			//TODO erase
			int minSize = getMinimunLine();
			if(minSize != -1) {
				for(int i = 0; i < serverListNumber.length; i++) {
					if(serverListNumber[i].size() == minSize) {
						serverListNumber[i].add(lLCustomer.removeFirst());
						//TODO erase
	//					System.out.println("A customer was added to server in line: " + i);
						//TODO erase
						break;
					}
				}
			}
		}
		
	}
	
	private boolean areLinesEmpty() {
		for(int i = 0; i < serverListNumber.length; i++) {
			if(!serverListNumber[i].isEmpty())
				return false;
		}		
		return true;
		
	}
	
	
	
	@Override
	public void verifyServiceStart() {
		
		for(int i = 0; i < serverListNumber.length; i++) {
			if(!areLinesEmpty()) {
				if(!serverListNumber[i].isEmpty()) {
						if(!getServerList()[i].isAttending() && serverListNumber[i].getFirst().getArrival() <= getCurrentTime()) {
							getServerList()[i].setCustomer(serverListNumber[i].removeFirst());
							verifyIfCustomerIsAttendedBefore(getServerList()[i]);
							getServerList()[i].getCustomer().setWaitTime(getCurrentTime());
							getServerList()[i].getCustomer().setFinishedTime(getCurrentTime());
							if(getServerList()[i].getCustomer().getTimeOfEnd() > getNextEvent()) {
								setNextEvent(getServerList()[i].getCustomer().getTimeOfEnd());
							}
						}
				}
			}
		}
		
		
	}
	
	private int getMaxNumberOnList() {
		int ntr = 0;
		for(int i = 0; i < getServerList().length; i++) {
			if(ntr < serverListNumber[i].size())
				ntr = serverListNumber[i].size();
		}
		return ntr;
	}
	
	//TODO verify logic
	private int getMinimunLine(){
		if(!lLCustomer.isEmpty()) {
			int[] value = new int[getServerList().length];
			int ntr = serverListNumber[0].size();
			for(int i = 0; i< value.length; i++) {
				value[i] = serverListNumber[i].size();
				if(ntr > serverListNumber[i].size())
					ntr = serverListNumber[i].size();
			}
			return ntr;
		}
		
		else
			return -1;		
	}
	
	
	
	

	
	
	
	
}
