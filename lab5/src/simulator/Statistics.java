package simulator;

public class Statistics {
	
	//all variables
	static double totalCust = 0;
	static double totalTime = 0;
	static double averageTime = (totalTime)/(totalCust);
	static int leave = 0;
	static double totalQtime = 0;
	static int max_queue = 0;
	static int totalDiss = 0;
	static double finalCustTime = 0;
	static double temp1 = 0; //when it gets full
	static double temp2 = 0; //when it gets empty
	static double idleTime = 0;
	static boolean going = false;
	
	//addition methods
	public void custStatAddTime(double waitTime){
		totalTime += waitTime;
	}
	public void custCountAdd(){
		totalCust++;
	}
	public void addLeave(){
		leave++;
		}
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
	public void idleCalc(){
		double timeDiff = temp1 - temp2;
		idleTime += timeDiff;
		going = false;
		System.out.println("----- "+timeDiff);
	}
	
	//get... methods
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
