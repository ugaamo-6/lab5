package lab5;

import java.util.ArrayList;
import java.util.Collections;

public class FIFO {
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
 * **/
	public FIFO(){

		
	}
	
	//necessary variables
	private ArrayList<Object> queue =  new ArrayList<Object>(); //The queue for the salon
	private int SEATS = 0; //The number of seats available
	private int totalVisitors = 0; //total visitors of the day
	
	public void add(Object o){
		if(checkFull()){
			System.out.println("The queue is full, customer leaves");
		}
		else if(isEmpty()){
			System.out.println("Customer sits direct in seat.");
		}
		
		queue.add(o);
		totalVisitors += 1;
	}
	public boolean checkFull(){
		if(size() >= SEATS){
			return true;
		}return false;
	} 
	public void returnCust(Object o){
		if(isEmpty()){
			System.out.println("Queue is empty, gets seated directly.");
		}else{
			removeLast();
			queue.add(0);
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

