package event;

import simulator.Event;
import simulator.State;
import simulator.Statistics;

public class StopSim extends Event {
	State s;
	Statistics stat = new Statistics();
	public StopSim(State s){
		System.out.println("---- Some Statistics ----");
		System.out.println("Total Customers: "+stat.getCust());
		System.out.println("Average haircut time: "+(stat.getTime()/stat.getCust()));
		System.out.println("Total leaves: "+stat.getLeave());
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
