package hairdresser;

import simulator.EventStore;
import simulator.Statistics;
import simulator.View;
import state.FIFO;
import state.SalongState;




import java.util.Observable;
import java.util.Observer;

public class SalongView extends View implements Observer {
	
	private SalongState salongState;
	private FIFO fifo;
	private EventStore eventStore;
	
	public SalongView(FIFO f, SalongState ss, EventStore es){
		this.fifo=f;
		this.salongState=ss;
		this.eventStore=es;
		f.addObserver(this);
	}
	/*
	 * Text printed when simulator starts
	 */
	public void startInfoPrint(){
		System.out.println(eventStore.getTime() + "   START------");
	}
	
	/*
	 * Text printing when the simlutator i closing.
	 */
	public void closingInfoPrint(){
		System.out.println(eventStore.getTime() + "   CLOSING------");
	}
	
	/*
	 * Pints variable-information about the simulation
	 */
	public void variableInfoPrint() {
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
	
	/*
	 * Print a summary of the statistics.
	 */
	public void summaryInfoPrint() {
		Statistics stat = new Statistics();
		System.out.println("999,0 STOP----" + "\n" +"---- Some Statistics ----"
				 + "\n" +"Number of customers cut: ......: "+(int)stat.getCust() +"\n" +
				 "Average cutting time...........: "+(stat.getTime()/stat.getCust()) + "\n" +	"Average queueing time: ........: "+(stat.getQtime()/stat.getCust())
				 + "\n" + "Largest queue (max NumWaiting) : "+ stat.getMax()
				 + "\n" +	"Customers not cut (NumLost) ...: "+stat.getLeave()	 + "\n" +	"Dissatisfied customers: .......: " + stat.getDiss()
				 + "\n" +	"Time chairs were idle: ........: "+stat.getIdle() + "\n" + "-------------------------");
	}
	
	//Kommentera?
	@Override
	public void update(Observable arg0, Object arg) 
	{
		System.out.println(fifo.getMessageString());
	}
}
