package com.jpmorgan.assignment.supersimple.stock.app;

import java.util.List;

import org.apache.log4j.Logger;

import com.jpmorgan.assignment.supersimple.stock.api.StockProcessor;
import com.jpmorgan.assignment.supersimple.stock.bean.Display;
import com.jpmorgan.assignment.supersimple.stock.bean.Stock;
import com.jpmorgan.assignment.supersimple.stock.helper.SuperSimpleStockHelper;
import com.jpmorgan.assignment.supersimple.stock.util.SuperStockConstants;

/**
 * This is the Starting point for the application which will load and process
 * the stock and display the result stated in the requirement
 * 
 * @author Girish
 */

public class SuperSimpleStockApp extends SuperSimpleStockService {

	private static Logger logger = Logger.getLogger("SuperSimpleStockApp");

	public static void main(String[] args) {

		logger.debug("*****SuperSimpleStockApp - Started******");

		StockProcessor stockProcessor = getStockProcessor(SuperStockConstants.LOAD_IN_MEMORY_IMPL);

		if (stockProcessor != null) {

			// Load the stock
			List<Stock> stockList = stockProcessor.loadStock();

			if (stockList != null && stockList.size() > 0) {

				// Process the loaded stock
				List<Display> displayList = stockProcessor
						.processStock(stockList);

				// Calculate all share index
				Double allShareIndex = SuperSimpleStockHelper
						.allShareIndex(stockList);

				// call display method to show the result
				stockProcessor.display(displayList, allShareIndex);

			}
		} else {

			logger.debug("StockProcessor is not implemented for the given type");
		}

		logger.debug("*****SuperSimpleStockApp - End******");

	}
}
