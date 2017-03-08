package state;

import simulator.EventStore;
import simulator.Statistics;
import simulator.View;

import java.util.Observable;
import java.util.Observer;

/**
 * This class contains the view and the output from the program.
 * 
 * @author Gustav Mattsson (ugaamo-6)
 * @author Johan Bråtendal
 * @author Jonas Jarnhäll Sjöman
 * 
 * 
 * 
 * */
public class SalongView extends View implements Observer {
	
	private SalongState salongState;
	private FIFO fifo;
	private EventStore eventStore;
	
	/**
	 * Constructor of the class
	 * @param f, FIFO  queue
	 * @param ss, Salong State
	 * @param es, Event Store
	 */
	public SalongView(FIFO f, SalongState ss, EventStore es){
		this.fifo=f;
		this.salongState=ss;
		this.eventStore=es;
		f.addObserver(this);
	}
	/**
	 * Text printed when simulator starts
	 */
	public void startInfoPrint(){
		System.out.println(eventStore.getTime() + "   START------");
	}
	
	/**
	 * Text printing when the simlutator i closing.
	 */
	public void closingInfoPrint(){
		System.out.println(eventStore.getTime() + "   CLOSING------");
	}
	
	/**
	 *  Pints variable-information about the simulation
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
		System.out.println("Time " + " Event " + "     ID "+"        Idle "+"      TIdle"+"     TWait " + "     InQ"+
		"     Cut"+"      Lost"+"     Ret");
	}
	
	/**
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
	
	/**
	 * When this method is called the content is executed. In this case, print the input.
	 * 
	 * @param Observable
	 * @param null
	 * 
	 */
	public void update(Observable arg0, Object arg) 
	{
		System.out.println(fifo.getMessageString());
	}
}
