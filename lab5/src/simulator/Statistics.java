package simulator;

public class Statistics {
	//average time
	double totalCust = 0; double totalTime = 0;
	public void custStatAddTime(double waitTime){
		totalTime += waitTime;
		System.out.println(totalTime);
	}
	public void custCountAdd(){
		totalCust++;
	}
	public double getAvgTime(){
		return totalTime / totalCust;
	}
	
	//Hur många som försvinner
	int leave = 0;
	public void addLeave(){
		leave += 1;
		System.out.println("---- "+leave);	}
	public int returnLeave(){
		return leave;
	}
	
	public void returnStats(){
		System.out.println(totalCust);
		System.out.println(returnLeave());
	}
}
