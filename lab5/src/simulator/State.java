package simulator;


public class State {
	private boolean running = false; //Butik kör
	private boolean open = false; //Butik öppen
	
	
	/**Butiken öppnar och är aktiv*/
	public void start(){
	 	running = true; 
	 	open = true;
	}
	
	/**Butik blir inaktiv*/
	public void stop(){
		running = false;
	}
	/**Butik stänger*/
	public void closing(){
		open = false;
	}
	/**Hämta värdet för open*/
	public boolean opened(){
		return open;
	}
	/**Hämta värdet för running*/
	public boolean running(){
		return running;
	}
}
