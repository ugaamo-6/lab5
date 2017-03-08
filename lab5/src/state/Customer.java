package state;

import simulator.EventStore;
import simulator.State;

/**
 * This class creates a customer in which we have  variables that can be usefull.
 * 
 * @author Gustav Mattsson (ugaamo-6)
 * 
 * @param id+id_counter when a customer is created he/she gets a unique id which is created by adding +1 to a counter
 * @param happy decides wheter the customer is satisfied with the haircut or not.
 * @param queueTime keeps the time when the customer is adden into the FIFO queue.
 * 
 * @param getID returns the ID of a customer.
 * @param getHappy returns the happy variabel for a customer.
 * @param getFIFO returns the FIFO queue for the customer. 
 * 
 * 
 * 
 * 
 * */
public class Customer {
	
	private FIFO fifo;
	
	private static int id_counter = 0;
	
	private double queueTime = 0;
	
	private int id;
	private boolean happy;
	
	/**
	 * @param Customer This is the constructor that sets the values when you create a new customer.
	 * */
	public Customer(FIFO f){
		this.fifo=f;
		id = id_counter; //Det finns inte ett id nummer som är 0 om detta gäller.
		happy = true;
		id_counter++;
		
	}
	
	/**
	 * setQtime decides when the customer where set in queue.
	 * */
	public void setQtime(double time){
		queueTime = time;
	}
	/**
	 * happyTrue sets happy to true.
	 * */
	public void happyTrue(){
		happy = true;
	}
	/**
	 * happyFalse sets happy to false.
	 * */
	public void happyFalse(){
		happy = false;
	}
	
	//nedan get metoder
	/**
	 * getID returns the ID for te customer
	 * */
	public int getID(){
		return id;
	}
	/**
	 * get_happy returns if the customer where happy with his/her last haircut.
	 * */
	public boolean get_happy(){
		return happy;
	}
	/**
	 * getFIFO returns the queue from a customer.
	 * */
	public FIFO getFIFO(){
		return fifo;
	}
}

