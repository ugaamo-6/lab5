//Kika pï¿½ 6.8, har vi gjort rï¿½tt?
//Kika pï¿½ 6.9, har vi gjort rï¿½tt?
//Anvï¿½nd private!!
//Kika pï¿½ 6.10, har vi gjort rï¿½tt?
//Kika pï¿½ 6.15, har vi gjort rï¿½tt?


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
	 * Kör ett program.
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
