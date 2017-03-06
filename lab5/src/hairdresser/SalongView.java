package hairdresser;

import simulator.View;
import state.FIFO;
import state.SalongState;
import java.util.Observable;
import java.util.Observer;

public class SalongView extends View implements Observer {
	
	SalongState ss;
	FIFO f;
	
	public SalongView(FIFO f){
		this.f=f;
		f.addObserver(this);
	}
	
	public void runningInfoPrint() {
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

	@Override
	public void update(Observable arg0, Object arg) {
		System.out.println(f.getMessageString());
		
	}
}
