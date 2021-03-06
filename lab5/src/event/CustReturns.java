package event;

import java.util.ArrayList;

import simulator.*;
import state.*;

/**
 * A subclass to event, creating a returning customer.
 * 
 * @author Johan Br�tendal
 * @author Gustav Mattsson
 * @author Jonas Jarnh�ll Sj�man
 */
public class CustReturns extends Event {	

	private Statistics stat = new Statistics();	
	private EventStore es;
	private SalongState ss;
	private State s;
	private SalongView sv;
	private FIFO f;
	private Customer C;
	private double time;
	private String namn = "Returns";
	
	private static ArrayList<Integer> dissatisfied = new ArrayList<Integer>();
	
	/**
	 * this is the construcor
	 * @param time, the time the event will occur.
	 * @param C, Customer that returns.
	 * @param es, Event Store.
	 * @param ss, Salong State.
	 * @param s, State.
	 * @param sv, Salong View.
	 * @param f, FIFO queue.
	 */
	public CustReturns(double time, Customer C, EventStore es, SalongState ss, State s, SalongView sv, FIFO f){
		this.time = time;
		this.C=C;
		this.es=es;
		this.ss=ss;
		this.s=s;
		this.sv=sv;
		this.f=f;
	}
	
	
	/**
	 * Make a customer return to the salon.
	 */
	public void execute() {
		ss.idleCounter();
		f.timeDiffCalc(f.queueSize());
		f.setLET(es.getTime());
		
		f = (FIFO) C.getFIFO();
		f.toString(namn, C.getID());
		addReturnCust(C);
		
		if(!dissatisfied.contains(C.getID())){
			stat.addDiss(); //if customer not happy, add 1 to counter in stat.
			dissatisfied.add(C.getID());
		}
	}
	
	private void addReturnCust(Customer C){
		f = (FIFO) C.getFIFO();
	
		if (ss.getFreeChairs() == ss.totalChairs()) {
			f.addReturnToQueue(C);
			getFirst();
		} else if(f.isFull()){

			//Kontrollerar ifall hela k�n �r �terkommande. 
			if (f.returningCustInQueue() == ss.maxWaitInQueue()) {
				double returnTime = es.getTime()+ss.randReturnTime();
				es.addEvent(new CustReturns(returnTime, C, es, ss, s, sv, f));
				} else {
				f.removeLast();
				f.addReturnToQueue(C);
			} 
		} else if (ss.getFreeChairs() != ss.totalChairs() && ss.getFreeChairs() != 0 ) {
			f.addReturnToQueue(C);
			getFirst();
			
		} else if(!f.isFull()){
			f.addReturnToQueue(C);
		}
			
		
		}
	
	/**
	 * Get the first element(Customer) in the FIFO-queue.
	 */
	public void getFirst(){
		FIFO f = C.getFIFO(); // Kan detta l�sas p� annat s�tt?
		if(!f.isEmpty()){
			ss.chairGotBusy();
			es.addEvent(new CustLeaves(es.getTime(), f.getFirst(), es, ss, s, sv, f));
			f.removeFirst();
		} 
	}
	
	/**
	 * Returns the time when the event is executed, with other words the the customer returns.
	 * @return Time when executed.
	 */
	public double getTime() {
		return time;
	}
	
}

