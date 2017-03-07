package state;

import hairdresser.SalongView;
import simulator.EventStore;
import simulator.State;

/**
 * This class defines the variables that a customer needs
 * 
 * @author Gustav Mattsson (ugaamo-6)
 * @param id all id:s are unique because we add the counter +1 for every new created customer
 * @param happy is declared wheter the customer is happy after it's haircut
 * 
 * */
public class Customer {
	
//	private EventStore es;
//	private SalongState ss;
//	private State s;
//	private SalongView sv;
	private FIFO fifon;
		
	static int id_counter = 0;
	
	public double queueTime = 0;
	
	private int id;
	public boolean happy;
	
	public Customer(EventStore es, SalongState ss, State s, SalongView sv, FIFO f){
//		this.es=es;
//		this.ss=ss;
//		this.s=s;
//		this.sv=sv;
		
		this.fifon=f;
		id = id_counter + 1; //Det finns inte ett id nummer som är 0 om detta gäller.
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
		return fifon;
	}
}
