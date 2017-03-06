package event;
import java.text.DecimalFormat;

import hairdresser.SalongView;
import simulator.EventStore;
import simulator.State;
import state.SalongState;
import simulator.Statistics;
import state.FIFO;
public class EventPrint {
	
	FIFO f;
	Statistics s = new Statistics();
	SalongState ss;
	EventStore es;

	String name;
	int ID;
	
	public EventPrint(String name, int ID, EventStore es, SalongState ss, FIFO f){
		this.es = es;
		this.ss = ss;
		this.f = f;
		this.name = name;
		this.ID = ID;
		//System.out.format(format, args)
		//"%20a%20b%20c%20d%20e%20f%20g%20h%20i", 
		
		System.out.format("%-5.1f %-10s %-10d %-10d %-10.1f %-7d %-7d  %-7d %-10d\n",
				es.getTime(),                //a
				name,                        //b
				ID,                          //c
				ss.getFreeChairs(),      	 //d
				s.getQtime(),          		 //e
				f.queueSize(),        		 //f
				(int)s.getCust(),                 //g
				s.getLeave(),                 //h
				s.getDiss()
				);
	}
	
	public void printingTask(){
		System.out.format("%20a%20b%20c%20d%20e%20f%20g%20h%20i", es.getTime(), name, ID, 
		ss.getFreeChairs(), s.getIdle(), f.queueSize(), s.getCust(), s.getLeave(), s.getDiss());
	}
	
	
	
	
	
}
