package simulator;

import simulator.Statistics;

public class State {
	Statistics s = new Statistics();
	boolean running = false;
	boolean open = false;
	
	public void start(){
		
	 	running = true; 
	 	open = true;
	}
	 	
	public void stop(){
		s.returnStats();
		running = false;
	}
	
	public void closing(){
		open = false;
	}
	
	public boolean opened(){
		return open;
	}
		
	public boolean running(){
		return running;
	}
}
