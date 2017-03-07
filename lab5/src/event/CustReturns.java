package event;

import java.util.ArrayList;

import hairdresser.SalongView;
import simulator.*;
import state.*;


public class CustReturns extends Event {

	
	Statistics stat = new Statistics();	
	private EventStore es;
	private SalongState ss;
	private State s;
	private SalongView sv;
	private FIFO f;
	
	private Customer C;
	private double time;
	
	private String namn = "Returns";
	
	static ArrayList<Integer> dissatisfied = new ArrayList<Integer>();
	
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
	 * 
	 */
	public void execute() {
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
	
	public void addReturnCust(Customer C){
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

