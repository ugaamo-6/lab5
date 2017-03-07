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
	private SalongView salonView;
	private SalongState salonState;
	private FIFO f;

	//Ha en main utanför. ALLTSÅ SKAPA EN MAIN klass som har en main metod som kör denna.
	
	public Simulator(EventStore es, State s, SalongState ss, FIFO f){
		this.eventStore = es;
		this.state = s;
		this.salonState = ss;
		this.f=f;
	}
	/**Kör ett program.*/
	public void Run() {
		state.start();
		eventStore.addEvent(new StartSim(eventStore, salonState, state, salonView, f));
		eventStore.addEvent(new Closing(salonState.getCloseTime() , eventStore,salonState,state,salonView,f));
		
		salonView = new SalongView(f,salonState,eventStore); //Printar ut viktig information direkt
		while (state.running()) {		//Printar ut information hela tiden under körningen
			Event currentEvent = eventStore.nextEvent();
			currentEvent.execute();
		}
		salonView.endInfoPrint();
			
	}


	public static void main(String args[]){
		
		State s = new State();
		EventStore es = new EventStore(s);
		SalongState ss = new SalongState(es);
		FIFO f = new FIFO(es, ss, s);
		Simulator sim = new Simulator(es, s, ss, f);
		sim.Run();
	}

}
