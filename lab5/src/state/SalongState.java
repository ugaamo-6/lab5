package state;

import java.util.Observable;
import java.util.Observer;
import state.FIFO;
import simulator.EventState;

public class SalongState implements Observer{
	FIFO f = new FIFO();
	EventState e = new EventState();
	
	
	public SalongState(){
		
	}
	public Object getFirst(){
		return f.getFirst();
	}
	public boolean isFull(){
		return f.checkFull();
	}
	public int currentTime(){
		return e.time;
	}
	public int haircitTime(){
		return 1;
	}
	public void start(){
		
	}
	public void stop(){
		
	}
	public void running(){
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void update(Observable FIFO, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
