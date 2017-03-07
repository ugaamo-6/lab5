
package simulator;
import state.SalongState;

public abstract class View {
	SalongState ss = new SalongState();
	public void beginInfoPrint(){	
	}
	
	public void endInfoPrint() {
		System.out.println("Closed.");
		
	}
	
	public abstract void runningInfoPrint();
}
