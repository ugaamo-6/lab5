package event;

import hairdresser.SalongView;
import simulator.*;
import state.*;

public class CustLeaves extends Event{
	
	Statistics stat = new Statistics();
	EventStore es;
	SalongState ss;
	State s;
	SalongView sv;
	FIFO f;
	Customer C;
	double time;
	
	private String namn = "Customer Leaves";
	public String getName(){
		return namn;
	}
	public int getCustomerID(){
		return C.getID();
	}
	
	public CustLeaves(double time, Customer C, EventStore es, SalongState ss, State s, SalongView sv, FIFO f){
		this.time = time + ss.haircutTime();
		this.C = C;
		this.es=es;
		this.ss=ss;
		this.s=s;
		this.sv=sv;
		this.f=f;
	}
	
	public double getTime() {
		return this.time;
	}
	
	public void execute() {
		
		stat.custCountAdd();
		stat.qTime(qTimeCalc(C));
		stat.lastCustTime(es.getTime());
		
		FIFO f = C.getFIFO();
		
		checkIfSatisfied(C);
		f.custFinished();
		getFirst();	
	}
	
	public void getFirst(){
		FIFO f = C.getFIFO(); // Kan detta lösas på annat sätt?
		if(!f.isEmpty()){
			
			ss.chairGotBusy();
			es.addEvent(new CustLeaves(es.getTime(), f.getFirst(), es, ss, s, sv, f));
			f.removeFirst();
			f.messageString("Customer gets a haircut.");
		} 
	}
		
	public void checkIfSatisfied(Customer C){
		FIFO f = C.getFIFO(); // Kan detta lösas på annat sätt?
		
		if(ss.randReturn()<=ss.percentageReturn()){
			f.messageString("Customer is not happy.");
			double returnTime = es.getTime()+ss.returnTime();
			es.addEvent(new CustReturns(returnTime, C, es, ss, s, sv,f));	
			C.happy = false;
		} else { C.happy = true; }
		
}
	
	private double qTimeCalc(Customer C){
		return time-C.queueTime;
	}

}
