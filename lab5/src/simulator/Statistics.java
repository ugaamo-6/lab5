package simulator;
import state.FIFO;
public class Statistics {
	
	//all variables
	static double totalCust = 0;
	static double totalTime = 0;
	static double averageTime = (totalTime)/(totalCust);
	static int leave = 0;
	static double totalQtime = 0;
	static int max_queue = 0;
	static int totalDiss = 0;
	
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

}
