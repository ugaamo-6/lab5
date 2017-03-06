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

	public double getTime() { 
		return currentTime; }
	
	public void setTime(double time) { currentTime = time; }

	public void addEvent(Event addEvent){
		
		
		if (queue.size() == 0) {
			queue.add(addEvent);

		} else {	
				for(int i=0; i < queue.size(); i++){
					if (compareTime(addEvent, queue.get(i))){
						queue.add(i,addEvent);
						break;
					} else if (i+1 == queue.size()){
						queue.add(addEvent);
						break;
					}
				}
		}
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
	

