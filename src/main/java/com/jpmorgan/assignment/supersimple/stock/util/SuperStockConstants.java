package com.jpmorgan.assignment.supersimple.stock.util;

/**
 * Class contains the constants values for the application
 * 
 * @author Girish
 *
 */
public class SuperStockConstants{
	
	//Stockprocessor type
	public static final String LOAD_IN_MEMORY_IMPL = "inMemoryImpl";
	

	//MarketPrice for the stock
	public static final Double MARKET_PRICE = 9.5;
	
	//TimeInterval to fetch the trade
	public static final int TRADE_TIME_INTERVAL_IN_MIN = 15;
	
	//TimeInterval to fetch the trade
	public static final int NUM_OF_TRADES_TO_GENERATE = 5;
	
	//Stock Type Enum is defined
	public enum StockType{
		COMMON,PREFERRED
	}
	
	//Trade Type Enum is defined
	public enum TradeType{
		BUY,SELL
	}

}
