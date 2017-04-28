package com.jpmorgan.assignment.supersimple.stock.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jpmorgan.assignment.supersimple.stock.api.StockProcessor;
import com.jpmorgan.assignment.supersimple.stock.api.TradeProcessor;
import com.jpmorgan.assignment.supersimple.stock.bean.Display;
import com.jpmorgan.assignment.supersimple.stock.bean.Stock;
import com.jpmorgan.assignment.supersimple.stock.exception.SuperStockException;
import com.jpmorgan.assignment.supersimple.stock.util.SuperStockConstants.StockType;
import com.jpmorgan.assignment.supersimple.stock.util.SuperStockConstants;


/**
 * Implementation class for the StcokProcessor interface this class helps
 * in loading the stock,processing each stock and display the result  
 * 
 * 
 * @author Girish
 *
 */
public class InMemoryStockProcessorImpl implements StockProcessor {

	private static Logger logger = Logger
			.getLogger("InMemoryStockProcessorImpl");

	@Override
	/**
	 * Method will load the data manually by calling the stock constructor
	 * 
	 * @return stockList
	 */
	public List<Stock> loadStock() {

		List<Stock> stockList = new ArrayList<>();

		// manually load the stocks given in the requirement document
		stockList.add(new Stock("TEA", StockType.COMMON, 0.0, 0.0, 100.0));
		stockList.add(new Stock("POP", StockType.COMMON, 8.0, 0.0, 100.0));
		stockList.add(new Stock("ALE", StockType.COMMON, 23.0, 0.0, 60.0));
		stockList.add(new Stock("GIN", StockType.PREFERRED, 8.0, 0.2, 100.0));
		stockList.add(new Stock("JOE", StockType.COMMON, 13.0, 0.0, 250.0));

		return stockList;
	}

	@Override
	/**
	 * Method will get the dividend,P/E ratio and voulme weighted price
	 * for each stock.
	 * 
	 * 
	 * @param stockList
	 * @return displayList
	 */
	public List<Display> processStock(List<Stock> stockList) {

		TradeProcessor tradeProcessor = new GenerateTradeProcessorImpl();
		List<Display> displayList = new ArrayList<>();
		
		//Looping each stock
		for (Stock stock : stockList) {

			if (stock != null && !stock.getSymbol().isEmpty()) {
				Display display = new Display();
				try {
					
					display.setSymbol(stock.getSymbol());
					
					// call method to calculate the dividend yield and set to display ojb
					display.setDividendYield(stock.calculateDividendYield(SuperStockConstants.MARKET_PRICE));
					
					// call method to calculate the P/E ratio and set to display ojb
					display.setPeRatio(stock.calculatePERatio(SuperStockConstants.MARKET_PRICE));
					
					// call method to generate the trade for each symbol
					stock.setTradeList(tradeProcessor.fetchTrade(stock.getSymbol()));
					display.setNoOfTrade(stock.getTradeList().size());

					// calculate Volume weighted price for a given stock and set to display ojb
					display.setWeightedStockPrice(stock.calculateVolumeWeightedStockPrice(SuperStockConstants.TRADE_TIME_INTERVAL_IN_MIN));
					
					//add to the list
					displayList.add(display);

				} catch (SuperStockException ex) {
					logger.error(ex);
				}
			}

		}
		return displayList;
	}
	
	/**
	 * Method will display the result on the console.
	 * 
	 * @param displayList
	 * @param allShareIndex
	 * 
	 */
	public void display(List<Display> displayList,Double allShareIndex){
		
		for(Display display : displayList){
			
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("Dividend Yield For Stock "+ display.getSymbol()+ " : " +display.getDividendYield());
			System.out.println("P/E Ratio For Stock " +display.getSymbol()+ " : " +display.getPeRatio());
			System.out.println("Number of Trade Recorded For Stock " +display.getSymbol()+ " : " +display.getNoOfTrade());
			System.out.println("Volume Weighted Price For Stock " +display.getSymbol()+ " : " +display.getWeightedStockPrice());
			System.out.println("----------------------------------------------------------------------------------");
		}
		System.out.println("GBCE All Share Index Price For All Stocks :"+allShareIndex);
	}

}
