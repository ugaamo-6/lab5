package state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import state.SalongState;

/**
 * 
 * A queue that represents the waiting room for a hairdresser
 * 
 * @author Gustav Mattsson (ugaamo-6)
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
	SalongState s = new SalongState();
	
	public FIFO(){

	}
	
	//necessary variables
<<<<<<< HEAD
	private ArrayList<Object> queue =  new ArrayList<Object>(); //The queue for the salon
	private int SEATS = s.freeChairs; //The number of seats available
	private static final int maxWait = 10;
=======
	private static ArrayList<Object> queue =  new ArrayList<Object>(); //The queue for the salon
	private int SEATS = 0; //The number of seats available
>>>>>>> branch 'master' of https://github.com/ugaamo-6/lab5.git
	private int totalVisitors = 0; //total visitors of the day
	String message;
	
	public void messageString(String s){
		message = s;
<<<<<<< HEAD
		//update();
=======
		setChanged();
		notifyObservers(Object o);
>>>>>>> branch 'master' of https://github.com/ugaamo-6/lab5.git
	}
	
<<<<<<< HEAD

	public void add(Object customer){
		if(SEATS == 0 && !checkFull()){ //if seats are 
			queue.add(customer);
			totalVisitors += 1;
			setChanged();
			notifyObservers();}
			}

	public void add(){
=======
	public void add(Customer C){//L�gg till input i form av kund
>>>>>>> branch 'master' of https://github.com/ugaamo-6/lab5.git
		if(checkFull()){
			messageString("The queue is full, customer leaves");

		}
		else if(isEmpty() && SEATS != 0){//om v�ntrummet �r tomt
			System.out.println("Customer gets seated!");
			SEATS -= 1;
			
		}
		else if(checkFull()){
			System.out.println("customer leaves, waiting room full!");}

		else if(isEmpty()){
			messageString("Customer sits direct in seat.");
		}
		
<<<<<<< HEAD
=======
		queue.add(C);
		totalVisitors += 1;
>>>>>>> branch 'master' of https://github.com/ugaamo-6/lab5.git
	}
	public boolean checkFull(){
		if(size() >= maxWait){
			return true;
		}return false;
	} 
	
	public void returnCust(Object customer){
		if(isEmpty()){
			messageString("Queue is empty, gets seated directly.");
		}else if(checkFull()){
			removeLast();
			queue.add(customer);
			Collections.rotate(queue, (SEATS-1));
		}else{
			queue.add(customer);
			Collections.rotate(queue, (SEATS-1));
		}
	}
	public void removeLast(){
		queue.remove(queue.get(-1));
	}
	public boolean isEmpty(){
		if(size() == 0){
			return true;
		}return false;
	}
	public int size(){
		return queue.size();
	}
	public Object getFirst(){
		return queue.get(0);
	}
	public int getTotalVisitors(){
		return totalVisitors;
	}
}

