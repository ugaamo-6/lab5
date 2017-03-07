package simulator;

import java.util.ArrayList;
import java.util.Observable;

import event.StopSim;

/**
 * Consist of a queue of events, sorting events with execution-time.
 * 
 * @author Johan Br�tendal
 * @author Gustav Mattsson
 * @author Jonas Jarnh�ll Sj�man
 */
public class EventStore extends Observable{
	
	//This array-list keep track of the queue of Events
	ArrayList<Event> queue = new ArrayList<Event>();
	private double currentTime = 0.00;


	ArrayList<Event> eventQueue = new ArrayList<Event>();
	private State state; 

	public EventStore(State s) {
		this.state=s;
	}

	
	/**Lägger till ett event
	 * @param Event, event we will add.
	 * */
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
	

	/**Hämtar ut ett event från index 0
	 * @return Next event.
	 * */
	public Event nextEvent() {
		if (eventQueue.size() == 0 ) { 	//Basfall om eventlistan är tom lägg in Stop eventet
			addEvent(new StopSim(state));
		} else {
			setTime(eventQueue.get(0).time);
			currentTime = eventQueue.get(0).getTime();//Hämtar ett event och dess tid
		}
			//nedan följer pop av eventet
			Event sendEvent = eventQueue.get(0);
			eventQueue.remove(0);
			return sendEvent;
		}
	
	

	/**När event poppas och denna metod används får nuvarande tid en ny tid
	 * @param double time, the time we set as the current time.
	 * */
	public void setTime(double time) {
		currentTime = time; 
	}
	
	/**Hämtar nuvarande tid
	 * @return Current Time.
	 * */
	public double getTime() { 
		return currentTime; 
	}
	
	
	
	/**Hjälpmetod jämför storleken/olikheten mellan två element
	 * @return If B is larger than A.
	 * @param Event A, event to compare.
	 * @param Event B, event to compare.
	 * */
	private boolean compareTime(Event A, Event B){
		return A.getTime() < B.getTime();
		
	}
}
	

