package lab5;

public class Customer {
	
	int id_counter = 0;
	
	private int id;
	boolean happy;
	
	public Customer(){
		id = id_counter + 1;
		happy = true;
		id_counter++;
	}

	public int get_id(){
		return id;
	}
	public boolean get_happy(){
		return happy;
	}
}
