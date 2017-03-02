package state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;
import simulator.Statistics;
import event.CustLeaves;
import event.CustReturns;
import hairdresser.SalongView;
import simulator.EventStore;
import simulator.State;

import state.SalongState;
/**
 * 
 * A queue that represents the waiting room for a hairdresser
 * 
 * @author Gustav Mattsson (ugaamo-6)
 * 
 * @param add the add method adds to the queue unless the queue is full or empty
 * @param checkFull checks if the queue is full or not
 * @param returnCust a returning customer takes first place in queue and the last customer has to leave
 * @param removeLast removes the last customer in queue
 * @param isEmpty checks if waiting queue is empty
 * @param size returns the size of the queue
 * @param getFirst picks the first customer in the queue
 * @param totalVisitors counts the total customers who have gotten a haircut
 * 
 * */

public class FIFO extends Observable {

	private EventStore es;
	private SalongState ss;
	private State s;
	private SalongView sv;
	private FIFO f;
	private Observable notifier;
	
	public FIFO(EventStore es, SalongState ss, State s){
		this.es=es;
		this.ss=ss;
		this.s=s;
	}

	private Statistics stat = new Statistics();
	private int hairdressSeats = ss.freeChairs; //The number of seats available
	private final int maxWait = 10;
	private ArrayList<Object> queue =  new ArrayList<Object>(); //The queue for the salon
	private int totalVisitors = 0; //total visitors of the day
	private static String message;
	
	public void add(Customer C){//Lägg till input i form av kund
		if(isFull() && s.opened() && ss.freeChairs() != 0){
			messageString("The queue is full, customer leaves");
			stat.addLeave();
		}

		else if(hairdressSeats != 0 && isEmpty() && ss.freeChairs() != 0){//DENNA ÄR FEL, kunder går direkt in.
			messageString("Customer gets a haircut!");
			ss.chairGotBusy();	
			es.addEvent(new CustLeaves(es.getTime() , C, es, ss, s, sv));
		}
		else if(isFull()){
			messageString("Customer leaves, waiting room full!");	
		} 
		
		else {//Ändra?? Blir fel, om en person lämnar en full salong kommer värdet aldrig bli 0 igen.
			queue.add(C);
			messageString("Customer wait.");
			
//			System.out.println(stat.getCust());
		}
		
		

	}
	
	public void messageString(String s){
		message = s;
		setChanged();
		notifyObservers();
	}
	
	public String getMessageString(){
		return message;
	}
	
	public boolean isFull(){
		if(queueSize() >= maxWait){
			return true;
		}return false;
	} 
	
	public void custFinished() {
		ss.chairGotFree();	
	}
	
	public void returnCust(Object customer){
		if(isEmpty()){
			messageString("Returning customer: Queue is empty, gets seated directly.");
		}else if(isFull()){
			messageString("Returning customer: Stands in queue. Last person left.");
			removeLast();
			queue.add(customer);
			Collections.rotate(queue, (hairdressSeats-1));
		}else{
			queue.add(customer);
			Collections.rotate(queue, (hairdressSeats-1));
		}
	}
	
	public void removeLast(){
		queue.remove(queue.size()-1);
	}
	
	public boolean isEmpty(){//Fixa denna, den kan gå till negativa tal.
		if(queueSize() == 0){
			return true;
		}return false;
	}
	
	public int queueSize(){
		return queue.size();
	}

	public Customer getFirst(){
		if(!isEmpty()){
			messageString("Customer leaves queue and gets a haircut.");
			Customer getFirst = (Customer) queue.get(0);
			es.addEvent(new CustLeaves(es.getTime(), getFirst, es, ss, s, sv));
			queue.remove(0);
			return getFirst;
		}
		return null;
	}
	
	public void checkIfSatisfied(Customer C){
		if(ss.randReturn()<=ss.percentageReturn()){
			messageString("Customer is not happy.");
			double returnTime = es.getTime()+ss.returnTime();
			es.addEvent(new CustReturns(returnTime, C, es, ss, s, sv));	
		}
	}
	
	public int getTotalVisitors(){
		return totalVisitors;
	}

}

