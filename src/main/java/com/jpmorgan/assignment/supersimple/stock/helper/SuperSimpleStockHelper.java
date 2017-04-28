package com.jpmorgan.assignment.supersimple.stock.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jpmorgan.assignment.supersimple.stock.bean.Stock;
import com.jpmorgan.assignment.supersimple.stock.bean.Trade;
import com.jpmorgan.assignment.supersimple.stock.exception.SuperStockException;


/**
 * This is the helper class which provides methods to calculate the allshare index
 * 
 * 
 * @author Girish
 */

public class SuperSimpleStockHelper {
	
	private static Logger logger = Logger.getLogger("SuperSimpleStockHelper");
	
	/**
	 * Calculate the GBCE All Share Index for all stocks
	 * 
	 * 
	 * @param List<Stock> stockList :StockList to compute all share index
	 * @return Double allShareIndex : AllShare index value
	 * @throws SuperStockException : custom exception
	 */
	public static Double allShareIndex(List<Stock> stockList) {
		
		Double allShareIndex = 0.0;
		List<Double> priceList = new ArrayList<>();
		
		//Loop through each stock
		for(Stock stock: stockList) {
			
			//Check if the trade is recorded for the stock
			if(stock.getTradeList()!=null && stock.getTradeList().size() > 0){
				
				//Loop through the recorded trade 
				for(Trade trade : stock.getTradeList()){
					
					//fetch the price from the trade and add it to the list
					if(trade.getPrice()!=null && trade.getPrice() >0){
						priceList.add(trade.getPrice());
					}
				}
			}
		}
		
		//Check if there is any price
		if(priceList.size() > 0){
			
			//call the method to get the gemoetric mean for all prices for all stock
			allShareIndex = computeGeometricMean(priceList);
			
		}else{
			logger.debug("There is No Trade Recorded To Calculate All Share Index");
		}
		return allShareIndex;
	}
	
	 /**
     * Method to compute and return geometric mean of price values
     * 
     * @params List<Double> priceList: Price list for computing geometric means
     * @return double geoMean: geometric mean for price list
     */
    public static double computeGeometricMean(List<Double> priceList) {

        double allTradeProd = 1.0;
        double power = 1 / (double) priceList.size();
        
        //Loop through the priceList 
        for (int i = 0 ; i < priceList.size(); i++){
        	
        	allTradeProd  = allTradeProd * priceList.get(i);
        }
        return Math.pow(allTradeProd, power);
    }
	
   
	
}
