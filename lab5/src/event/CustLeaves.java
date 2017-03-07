package event;

import java.util.ArrayList;

import simulator.*;
import state.*;

public class CustLeaves extends Event{
	
	Statistics stat = new Statistics();
	private EventStore eventStore;
	private SalongState ss;
	private State s;
	private SalongView sv;
	private FIFO f;
	private Customer C;
	private double time;
	
	
	static ArrayList<Integer> oldCustomers = new ArrayList<Integer>();
	
	private String namn = "Leaves";
	
	public CustLeaves(double time, Customer C, EventStore es, SalongState ss, State s, SalongView sv, FIFO f){
		this.time = time + ss.haircutTime();
		this.C = C;
		this.eventStore=es;
		this.ss=ss;
		this.s=s;
		this.sv=sv;
		this.f=f;
	}
	

	
	public void execute() {
		ss.idleCounter();
		f.timeDiffCalc(f.queueSize());
		f.setLET(eventStore.getTime());
		
		f.toString(namn, C.getID());
		
		if(!oldCustomers.contains(C.getID())){
			stat.custCountAdd();
			oldCustomers.add(C.getID());
		}
		
		FIFO f = C.getFIFO();
		
		checkIfSatisfied(C);
		f.custFinished();
		getFirst();	
	}
	
	/**Kollar om kunden är nöjd
	 * @return true om nöjd och false om missnöjd*/
	public void checkIfSatisfied(Customer C){
		FIFO f = C.getFIFO(); // Kan detta l�sas p� annat s�tt?
		
		if(ss.randReturn()<=ss.percentageReturn()){
			double returnTime = eventStore.getTime()+ss.randReturnTime();
			eventStore.addEvent(new CustReturns(returnTime, C, eventStore, ss, s, sv, f));	
			C.happy = false;
			
		} else {
			C.happy = true; 
		}	
}
	
	public String toString(){
		return namn;
	}
	public int getCustomerID(){
		return C.getID();
	}
	
	public void getFirst(){
		FIFO f = C.getFIFO(); // Kan detta l�sas p� annat s�tt?
		if(!f.isEmpty()){
			ss.chairGotBusy();
			eventStore.addEvent(new CustLeaves(eventStore.getTime(), f.getFirst(), eventStore, ss, s, sv, f));
			f.removeFirst();
		} 
	}
	
	public double getTime() {
		return this.time;
	}
}