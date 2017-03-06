package simulator;

import java.util.ArrayList;
import event.CustArrives;
import event.StopSim;
import state.SalongState;
import state.FIFO;
import simulator.Statistics;

public class EventStore {
	
	//This array-list keep track of the queue of Events
	ArrayList<Event> queue = new ArrayList<Event>();
	private double currentTime;
	private State s; 
	private SalongState ss = new SalongState();
	private FIFO f;
	private Statistics stat;

	
	public EventStore(State s) {
		this.s=s;
	}

	public double getTime() { return currentTime; }
	
	public void setTime(double time) { currentTime = time; }

	public void addEvent(Event addEvent){
		
//		Alla print-satser ska bort.
		
		if (queue.size() == 0) {
			queue.add(addEvent);

		} else {	
				for(int i=0; i < queue.size(); i++){
					if (compareTime(addEvent, queue.get(i))){
						queue.add(i,addEvent);
<<<<<<< HEAD
=======
//						System.out.println("--- Time of event: "+getTime());
//						System.out.println("--- Event type/name: "+addEvent.getName());
//						System.out.println("--- Customer ID: "+addEvent.getCustomerID());
//						System.out.println("--- Free Chairs: ");

>>>>>>> branch 'master' of https://github.com/ugaamo-6/lab5.git
						break;
					} else if (i+1 == queue.size()){
						queue.add(addEvent);
<<<<<<< HEAD
=======
//						System.out.println("--- Time of event: "+getTime());
//						System.out.println("--- Event type/name: "+addEvent.getName());
//						System.out.println("--- Customer ID: "+addEvent.getCustomerID());
//						System.out.println("--- Free Chairs: ");
>>>>>>> branch 'master' of https://github.com/ugaamo-6/lab5.git
						break;
					}
				}
		}
	}
	
	
//	Dessa tv� metoder f�r inte finnas i simulatorn!!
	
	public int getIdleCut(){
		//return ss.freeChairs();
		return 2;
	}
	public int getIdleQueue(){
		//return f.getMax() - f.queueSize();
		return 1;
	}
	
	public boolean compareTime(Event A, Event B){
		return A.getTime() < B.getTime();
		
	}
	
	public Event nextEvent() {
		if (queue.size() == 0 ) {
			addEvent(new StopSim(s));
		} else {
			setTime(queue.get(0).time);
			currentTime = queue.get(0).getTime();
		}

		Event sendEvent = queue.get(0);

		
		queue.remove(0);
		return sendEvent;

		}
	}
	

