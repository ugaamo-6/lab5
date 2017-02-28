package simulator;

public class State {
	boolean running = false;
	
	public void start(){
	 	running = true; 
	}
	 	
	public void stop(){
		running = false;
	}
		
	public boolean running(){
		return running;
	}
}
