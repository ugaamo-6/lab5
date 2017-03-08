package event;

import simulator.Event;
import simulator.State;
import simulator.Statistics;
import simulator.EventStore;
import state.SalongState;
import state.SalongView;
import state.FIFO;

/**
 * A subclass to event, stopping the simulation.
 * 
 * @author Johan Bråtendal
 * @author Gustav Mattsson
 * @author Jonas Jarnhäll Sjöman
 */
public class StopSim extends Event {

	private State state;
	
	//To be able to create a SalongView object.
	private EventStore es ;
	private SalongState ss = new SalongState(es/*, 0.0*/);
	private FIFO f = new FIFO(es, ss);
	private SalongView sv = new SalongView(f, ss, es);
	
	/**
	 * This is the constructor
	 * @param s, Sate.
	 */
	public StopSim(State s){
		this.state=s;
	}
	
	/**
	 * Make the simulation stop.
	 */
	public void execute() {
		sv.summaryInfoPrint();
		state.stop();
		
	}
	
	/**
	 * Returns the time when the event is executed, with other words the the customer arrives.
	 * @return Time when executed.
	 */
	public double getTime() {
		return time;
	}
}
