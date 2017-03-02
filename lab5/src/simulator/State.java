package simulator;

public class State {
	boolean running = false;
	boolean open = false;
	
	public void start(){
		
	 	running = true; 
	 	open = true;
	}
	 	
	public void stop(){
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
