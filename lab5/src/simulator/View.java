package simulator;

import simulator.Statistics;

public abstract class View {
	
	private Statistics s = new Statistics();
	
	public void beginInfoPrint(){	
		System.out.println("Opened.");
	}
	
	public void endInfoPrint() {
		System.out.println("Closed.");
		
	}
	
	public abstract void runningInfoPrint();
}
