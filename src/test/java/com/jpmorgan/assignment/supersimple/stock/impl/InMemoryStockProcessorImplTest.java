package com.jpmorgan.assignment.supersimple.stock.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.assignment.supersimple.stock.api.StockProcessor;
import com.jpmorgan.assignment.supersimple.stock.bean.Display;
import com.jpmorgan.assignment.supersimple.stock.bean.Stock;
import com.jpmorgan.assignment.supersimple.stock.util.SuperStockConstants.StockType;

public class InMemoryStockProcessorImplTest {

	StockProcessor stockProcessor = null;
	List<Stock> stockList = null;
	List<Stock> inValidStockList = null;
	
	@Before
	public void setUp() throws Exception {
		
		stockProcessor = new InMemoryStockProcessorImpl();
		stockList = new ArrayList<>();
		inValidStockList = new ArrayList<>();
		
		Stock s1  = new Stock("TEST", StockType.COMMON, 0.0, 0.0, 100.0);
		Stock s2  = new Stock("JP", StockType.COMMON, 10.0, 70.0, 100.0);
		stockList.add(s1);
		stockList.add(s2);
		
		Stock invalidStock  = new Stock("", StockType.COMMON, 0.0, 0.0, 100.0);
		inValidStockList.add(invalidStock);
		
	}


	@Test
	public void testForValidProcess() {
		
		List<Display> displayList = stockProcessor.processStock(stockList);
		
		assertNotNull(displayList);
		assertEquals(stockList.size(),displayList.size());
		
	}
	
	@Test
	public void testWhenStockSymbolIsEmpty() {
		
		List<Display> displayList = stockProcessor.processStock(inValidStockList);
		assertNotEquals(inValidStockList.size(),displayList.size());
		
	}
	
	@After
	public void tearDown() throws Exception {
		
		stockProcessor = null;
		stockList = null;
		inValidStockList = null;
	}

}
