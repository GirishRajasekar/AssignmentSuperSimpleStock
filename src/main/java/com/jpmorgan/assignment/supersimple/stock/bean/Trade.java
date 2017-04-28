package com.jpmorgan.assignment.supersimple.stock.bean;

import java.util.Date;

import com.jpmorgan.assignment.supersimple.stock.util.SuperStockConstants.TradeType;

/**
 * Class holds the trade related data
 * 
 * @author Girish
 *
 */
public class Trade {
	
	//Declaring the variables 
	private TradeType type;
	private Integer quantity;
	private Double price;
	private Date date;
	
	
	//Getter and setter methods 
	public TradeType getType() {
		return type;
	}
	public void setType(TradeType type) {
		this.type = type;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
