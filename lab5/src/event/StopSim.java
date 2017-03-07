package event;

import simulator.Event;
import simulator.State;
import simulator.Statistics;
import simulator.EventStore;

public class StopSim extends Event {
//	
//	private EventStore es;
//	private EventPrint ep;
	
<<<<<<< HEAD
	
	EventPrint ep;
	State s;
=======
	private State state;
	
>>>>>>> branch 'master' of https://github.com/ugaamo-6/lab5.git
	Statistics stat = new Statistics();
	EventStore es = new EventStore(s);
	
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
<<<<<<< HEAD
	
	public StopSim(State s){
		
		this.s=s;
	}
	
	public void execute() {
		if(stat.getGoing()){
			stat.setTime1(es.getTime());
			stat.idleCalc();
		}
		System.out.println("999,0 STOP----");
		System.out.println("---- Some Statistics ----");
		System.out.println("Number of customers cut: ......: "+(int)stat.getCust());
		System.out.println("Average cutting time...........: "+(stat.getTime()/stat.getCust()));
		System.out.println("Average queueing time: ........: "+(stat.getQtime()/stat.gQcust()));
		System.out.println("Largest queue (max NumWaiting) : "+ stat.getMax());
		System.out.println("Customers not cut (NumLost) ...: "+stat.getLeave());
		System.out.println("Dissatisfied customers: .......: " + stat.getDiss());
		System.out.println("Time chairs were idle: ........: "+stat.getIdle());
		System.out.println("-------------------------");
		s.stop();
		
	}
	@Override
=======
>>>>>>> branch 'master' of https://github.com/ugaamo-6/lab5.git
	public double getTime() {
		return time;
	}
}
