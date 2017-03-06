package event;

import simulator.Event;
import simulator.State;
import simulator.Statistics;
import simulator.EventStore;

public class StopSim extends Event {
	
	EventStore es;
	EventPrint ep;
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
		
		this.s=s;
	}
	
	public void execute() {
		System.out.println("999,0 STOP----");
		System.out.println("---- Some Statistics ----");
		System.out.println("Number of customers cut: ......: "+(int)stat.getCust());
		System.out.println("Average cutting time...........: "+(stat.getTime()/stat.getCust()));
		System.out.println("Average queueing time: ........: "+(stat.getQtime()/stat.getCust()));
		System.out.println("Largest queue (max NumWaiting) : "+ stat.getMax());
		System.out.println("Customers not cut (NumLost) ...: "+stat.getLeave());
		System.out.println("Dissatisfied customers: .......: " + stat.getDiss());
		System.out.println("Time chairs were idle: ........: "+stat.getIdle());
		System.out.println("-------------------------");
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
