package com.jpmorgan.assignment.supersimple.stock.app;

import com.jpmorgan.assignment.supersimple.stock.api.StockProcessor;
import com.jpmorgan.assignment.supersimple.stock.impl.InMemoryStockProcessorImpl;
import com.jpmorgan.assignment.supersimple.stock.util.SuperStockConstants;

public class SuperSimpleStockService {

	 public static StockProcessor getStockProcessor(String type){
	    	
	    	StockProcessor stockProcessor = null;
	    	
	    	if(type.equalsIgnoreCase(SuperStockConstants.LOAD_IN_MEMORY_IMPL)){
	    		
	    		stockProcessor = new InMemoryStockProcessorImpl(); 
	    	}
	    	
	    	return stockProcessor;
	    }
}
