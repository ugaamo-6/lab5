package state;

import simulator.Statistics;
import random.*;

public class SalongState {
		
	private Statistics s = new Statistics();
	private double closeTime = 7.0;
	
	private double haircutMinTime = 1.0;
	private double haircutMaxTime = 2.0;
	private double returnMinTime = 1.0;
	private double returnMaxTime = 2.0;
	private final int maxWaitInQueue = 2;
	private int freeChairs = 2;
	private final int totalChairs = 2;
	private double percentageReturn=0.5; 
	private double lambda = 1.2;
	private long seed = 1116; //System.currentTimeMillis();
	
	
	
	private UniformRandomStream returntimeUniRand = new UniformRandomStream(returnMinTime, returnMaxTime, seed);
	private ExponentialRandomStream expRand = new ExponentialRandomStream(lambda, seed);
	private UniformRandomStream pRand = new UniformRandomStream(0,1);
	private UniformRandomStream haircuttimeUniRand = new UniformRandomStream(haircutMinTime, haircutMaxTime, seed);
	
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
	
	public int getFreeChairs() {
		return freeChairs;
	}
	
	public int totalChairs() {
		return totalChairs;
	}

	public double getLambda(){
		return lambda;
	}
	
	public long getSeed() {
		return seed;
	}
	
	public double getHMax(){
		return haircutMaxTime;
	}
	public double getHMin(){
		return haircutMinTime;
	}
	public double getRetMax(){
		return returnMaxTime;
	}
	public double getRetMin(){
		return returnMinTime;
	}

	public double haircutTime(){
		
		double rand = haircuttimeUniRand.next();
//		System.out.println("----- "+rand);
		s.custStatAddTime(rand);
		return rand;
	}
	
	public double randReturn() {
		
		double rand = pRand.next();
//		System.out.println("----- "+rand);
		return rand;
	}
	
	
	public double percentageReturn() {
		return percentageReturn;
	}
	
	public double returnTime(){
		
		double rand = returntimeUniRand.next();
		return rand;
		
	}
	
	public double nextCustTime(){//F� KOLL P� DENNA.
		double rand = expRand.next();
		return rand;
	}
	
	public double getCloseTime() {
		return closeTime;
	}

	


}
