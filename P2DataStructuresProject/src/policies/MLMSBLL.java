package policies;

import java.util.ArrayList;


import resources.Customer;

/**
 * 
 * @author Hector Montes Martinez
 * 841-14-4960
 * 
 * Policy that can have multiple servers and multiple lines. Customers are swapped according to the line lengths so that the waiting lines
 * are balanced.
 *
 */

public class MLMSBLL extends MLMS {

	private int[] sizesOfLines;
	
	public MLMSBLL(ArrayList<Customer> cList, int server) {		
		super(cList, server);
		
		setPolicyName("MLMSBLL");
	
	}
	
	//Method that assigns the customers according to the line lengths.
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
	
	//Method that swaps the customers from the indicated lines.
	private void doSwap(int n, int k) {
		
		int i = sizesOfLines[n] - sizesOfLines[k];
		
		if(i > 0) {
			serverListNumber[k].add(serverListNumber[n].removeLast());

		}
		
		else if(i < 0) {
			serverListNumber[n].add(serverListNumber[k].removeLast());
		}
		
	}	
	
}
