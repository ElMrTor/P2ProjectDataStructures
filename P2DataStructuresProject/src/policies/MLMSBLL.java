package policies;

import java.util.ArrayList;


import resources.Customer;

public class MLMSBLL extends MLMS {

	private int[] sizesOfLines;
	
	public MLMSBLL(ArrayList<Customer> cList, int server) {		
		super(cList, server);
		
		setPolicyName("MLMSBLL");
	
	}
	
	
	@Override
	public void doLineChange() {
		super.doLineChange();
		
		sizesOfLines = new int[getServers()];
		for(int i = 0; i < getServers(); i++) {
			sizesOfLines[i] = serverListNumber[i].size();
		}		
		
		int i = 0;
		for(int k = 1; k < sizesOfLines.length ; k++) {
			i = Math.abs(sizesOfLines[k-1] - sizesOfLines[k]);
			if(i > 1) {
				doSwap(k-1, k);
				break;
			}
		}		
	}
	
	private void doSwap(int n, int k) {
		
		int i = sizesOfLines[n] - sizesOfLines[k];
		
		if(i > 0) {
			serverListNumber[k].add(serverListNumber[n].removeLast());

		}
		
		else if(i < 0) {
			serverListNumber[n].add(serverListNumber[k].removeLast());
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	private LinkedList<Customer> lLCustomer;
//	
//	public MLMSBLL(List<Customer> cList, int server) {
//		serverListNumber = new LinkedList[server];
//		for(int i = 0; i < serverListNumber.length; i++) {
//			serverListNumber[i] = new LinkedList<Customer>();
//		}
//		
//		setPolicyName("MLMSBLL");
//		setQueue(new PriorityQueue<Customer>(cList));
//		lLCustomer = new LinkedList<Customer>(getQueue());
//		setQueue(null);
//		setAttendedCustomerList(new ArrayList<Customer>());
//		setLineChange(true);
//		setCurrentTime(0);
//		setNextEvent(0);
//		setAverageAttendedCustomer(0);
//
//		setServers(server);
//		setServerList(new Server[getServers()]);
//		for(int i = 0; i < getServers(); i++) {
//			getServerList()[i] = new Server();
//		}
//		
//		runSim();
//
//		
//	}
//	
//	@Override
//	public void runSim() {
//		
//		while(!areLinesEmpty() || getCurrentTime() <= getNextEvent()) {
//			verifyCompletedTask();
//			if(canChangeLine()) {
//				doLineChange();
//			}
//			verifyServiceStart();
//		}
//		
//	}
//	
//	@Override
//	public void verifyCompletedTask() {
//		
//	}
//	
//	//TODO Implement method
//	public boolean areLinesEmpty() {
//		return false;
//	}
//
//	
	
}
