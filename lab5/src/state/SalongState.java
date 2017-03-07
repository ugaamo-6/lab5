package state;

import simulator.Statistics;
import simulator.EventStore;
import random.*;

public class SalongState {
		
	private Statistics stat = new Statistics();
	private EventStore es;
	
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
	private UniformRandomStream pRand = new UniformRandomStream(0,1, seed);
	private ExponentialRandomStream expRand = new ExponentialRandomStream(lambda, seed);
	private UniformRandomStream haircuttimeUniRand = new UniformRandomStream(haircutMinTime, haircutMaxTime, seed);

	
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
		if((freeChairs != 0) && !stat.getGoing()){
			stat.goingTrue();
			stat.setTime2(es.getTime());
		}
	}
	/**
	 * 
	 * A customer sits down in a hairdress chair.
	 */
	public void chairGotBusy() {
		freeChairs--;
		if((freeChairs == 0) && stat.getGoing()){
			stat.setTime1(es.getTime());
			stat.idleCalc();
			stat.goingFalse();
		}
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
