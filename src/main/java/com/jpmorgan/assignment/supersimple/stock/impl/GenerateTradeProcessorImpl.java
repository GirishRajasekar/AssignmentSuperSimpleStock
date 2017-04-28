package com.jpmorgan.assignment.supersimple.stock.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.jpmorgan.assignment.supersimple.stock.api.TradeProcessor;
import com.jpmorgan.assignment.supersimple.stock.bean.Trade;
import com.jpmorgan.assignment.supersimple.stock.exception.SuperStockException;
import com.jpmorgan.assignment.supersimple.stock.util.SuperStockConstants;
import com.jpmorgan.assignment.supersimple.stock.util.SuperStockConstants.TradeType;

/**
 * Implementation class for the TradeProcessor Interface this will help
 * to generate the random trade for given stock
 * 
 * @author Girish
 *
 */
public class GenerateTradeProcessorImpl implements TradeProcessor {
	
	private static Logger logger = Logger.getLogger("TradeProcessorImpl");
	
	@Override
	/**
	 * (non-Javadoc)
	 * @see com.jpmorgan.assignment.supersimple.stock.api.TradeProcessor#buy(java.lang.Integer, java.lang.Double)
	 * 
	 * @param quantity
	 * @param price
	 * @return trade
	 */
	public Trade instruction(Integer quantity, Double price,TradeType type) throws SuperStockException{
		
		//Manually generate the buy trade for given quantity and price
		Trade trade = new Trade();
		
		trade.setDate(new Date());
		if(price!=null && price > 0){
			trade.setPrice(price);
		}else{
			throw new SuperStockException("Invalid Price");
		}
		if(quantity!=null && quantity > 0){
			trade.setQuantity(quantity);
		}else{
			throw new SuperStockException("Invalid Quantity");
		}
		trade.setType(type);

		return trade;
	}

	
	@Override
	/**
	 * (non-Javadoc)
	 * @see com.jpmorgan.assignment.supersimple.stock.api.TradeProcessor#fetchTrade(java.lang.String)
	 * 
	 * @param symbol
	 * @return tradeList
	 */
	public  List<Trade> fetchTrade(String symbol) throws SuperStockException{
		
		List<Trade> tradeList = new ArrayList<>();
		Random random  = null;
		double randomValue = 0.0;
		
		try{
			
			//Generating random trades for given symbol
			for (int i=1; i <= SuperStockConstants.NUM_OF_TRADES_TO_GENERATE; i++) {
				
			    random = new Random();
	    	    
	    		Integer rangeMin = 1;
	    		Integer rangeMax = 10;
	    		
	    		//Get the random price value for buy trade
	    	    randomValue = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
	    	    
	    	    //Excute Buy trade
	    		Trade buyTrade = instruction(i, randomValue,TradeType.BUY);
	    		
	    		//add buy trade to list
	    		tradeList.add(buyTrade);
	    		logger.debug(symbol + " bought " + i + " shares for price:: $ " + randomValue);
	    		
	    		//Get the random price value for sell trade
	    		randomValue = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
	    		
	    		//Excute Sell Trade
	    		Trade sellTrade = instruction(i, randomValue,TradeType.SELL);
	    		
	    		//add sell trade to the list
	    		tradeList.add(sellTrade);
	    		logger.debug( symbol + " sold " + i + " shares for price:: $ " + randomValue);
	    		
	    		Thread.sleep(100);
	    	}
		}catch(InterruptedException e){
			throw new SuperStockException("Error while generatng the random trade",e);
		}
		return tradeList;
	}

}
