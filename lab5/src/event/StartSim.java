package event;

import hairdresser.*;
import simulator.*;
import state.*;

public class StartSim extends Event {
	
	EventStore es;
	SalongState ss;
	State s;
	SalongView sv;
	FIFO f;
	
	private int C;
	private String namn = "Start Simulation";
	public String getName(){
		return namn;
	}
	public int getCustomerID(){
		return C;
	}
	
	public StartSim(EventStore es, SalongState ss, State s, SalongView sv, FIFO f){
		this.es=es;
		this.ss=ss;
		this.s=s;
		this.sv=sv;
		this.f=f;
		time = 0.0;
	}
	
	public void execute() {		
		es.setTime(0.0);

		s.start();

		
//		Ha inte printsatser här, använd vår view-klass.
		s.start();
		Event arrive = new CustArrives(time, es, ss, s, sv, f);
		es.addEvent(arrive);
	}
	
	public double getTime() {
		return time;
	}

//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
