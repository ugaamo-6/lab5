package event;

import simulator.Event;
import simulator.State;
import simulator.Statistics;
import simulator.EventStore;
import state.SalongState;
import state.SalongView;
import state.FIFO;

public class StopSim extends Event {

	State s;
	private State state;
	
	//To be able to create a SalongView object.
	Statistics stat = new Statistics();
	EventStore es ;
	SalongState ss = new SalongState(es);
	FIFO f = new FIFO(es, ss);
	SalongView sv = new SalongView(f, ss, es);
	
	private int C;
	
	
	public StopSim(State s){
		this.state=s;
	}
	public int getCustomerID(){
		return C;
	}
	
	public void execute() {
		if(stat.getGoing()){
			stat.setTime1(getTime());
			stat.idleCalc();
		}
		sv.summaryInfoPrint();
		state.stop();
		
	}
	public double getTime() {
		return time;
	}
}
