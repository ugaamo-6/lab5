package event;
import java.text.DecimalFormat;
import java.util.Observable;

import hairdresser.SalongView;
import simulator.EventStore;
import simulator.State;
import state.SalongState;
import simulator.Statistics;
import state.FIFO;
public class EventPrint extends Observable{
	
	private FIFO f;
	private Statistics s = new Statistics();
	private SalongState ss;
	private EventStore eventStore;

	private String name;
	private int ID;
	
	public EventPrint(String name, int ID, EventStore es, SalongState ss, FIFO f){
		this.eventStore = es;
		this.ss = ss;
		this.f = f;
		this.name = name;
		this.ID = ID;
		//System.out.format(format, args)
		//"%20a%20b%20c%20d%20e%20f%20g%20h%20i", 
		
		System.out.format("%-5.2f %-10s %-10d %-10d %-10.2f %-7d %-7d  %-7d %-10d %-10.2f \n",
				es.getTime(),                //eventStore tid
				name,                        //name
				ID,                          //customer ID
				ss.getFreeChairs(),      	 //fria klippstolar
				s.getQtime(),          		 //Hur länga alla har suttit i kö
				f.queueSize(),        		 //fifo köns storlek
				(int)s.getCust(),            //totala antalet kunder
				s.getLeave(),                //hur många som är förlorade
				s.getDiss(),				 //hur många som blev missnöjda.
				s.getIdle()
				);
	}
	
	private void toStringEvent()
	{
		
	}
	
	
	/**To string metod*/
	private void printingTask(){ //ANVÃ„NDS Inte nÃ¥gonstans
		System.out.format("%20a%20b%20c%20d%20e%20f%20g%20h%20i", eventStore.getTime(), name, ID, 
		ss.getFreeChairs(), s.getIdle(), f.queueSize(), s.getCust(), s.getLeave(), s.getDiss());
	}
	
	
	
	
	
}
