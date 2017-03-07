package simulator;

abstract public class Event{
	
	public double time;
	
	public abstract void execute();
	
	public abstract double getTime();
	
	public abstract String toString();
	
	public abstract int getCustomerID(); 
		
}
