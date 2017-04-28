package com.jpmorgan.assignment.supersimple.stock.app;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jpmorgan.assignment.supersimple.stock.api.StockProcessor;

public class SuperSimpleStockServiceTest {

	

	@Test
	public void testForValidStockProcessorObject() {
		StockProcessor actualStockProcessor = SuperSimpleStockService.getStockProcessor("inMemoryImpl");
		assertNotNull(actualStockProcessor);
	}
	
	@Test
	public void testForInValidTypeForStockProcessort() {
		StockProcessor actualStockProcessor = SuperSimpleStockService.getStockProcessor("test");
		assertNull(actualStockProcessor);
	}

}
