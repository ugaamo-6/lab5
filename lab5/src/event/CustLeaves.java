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
		stat.qTime(qTimeCalc(C));
		stat.lastCustTime(es.getTime());
		FIFO f = C.getFIFO();
		
		f.messageString("Haircut is done, customer leaves.");
		ss.chairGotFree();
		
		f.checkIfSatisfied(C);
		
		
		f.getFirst();

	}
	private double qTimeCalc(Customer C){
		return time-C.queueTime;
	}

}
