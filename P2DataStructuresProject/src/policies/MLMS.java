package policies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import resources.Customer;
import resources.Server;

/**
 * 
 * @author Hector Montes Martinez
 * 841-14-4960
 * 
 * Policy that has multiple lines and multiple servers. Each line correspond to each server according to their index.
 *
 */

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
		
	}
	
	//Method that runs the simulation of the policy.
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
	

	
	//Method that sets each according customer to the line with less customers in it as they arrive.
	@Override
	public void doLineChange() {		
	
		while(!lLCustomer.isEmpty() && lLCustomer.getFirst().getArrival() <= getCurrentTime() ) {
			
			int minSize = getMinimunLine();
			if(minSize != -1) {
				for(int i = 0; i < serverListNumber.length; i++) {
					if(serverListNumber[i].size() == minSize) {
						serverListNumber[i].add(lLCustomer.removeFirst());
						
						break;
					}
				}
			}
		}
		
	}
	
	//Method that verifies if all lines are empty.
	private boolean areLinesEmpty() {
		for(int i = 0; i < serverListNumber.length; i++) {
			if(!serverListNumber[i].isEmpty())
				return false;
		}		
		return true;
		
	}
	
	
	//Method that verifies if the service of a customer starts or it gives  a customer to a server that was not attennding.
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
	
	
	//Method that gives us the size of the line that has less customers.
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
