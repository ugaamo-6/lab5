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
			addToFIFO(C);
			double nextCustTime = es.getTime() + ss.nextCustTime();
			CustArrives nextCust = new CustArrives(nextCustTime, es, ss, s, sv, f);
			es.addEvent(nextCust);
		}
	}
	
	private void addToFIFO(Customer C) {
		if(f.isFull()){
			f.messageString("The queue is full, customer leaves");
//			stat.addLeave();
//			stat.setTime1(es.getTime());
//			stat.idleCalc();
		}
		else if(ss.freeChairs() != 0 && f.isEmpty()){
			f.addNewCustomerToFIFO((Customer) C);
			getFirst();
		} else if(ss.freeChairs() != 0 && f.isEmpty() && ss.freeChairs() != 0){
			f.messageString("Customer gets a haircut!");
			ss.chairGotBusy();	
			es.addEvent(new CustLeaves(es.getTime() , C, es, ss, s, sv, f));
		} else {
			f.addNewCustomerToFIFO((Customer) C);
			C.queueTime = es.getTime();
			f.messageString("Customer wait.");
		}
		
	}
		
	public void getFirst(){
		
		if(!f.isEmpty()){
			ss.chairGotBusy();
			Customer getFirst = f.getFirst2();
			es.addEvent(new CustLeaves(es.getTime(), getFirst, es, ss, s, sv, f));
			f.removeFirst();;
			f.messageString("Customer gets a haircut.");
		} 
		
		
	}
	
	public double getTime() {
		return time;
	}
	
	public double getArrivalTime(){
		return time;
	}
	
	
		
	



}
