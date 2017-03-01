package state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

import event.CustLeaves;
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

public class FIFO extends Observable{

	private EventStore es;
	private SalongState ss;
	private State s;
	private SalongView sv;
	private FIFO f;
	
	public FIFO(EventStore es, SalongState ss, State s, SalongView sv){
		this.es=es;
		this.ss=ss;
		this.s=s;
		this.sv=sv;
	}


	private int hairdressSeats = ss.freeChairs; //The number of seats available
	private final int maxWait = 10;
	private static ArrayList<Object> queue =  new ArrayList<Object>(); //The queue for the salon
	private int totalVisitors = 0; //total visitors of the day
	private static String message;
	
	public void messageString(String s){
		message = s;
		setChanged();
		notifyObservers();
	}
	
	public String getMessageString(){
		return message;
	}
	
	public void add(Customer C){//Lägg till input i form av kund
		if(checkFull() && s.opened() && hairdressSeats == 0){
//			System.out.println("The queue is full, customer leaves");

		}
		else if(hairdressSeats != 0 && !checkFull()){//om väntrummet är tomt & palts är ledig
			System.out.println("Customer gets a haircut!");
			hairdressSeats--;	
			es.addEvent(new CustLeaves(es.getTime() , C, es, ss, s, sv, f));
		}
		else if(checkFull()){
			System.out.println("Customer leaves, waiting room full!");	
		} 
		else if(hairdressSeats == 0 && !checkFull()){
			queue.add(C);
			System.out.println("Customer seats in waitroom.");
		}
		
		totalVisitors += 1;

	}
	public boolean checkFull(){
		if(queueSize() >= maxWait){
			return true;
		}return false;
	} 
	
	public void returnCust(Object customer){
		if(isEmpty()){
			messageString("Queue is empty, gets seated directly.");
		}else if(checkFull()){
			removeLast();
			queue.add(customer);
			Collections.rotate(queue, (hairdressSeats-1));
		}else{
			queue.add(customer);
			Collections.rotate(queue, (hairdressSeats-1));
		}
	}
	public void removeLast(){
		queue.remove(queue.get(-1));
	}
	public boolean isEmpty(){
		if(queueSize() == 0){
			return true;
		}return false;
	}
	public int queueSize(){
		return queue.size();
	}
	public Object getFirst(){
		return queue.get(0);
	}
	public int getTotalVisitors(){
		return totalVisitors;
	}

}

