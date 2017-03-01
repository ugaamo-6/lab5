package state;

import java.util.Observable;
import java.util.Observer;

import random.*;
import state.FIFO;
import simulator.EventStore;

public class SalongState implements Observer{
	 
	
	private double haircutMinTime = 0.5;
	private double haircutMaxTime = 1;
	private double returnMinTime = 0.5;
	private double returnMaxTime = 6;
	
	private double lambda = 4;
	private int seed = 400;//Denna ska vara satt efter "tid"?
	
	private int percentageLeave = 20; //skrivs i heltal, ett nummer mellan 0-100
	
	private UniformRandomStream uniRand;
	private ExponentialRandomStream expRand;
	private FIFO f;
	
	EventStore e = new EventStore();
	public static int freeChairs = 5;
	
	
	
	public SalongState(){
		
	}
	
	public void chairGotFree() {
		freeChairs--;
	}
	
	public int freeChairs() {
		return freeChairs;
	}
	
	public Object getFirst(){
		return f.getFirst();
	}
	
	public boolean isFull(){
		return f.checkFull();
	}
	
	public double currentTime(){
		return e.getTime();
	}

	public double haircutTime(){
		uniRand = new UniformRandomStream(haircutMinTime, haircutMaxTime);
		double rand = uniRand.next();
		return rand;
	}
	
	public double randLeave() {
		UniformRandomStream pRand = new UniformRandomStream(0,percentageLeave);
		double rand = pRand.next()/100.0;
		return rand;
	}
	
	public int percentageLeave() {
		return percentageLeave;
	}
	
	public double returnTime(){
		uniRand = new UniformRandomStream(returnMinTime, returnMaxTime);
		double rand = uniRand.next();
		return rand;
	}
	
	public double nextCustTime(){
		expRand = new ExponentialRandomStream(lambda, seed);
		double rand = expRand.next();
		return rand;
	}
	
	
	
	@Override
	public void update(Observable FIFI, Object arg1) {
		// TODO Auto-generated method stub
	}


}
