package event;

import java.util.Observable;


import hairdresser.SalongView;
import simulator.*;
import state.*;

public class CustArrives extends Event {
	
	EventStore es;
	SalongState ss;
	State s;
	SalongView sv;
	FIFO f;
	double time;
	
	
	public CustArrives(double arrivalTime, EventStore es, SalongState ss, State s, SalongView sv, FIFO f){
		this.time = arrivalTime;
		this.es=es;
		this.ss=ss;
		this.s=s;
		this.sv=sv;
		this.f=f;
	}
	
	@Override
	public void execute() {
		Customer kund = new Customer(es, ss, s, sv, f);
		f.add(kund);
		double nextCustTime = es.getTime() + ss.nextCustTime();
		CustArrives nextCust = new CustArrives(nextCustTime, es, ss, s, sv, f);
		es.addEvent(nextCust);
	}
	
	public double getTime() {
		return time;
	}
	
	public double getArrivalTime(){
		return time;
	}
	
		
	



}
