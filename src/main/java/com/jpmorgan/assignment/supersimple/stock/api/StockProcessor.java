package com.jpmorgan.assignment.supersimple.stock.api;

/*
 * 
 * @author Girish
 */
import java.util.List;

import com.jpmorgan.assignment.supersimple.stock.bean.Display;
import com.jpmorgan.assignment.supersimple.stock.bean.Stock;

public interface StockProcessor {

	public List<Stock> loadStock();
	public List<Display> processStock(List<Stock> stockList);
	public void display(List<Display> displayList,Double allShareIndex);
	
}
