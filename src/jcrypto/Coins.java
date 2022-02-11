/**
 * 
 */
package jcrypto;

import java.util.List;
import java.util.Random;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

/**
 * @author Lilo
 *
 */
public class Coins {
	
	private String name; //bitcoin, ethereum, cardano etc
	private double value; // current value as calculated 
	private ContinuousSpace<Object> space; 
	private Grid<Object> grid;
	private boolean priceChange;
	private int age;
	private Random rnd = new Random();
	
	//default constructor
	public Coins(String name, ContinuousSpace < Object > space , Grid < Object > grid, double value){
		this.space = space;
		this.grid = grid;
		this.name = name;
		this.value = value;
		this.age = 0;
	}

	
	public double getPrice() {
		return this.value;
	}
	
	
	//every tick, coin value goes up/down dependent on temp formula
	@ScheduledMethod(start=1, interval=1)
	public void step() {
		System.out.println(this.value);

		age();
	}
	
	
	//coin value calculated as time passes
	public void age() {	
        
		double ri = rnd.nextDouble();
		
		double smallScale = 0;
		
		GridPoint pt = grid.getLocation (this);
		
		ri = (ri*.01) - .05 + 1; //TODO replace hardcoded with vars
		
		System.out.println("Ri: " + ri);
		
		this.value *= ri; 
		age++;
		
		smallScale = this.value/100; //scale down # to show on grid
		
		//move coin vertically based on new price, X axis static
		//grid.moveByDisplacement(this, 0, (int)smallScale, 0);
		
	}
	


}
