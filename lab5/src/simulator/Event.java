package simulator;

abstract public class Event {
	
	public int time;
	
	public abstract void execute();
	
	public abstract String toString();
	
}
