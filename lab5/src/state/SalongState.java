package state;

import java.util.Observable;
import java.util.Observer;
import simulator.Statistics;
import random.*;
import state.FIFO;
import simulator.EventStore;

public class SalongState {
	
	private Statistics s = new Statistics();
	
	private double closeTime = 480.0;
	
	private double haircutMinTime = 30;
	private double haircutMaxTime = 60;
	private double returnMinTime = 30;
	private double returnMaxTime = 200;
	
	private double lambda = 60;
	private long seed = System.currentTimeMillis();
	
	private double percentageLeave = 0.5; //skrivs i heltal, ett nummer mellan 0-100
	
	private UniformRandomStream uniRand;
	private ExponentialRandomStream expRand;

	public static int freeChairs = 5;

	
	public SalongState(){

	}
	
	public void chairGotFree() {
		freeChairs++;
	}
	
	public void chairGotBusy() {
		freeChairs--;
	}
	
	public int freeChairs() {
		return freeChairs;
	}
	
//	public Object getFirst(){
//		return f.getFirst();
//	}
//	
//	public boolean isFull(){
//		return f.checkFull();
//	}
//	
//	public double currentTime(){
//		return e.getTime();
//	}

	public double haircutTime(){
		uniRand = new UniformRandomStream(haircutMinTime, haircutMaxTime);
		double rand = uniRand.next();
		return rand;
	}
	
	public double randReturn() {
		UniformRandomStream pRand = new UniformRandomStream(0,1);
		double rand = pRand.next();
		return rand;
	}
	
	
	public double percentageLeave() {
		return percentageLeave;
	}
	
	public double returnTime(){
		uniRand = new UniformRandomStream(returnMinTime, returnMaxTime, seed);
		double rand = uniRand.next();
		s.custStatTime(rand);
		return rand;
	}
	
	public double nextCustTime(){
		expRand = new ExponentialRandomStream(lambda, seed);
		double rand = expRand.next();
		return rand;
	}
	
	public double getCloseTime() {
		return closeTime;
	}
	


}
