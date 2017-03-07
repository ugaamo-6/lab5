package simulator;

import java.util.ArrayList;
import java.util.Observable;

import event.CustArrives;
import event.StopSim;
import state.SalongState;
import state.FIFO;
import simulator.Statistics;

public class EventStore extends Observable{
	
	//This array-list keep track of the queue of Events
	ArrayList<Event> queue = new ArrayList<Event>();
	private double currentTime;
	private State s; 
	private SalongState ss = new SalongState(this);
	private FIFO f;
	private Statistics stat;

	ArrayList<Event> eventQueue = new ArrayList<Event>();
	private State state; 
//	private SalongState ss = new SalongState();
//	private FIFO f;
//	private Statistics stat;

	
	public EventStore(State s) {
		this.state=s;
	}

	
	/**Lägger till ett event*/
	public void addEvent(Event addEvent){
		
		if (eventQueue.size() == 0) { //Basfallet
			eventQueue.add(addEvent);

		} else {	
				for(int i=0; i < eventQueue.size(); i++){
					if (compareTime(addEvent, eventQueue.get(i))){
						eventQueue.add(i,addEvent);
						break;
					} else if (i+1 == eventQueue.size()){
						eventQueue.add(addEvent);
						break;
					}
				}
		}
	}
	

	/**Hämtar ut ett event från index 0*/
	public Event nextEvent() {
		if (eventQueue.size() == 0 ) { 	//Basfall om eventlistan är tom lägg in Stop eventet
			addEvent(new StopSim(state));
		} else {
			setTime(eventQueue.get(0).time);
			currentTime = eventQueue.get(0).getTime();//Hämtar ett event och dess tid
		}
			//nedan följer pop av eventet
			Event sendEvent = eventQueue.get(0);
			String info = sendEvent.toString();
			setChanged();
			notifyObservers(info);
			eventQueue.remove(0);
			return sendEvent;
		}
	
	

	/**När event poppas och denna metod används får nuvarande tid en ny tid*/
	public void setTime(double time) {
		currentTime = time; 
	}
	
	/**Hämtar nuvarande tid*/
	public double getTime() { 
		return currentTime; 
	}
	
	
	
	/**Hjälpmetod jämför storleken/olikheten mellan två element*/
	private boolean compareTime(Event A, Event B){
		return A.getTime() < B.getTime();
		
	}
}
	

