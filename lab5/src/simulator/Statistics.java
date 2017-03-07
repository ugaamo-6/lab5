package simulator;
public class Statistics {
	
	//all variables
	static int totalCust = 0;
	static int totalQcust = 0;
	static double totalTime = 0;
	static double averageTime = (totalTime)/(totalCust);
	static int leave = 0;
	static double totalQtime = 0;
	static int max_queue = 0;
	static int totalDiss = 0;
	static double finalCustTime = 0;
	static double temp1 = 0; //when it gets empty
	static double temp2 = 0; //when it gets full
	static double idleTime = 0;
	static boolean going = false;
	
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
	public void setTime1(double time){
		temp1 = time;
	}
	public void setTime2(double time){
		temp2 = time;
	}
	public void goingTrue(){
		going = true;
	}
	public void goingFalse(){
		going = false;
	}
	public void idleCalc(){
		double timeDiff = temp1 - temp2;
		idleTime += timeDiff;
		going = false;
	}
	public void addIdletime(double diff){
		idleTime += diff;
	}
	
	public int gQcust(){
		return totalQcust;
	}
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
