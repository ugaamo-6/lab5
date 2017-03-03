package event;

import simulator.Event;
import simulator.State;
import simulator.Statistics;

public class StopSim extends Event {
	
	State s;
	Statistics stat = new Statistics();
	
	private int C;
	private String namn = "Stop Simulation";
	public String getName(){
		return namn;
	}
	public int getCustomerID(){
		return C;
	}
	
	public StopSim(State s){
		System.out.println("---- Some Statistics ----");
		System.out.println("Total Customers: "+stat.getCust());
		System.out.println("Average haircut time: "+(stat.getTime()/stat.getCust()));
		System.out.println("Total leaves: "+stat.getLeave());
		System.out.println("Average queue time: " + (stat.getQtime()/stat.getCust()));
		System.out.println("Max customers at the same time in queue: "+ stat.getMax());
		System.out.println("Amount of disssatisfied customers: " + stat.getDiss());
		System.out.println("Last customer left at: "+stat.getLast());
		System.out.println("Chair idle time: "+stat.getIdle());
		System.out.println("-------------------------");
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
