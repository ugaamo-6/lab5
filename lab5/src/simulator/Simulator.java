
package simulator;

/**
 * Simulator calling future events.
 * 
 * @author Johan Br�tendal
 * @author Gustav Mattsson
 * @author Jonas Jarnh�ll Sj�man
 */
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
	 * Executes a program.
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
