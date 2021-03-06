package event;

import simulator.*;
import state.*;

/**
 * A subclass to event, starting the simulation.
 * 
 * @author Johan Br�tendal
 * @author Gustav Mattsson
 * @author Jonas Jarnh�ll Sj�man
 */
public class StartSim extends Event {
	
	private EventStore eventStore;
	private SalongState ss;
	private State s;
	private SalongView sv;
	private FIFO f;
	private Statistics stat = new Statistics();
	
	private String toString = "Start Simulation";

	/**
	 * This is the constructor.
	 * @param es, Event Store.
	 * @param ss, Salong State.
	 * @param s, State.
	 * @param sv, Salong View.
	 * @param f, FIFO.
	 */
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
		sv.variableInfoPrint();		
		eventStore.setTime(0.0);
		stat.goingTrue();
		s.start();

		Event arrive = new CustArrives(ss.nextCustTime(), eventStore, ss, s, sv, f);
		eventStore.addEvent(arrive);
		
		sv.startInfoPrint();
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
