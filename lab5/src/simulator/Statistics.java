package simulator;
/**
 * Statistics keep all information necessary to calculate average time, keep track of total customers and so on
 *  
 * 
 * @author Gustav Mattsson (ugaamo-6)
 * 
 * @param addQCust adds the customers that have been added to the queue
 * @param custAddTime adds the customers waiting time, this helps ut calculate the average queue time.
 * @param custCountAdd adds +1 the total amount of customers that have been cut in 1 day
 * @param addLeave adds +1 to the total amount of customers who entered the store but didn't get cut
 * @param qTime adds the queue time for a customer that have been seated at a barber
 * @param maxSize increases the largest queue => how many people have been queuing at the most at once
 * @param addDiss adds +1 to the counter for unhappy customers
 * @param lastCustTime the time that the last customer were cut
 * @param goingTrue sets the going variable to true wich means that we have seen an idle chair
 * @param goingFalse sets the going variable to false wich means that we have seen a non idle chair
 * @param addIdleTime adds the cutting chairs idle time to a "total" variable
 * 
 * @param getMethods the get methods return the variables that might be useful to the statistics later.
 * 
 * 
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
	
	public void addQcust(){
		totalQcust++;
	}
	
	public void custStatAddTime(double waitTime){
		totalTime += waitTime;
	}
	public void custCountAdd(){
		totalCust++;
	}
	public void addLeave(){
		leave++;
		}
	/**Increases the total wait time with additional wait time.*/
	public void qTime(double waitTime){
		totalQtime += waitTime;
	}
	public void maxSize(int maximus){
		max_queue = maximus;
	}
	public void addDiss(){
		totalDiss++;
	}
	public void lastCustTime(double time){
		finalCustTime = time;
	}
	public void goingTrue(){
		going = true;
	}
	public void goingFalse(){
		going = false;
	}
	public void addIdletime(double diff){
		idleTime += diff;
	}
	
//	public int gQcust(){
//		return totalQcust;
//	}
	public double getCust(){
		return totalCust;
	}
	public double getTime(){
		return totalTime;
	}
	public double getAvgTime(){
		return averageTime;
	}
	public int getLeave(){
		return leave;
	}
	public double getQtime(){
		return totalQtime;
	}
	public int getMax(){
		return max_queue;
	}
	public int getDiss(){
		return totalDiss;
	}
	public double getLast(){
		return finalCustTime;
	}
	public boolean getGoing(){
		return going;
	}
	public double getIdle(){
		return idleTime;
	}
}
