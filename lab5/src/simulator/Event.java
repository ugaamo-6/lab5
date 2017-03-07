package simulator;

abstract public class Event{
	
	public double time;
	
	public abstract void execute();
	
	public abstract double getTime();
		
}
