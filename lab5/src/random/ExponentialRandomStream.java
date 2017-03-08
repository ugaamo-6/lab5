
package random;

import java.util.Random;

public class ExponentialRandomStream {
	
	private Random rand;
	private double lambda;
	  
	/**
	 * Construcor. A variable get Random as an object, which van be called to get a random number(Since there is a seed, this variable will not be random, but follow a "random" sequence.
	 * @param lambda
	 * @param seed
	 
	 */
	public ExponentialRandomStream(double lambda, long seed) {
	  	rand = new Random(seed);
	  	this.lambda = lambda;
	}
	  
	/**
	 * Construcor. A variable get Random as an object, which van be called to get a random number
	 * @param lambda
	 * 
	 
	 */
	public ExponentialRandomStream(double lambda) {
		rand = new Random();
	    this.lambda = lambda;
	}
	  /**
	   * Returns next exponential random stream number(If seed is used, there is a sequence)
	   * @return Next exponantial "random" number.
	   */
	public double next() {
		return -Math.log(rand.nextDouble())/lambda;
	}
}
