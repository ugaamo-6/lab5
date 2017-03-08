
package random;

import java.util.Random;

/**
 * This class contains uniform random stream.
 * 
 * @author Johan Bråtendal
 * @author Gustav Mattsson
 * @author Jonas Jarnhäll Sjöman
 */
public class UniformRandomStream {

	private Random rand;
	private double lower, width;
	
	/**
	 * Construcor. A variable get Random as an object, which van be called to get a random number(Since there is a seed, this variable will not be random, but follow a "random" sequence.
	 * @param lower, lower number
	 * @param upper, upper number
	 * @param seed
	 */
	public UniformRandomStream(double lower, double upper, long seed) {
		rand = new Random(seed);
		this.lower = lower;
		this.width = upper-lower;
	}
	
	/**
	 * Constructor. A variable get Random as an object, which can be called to get a random number.
	 * @param lower, lower value
	 * @param upper, upper value
	 */
	public UniformRandomStream(double lower, double upper) {
		rand = new Random();
	    this.lower = lower;
	    this.width = upper-lower;
	}

	/**
	 * Returns och next random number.(If seed is used, in the sequence)
	 * @return next random number.
	 */
	public double next() {
	    return lower+rand.nextDouble()*width;
	}
}
