package state;

import java.util.Observable;
import java.util.Observer;
import state.FIFO;
import simulator.EventStore;

public class SalongState implements Observer{
	FIFO f = new FIFO();
	EventStore e = new EventStore();
	public static final int hMax = 4;
	public static final int hMin = 2;
	public int freeChairs = 5;
	
	
	
	public SalongState(){
		
	}
	public Object getFirst(){
		return f.getFirst();
	}
	public boolean isFull(){
		return f.checkFull();
	}
	public int currentTime(){
		return e.currentTime;
	}
	public int haircutTime(){
		return 1; //(h_max + h_min)/2
	}
	
	@Override
	public void update(Observable FIFI, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
