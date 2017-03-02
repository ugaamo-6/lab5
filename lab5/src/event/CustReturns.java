package event;

import hairdresser.SalongView;
import simulator.*;
import state.*;


public class CustReturns extends Event {
	
	EventStore es;
	SalongState ss;
	State s;
	SalongView sv;
	FIFO f;
	
	private Customer C;
	double time;
	
	public CustReturns(double time, Customer C, EventStore es, SalongState ss, State s, SalongView sv){
		this.time = time;
		this.C=C;
		this.es=es;
		this.ss=ss;
		this.s=s;
		this.sv=sv;
	}
	
	
	
	public void execute() {
		f = (FIFO) C.getFIFO();
		f.custFinished();
		f.returnCust(C);
	}
	
	public double getTime() {
		return time;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
