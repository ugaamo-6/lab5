//Kika p� 6.8, har vi gjort r�tt?
//Kika p� 6.9, har vi gjort r�tt?
//Anv�nd private!!
//Kika p� 6.10, har vi gjort r�tt?
//Kika p� 6.15, har vi gjort r�tt?


package simulator;

import event.*;
import hairdresser.SalongView;
import state.*;

public class Simulator {
	private EventStore eventStore;
	private State state;
	private View v;

	//Ha en main utanför. ALLTSÅ SKAPA EN MAIN klass som har en main metod som kör denna.
	
	public Simulator(EventStore es, State s, View v){
		this.eventStore = es;
		this.state = s;
		this.v=v;
	}
	/**Kör ett program.*/
	public void Run() {
		state.start();
		v.beginInfoPrint();
		while (state.running()) {		//Printar ut information hela tiden under körningen
			Event currentEvent = eventStore.nextEvent();
			currentEvent.execute();
		}
		v.endInfoPrint();
			
	}


	public static void main(String args[]){
		
		State s = new State();
		EventStore es = new EventStore(s);
		View v = new View();		
		
		SalongState ss = new SalongState(es);
		FIFO f = new FIFO(es, ss, s);
		SalongView sv = new SalongView(f, ss);
		

		
		es.addEvent(new StartSim(es, ss, s, sv, f));
		es.addEvent(new Closing(ss.getCloseTime(),es,ss,s,sv,f));
		
		Simulator sim = new Simulator(es, s, v);
		sim.Run();
	
		
		
	}

}
