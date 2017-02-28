package state;
/**
 * This class defines the variables that a customer needs
 * 
 * @author Gustav Mattsson (ugaamo-6)
 * @param id all id:s are unique because we add the counter +1 for every new created customer
 * @param happy is declared wheter the customer is happy after it's haircut
 * 
 * */
public class Customer {
	
	int id_counter = 0;
	
	private int id;
	public boolean happy;

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
