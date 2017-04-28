package com.jpmorgan.assignment.supersimple.stock.api;

import java.util.List;

import com.jpmorgan.assignment.supersimple.stock.bean.Trade;
import com.jpmorgan.assignment.supersimple.stock.exception.SuperStockException;
import com.jpmorgan.assignment.supersimple.stock.util.SuperStockConstants.TradeType;

public interface TradeProcessor {
	
	public Trade instruction(Integer quantity,Double price,TradeType type) throws SuperStockException;
	public List<Trade> fetchTrade(String symbol) throws SuperStockException;
	
}
