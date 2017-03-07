package simulator;

/**
 * Keep track of the general state of the simulation.
 * 
 * @author Johan Bråtendal
 * @author Gustav Mattsson
 * @author Jonas Jarnhäll Sjöman
 */
public class State {
	private boolean running = false; //simulation is running or not.
	private boolean open = false; //opens salon
	
	
	/**Start the simulation and opens the simulated store
	 * */
	public void start(){
	 	running = true; 
	 	open = true;
	}
	
	/**Stop the simulation
	 * */
	public void stop(){
		running = false;
	}
	/**stop the salon*/
	public void closing(){
		open = false;
	}
	/**Returns if salon is open or not.
	 * @return True if open, false if not.*/
	public boolean opened(){
		return open;
	}
	/**Check if simulation is running.
	 * @return true if running false if not.*/
	public boolean running(){
		return running;
	}
}
