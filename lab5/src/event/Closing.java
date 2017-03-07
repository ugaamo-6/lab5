package event;

import java.util.Observable;
import java.util.Observer;

import hairdresser.SalongView;
import simulator.Event;
import simulator.EventStore;
import simulator.State;
import state.FIFO;
import state.SalongState;
import simulator.Statistics;

public class Closing extends Event implements Observer{
	
	
	static Statistics stat = new Statistics();
	private State state;
	private EventStore es;
	private SalongState ss;
//	private SalongView sv;
//	private FIFO f;
//	private EventPrint ep;
	
	private int C;
	private String namn = "CustArrives";
	
	
	public Closing(double time, EventStore es, SalongState ss, State s, SalongView sv, FIFO f) {
		this.time=time;
		this.es=es;
		this.ss=ss;
		this.state=s;
//		this.sv=sv;
//		this.f=f;
		f.addObserver(this);
	}

	public void execute() {
		state.closing();
		state.closing();//duplicate?
//		System.out.println(es.getTime()+"   CLOSING-------");
		//f.messageString("----------The store is closing.---------");
	}
	
	public double getTime() {
		return time;
	}

	public String toString(){
		String toString = es.getTime() +"   CLOSING-------"; 
		return toString;
	}
	public int getCustomerID(){
		return C;
	}
	
	
	
	public void update(Observable arg0, Object arg) {
		if (ss.totalChairs() == ss.getFreeChairs() && !state.opened()) {
			/*if (f.isEmpty()){
				es.allEventDone(true);
			}*/
			
//			f.getFirst();
		}
	}

}
