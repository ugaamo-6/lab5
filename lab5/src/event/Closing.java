package event;

import hairdresser.SalongView;
import simulator.Event;
import simulator.EventStore;
import simulator.State;
import state.FIFO;
import state.SalongState;
import simulator.Statistics;

public class Closing extends Event {
	
	
	static Statistics stat = new Statistics();
	State s;
	EventStore es;
	SalongState ss;
	SalongView sv;
	FIFO f;
	
	
	public Closing(double time, EventStore es, SalongState ss, State s, SalongView sv, FIFO f) {
		this.time=time;
		this.es=es;
		this.ss=ss;
		this.s=s;
		this.sv=sv;
		this.f=f;
	}

	public void execute() {
		s.closing();
	}
	
	public double getTime() {
		return time;
	}

	public String toString() {
		return null;//FIXME
	}

}
