package state;

/**
 * This class creates a customer in which we have  variables that can be usefull.
 * 
 * @author Gustav Mattsson (ugaamo-6)
 * @author Johan Bråtendal
 * @author Jonas Jarnhäll Sjöman
 * 
// * @param id+id_counter when a customer is created he/she gets a unique id which is created by adding +1 to a counter
// * @param happy decides wheter the customer is satisfied with the haircut or not.
// * @param queueTime keeps the time when the customer is adden into the FIFO queue.
// * 
// * @param getID returns the ID of a customer.
// * @param getHappy returns the happy variabel for a customer.
// * @param getFIFO returns the FIFO queue for the customer. 
 * 
 * 
 * 
 * 
 * */
public class Customer {
	
	private FIFO fifo;
		 
	static int id_counter = 0;
	
	private double queueTime = 0;
	
	private int id;
	private boolean happy;
	
	/**
	 * @param Customer This is the constructor that sets the values when you create a new customer.
	 * */
	public Customer(FIFO f){
		this.fifo=f;
		id = id_counter; //Det finns inte ett id nummer som Ã¤r 0 om detta gÃ¤ller.
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
		happy = false;}
	/**
	 * Get the time when the customer started to queue.
	 * @return Time, when the customer started to queue.
	 */
	public double getQueueTime(){
		return queueTime;
	}
	
	//nedan get metoder
	/**
	 * Get the ID of customer.
	 * @return ID of customer
	 */
	public int getID(){
		return id;
	}
	/**
	 * Get true/false if the customer is happy or not.
	 * @return true if customer is happy, false if not.
	 */
	public boolean get_happy(){
		return happy;
	}
	/**HÃ¤mtar kÃ¶n frÃ¥n en kund
	 * @return Object of the fifo-queue we are using.*/
	public FIFO getFIFO(){
		return fifo;
	}
}

