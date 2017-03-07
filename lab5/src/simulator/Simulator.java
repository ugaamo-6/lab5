
package simulator;

/**
 * Simulator calling future events.
 * 
 * @author Johan Bråtendal
 * @author Gustav Mattsson
 * @author Jonas Jarnhäll Sjöman
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
		while (state.running()) {		//Printar ut information hela tiden under kÃ¶rningen
			Event currentEvent = eventStore.nextEvent();
			currentEvent.execute();
		}
		v.endInfoPrint();
			
	}

}
