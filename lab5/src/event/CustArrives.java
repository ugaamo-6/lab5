package event;

import simulator.Event;
import state.Customer;
import state.FIFO;

public class CustArrives extends Event {
	FIFO f = new FIFO();
	
	@Override
	public void execute() {
		Customer kund = new Customer();
		f.add(kund);
	}

//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
