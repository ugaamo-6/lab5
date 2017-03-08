package event;

import simulator.*;
import state.*;

/**
 * A subclass to event, creating arriving customer.
 * 
 * @author Johan Bråtendal
 * @author Gustav Mattsson
 * @author Jonas Jarnhäll Sjöman
 */
public class CustArrives extends Event {
	
	private EventStore eventStore;
	private SalongState salongState;
	private State state;
	private SalongView sv;
	private FIFO fifo;
	private double time;
	private Statistics stat = new Statistics();
	private String namn = "Arrives";
	
	/**
	 * this is the constructor.
	 * @param arrivalTime, the time the event will occur.
	 * @param es, Event Store.
	 * @param ss, Salong State.
	 * @param s, State.
	 * @param sv, Salong View.
	 * @param f, FIFO.
	 */
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
			salongState.idleCounter();
			fifo.timeDiffCalc(fifo.queueSize());
			fifo.setLET(eventStore.getTime());

			fifo.toString(namn, C.getID());
			
			addToFIFO(C);
			
			double nextCustTime = eventStore.getTime() + salongState.nextCustTime();
			CustArrives nextCust = new CustArrives(nextCustTime, eventStore, salongState, state, sv, fifo);
			eventStore.addEvent(nextCust);
			

		}
	}
	

	private void addToFIFO(Customer C) {
		if(fifo.isFull()){	
			stat.addLeave();
		}
		else if(salongState.getFreeChairs() != 0 && fifo.isEmpty()){
			
			fifo.addNewCustomerToFIFO((Customer) C);
			getFirst();
		} else if(salongState.getFreeChairs() != 0 && fifo.isEmpty() && salongState.getFreeChairs() != 0){
			salongState.chairGotBusy();	
			System.out.println("---- heluuuuuuuu");
			eventStore.addEvent(new CustLeaves(eventStore.getTime() , C, eventStore, salongState, state, sv, fifo));
		} else {
			fifo.addNewCustomerToFIFO((Customer) C);
		}
		
	}
	
	private void getFirst(){
		if(!fifo.isEmpty()){
			salongState.chairGotBusy();
			eventStore.addEvent(new CustLeaves(eventStore.getTime(), fifo.getFirst(), eventStore, salongState, state, sv, fifo));
			fifo.removeFirst();
		} 
	}
	
	/**
	 * Returns the time when the event is executed, with other words the the customer arrives.
	 * @return Time when executed.
	 */
	public double getTime() {
		return time;
	}

}

