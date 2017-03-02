package hairdresser;

import simulator.View;
import state.FIFO;

import java.util.Observable;
import java.util.Observer;

public class SalongView extends View implements Observer {

	FIFO f;
	
	public SalongView(FIFO f){
		this.f=f;
		f.addObserver(this);
	}
	
	public void runningInfoPrint() {
		
	}

	@Override
	public void update(Observable arg0, Object arg) {
		System.out.println(f.getMessageString());
		
	}
}
