/**
 * 
 */
package jcrypto;

import java.util.List;
import java.util.Random;

import jcrypto.Coins;
import repast.simphony.engine.watcher.Watch;
import repast.simphony.engine.watcher.WatcherTriggerSchedule;
import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.SimUtilities;

/**
 * @author Lilo

 *
 */

public class Investors {

	private double initMoney; //initial amt
	private double money; //max amt of cash a bro starts with
	//private double lossAllowance; //max amt bro willing to lose before removing investment/swapping to another coin
	private int cryptoValue; //current value of all investments, 
	private ContinuousSpace<Object> space; 
	private Grid<Object> grid;
	
	
	
	//default constructor
	public Investors() {
		Random rand = new Random();
		this.space = space;
		this.grid = grid;
		this.money = 100 + (rand.nextDouble() * 500); //rand from 100-1000
		this.initMoney = money;
	}
	
	
//	//basic strategy:
//	//if profiting, add more
//	//if losing, remove to minimize losses. 
//	//if money =0, die lol (context.remove)
//	@Watch(watcheeClassName = "jcrypto.Coins", watcheeFieldNames = "priceChange",
//			whenToTrigger = WatcherTriggerSchedule.IMMEDIATE)
//	public void invest() {
//		
//		Random rand = new Random();
//		double heldMoney = 1 + (rand.nextDouble() * money); //invest btwn 1-remaining money 
//		
//		  
//		
//		if(gain_loss(cryptoValue)) { //TODO how current coin value from coin agent?)) {
//			//if profitable, invest random amt more
//			 money -= heldMoney;
//			 cryptoValue += heldMoney; 
//		}
//		else {
//			//if loss, remove random amt and wait random amt of turns before investing again
//			money += heldMoney;
//			cryptoValue -= heldMoney; 
//			
//		}
//		
//	}
//	
//	
//	// gain_loss TODO: users buying share of token? or cash investment
//	// crypyoValue = current $ invested by user, coinValue = stock price
//	//false if loss from trading, true if gain
//	public boolean gain_loss(double cryptoValue, double coinValue) {
//		
//		double trade = cryptoValue - coinValue; //not at all how this works but revise later lol
//		
//		if (trade < 0) {
//			return false; 
//		}
//		else {
//			return true;
//		}
//		
//	}
//	
//	
//	
//	//findCoin TODO
//	//Look at neighbors in my moore neighborhood and choose coin/investment amt based on their choices
//	public void findCoin() {
//		GridPoint pt = grid.getLocation(this);
//		
//		//TODO revise zombie snippet for investors looking at other investors to determine
//		//investment choice/amt 
//		GridCellNgh<Investors> nghCreator = new GridCellNgh <Investors>(grid, pt, Investors.class, 1, 1);
//		
//		List<GridCell<Investors>> gridCells = nghCreator.getNeighborhood(true);
//		SimUtilities.shuffle(gridCells, RandomHelper.getUniform());
//		
//		GridPoint pointWithMostInvestors = null;
//		int minCount = Integer.MAX_VALUE;
//		
//		for(GridCell<Investors> cell : gridCells) {
//			if (cell.size() < minCount) {
//				pointWithMostInvestors = cell.getPoint();
//				minCount = cell.size();
//			}
//		}
//		
//		
//	}
//	
}
