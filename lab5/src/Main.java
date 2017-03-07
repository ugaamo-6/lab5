import event.Closing;
import event.StartSim;
import simulator.EventStore;
import simulator.Simulator;
import simulator.State;
import simulator.View;
import state.FIFO;
import state.SalongState;
import state.SalongView;

/**
 * 
 */

/**
 * @author Johan Bråtendal
 * @author
 * @author
 *
 */
public class Main {

	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		State s = new State();
		EventStore es = new EventStore(s);
		View v = new View();		
		
		SalongState ss = new SalongState(es);
		FIFO f = new FIFO(es, ss);
		SalongView sv = new SalongView(f, ss, es);
		
		//Creates two events, one staring and one closing.
		es.addEvent(new StartSim(es, ss, s, sv, f));
		es.addEvent(new Closing(ss.getCloseTime(),s,sv));
		
		Simulator sim = new Simulator(es, s, v);
		sim.Run();

	}

}
