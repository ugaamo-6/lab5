package event;

import simulator.Event;
import simulator.State;

public class Closing extends Event {
	
	State s;
	
	public Closing(){
		time = 10.0;
	}
	public void execute() {
		s.closing();
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
