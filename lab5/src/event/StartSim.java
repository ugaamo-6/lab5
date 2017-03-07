package event;

import java.util.Observable;
import java.util.Observer;

import hairdresser.*;
import simulator.*;
import state.*;

public class StartSim extends Event {
	
	private EventPrint ep;
	private EventStore eventStore;
	private SalongState ss;
	private State s;
	private SalongView sv;
	private FIFO f;
	
	private int C;
	private String toString = "Start Simulation";

	
	public StartSim(EventStore es, SalongState ss, State s, SalongView sv, FIFO f){
		this.eventStore=es;
		this.ss=ss;
		this.s=s;
		this.sv=sv;
		this.f=f;
		time = 0.0;
	}
	
	public void execute() {		
		eventStore.setTime(0.0);

		s.start();

		
//		Ha inte printsatser h�r, anv�nd v�r view-klass.
		s.start();
		Event arrive = new CustArrives(time, eventStore, ss, s, sv, f);
		eventStore.addEvent(arrive);
		
		System.out.println(eventStore.getTime()+"   START----");
	}
	
	//Måste ha pga extend
	public double getTime() {
		return time;
	}
	

	
	//Används ej
	public int getCustomerID(){
		return C;
	}

//	@Override
	public String toString() {
		return toString;
	}
	
	



}
