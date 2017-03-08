package event;

import java.util.ArrayList;

import simulator.*;
import state.*;

/**
 * A subclass to event, creating a leaving customer.
 * 
 * @author Johan Bråtendal
 * @author Gustav Mattsson
 * @author Jonas Jarnhäll Sjöman
 */
public class CustLeaves extends Event{
	
	Statistics stat = new Statistics();
	private EventStore eventStore;
	private SalongState ss;
	private State s;
	private SalongView sv;
	private FIFO f;
	private Customer C;
	private double time;
	
	
	static ArrayList<Integer> oldCustomers = new ArrayList<Integer>();
	
	private String namn = "Leaves";
	
	public CustLeaves(double time, Customer C, EventStore es, SalongState ss, State s, SalongView sv, FIFO f){
		this.time = time + ss.haircutTime();
		this.C = C;
		this.eventStore=es;
		this.ss=ss;
		this.s=s;
		this.sv=sv;
		this.f=f;
	}
	

	/**
	 * Makes a customer leave.
	 */
	public void execute() {
		ss.idleCounter();
		f.timeDiffCalc(f.queueSize());
		f.setLET(eventStore.getTime());
		
		f.toString(namn, C.getID());
		
		if(!oldCustomers.contains(C.getID())){
			stat.custCountAdd();
			oldCustomers.add(C.getID());
		}
		
		FIFO f = C.getFIFO();
		
		checkIfSatisfied(C);
		f.custFinished();
		getFirst();	
	}
	
	/**Kollar om kunden Ã¤r nÃ¶jd
	 * @return true om nÃ¶jd och false om missnÃ¶jd*/
	public void checkIfSatisfied(Customer C){
		FIFO f = C.getFIFO(); // Kan detta lï¿½sas pï¿½ annat sï¿½tt?
		
		if(ss.randReturn()<=ss.percentageReturn()){
			double returnTime = eventStore.getTime()+ss.randReturnTime();
			eventStore.addEvent(new CustReturns(returnTime, C, eventStore, ss, s, sv, f));	
			C.happyFalse();;
			
		} else {
			C.happyTrue(); 
		}	
}
	/**
	 * Seats the first customer in FIFO-queue in hairdress-chair.
	 */
	public void getFirst(){
		FIFO f = C.getFIFO(); // Kan detta lï¿½sas pï¿½ annat sï¿½tt?
		if(!f.isEmpty()){
			ss.chairGotBusy();
			eventStore.addEvent(new CustLeaves(eventStore.getTime(), f.getFirst(), eventStore, ss, s, sv, f));
			f.removeFirst();
		} 
	}
	
	/**
	 * Returns the time when the event is executed, with other words the the customer arrives.
	 * @return Time when executed.
	 */
	public double getTime() {
		return this.time;
	}
}