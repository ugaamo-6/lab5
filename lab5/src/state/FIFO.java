package state;

import java.util.ArrayList;
import java.util.Observable;
import simulator.Statistics;
import simulator.EventStore;
import simulator.State;

import state.SalongState;
/**
 * 
 * A queue that represents the waiting room for a hairdresser
 * 
 * @author Gustav Mattsson (ugaamo-6)
 * @author Johan Bråtendal
 * @author Jonas Jarnhäll Sjöman
 * 
// * @param add the add method adds to the queue unless the queue is full or empty
// * @param checkFull checks if the queue is full or not
// * @param returnCust a returning customer takes first place in queue and the last customer has to leave
// * @param removeLast removes the last customer in queue
// * @param isEmpty checks if waiting queue is empty
// * @param size returns the size of the queue
// * @param getFirst picks the first customer in the queue
// * @param totalVisitors counts the total customers who have gotten a haircut
 * 
 * 
 * */

public class FIFO extends Observable {

	private EventStore es;
	private SalongState ss;

	
	private int NumWaiting = 0; //customer in the queue 
	private static double lastEventTime = 0;
	
	private Statistics stat = new Statistics();
	private ArrayList<Object> queue =  new ArrayList<Object>();	 
	private int totalVisitors = 0; 
	private static String message;
	
	/**
	 * This is the constructor.
	 * @param es, Event Store.
	 * @param ss, Salong State.
	 */
	public FIFO(EventStore es, SalongState ss){
		this.es=es;
		this.ss=ss;
		
	}

	/**
	 * Adds a new customer to the queue.
	 * @param C, customer we want to add.
	 * */
	public void addNewCustomerToFIFO(Customer C) {

		if(ss.getFreeChairs() == 0){
			queue.add(C);
			lastEventTime = es.getTime();
		}else{
			queue.add(C);
		}
		C.queueTime = es.getTime();
		if(NumWaiting < queueSize()){
				stat.maxSize(queueSize());
				NumWaiting = queueSize();
			}
	}
	
	
	/**
	 * Changes last event time to the parameter time.
	 * @param time, the time of last event.
	 */
	public void setLET(double time){
		lastEventTime = time;
	}
	
	/**Checks if queue is full
	 * @return true if full, false if not*/
	public boolean isFull(){
		if(queueSize() >= ss.maxWaitInQueue()){
			return true;
		}
		return false;
	} 
	/**Counts the amount of disappointed customers
	 * @return disappointed customers*/
	public int returningCustInQueue(){
		int count=0;
		for (int i=0;i<queue.size();i++){
			if (!((boolean) ((Customer) queue.get(i)).get_happy())) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Empties hairdresschair.
	 * */
	public void custFinished(){
		ss.chairGotFree();
	}

	/**
	 * Returning customer is placed behind the rest of all the returning customers.
	 * @param C, in inserted in the queue.
	 */
	public void addReturnToQueue(Customer C){
			queue.add(returningCustInQueue(), C);		
		}

	/**
	 * Removes the last customer from the queue
	 *Tar bort sista kunden*/
	public void removeLast(){
		double lastCustomerQueueTime = es.getTime()-((Customer) queue.get(queue.size()-1)).getQueueTime() ;
		stat.qTimeRemove(lastCustomerQueueTime);//Remove the time the leaving customer has been in the queue
		queue.remove(queue.size()-1);
		stat.addLeave();
	
	}
	/**
	 * Removes customer who is first in queue 
	 * */
	public void removeFirst() {
		queue.remove(0);
	}
	/**
	 * Retrieves the first customer from the queue
	 * @return First customer in queue.
	 * */
	public Customer getFirst() {
		return (Customer) queue.get(0);
	}
	/**Checks if the queue is empty or not.
	 * @return true if empty, false if not.
	 * */
	public boolean isEmpty(){
		if(queueSize() == 0){
			return true;
		}return false;
	}
	
	/**HÃ¤mtan kÃ¶ storleken.
	 * @return Size of the queue
	 * */
	public int queueSize(){
		return queue.size();
	}
	/**
	 * Hämtar totala vistelse kunder
	 * @return totalVisitor
	 */

	public int getTotalVisitors(){
		return totalVisitors;
	}

	/**
	 * Subtracts current time with lastEventTime and records 
	 * @param Length, number of customers in queue.
	 * */
	public void timeDiffCalc(int length){
		double diff = es.getTime() - lastEventTime;
		for(int j = 1; j<=length; j++){
			stat.qTime(diff);}	
	}	
	
	/**
	 * Creates a message string describing an event with cocurrent and relevant statistic.
	 * @param Name of the event.
	 * @param ID of customer
	 * */
	public void toString(String name,int ID)
	{
		String b = String.format("%-5.2f %-10s %-10d %-10d %-10f %-10.2f %-7d %-7d %-7d %-10d ",
					es.getTime(),name,ID,ss.getFreeChairs(),stat.getIdle(),stat.getQtime(),queueSize(),(int)stat.getCust(),             
					stat.getLeave(), stat.getDiss());
		message = b;
		setChanged();
		notifyObservers();
	}
	
	/**Retrieves a string with information
	 * @return The current message we want to print.*/
	public String getMessageString(){
		return message;
	}
}

