package event;

import simulator.Event;
import simulator.State;

public class StopSim extends Event {
	State s;
	@Override
	public void execute() {
		s.stop();
		
	}

	
//	public String toString() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
