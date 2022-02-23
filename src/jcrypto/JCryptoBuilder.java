/**
 * 
 */
package jcrypto;

import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.continuous.RandomCartesianAdder;
import repast.simphony.space.continuous.SimpleCartesianAdder;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.space.grid.WrapAroundBorders;
import repast.simphony.ui.RunOptionsModel;

/**
 * @author Lilo
 *
 */
public class JCryptoBuilder implements ContextBuilder<Object> {

	@Override
	public Context build(Context<Object> context) {
		
		context.setId("jcrypto");
		
		Parameters params = RunEnvironment . getInstance (). getParameters ();

		
		ContinuousSpaceFactory spaceFactory = 
				ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
		ContinuousSpace<Object> space =
		spaceFactory.createContinuousSpace("space", context, new SimpleCartesianAdder<Object>(), new repast.simphony.space.continuous.WrapAroundBorders(), 50, 50);
		
		GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
		Grid<Object> grid = gridFactory.createGrid("grid", context, new GridBuilderParameters<Object>(new WrapAroundBorders(), new SimpleGridAdder<Object>(), true, 50, 50));
		
		
		int coinCount = 1;
		double value = 0;
		String[] nameArray = {"BTC", "ETH", "ADA"}; //TODO add random generation lol
		Coins[] coinAr = new Coins[coinCount];
		
		for(int i = 0; i < coinCount; i++) {
			int xpos = (50 / (coinCount + 1));
			value = params.getDouble("initPrice");
			//System.out.println(value); //print current val of coin
			coinAr[i] = new Coins(nameArray[i], space, grid, value,params.getDouble("clampMin"),params.getDouble("clampMax") );
			
			context.add(coinAr[i]);
			space.moveTo(coinAr[i], xpos, (int)coinAr[i].getPrice()/10);
		}
		
		
		for (Object obj : context) {
			NdPoint pt = space.getLocation(obj);
			grid.moveTo(obj, (int)pt.getX(), (int)pt.getY());
		}
		
		
		RunEnvironment.getInstance().endAt(params.getInteger("stopTime"));
		
		return context;
	}

	
	
}
