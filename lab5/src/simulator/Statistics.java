package simulator;
/**
 * Statistics keep all information necessary to calculate average time, keep track of total customers and so on
 *  
 * 
 * @author Gustav Mattsson (ugaamo-6) 
 * */
public class Statistics {
	
	//all variables
	private static int totalCust = 0;
	private static int totalQcust = 0;
	private static double totalTime = 0;
	private static double averageTime = (totalTime)/(totalCust);
	private static int leave = 0;
	private static double totalQtime = 0;
	private static int max_queue = 0;
	private static int totalDiss = 0;
	private static double finalCustTime = 0;
	private static double idleTime = 0;
	private static boolean going = false;
	
	//addition methods
	 /** 
	 * @param addQCust adds the customers that have been added to the queue
	 */
	public void addQcust(){
		totalQcust++;
	}
	/** 
	 * @param custAddTime adds the customers waiting time, this helps out calculate the average queue time.
	 */
	public void custStatAddTime(double waitTime){
		totalTime += waitTime;
	}
	/** 
	 * @param custCountAdd adds +1 the total amount of customers that have been cut in 1 day
	 */
	public void custCountAdd(){
		totalCust++;
	}
	/** 
	 * @param addLeave adds +1 to the total amount of customers who entered the store but didn't get cut
	 */
	public void addLeave(){
		leave++;
		}
	/** 
	 * @param qTime adds the queue time for a customer that have been seated at a barber
	 */
	public void qTime(double waitTime){
		totalQtime += waitTime;
	}
	/** 
	 * @param maxSize increases the largest queue => how many people have been queuing at the most at once
	 */
	public void maxSize(int maximus){
		max_queue = maximus;
	}
	/** 
	 * @param addDiss adds +1 to the counter for unhappy customers
	 */
	public void addDiss(){
		totalDiss++;
	}
	/** 
	 * @param lastCustTime the time that the last customer were cut
	 */
	public void lastCustTime(double time){
		finalCustTime = time;
	}
	/** 
	 * @param goingTrue sets the going variable to true which means that we have seen an idle chair
	 */
	public void goingTrue(){
		going = true;
	}
	/** 
	 * @param goingFalse sets the going variable to false wich means that we have seen a non idle chair
	 */
	public void goingFalse(){
		going = false;
	}
	/** 
	 * @param addIdleTime adds the cutting chairs idle time to a "total" variable
	 */
	public void addIdletime(double diff){
		idleTime += diff;
	}
	
//	public int gQcust(){
//		return totalQcust;
//	}
	/**
	 * @param getCust returns the value of totalCust which counts the total amount of cut customers.
	 * */
	public double getCust(){
		return totalCust;
	}
	/**
	 * @param getTime returns the value of totalTime which returns the time a customer has waitet in queue
	 * */
	public double getTime(){
		return totalTime;
	}
	/**
	 * @param getAvgTime returns the average time a customer has waited in queue (totalTime/totalCust).
	 * */
	public double getAvgTime(){
		return averageTime;
	}
	/**
	 * @param getLeave returns the value of leave which counts the amount of customers that entered but didn't get cut.
	 * */
	public int getLeave(){
		return leave;
	}
	/**
	 * @param getQtime returns the value of totalQtime which keeps track of how long the customers have queued in total.
	 * */
	public double getQtime(){
		return totalQtime;
	}
	/**
	 * @param getMax returns the value of max_queue that contains the size of the largest queue of the day
	 * */
	public int getMax(){
		return max_queue;
	}
	/**
	 * @param getDiss returns the value of totalDiss which counts the amount of dissatisfied customers.
	 * */	
	public int getDiss(){
		return totalDiss;
	}
	/**
	 * @param getLast returns the value of finalCustTime that contains the time that the last customer where cut.
	 * */
	public double getLast(){
		return finalCustTime;
	}
	/**
	 * @param getGoing returns if going is true or false.
	 * */
	public boolean getGoing(){
		return going;
	}
	/**
	 * @param getIdle returns the value of idleTime which contains the time cutting chairs has been idle.
	 * */
	public double getIdle(){
		return idleTime;
	}
}
