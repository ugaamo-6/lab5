package event;

import java.util.Observable;
import java.util.Observer;

import hairdresser.*;
import simulator.*;
import state.*;

public class StartSim extends Event {
	
	private EventPrint ep;
	private EventStore eventStore;
	private SalongState ss;
	private State s;
	private SalongView sv;
	private FIFO f;
	
	private int C;
	private String toString = "Start Simulation";

	
	public StartSim(EventStore es, SalongState ss, State s, SalongView sv, FIFO f){
		this.eventStore=es;
		this.ss=ss;
		this.s=s;
		this.sv=sv;
		this.f=f;
		time = 0.0;
	}
	
	/**
	 * Executes the event.
	 */
	public void execute() {		
		eventStore.setTime(0.0);
		s.start();

		
//		Ha inte printsatser h�r, anv�nd v�r view-klass.
		s.start();
		Event arrive = new CustArrives(ss.nextCustTime(), eventStore, ss, s, sv, f);
		eventStore.addEvent(arrive);
		
		System.out.println(eventStore.getTime()+"   START----");
	}

	/**
	 * Returns the time when the event is executed.
	 * @return Time when executed.
	 */
	public double getTime() {
		return time;
	}
	

	/**Returns a string that describes the event.
	 * @return String describing event. 
	 */
	public String toString() {
		return toString;
	}

	



}
