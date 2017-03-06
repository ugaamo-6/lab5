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
		System.out.println("Number of customers cut: ......: "+stat.getCust());
		System.out.println("Average cutting time...........: "+(stat.getTime()/stat.getCust()));
		System.out.println("Average queueing time: ........: "+(stat.getQtime()/stat.getCust()));
		System.out.println("Largest queue (max NumWaiting) : "+ stat.getMax());
		System.out.println("Customers not cut (NumLost) ...: "+stat.getLeave());
		System.out.println("Dissatisfied customers: .......: " + stat.getDiss());
		System.out.println("Time chairs were idle: ........: "+stat.getIdle());
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
