package state;

import java.util.ArrayList;
import java.util.Observable;
import simulator.Statistics;
import event.CustLeaves;
import event.CustReturns;
import hairdresser.SalongView;
import simulator.EventStore;
import simulator.State;
import event.CustLeaves;

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
	private CustLeaves cl;
	
	private int NumWaiting = 0; //max customers in queue at once 
	public static double lastEventTime = 0;
	

	
	
	public FIFO(EventStore es, SalongState ss, State s){
		this.es=es;
		this.ss=ss;
		this.s=s;
		
	}

	private Statistics stat = new Statistics();
	private ArrayList<Object> queue =  new ArrayList<Object>(); 
	private int totalVisitors = 0; 
	private static String message;

	public void addNewCustomerToFIFO(Customer C) {

		if(ss.getFreeChairs() == 0){
//			timeDiffCalc(queueSize());
			queue.add(C);
			lastEventTime = es.getTime();
//			stat.qTime(qTimeCalc(getFirst()));
//			stat.lastCustTime(es.getTime());
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
	 * Check if the FIFO queue is full.
	 * @return True in full, false if not. 
	 */
	
	public void setLET(double time){
		lastEventTime = time;
	}
	
	public boolean isFull(){
		if(queueSize() >= ss.maxWaitInQueue()){
			return true;
		}
		return false;
	} 
	/**Räknar upp antal missnöjda kunder
	 * @return missnöjda kunder*/
	public int returningCustInQueue(){
		int count=0;
		for (int i=0;i<queue.size();i++){
			if (!((boolean) ((Customer) queue.get(i)).get_happy())) {
				count++;
			}
		}
		return count;
	}

	public void custFinished(){
		//System.out.println("--- FINISHED");
		ss.chairGotFree();
	}
	public void addReturnToQueue(Customer C){
			queue.add(returningCustInQueue(), C);		
		}
	/**Tar bort sista kunden*/
	public void removeLast(){
		queue.remove(queue.size()-1);
	}
	/**Tar bort första kunden*/
	public void removeFirst() {
		queue.remove(0);
	}
	/**Hämtar första kunden*/
	public Customer getFirst() {
		return (Customer) queue.get(0);
	}
	/**Kollar om kön är tom*/
	public boolean isEmpty(){
		if(queueSize() == 0){
			return true;
		}return false;
	}
	/**Hämtan kö storleken.*/
	public int queueSize(){
		return queue.size();
	}
	/**
	 * H�mtar totala vistelse kunder
	 * @return totalVisitor
	 */
	public int getTotalVisitors(){
		return totalVisitors;
	}
	/**
	 * H�mtar eventStore tid subtraherat med en kund k� tid.
	 * @param C, Customer
	 * @return ????????????????????????????
	 */
	private double qTimeCalc(Customer C){
		return es.getTime()-C.queueTime;
	}
	
	public void timeDiffCalc(int i){
		double diff = es.getTime() - lastEventTime;
		for(int j = 1; j<=i; j++){
		stat.qTime(diff);}	
	}	
	
	public void toString(String name,int ID)
	{
		String b = String.format("%-5.2f %-10s %-10d %-10d %-10.2f %-7d %-7d  %-7d %-10d %-10f",
					es.getTime(),name,ID,ss.getFreeChairs(),stat.getQtime(),queueSize(),(int)stat.getCust(),             
					stat.getLeave(),                 
					stat.getDiss(),
					stat.getIdle()
					
				);
		message = b;
		setChanged();
		notifyObservers();
	}
	
	public String getMessageString(){
		return message;
	}
}

