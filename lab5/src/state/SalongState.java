package state;

import java.util.Observable;
import java.util.Observer;
import state.FIFO;
import simulator.EventStore;

public class SalongState implements Observer{
	FIFO f = new FIFO();
	EventStore e = new EventStore();
	
	
	public SalongState(){
		
	}
	public Object getFirst(){
		return f.getFirst();
	}
	public boolean isFull(){
		return f.checkFull();
	}
	public int currentTime(){
		return e.time_counter;
	}
	public int haircitTime(){
		return 1;
	}
	
	@Override
	public void update(Observable FIFO, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
