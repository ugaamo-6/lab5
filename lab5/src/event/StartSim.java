package event;

import simulator.Event;
import simulator.EventStore;
import simulator.State;

public class StartSim extends Event {
	
	EventStore es;
	State s;
	
	public StartSim(){
		time = 0.0;
	}
	
	public void execute() {		
		s.start();
		Event arrive = new CustArrives();
		es.addEvent(arrive);
	}

//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
