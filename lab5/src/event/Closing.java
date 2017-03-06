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
	State s;
	EventStore es;
	SalongState ss;
	SalongView sv;
	FIFO f;
	EventPrint ep;
	
	private int C;
	private String namn = "CustArrives";
	public String getName(){
		return namn;
	}
	public int getCustomerID(){
		return C;
	}
	
	public Closing(double time, EventStore es, SalongState ss, State s, SalongView sv, FIFO f) {
		this.time=time;
		this.es=es;
		this.ss=ss;
		this.s=s;
		this.sv=sv;
		this.f=f;
		f.addObserver(this);
	}

	public void execute() {
		s.closing();
		s.closing();
		System.out.println(es.getTime()+"   CLOSING-------");
		//f.messageString("----------The store is closing.---------");
	}
	
	public double getTime() {
		return time;
	}

	public void update(Observable arg0, Object arg) {
		if (ss.totalChairs() == ss.getFreeChairs() && !s.opened()) {
			/*if (f.isEmpty()){
				es.allEventDone(true);
			}*/
			
//			f.getFirst();
		}
	}

}
