package policies;

import java.util.ArrayList;

import resources.Customer;

public class MLMSBWT extends MLMS{
	
	private int[] totalTimePerLine;
	
	public MLMSBWT(ArrayList<Customer> cList, int server) {
		super(cList, server);
		setPolicyName("MLMSBWT");
		
	}
	
	@Override
	public void doLineChange() {
	
		totalTimePerLine = new int[getServers()];
		
		for(int i = 0; i< totalTimePerLine.length; i++) {
			int value = 0;
			if(getServerList()[i].isAttending())
			value += (getServerList()[i].getCustomer().getTimeOfEnd() - getCurrentTime());
			for(int k = 0; k < serverListNumber[i].size(); k++) {
				value += serverListNumber[i].get(k).getRequest();
			}
			totalTimePerLine[i] = value;
		}	
		
		int vtr = totalTimePerLine[0];
		for(int i = 1; i < totalTimePerLine.length; i++) {
			if(vtr > totalTimePerLine[i])
				vtr = totalTimePerLine[i];
		}
		
		while(!lLCustomer.isEmpty() && lLCustomer.getFirst().getArrival() <= getCurrentTime()) {
			for(int i = 0; i < getServerList().length; i++) {
				if(vtr == totalTimePerLine[i]) {
					serverListNumber[i].add(lLCustomer.removeFirst());
					break;
				}
			}
		}
		
	}

}
