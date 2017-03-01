package simulator;

import event.StartSim;
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
		sv.beginInfoPrint();
		while (s.running()) {
			Event currentEvent = es.nextEvent();
			currentEvent.execute();
		}
		sv.endInfoPrint();
			
	}


	public static void main(String args[]){
		EventStore es = new EventStore();
		State s = new State();
		SalongView sv = new SalongView();
		SalongState ss = new SalongState();
		FIFO f = new FIFO(es, ss, s, sv);
		Simulator sim = new Simulator(es, s, sv, ss, f);
		sim.Run();
	}

}
