
package simulator;
import state.SalongState;
<<<<<<< HEAD
import simulator.EventStore;

public abstract class View {
	EventStore es;
	SalongState ss = new SalongState(es);
	public void beginInfoPrint(){	
	}
	
	public void endInfoPrint() {
		System.out.println("Closed.");
=======
//Gör om till interface istället?
public abstract class View {
	private SalongState salonState = new SalongState();
	
	public void beginInfoPrint()
	{
>>>>>>> branch 'master' of https://github.com/ugaamo-6/lab5.git
		
	}
	
	public void endInfoPrint() {
		System.out.println("Closed.");	
	}
	
//	private abstract void runningInfoPrint();
}
