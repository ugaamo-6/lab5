package hairdresser;

import simulator.Event;
import simulator.EventStore;
import simulator.View;
import state.FIFO;
import state.SalongState;




import java.util.Observable;
import java.util.Observer;

public class SalongView extends View implements Observer {
	
	private SalongState salongState;
	private FIFO fifo;
	
	public SalongView(FIFO f, SalongState ss){
		this.fifo=f;
		this.salongState=ss;
		f.addObserver(this);
		
		runningInfoPrint();
	}
	
	private void runningInfoPrint() {
		System.out.println("Opened.");
		System.out.println("--- Information ---");
		System.out.println("Closing time of the day ..............: "+salongState.getCloseTime());
		System.out.println("Total number of chairs ...............: "+salongState.getFreeChairs());
		System.out.println("Maximum queue size ...................: "+salongState.maxWaitInQueue());
		System.out.println("Lambda (customers/timeunit entering)..: "+salongState.getLambda());
		System.out.println("hmin and hmax (cutting time interval) : "+salongState.getHMin()+", "+salongState.getHMax());
		System.out.println("dmin and dmax (return time interval) .: "+salongState.getRetMin()+", "+salongState.getRetMax());
		System.out.println("Risk dissatisfied returns: ...........: "+salongState.percentageReturn());
		System.out.println("Seed used in pseudo random generator .: "+salongState.getSeed());
		System.out.println("-------------------");
		System.out.println("Time " + " Event " + "     ID "+"        Idle "+"     TWait " + "     InQ"+
		"     Cut"+"      Lost"+"     Ret");
	}
	
	@Override
	public void update(Observable arg0, Object arg) 
	{
		
		System.out.println(fifo.getMessageString());
	}
}
