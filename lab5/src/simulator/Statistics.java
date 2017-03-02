package simulator;

public class Statistics {
	//average time
	private int totalCust = 0; private double totalTime = 0; private double avgTime = totalTime/totalCust;
	public void custStatTime(double waitTime){
		totalCust ++;
		totalTime += waitTime;
	}
	public double getAvgTime(){
		return avgTime;
	}
	
	//Hur många som försvinner
	private int leave = 0;
	public void addLeave(){
		leave++;
	}
	
	
	
}
