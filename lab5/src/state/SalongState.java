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
	private final int maxWaitInQueue = 10;
	private int freeChairs = 5;
	private final int totalChairs = 5;
	
	private double lambda = 60;
	private long seed = System.currentTimeMillis();
	
	private double percentageReturn=0.1; //skrivs i heltal, ett nummer mellan 0-100
	
	private UniformRandomStream uniRand;
	private ExponentialRandomStream expRand;

	
	public SalongState(/*double percentageReturn*/){
		/*this.percentageReturn=percentageReturn;*/
	}
	
	public int maxWaitInQueue() {
		return maxWaitInQueue;
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
	
	public int totalChairs() {
		return totalChairs;
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
//		System.out.println("----- "+rand);
		s.custStatAddTime(rand);
		return rand;
	}
	
	public double randReturn() {
		UniformRandomStream pRand = new UniformRandomStream(0,1);
		double rand = pRand.next();
//		System.out.println("----- "+rand);
		return rand;
	}
	
	
	public double percentageReturn() {
		return percentageReturn;
	}
	
	public double returnTime(){
		uniRand = new UniformRandomStream(returnMinTime, returnMaxTime, seed);
		double rand = uniRand.next();
		return rand;
		
	}
	
	public double nextCustTime(){//FÅ KOLL PÅ DENNA.
		expRand = new ExponentialRandomStream(lambda, seed);
		double rand = expRand.next();
		return rand;
	}
	
	public double getCloseTime() {
		return closeTime;
	}
	


}
