package state;

import java.util.ArrayList;
import java.util.Observable;
import simulator.Statistics;
import event.CustLeaves;
import event.CustReturns;
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

public class FIFO extends Observable {

	private EventStore es;
	private SalongState ss;
	private State s;
	private SalongView sv;
	private FIFO f;
	
	private int maximus = 0; //max customers in queue at once 
	
	public FIFO(EventStore es, SalongState ss, State s){
		this.es=es;
		this.ss=ss;
		this.s=s;
		
	}

	private Statistics stat = new Statistics();
	private ArrayList<Object> queue =  new ArrayList<Object>(); 
	private int totalVisitors = 0; 
	private static String message;
	
	public void add(Customer C){
		if(isFull()){
			messageString("The queue is full, customer leaves");
			stat.addLeave();
			stat.setTime1(es.getTime());
			stat.idleCalc();
		}
		else if(ss.freeChairs() != 0 && isEmpty()){
			queue.add(C);
			getFirst();
		} else if(ss.freeChairs() != 0 && isEmpty() && ss.freeChairs() != 0){
			messageString("Customer gets a haircut!");
			ss.chairGotBusy();	
			es.addEvent(new CustLeaves(es.getTime() , C, es, ss, s, sv));
		} else {
			queue.add(C);
			C.queueTime = es.getTime();
			messageString("Customer wait.");
		}

		if(isFull()){
				
			}
		
		
		if(maximus < queueSize()){
			stat.maxSize(queueSize());
			maximus = queueSize();
		}
		
		

	}
	
	public void messageString(String s){
		message = s;
		setChanged();
		notifyObservers();
	}
	
	public String getMessageString(){
		return message;
	}
	
	public boolean isFull(){
		if(queueSize() >= ss.maxWaitInQueue()){
			return true;
		}
		return false;
	} 
	
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
<<<<<<< HEAD
		messageString("Customer is finished, pays and leaves the salon.");
		//ss.chairGotFree();
=======
		ss.chairGotFree();
		messageString("Customer is finished, pays and leaves the salon.");

>>>>>>> branch 'master' of https://github.com/ugaamo-6/lab5.git
		if(!isFull() && !stat.getGoing()){
			stat.setTime2(es.getTime());
			stat.goingTrue();
		}
	}
//		System.out.println(ss.freeChairs());
//		System.out.println(queueSize());
	
	public void addReturnCust(Customer C){
<<<<<<< HEAD
		if(isEmpty()){
			messageString("Returning customer: Queue is empty, gets haircut directly.");
		}else if(isFull()){
			
			//DUBBELKOLLA OM HELA KÖN ÄR RETURNING!!
			
			messageString("Returning customer: Stands in queue. Last customer in queue left.");
			removeLast();
			queue.add(C);
			Collections.rotate(queue, (hairdressSeats-1));
			stat.addDiss();
			ss.chairGotBusy();
		}else if(!isFull()){
			messageString("Returning customer: Customer stands in queue.");
			queue.add(returningCustInQueue(), C);
			ss.chairGotBusy();
		}else{
			queue.add(C);
			Collections.rotate(queue, (hairdressSeats-1));
			stat.addDiss();
			queue.add(returningCustInQueue(), C);
			ss.chairGotBusy();
		}
	}
=======
		
			if (ss.freeChairs() == ss.totalChairs()) {
				queue.add(returningCustInQueue(), C);
				getFirst();
				messageString("Returning customer: Customer get haircut.");
			} else if(!isFull()){
				queue.add(returningCustInQueue(), C);
				messageString("Returning customer: Customer stands in queue.");		
//				System.out.println(ss.freeChairs());
//				System.out.println(queueSize());
			}else if(isFull()){
				

				//Kontrollerar ifall hela kön är återkommande. 
				if (returningCustInQueue() == ss.maxWaitInQueue()) {
					double returnTime = es.getTime()+ss.returnTime();
					es.addEvent(new CustReturns(returnTime, C, es, ss, s, sv));	
					messageString("Queue full with dissatisfied customers, gets a walk and come back later.");
//					System.out.println(ss.freeChairs());
//					System.out.println(queueSize());
				} else {
					removeLast();
					queue.add(returningCustInQueue(), C);
					messageString("Returning customer: Stands in queue. Last customer in queue left.");		
					stat.addDiss();
//					System.out.println(ss.freeChairs());
//					System.out.println(queueSize());
				}
			}
		}
>>>>>>> branch 'master' of https://github.com/ugaamo-6/lab5.git

	public void removeLast(){
		queue.remove(queue.size()-1);
	}
	
	public boolean isEmpty(){
		if(queueSize() == 0){
			return true;
		}return false;
	}
	
	public int queueSize(){
		return queue.size();
	}

	public void getFirst(){
		
		if(!isEmpty()){
			ss.chairGotBusy();
			Customer getFirst = (Customer) queue.get(0);
			es.addEvent(new CustLeaves(es.getTime(), getFirst, es, ss, s, sv));
			queue.remove(0);
			messageString("Customer gets a haircut.");
			System.out.println(ss.freeChairs());
			System.out.println(queueSize());
		} 
		
		
	}
	
	public void checkIfSatisfied(Customer C){
		if(ss.randReturn()<=ss.percentageReturn()){
			messageString("Customer is not happy.");
			System.out.println(ss.freeChairs());
			System.out.println(queueSize());
			double returnTime = es.getTime()+ss.returnTime();
			es.addEvent(new CustReturns(returnTime, C, es, ss, s, sv));	
			C.happy = false;
		} else { C.happy = true; }
		
	}

	public int getTotalVisitors(){
		return totalVisitors;
	}

}

