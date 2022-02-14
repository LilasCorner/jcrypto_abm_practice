/**
 * 
 */
package jcrypto;

import java.util.Random;

import cern.jet.random.Uniform;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;

/**
 * @author Lilo
 *
 */
public class Coins {
	
	private String name; //bitcoin, ethereum, cardano etc
	private double value; // current value as calculated 
	private ContinuousSpace<Object> space; 
	private Grid<Object> grid;
	private Random rnd = new Random();
	
	private double MaxPriceIncrease = 1.05;
	private double MinPriceIncrease = 0.95;
	
	private Uniform unigen = RandomHelper.createUniform(MinPriceIncrease, MaxPriceIncrease);
	
	//default constructor
	public Coins(String name, ContinuousSpace < Object > space , Grid < Object > grid, double value){
		this.space = space;
		this.grid = grid;
		this.name = name;
		this.value = value;
	}

	//returns this coin object
	public Coins getCoin() {
		return this;
	}
	
	public String getName() {
		return name;
	}
	
	//returns current price of coin in double
	public double getPrice() {
		return value;
	}
	
	public void setPrice(double newPrice) {
		value = newPrice;
	}
	
	//every tick, coin value goes up/down dependent on temp formula
	@ScheduledMethod(start=1, interval=1)
	public void step() {
		System.out.println(this.value);

		age();

		dieCheck(); 
	}
	
	
	//coin value calculated as time passes
	public void age() {	
        
		
		double ri = unigen.nextDouble();
		double coinPrice = this.value;
		double oldPrice  = this.value;
		int smallScale = 0;
		
		NdPoint myPoint = space.getLocation (this);
		
		//ri = ((ri * .1)-.05)+1;
		 
		
		coinPrice = coinPrice * ri; 
		
		smallScale = (int)( oldPrice - coinPrice )/10; //scale down # to show on grid
		
		//move coin vertically based on new price, X axis static
		space.moveByDisplacement(this, 1, smallScale); //x, y displacement
		myPoint = space.getLocation(this);
		grid.moveTo(this, (int)myPoint.getX(), (int)myPoint.getY());
		
		
		setPrice(coinPrice);
		
	}
	
	
	//check if coin price is <=0 to remove it 
	public void dieCheck() {

		if ((int)getPrice() <= 0) {
//			schedule.executeEndActions();
			System.out.println("DIE");
            RunEnvironment.getInstance().endRun();
		}
		
		
	}


}
