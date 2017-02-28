package event;

import simulator.Event;
import simulator.EventStore;
import state.Customer;

public class CustLeaves extends Event{
	
	private double time;
	Customer C;
	
	public CustLeaves(double t, Customer C){
		this.time = t;
		this.C = C;
	}
	
	
	@Override
	public void execute() {
		
		
	}

//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
