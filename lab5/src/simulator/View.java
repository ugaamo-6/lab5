
package simulator;
import state.SalongState;
//Gör om till interface istället?
public abstract class View {
	private SalongState salonState = new SalongState();
	
	public void beginInfoPrint()
	{
		
	}
	
	public void endInfoPrint() {
		System.out.println("Closed.");	
	}
	
//	private abstract void runningInfoPrint();
}
