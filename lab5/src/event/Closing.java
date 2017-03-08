package event;


import simulator.*;
import state.SalongView;
/**
 * A subclass to event, closing the salon.
 * 
 * @author Johan Br�tendal
 * @author Gustav Mattsson
 * @author Jonas Jarnh�ll Sj�man
 */
public class Closing extends Event {
	
	private State state;
	private SalongView sv;
		/**
		 * This is the construcor.
		 * @param time, the time the event will occur.
		 * @param s, State
		 * @param sv, Salong View.
		 */
	public Closing(double time, State s, SalongView sv) {
		this.time=time;
		this.state=s;
		this.sv=sv;
	}
	/**
	 *Closing the store. 
	 */
	public void execute() {
		state.closing();
		sv.closingInfoPrint();
	}
	
	/**
	 * Returns the time when the event will happen. 
	 * @return Time when event happens.
	 */
	public double getTime() {
		return time;
	}
	

}
