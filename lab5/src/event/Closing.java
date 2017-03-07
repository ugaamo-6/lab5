package event;


import simulator.Event;
import simulator.EventStore;
import simulator.State;
import state.SalongState;
import state.SalongView;
import simulator.Statistics;

public class Closing extends Event {
	
	
	static Statistics stat = new Statistics();
	private State state;
	private EventStore es;
	private SalongView sv;
	
	private int C;	
	
	public Closing(double time, EventStore es, State s, SalongView sv) {
		this.time=time;
		this.es=es;
		this.state=s;
		this.sv=sv;
	}

	public void execute() {
		state.closing();
		sv.closingInfoPrint();
	}
	
	public double getTime() {
		return time;
	}

	public String toString(){
		String toString = es.getTime() +"   CLOSING-------"; 
		return toString;
	}
	
	public int getCustomerID(){
		return C;
	}

}
