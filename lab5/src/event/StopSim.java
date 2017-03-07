package event;

import simulator.Event;
import simulator.State;
import simulator.Statistics;
import simulator.EventStore;

public class StopSim extends Event {

	State s;
	private State state;
	

	Statistics stat = new Statistics();
	EventStore es ;
	
	private int C;
	
	
	public StopSim(State s){
		this.state=s;
	}

	public int getCustomerID(){
		return C;
	}
	
	public void execute() {
		if(stat.getGoing()){
			stat.setTime1(getTime());
			stat.idleCalc();
		}
		
		//Byt plats?
		System.out.println("999,0 STOP----" + "\n" +"---- Some Statistics ----"
				 + "\n" +"Number of customers cut: ......: "+(int)stat.getCust() +"\n" +
				 "Average cutting time...........: "+(stat.getTime()/stat.getCust()) + "\n" +	"Average queueing time: ........: "+(stat.getQtime()/stat.getCust())
				 + "\n" + "Largest queue (max NumWaiting) : "+ stat.getMax()
				 + "\n" +	"Customers not cut (NumLost) ...: "+stat.getLeave()	 + "\n" +	"Dissatisfied customers: .......: " + stat.getDiss()
				 + "\n" +	"Time chairs were idle: ........: "+stat.getIdle() + "\n" + "-------------------------");

		state.stop();
		
	}
	public double getTime() {
		return time;
	}
}
