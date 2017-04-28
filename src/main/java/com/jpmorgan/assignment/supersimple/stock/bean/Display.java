package com.jpmorgan.assignment.supersimple.stock.bean;

/**
 * Class holds the data which is required to display
 * to the user based on the requirement
 * 
 * 
 * @author Girish
 */

public class Display {
	
	//Declaring the variable to hold the data
	private String symbol;
	private Double dividendYield;
	private Double peRatio;
	private int noOfTrade;
	private Double weightedStockPrice;
	
	//Getter and  setter methods for the above variables
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Double getDividendYield() {
		return dividendYield;
	}
	public void setDividendYield(Double dividendYield) {
		this.dividendYield = dividendYield;
	}
	public Double getPeRatio() {
		return peRatio;
	}
	public void setPeRatio(Double peRatio) {
		this.peRatio = peRatio;
	}
	public int getNoOfTrade() {
		return noOfTrade;
	}
	public void setNoOfTrade(int noOfTrade) {
		this.noOfTrade = noOfTrade;
	}
	public Double getWeightedStockPrice() {
		return weightedStockPrice;
	}
	public void setWeightedStockPrice(Double weightedStockPrice) {
		this.weightedStockPrice = weightedStockPrice;
	}
	
	
}
