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
	
	/**
	 * Executes the event.
	 */
	public void execute() {
		if (state.opened()) {
			Customer C = new Customer(fifo);
			ep = new EventPrint(namn, C.getID(), eventStore,salongState,fifo);
			addToFIFO(C);
			double nextCustTime = eventStore.getTime() + salongState.nextCustTime();
			CustArrives nextCust = new CustArrives(nextCustTime, eventStore, salongState, state, sv, fifo);
			eventStore.addEvent(nextCust);
		}
	}
	
	/**
	 * Add the customer that arrived to the FIFO queue.
	 * @param C is the arriving customer.
	 */
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
	
	/**
	 * Get the first element(Customer) in the FIFO-queue.
	 */
	public void getFirst(){
		
		if(!fifo.isEmpty()){
			salongState.chairGotBusy();
			eventStore.addEvent(new CustLeaves(eventStore.getTime(), fifo.getFirst(), eventStore, salongState, state, sv, fifo));
			fifo.removeFirst();
		} 
	}
	
	
	//Get metoder nedan.
	
	/**
	 * Returns the time when the event is executed, with other words the the customer arrives.
	 * @return Time when executed.
	 */
	public double getTime() {
		return time;
	}

	/**Returns a string that describes the event.
	 * @return String describing event. 
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return namn;
	}
}
