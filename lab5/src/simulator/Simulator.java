//Kika på 6.8, har vi gjort rätt?
//Kika på 6.9, har vi gjort rätt?
//Använd private!!
//Kika på 6.10, har vi gjort rätt?
//Kika på 6.15, har vi gjort rätt?


package simulator;

import event.*;
import hairdresser.SalongView;
import state.*;

public class Simulator {
	private EventStore es;
	private State s;
	private SalongView sv;
	private SalongState ss;
	private FIFO f;

	
	
	public Simulator(EventStore es, State s, SalongView sv, SalongState ss, FIFO f){
		this.es = es;
		this.s = s;
		this.sv = sv;
		this.ss = ss;
		this.f=f;
	}
	
	public void Run() {
		s.start();
		es.addEvent(new StartSim(es, ss, s, sv, f));
		es.addEvent(new Closing(ss.getCloseTime() , es,ss,s,sv,f));
		sv.beginInfoPrint();
		while (s.running()) {
			Event currentEvent = es.nextEvent();
			currentEvent.execute();
		}
		sv.endInfoPrint();
			
	}


	public static void main(String args[]){
		
		State s = new State();
		EventStore es = new EventStore(s);
		SalongState ss = new SalongState();
		FIFO f = new FIFO(es, ss, s);
		SalongView sv = new SalongView(f);
		Simulator sim = new Simulator(es, s, sv, ss, f);
		sim.Run();
	}

}
