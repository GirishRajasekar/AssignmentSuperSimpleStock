package com.jpmorgan.assignment.supersimple.stock.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jpmorgan.assignment.supersimple.stock.util.SuperStockConstants.StockType;
import com.jpmorgan.assignment.supersimple.stock.util.SuperStockUtil;

/**
 * Class which hold the data required for the stock and also has methods 
 * to compute dividend yield,P/E Ratio and Vloume weight price
 * 
 * 
 * @author Girish
 */
public class Stock {
	
	private static Logger logger = Logger.getLogger("Stock");
	
	//Declaring the variable for the stock
	private String symbol;
	private StockType type;
	private Double lastDividend;
	private Double fixedDividend;
	private Double parValue;
	private List<Trade> tradeList = new ArrayList<>();
	
	/**
	 * Empty Stock Constructor
	 * 
	 */
	public Stock(){
		
	}
	
	/**
	 * Constructor
	 * 
	 * @param String symbol
	 * @param StocType type
	 * @param Double lastDividend
	 * @param Double fixedDividend
	 * @param Double parValue
	 * @return Stock 
	 */
	public Stock(String symbol,StockType type,Double lastDividend,Double fixedDividend,Double parValue){
		this.symbol = symbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}
	
	//Getter and Setter for the above fields
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public StockType getType() {
		return type;
	}

	public void setType(StockType type) {
		this.type = type;
	}

	public Double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(Double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public Double getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public Double getParValue() {
		return parValue;
	}

	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}

	public List<Trade> getTradeList() {
		return tradeList;
	}

	public void setTradeList(List<Trade> tradeList) {
		this.tradeList = tradeList;
	}
	
	
	/**
	 * Method to calculate the dividendyield of the stock 
	 * for the given market price
	 * 
	 * @param Double marketPrice :MarketPrice for the stock
	 * @return Double dividendYield :computed value
	 */
	

	public Double calculateDividendYield(Double marketPrice){

		Double dividendYield = 0.0;

		if (marketPrice != null && marketPrice > 0 && this.getType() != null) {

			switch (this.getType()) {

			case COMMON:
				if (this.getLastDividend() != null
						&& this.getLastDividend() > 0) {
					dividendYield = this.getLastDividend() / marketPrice;
				}
				break;
			case PREFERRED:
				if (getFixedDividend() != null && getFixedDividend() > 0
						&& getParValue() != null && getParValue() > 0) {

					dividendYield = this.getFixedDividend()
							* this.getParValue() / marketPrice;
				}
				break;
			default:
				return dividendYield;
			}
		} else {
			logger.debug("MarketPrice must be greater than Zero to calculate dividend yield for a stock");
		}

		return dividendYield;
	}
	

	/**
	 * Method to calculate the P/E ratio of the stock 
	 * for the given market price
	 * 
	 * @param Double marketPrice :MarketPrice for the stock
	 * @return Double peRatio :Computed value
	 */
	
	public Double calculatePERatio(Double marketPrice) {
		
		double peRatio = 0.0;

		if (marketPrice != null 
				&& marketPrice > 0 
				&& this.getLastDividend() != null
				&& this.getLastDividend() > 0) {

			peRatio = marketPrice / this.getLastDividend();

		} else {
			logger.debug("MarketPrice and last dividend must be greater than Zero to calculate P/E ratio for a stock");
		}

		return peRatio;
	}
	
	/**
	 * Method to calculate the volume weight price of the stock 
	 * for the given market price
	 * 
	 * @param Double marketPrice :MarketPrice for the stock
	 * @return Double volumeWeigthedStockPrice :Computed value
	 */
	public Double calculateVolumeWeightedStockPrice(int timeIntervalInMin) {

		Double volumeWeigthedStockPrice = 0.0;
		Integer totalQuantity = 0;
		Double totalStockPrice = 0.0;
		
		if (timeIntervalInMin > 0 && tradeList != null && tradeList.size() > 0) {

			// Get trades for the given time interval
			List<Trade> filteredList = SuperStockUtil
					.getTradeWithInTimeInterval(timeIntervalInMin, tradeList);

			// Calculate the volumeweigthedprice
			if (filteredList != null && filteredList.size() > 0) {

				for (Trade trade : filteredList) {
					
					//Quantity and Price should be postivie value
					if(trade.getQuantity()!=null 
							&& trade.getQuantity() > 0
							&& trade.getPrice()!=null 
							&& trade.getPrice() >0){
						
						totalQuantity += trade.getQuantity();
						totalStockPrice += trade.getPrice()
								* trade.getQuantity();
					}
				}
				volumeWeigthedStockPrice =  totalStockPrice/totalQuantity;
			}
			
		} else {
			logger.debug("Atleast one Trade must be there to calculate volumeweightedprice for a stock");
		}
		return volumeWeigthedStockPrice ;
	}
	
}
