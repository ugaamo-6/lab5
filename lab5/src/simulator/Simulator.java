//Kika p� 6.8, har vi gjort r�tt?
//Kika p� 6.9, har vi gjort r�tt?
//Anv�nd private!!
//Kika p� 6.10, har vi gjort r�tt?
//Kika p� 6.15, har vi gjort r�tt?


package simulator;

public class Simulator {
	private EventStore eventStore;
	private State state;
	private View v;

	public Simulator(EventStore es, State s, View v){
		this.eventStore = es;
		this.state = s;
		this.v=v;
	}
	/**
	 * K�r ett program.
	 */
	public void Run() {
		state.start();
		v.beginInfoPrint();
		while (state.running()) {		//Printar ut information hela tiden under körningen
			Event currentEvent = eventStore.nextEvent();
			currentEvent.execute();
		}
		v.endInfoPrint();
			
	}

}
