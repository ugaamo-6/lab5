package state;

import simulator.Statistics;
import simulator.EventStore;
import random.*;
import state.FIFO;

/**
 * This class keeps track of the data for the salon.
 * 
 * @author Gustav Mattsson (ugaamo-6)
 * @author Johan Bråtendal
 * @author Jonas Jarnhäll Sjöman
 * 
 * 
 * 
 * */

public class SalongState {
		
	private Statistics stat = new Statistics();
	private EventStore es;
	
	private double closeTime = 8.0;
	private double haircutMinTime = 0.8;
	private double haircutMaxTime = 1.2;
	private double returnMinTime = 2.0;
	private double returnMaxTime = 3.0;
	private final int maxWaitInQueue = 4;
	private int freeChairs = 3;
	private final int totalChairs = 3;
	private double percentageReturn=0.25; 
	private double lambda = 3.0;
	private long seed = 1116; //System.currentTimeMillis();
	
	private static double oldChairTime = 0;
	
	private UniformRandomStream returntimeUniRand = new UniformRandomStream(returnMinTime, returnMaxTime, seed);
	private UniformRandomStream pRand = new UniformRandomStream(0,1, seed);
	private ExponentialRandomStream expRand = new ExponentialRandomStream(lambda, seed);
	private UniformRandomStream haircuttimeUniRand = new UniformRandomStream(haircutMinTime, haircutMaxTime, seed);

	/**
	 * The constructor of this class.
	 * @param es, Event Store.
	 */
	public SalongState(EventStore es){
		this.es = es;
	}
	
	/** 
	 * returns the maximum number of customers i queue.
	 * 
	 * @return maxWaitInQueue maximum number of people in queue.
	 * */
	public int maxWaitInQueue() {
		return maxWaitInQueue;
	}
	
	/**
	 * 
	 * A customer left hairdress chair.
	 */
	public void chairGotFree() {
		freeChairs++;
	}
	/**
	 * 
	 * A customer sits down in a hairdress chair.
	 */
	public void chairGotBusy() {
		freeChairs--;
	
	}
	
	/**
	 * Calculate how long the idle-time has been.
	 */
	public void idleCounter(){
		double diff = es.getTime() - oldChairTime;
		for(int i = 1; i <= getFreeChairs(); i++){
			stat.addIdletime(diff);
		} 
		oldChairTime = es.getTime();
		
	}
	/**
	 * Return the number of free hairdress seats.
	 *@return Number of hairdress chairs that is not busy.
	 */
	public int getFreeChairs() {
		return freeChairs;
	}
	
	/**
	 * returns the total number of hairdress chairs.
	 * @return Total number of hairdress chairs in the saloon.
	 */
	public int totalChairs() {
		return totalChairs;
	}

	/**
	 * Returns Lambda used in Exponential random method.
	 * @return Lambda.
	 */
	public double getLambda(){
		return lambda;
	}
	/**
	 * Returns the seed used in random methods.
	 * @return Seed.
	 */
	public long getSeed() {
		return seed;
	}
	
	/**
	 * Returns the maximum amount of time a customer can be cut.
	 * @return Maximum haircut time.
	 */
	public double getHMax(){
		return haircutMaxTime;
	}
	
	/**
	 * Returns the minimum amount of time a customer can be cut.
	 * @return Minimum haircut time.
	 */
	public double getHMin(){
		return haircutMinTime;
	}
	
	/**
	 * Returns the maximum time it can take for a customer to returns.
	 * @return Maximum returning time.
	 */
	public double getRetMax(){
		return returnMaxTime;
	}
	
	/**
	 * Returns the maximum time it can take for a customer to returns.
	 * @return Minimum returning time.
	 */
	public double getRetMin(){
		return returnMinTime;
	}

	/**
	 * Returns the time it takes to cut hair for customer.
	 * @return Haircut time.
	 */
	public double haircutTime(){		
		double rand = haircuttimeUniRand.next();
		stat.custStatAddTime(rand);
		return rand;
	}
	
	/**
	 * The time it takes for a customer to return.
	 * @return Time to return.
	 */
	public double randReturn() {
		double rand = pRand.next();
		return rand;
	}
	
	/**
	 * Returns the chance of getting dissatisfied and returning. Double between 0-1. Ex: 0.2 = 20% chacne.
	 * @return Chance of return. 
	 */
	public double percentageReturn() {
		return percentageReturn;
	}
	
	/**
	 * Random time to return for a returning customer.
	 * @return Random time of return.
	 */
	public double randReturnTime(){
		double rand = returntimeUniRand.next();
		return rand;
	}
	
	/**
	 * Generates a random time for the next customer to arrive.
	 * @return Random time for next customer to arrive.
	 */
	public double nextCustTime(){
		double rand = expRand.next();
		return rand;
	}
	
	/**
	 * Returns the closing time of the salon.
	 * @return Closing time of the salon.
	 */
	public double getCloseTime() {
		return closeTime;
	}

}
