package simulator;

public class Statistics {
	//all variables
	static double totalCust = 0;
	static double totalTime = 0;
	static double averageTime = (totalTime)/(totalCust);
	static int leave = 0;
	static double totalQtime = 0;
	
	//addition methods
	public void custStatAddTime(double waitTime){
		totalTime += waitTime;
	}
	public void custCountAdd(){
		totalCust++;
	}
	public void addLeave(){
		leave += 1;
		}
	public void qTime(double waitTime){
		totalQtime += waitTime;
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
	

	public void getStats(){
		System.out.println("Total customers: "+getCust());
		System.out.println("Average time/customer "+getAvgTime());
		System.out.println("Total leave: "+leave);
		
	}







}
