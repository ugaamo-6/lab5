package event;



import hairdresser.SalongView;
import simulator.*;
import state.*;

public class CustArrives extends Event {
	
	private EventPrint ep; //?? Typen vartifrån?
	private EventStore eventStore;
	private SalongState salongState;
	private State state;
	private SalongView sv;
	private FIFO fifo;
	private double time;
	private Statistics stat = new Statistics();
	private int C;
	private String namn = "Arrives";
	
	
	public CustArrives(double arrivalTime, EventStore es, SalongState ss, State s, SalongView sv, FIFO f){
		this.time = arrivalTime;
		this.eventStore=es;
		this.salongState=ss;
		this.state=s;
		this.sv=sv;
		this.fifo=f;
		
	}
	
	public void execute() {
		if (state.opened()) {
			Customer C = new Customer(eventStore, salongState, state, sv, fifo);
			addToFIFO(C);
			double nextCustTime = eventStore.getTime() + salongState.nextCustTime();
			CustArrives nextCust = new CustArrives(nextCustTime, eventStore, salongState, state, sv, fifo);
			eventStore.addEvent(nextCust);
			ep = new EventPrint(namn, C.getID(), eventStore,salongState,fifo);
		}
	}
	
	private void addToFIFO(Customer C) {
		if(fifo.isFull()){	
			fifo.timeDiffCalc(fifo.queueSize());
			stat.addLeave();
			//f.messageString("The queue is full, customer leaves");
			
			fifo.setLET(eventStore.getTime());
		}
		else if(salongState.getFreeChairs() != 0 && fifo.isEmpty()){
			
			fifo.addNewCustomerToFIFO((Customer) C);
			getFirst();
			fifo.setLET(eventStore.getTime());
		} else if(salongState.getFreeChairs() != 0 && fifo.isEmpty() && salongState.getFreeChairs() != 0){
			salongState.chairGotBusy();	
			eventStore.addEvent(new CustLeaves(eventStore.getTime() , C, eventStore, salongState, state, sv, fifo));
			fifo.setLET(eventStore.getTime());
		} else {
			fifo.addNewCustomerToFIFO((Customer) C);
			C.queueTime = eventStore.getTime();
			fifo.setLET(eventStore.getTime());
		}
		
	}
		
	public void getFirst(){
		
		if(!fifo.isEmpty()){
			salongState.chairGotBusy();
			eventStore.addEvent(new CustLeaves(eventStore.getTime(), fifo.getFirst(), eventStore, salongState, state, sv, fifo));
			fifo.removeFirst();
		} 
	}
	
	
	//Get metoder nedan.
	
	public double getTime() {
		return time;
	}
	
	public double getArrivalTime(){
		return time;
	}

	public int getCustomerID(){
		return C;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return namn;
	}
}
