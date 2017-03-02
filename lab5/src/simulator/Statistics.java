package simulator;

public class Statistics {
	//all variables
	static double totalCust = 0;
	static double totalTime = 0;
	static double averageTime = (totalTime)/(totalCust);
	static int leave = 0;
	
	//addition methods
	public void custStatAddTime(double waitTime){
		totalTime += waitTime;
	}
	public void custCountAdd(){
		totalCust++;
	}
	public void calcAvg(){
	}
	public void addLeave(){
		leave += 1;
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
	

	public void getStats(){
		System.out.println("Total customers: "+getCust());
		System.out.println("Average time/customer "+getAvgTime());
		System.out.println("Total leave: "+leave);
		
	};







}
