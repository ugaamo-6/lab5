
package simulator;
import state.SalongState;

public abstract class View {
	SalongState ss = new SalongState();
	public void beginInfoPrint(){	
		System.out.println("Opened.");
		System.out.println("--- Information ---");
		System.out.println("Closing time of the day ..............: "+ss.getCloseTime());
		System.out.println("Total number of chairs ...............: "+ss.getFreeChairs());
		System.out.println("Maximum queue size ...................: "+ss.maxWaitInQueue());
		System.out.println("Lambda (customers/timeunit entering)..: "+ss.getLambda());
		System.out.println("hmin and hmax (cutting time interval) : "+ss.getHMin()+", "+ss.getHMax());
		System.out.println("dmin and dmax (return time interval) .: "+ss.getRetMin()+", "+ss.getRetMax());
		System.out.println("Risk dissatisfied returns: ...........: "+ss.percentageReturn());
		System.out.println("Seed used in pseudo random generator .: "+ss.getSeed());
		System.out.println("-------------------");
		System.out.println("Time " + " Name " + "      ID "+"        Idle "+"     TWait " + "     InQ"+
		"     Cut"+"      Lost"+"     Ret");
	}
	
	public void endInfoPrint() {
		System.out.println("Closed.");
		
	}
	
	public abstract void runningInfoPrint();
}
