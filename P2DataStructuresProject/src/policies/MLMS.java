package policies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import resources.Customer;
import resources.Server;

public class MLMS extends Policy {
	
	private LinkedList<Customer> lLCustomer;
	private LinkedList<Customer>[] serverListNumber;
	
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
		
		
		//Testing
		setAverageAttendedCustomer(0);
		//Testing
		
		runSim();
		
	}
	
	@Override
	protected void runSim() {
		while(!areLinesEmpty() || getCurrentTime() <= getNextEvent()) {
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
		
//		int minSize = getMinimunLine();
		
		
		while(!lLCustomer.isEmpty() && lLCustomer.getFirst().getArrival() <= getCurrentTime()) {
			int minSize = getMinimunLine();
			for(int i = 0; i < serverListNumber.length; i++) {
				if(serverListNumber[i].size() == minSize) {
					serverListNumber[i].add(lLCustomer.removeFirst());
					break;
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
