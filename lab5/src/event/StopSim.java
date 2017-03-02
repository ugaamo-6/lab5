package event;

import simulator.Event;
import simulator.State;
import simulator.Statistics;

public class StopSim extends Event {
	State s;
	Statistics stat;
	public StopSim(State s){
		this.s=s;
	}
	
	public void execute() {
		s.stop();
		
	}
	@Override
	public double getTime() {
		return time;
	}

	
//	public String toString() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
