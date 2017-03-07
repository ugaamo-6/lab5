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
		
	static int id_counter = 0;
	
	public double queueTime = 0;
	
	private int id;
	public boolean happy;
	
	public Customer(FIFO f){

		
		this.fifo=f;
		id = id_counter; //Det finns inte ett id nummer som är 0 om detta gäller.
		happy = true;
		id_counter++;
		
	}

	//nedan get metoder
	public int getID(){
		return id;
	}

	public boolean get_happy(){
		return happy;
	}
	/**Hämtar kön från en kund*/
	public FIFO getFIFO(){
		return fifo;
	}
}

