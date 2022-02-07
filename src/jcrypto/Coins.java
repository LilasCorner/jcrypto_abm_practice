/**
 * 
 */
package jcrypto;

import java.util.Random;

import org.apache.commons.math3.distribution.NormalDistribution;
import static java.lang.Math.sqrt;
import static java.lang.Math.exp;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

/**
 * @author Lilo
 *
 */
public class Coins {
	
	private String name; //bitcoin, ethereum, cardano etc
	private double value; // current value as calculated 
	private double monthlyVal;
	private ContinuousSpace<Object> space; 
	private Grid<Object> grid;
	private boolean priceChange;
	private int age;

	
	
	//default constructor
	public Coins(){
		Random rand = new Random();
		this.space = space;
		this.grid = grid;
		this.value = 100 + (rand.nextDouble() * 1000); //rand from 100-1000
		this.monthlyVal = rand.nextDouble(); //rand from 0.0 - 1.0
		this.age = 0;
	}

	
	
	//every tick, coin value goes up/down dependent on temp formula
	@ScheduledMethod(start=1, interval=1)
	public void step() {
		age();
		//speculate();
	}
	
	
	//coin value calculated as time passes
	//temp formula will follow geometric brownian motion
	public void age() {	
		double mu = .05; // TODO hardcoded for now
		double sigma = .01; // will make adjustable later
		
		
		double periodizedMu = mu / 12;
        double periodizedSigma = sigma / Math.sqrt(12);
        
        value = value + (monthlyVal * age);
        NormalDistribution normalDistribution = new NormalDistribution(periodizedMu * (age + 1),
                periodizedSigma * sqrt(age + 1));
        
        double normInv = normalDistribution.inverseCumulativeProbability(6);
        value = value * exp(normInv);
        
        priceChange = true;
        age++;
	}
	
	//based on a coins history, speculation on if
	//coin good/bad investment calculated here
	public void speculate() {
		
	}

}
