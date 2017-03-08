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
 * 
 * This class starts the whole simulation. It starts by creating every class needed, then adds a customer
 * and to really start it creates a new simulator and uses the method run thta starts the simulation.
 * 
 * @author Johan Bråtendal
 * @author Gustav Mattsson
 * @author Jonas Jarnhäll Sjöman
 *
 */
public class Main {

	/**
	 * this is the main method.
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		
//		double percentageReturn = 0.25;
		
		State s = new State();
		EventStore es = new EventStore(s);
		View v = new View();		
		
		SalongState ss = new SalongState(es/*, percentageReturn*/);
		FIFO f = new FIFO(es, ss);
		SalongView sv = new SalongView(f, ss, es);
		
		//Creates two events, one staring and one closing.
		es.addEvent(new StartSim(es, ss, s, sv, f));
		es.addEvent(new Closing(ss.getCloseTime(),s,sv));
		
		Simulator sim = new Simulator(es, s, v);
		sim.Run();

	}

}
