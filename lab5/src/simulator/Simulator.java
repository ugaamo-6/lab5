package simulator;

public class Simulator {
	private EventStore es;
	private State s;
	private View v;
	
	public Simulator(EventStore es, State s, View v){
		this.es = es;
		this.s = s;
		this.v = v;
	}
	
	public void Run() {
		s.start();
		v.beginInfoPrint();
		while (s.running()) {
			Event currentEvent = es.nextEvent();
			currentEvent.execute();
		}
		v.endInfoPrint();
			
	}



public static void main(String args[]){
	
}
}