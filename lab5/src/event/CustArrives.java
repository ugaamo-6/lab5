package event;



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
	
	int C;
	private String namn = "Customer Arrives";
	public String getName(){
		return namn;
	}
	public int getCustomerID(){
		return C;
	}
	
	public CustArrives(double arrivalTime, EventStore es, SalongState ss, State s, SalongView sv, FIFO f){
		this.time = arrivalTime;
		this.es=es;
		this.ss=ss;
		this.s=s;
		this.sv=sv;
		this.f=f;
	}
	
	public void execute() {
		if (s.opened()) {
			Customer C = new Customer(es, ss, s, sv, f);
			f.add(C);
			double nextCustTime = es.getTime() + ss.nextCustTime();
			CustArrives nextCust = new CustArrives(nextCustTime, es, ss, s, sv, f);
			es.addEvent(nextCust);
		}
	}
	
	public double getTime() {
		return time;
	}
	
	public double getArrivalTime(){
		return time;
	}
	
	
		
	



}
