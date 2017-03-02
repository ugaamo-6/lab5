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
	
	
	public CustLeaves(double time, Customer C, EventStore es, SalongState ss, State s, SalongView sv){
		this.time = time + ss.haircutTime();
		this.C = C;
		this.es=es;
		this.ss=ss;
		this.s=s;
		this.sv=sv;
	}
	
	public double getTime() {
		return this.time;
	}
	
	public void execute() {
		stat.custCountAdd();
		FIFO f = C.getFIFO();
		
		f.messageString("Haircut is done, customer leaves.");
		ss.chairGotFree();
		
		if(ss.randReturn()<=ss.percentageReturn()){
			f.messageString("Customer is not happy.");
			double returnTime = es.getTime()+ss.returnTime();
			es.addEvent(new CustReturns(returnTime, C, es, ss, s, sv));	
		}
		
		
		f.getFirst();

	}

}
