package simulator;

public abstract class View {
	
	public void beginInfoPrint(){	
		System.out.println("Opened.");
	}
	
	public void endInfoPrint() {
		System.out.println("Closed.");
		
	}
	
	public abstract void runningInfoPrint();
}
