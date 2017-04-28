package com.jpmorgan.assignment.supersimple.stock.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jpmorgan.assignment.supersimple.stock.bean.Trade;

/**
 * Class contains utility methods for the applicagtion
 * 
 * 
 * @author Girish
 *
 */
public class SuperStockUtil {
	
	/**
	 * Method to fetch the trades with in given time frame.
	 * 
	 * @param timeInterval
	 * @param unFilteredTradeList
	 * @return filteredList
	 */

	public static List<Trade> getTradeWithInTimeInterval(int timeInterval,List<Trade> unFilteredTradeList){
		List<Trade> filteredTradeList = new ArrayList<>();
		Date currentDateTime = new Date();
		
		//StartTime based on the time interval
		Date startTime = new Date(currentDateTime.getTime() - (timeInterval * 60 * 1000));
		
		//Loop through the all the trades
		for(Trade trade : unFilteredTradeList){
			
			//Filter the trade withtin the given timeframe
			if(trade!=null 
					&& trade.getDate()!=null 
					&& trade.getDate().after(startTime)){
				
				filteredTradeList.add(trade);
			}
		}
		return filteredTradeList;
	}
}
