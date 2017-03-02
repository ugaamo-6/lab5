package event;

import hairdresser.SalongView;
import simulator.*;
import state.*;

public class CustLeaves extends Event{
	
	EventStore es;
	SalongState ss;
	State s;
	SalongView sv;
	FIFO f;
	Customer C;
	double time;
	
	
	public CustLeaves(double time, Customer C, EventStore es, SalongState ss, State s, SalongView sv, FIFO f){
		this.time = time + ss.haircutTime();
		this.C = C;
		this.es=es;
		this.ss=ss;
		this.s=s;
		this.sv=sv;
		this.f =f;
	}
	
	public double getTime() {
		return this.time;
	}
	
	public void execute() {
		if(ss.randReturn()<=ss.percentageLeave()){
			double returnTime = es.getTime()+ss.returnTime();
			es.addEvent(new CustReturns(returnTime, C, es, ss, s, sv));	
		}
		System.out.println("Left");
		ss.chairGotFree();
	}

}
