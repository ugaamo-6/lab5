package lab5;

import java.util.ArrayList;

public class FIFO {

	public FIFO(){
		
	}
	private ArrayList<Object> queue =  new ArrayList(); //The queue for the salon
	private int SEATS = 0; //The number of seats available
	private int totalVisitors = 0;
	
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
