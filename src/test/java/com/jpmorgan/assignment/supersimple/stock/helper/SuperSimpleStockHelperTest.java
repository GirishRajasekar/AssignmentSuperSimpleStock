package com.jpmorgan.assignment.supersimple.stock.helper;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.assignment.supersimple.stock.bean.Stock;
import com.jpmorgan.assignment.supersimple.stock.bean.Trade;
import com.jpmorgan.assignment.supersimple.stock.exception.SuperStockException;
import com.jpmorgan.assignment.supersimple.stock.util.SuperStockConstants.StockType;
import com.jpmorgan.assignment.supersimple.stock.util.SuperStockConstants.TradeType;

public class SuperSimpleStockHelperTest {

	
	
	List<Trade> tradeList  = null;
	List<Stock> stockList = null;
	
	@Before
	public void setUp() throws Exception {
		
		stockList = new ArrayList<>();
		
		
		Stock stock = new Stock("ALE", StockType.COMMON, 23.0, 20.0, 100.0);
		
		tradeList = new ArrayList<>();
		Trade trade1 = new Trade();
		trade1.setPrice(150.00);
		trade1.setQuantity(10);
		trade1.setType(TradeType.BUY);
		trade1.setDate(new Date());
		
		Trade trade2 = new Trade();
		trade2.setPrice(100.00);
		trade2.setQuantity(5);
		trade2.setType(TradeType.SELL);
		trade2.setDate(new Date());
		
		tradeList.add(trade1);
		tradeList.add(trade2);
		
		stock.setTradeList(tradeList);
		stockList.add(stock);
		
		Stock stock1 = new Stock("JPA", StockType.COMMON, 13.0, 10.0, 225.0);
		tradeList = new ArrayList<>();
		
		Trade trade3 = new Trade();
		trade3.setPrice(170.00);
		trade3.setQuantity(15);
		trade3.setType(TradeType.BUY);
		trade3.setDate(new Date());
		
		Trade trade4 = new Trade();
		trade4.setPrice(200.00);
		trade4.setQuantity(25);
		trade4.setType(TradeType.SELL);
		trade4.setDate(new Date());
		
		tradeList.add(trade3);
		tradeList.add(trade4);
		stockList.add(stock1);
	}

	@After
	public void tearDown() throws Exception {
		stockList = null;
		tradeList = null;
	}

	@Test
	public void testAllShareIndex() throws SuperStockException{
		Double actualResult = SuperSimpleStockHelper.allShareIndex(stockList);
		assertEquals(122.47448713915891, actualResult,0.0005);
	}
	
	@Test
	public void testAllShareIndexWhenTradePriceIsNegative(){
		
		for(Stock stock :stockList){
			for(Trade trade :stock.getTradeList()){
				trade.setPrice(-10.0);
			}
			
		}
		Double expectedResult = SuperSimpleStockHelper.allShareIndex(stockList);
		assertEquals(0.0, expectedResult,.005);
		
	}
	
	@Test
	public void testAllShareIndexWhenTradeIsNotRecorded() throws SuperStockException{
		for(Stock stock :stockList){
			stock.setTradeList(null);
		}
		Double expectedResult = SuperSimpleStockHelper.allShareIndex(stockList);
		assertEquals(0.0, expectedResult,.005);
	}
	

}
