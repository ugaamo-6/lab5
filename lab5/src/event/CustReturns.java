package event;

import hairdresser.SalongView;
import simulator.*;
import state.*;


public class CustReturns extends Event {
	
<<<<<<< HEAD
	private Statistics stat = new Statistics();
	private EventPrint ep; //Fortfarande vart?
=======
>>>>>>> branch 'master' of https://github.com/ugaamo-6/lab5.git
	private EventStore es;
	private SalongState ss;
	private State s;
	private SalongView sv;
	private FIFO f;
	
	private Customer C;
	private double time;
	
	private String namn = "Returns";
	
	
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
		f = (FIFO) C.getFIFO();
		f.toString(namn, C.getID());
		addReturnCust(C);
	}
	
	public void addReturnCust(Customer C){
		f = (FIFO) C.getFIFO();
	
		if (ss.getFreeChairs() == ss.totalChairs()) {
			f.addReturnToQueue(C);
			getFirst();
		} else if(!f.isFull()){
			f.timeDiffCalc(f.queueSize());
			f.addReturnToQueue(C);
			f.lastEventTime = es.getTime();
		}else if(f.isFull()){

			//Kontrollerar ifall hela k�n �r �terkommande. 
			if (f.returningCustInQueue() == ss.maxWaitInQueue()) {
				double returnTime = es.getTime()+ss.randReturnTime();
				f.timeDiffCalc(f.queueSize());
				es.addEvent(new CustReturns(returnTime, C, es, ss, s, sv, f));
				f.lastEventTime = es.getTime();
				} else {
				f.timeDiffCalc(f.queueSize());
				f.removeLast();
				f.addReturnToQueue(C);
				f.lastEventTime = es.getTime();
			} 
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

