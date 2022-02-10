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
	
	
	//default constructor
	public Coins(String name, ContinuousSpace < Object > space , Grid < Object > grid, double value){
		this.space = space;
		this.grid = grid;
		this.name = name;
		this.value = value;
		this.age = 0;
	}

	
	
	//every tick, coin value goes up/down dependent on temp formula
	@ScheduledMethod(start=1, interval=1)
	public void step() {
		age();
	}
	
	
	//coin value calculated as time passes
	public void age() {	
		
		double ri = RandomHelper.nextDoubleFromTo(0, 1);
		double smallScale = 0;
		
		GridPoint pt = grid.getLocation (this);
		
		ri = (ri/.01) - .05; //TODO replace hardcoded with vars
		this.value *= ri; 
		age++;
		
		smallScale = this.value/100; //scale down # to show on grid
		
		//move coin vertically based on new price, X axis static
		grid.moveTo(this, (int)pt.getX() , (int)smallScale);
	
	}
	


}
