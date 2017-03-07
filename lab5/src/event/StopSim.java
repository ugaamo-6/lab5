package event;

import simulator.Event;
import simulator.State;
import simulator.Statistics;
import simulator.EventStore;

public class StopSim extends Event {
//	
//	private EventStore es;
//	private EventPrint ep;
	
	private State state;
	
	Statistics stat = new Statistics();
	
	private int C;
	private String toString;// = "Stop Simulation";FIXME
	
	
	public StopSim(State s){
		
		this.state=s;
	}
	
	public void execute() {
		state.stop();
	}
	
	//Måste implementeras
	public String toString(){//används
		toString = "999,0 STOP----" + "\n" +"---- Some Statistics ----"
				 + "\n" +"Number of customers cut: ......: "+(int)stat.getCust() +"\n" +
				 "Average cutting time...........: "+(stat.getTime()/stat.getCust()) + "\n" +	"Average queueing time: ........: "+(stat.getQtime()/stat.getCust())
				 + "\n" + "Largest queue (max NumWaiting) : "+ stat.getMax()
				 + "\n" +	"Customers not cut (NumLost) ...: "+stat.getLeave()	 + "\n" +	"Dissatisfied customers: .......: " + stat.getDiss()
				 + "\n" +	"Time chairs were idle: ........: "+stat.getIdle() + "\n" + "-------------------------";
		return toString;
	}
	public int getCustomerID(){
		return C;
	}
	public double getTime() {
		return time;
	}
}
