package event;

import hairdresser.SalongView;
import simulator.*;
import state.*;


public class CustReturns extends Event {
	
	private EventPrint ep; //Fortfarande vart?
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
	
	
	
	public void execute() {
		f = (FIFO) C.getFIFO();
//		f.custFinished();
//		f.checkIfSatisfied(C);
		addReturnCust(C);
		ep = new EventPrint(namn, C.getID(), es,ss,f);
	}
	
	public void addReturnCust(Customer C){
		f = (FIFO) C.getFIFO();
	
		if (ss.getFreeChairs() == ss.totalChairs()) {
			f.addReturnToQueue(C);
			getFirst();
			//f.messageString("Returning customer: Customer get haircut.");
		} else if(!f.isFull()){
			f.addReturnToQueue(C);
			//f.messageString("Returning customer: Customer stands in queue.");		
		}else if(f.isFull()){

			//Kontrollerar ifall hela k�n �r �terkommande. 
			if (f.returningCustInQueue() == ss.maxWaitInQueue()) {
				double returnTime = es.getTime()+ss.returnTime();
				es.addEvent(new CustReturns(returnTime, C, es, ss, s, sv, f));	
//				f.messageString("Queue full with dissatisfied customers, gets a walk and come back later.");

			} else {
				f.removeLast();
				f.addReturnToQueue(C);
//				f.messageString("Returning customer: Stands in queue. Last customer in queue left.");		
//				stat.addDiss();
			}
		}
	}
	
	public void getFirst(){
		FIFO f = C.getFIFO(); // Kan detta l�sas p� annat s�tt?
		if(!f.isEmpty()){
			
			ss.chairGotBusy();
//			Customer getFirst = (Customer) f.getFirst();
			es.addEvent(new CustLeaves(es.getTime(), f.getFirst(), es, ss, s, sv, f));
			f.removeFirst();
			//f.messageString("Customer gets a haircut.");
		} 
	}
	
	public double getTime() {
		return time;
	}

	@Override
	public String toString() {//ANVÄNDS EJ ÄNNU
		// TODO Auto-generated method stub
		return namn;
	}
	
	public int getCustomerID(){
		return C.getID();
	}

}
