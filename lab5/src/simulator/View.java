
package simulator;
import state.SalongState;
import simulator.EventStore;

public abstract class View {
	EventStore es;
	SalongState ss = new SalongState(es);
	public void beginInfoPrint(){	
	}
	
	public void endInfoPrint() {
		System.out.println("Closed.");
		
	}
	
	public abstract void runningInfoPrint();
}
