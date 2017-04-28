/**
 * 
 */
package com.jpmorgan.assignment.supersimple.stock.bean;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.assignment.supersimple.stock.util.SuperStockConstants.StockType;
import com.jpmorgan.assignment.supersimple.stock.util.SuperStockConstants.TradeType;

/**
 * @author Girish
 *
 */
public class StockTest {
	
	Stock stock = null;
	List<Trade> tradeList  = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		stock = new Stock("ALE", StockType.COMMON, 23.0, 20.0, 100.0);
		
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
		
		tradeList.add(trade1);
		tradeList.add(trade2);
		tradeList.add(trade3);
		tradeList.add(trade4);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		stock = null;
		tradeList = null;
	}

	@Test
	public void testForCommonStockDividendYield() {
		Double actualDividendYield = stock.calculateDividendYield(9.1);
		Double expectedResult = 23.0/9.1;
		assertEquals(expectedResult, actualDividendYield,.005);
	}
	
	@Test
	public void testForPereferdStockDividendYield() {
		stock.setType(StockType.PREFERRED);
		Double actualDividendYield = stock.calculateDividendYield(9.1);
		Double expectedResult = 20.0*100.0/9.1;
		assertEquals(expectedResult, actualDividendYield,.005);
	}
	
	@Test
	public void testWhenMarketPriceIsNullStockDividendYield() {
		Double actualDividendYield = stock.calculateDividendYield(null);
		assertEquals(0.0, actualDividendYield,.005);
	}
	
	@Test
	public void testWhenMarketPriceIsZeroStockDividendYield() {
		Double actualDividendYield = stock.calculateDividendYield(0.0);
		assertEquals(0.0, actualDividendYield,.005);
	}
	
	@Test
	public void testWhenMarketPriceIsNegativeStockDividendYield() {
		Double actualDividendYield = stock.calculateDividendYield(-10.0);
		assertEquals(0.0, actualDividendYield,.005);
	}
	
	@Test
	public void testForPERatio(){
		Double peRatio = stock.calculatePERatio(9.1);
		Double expectedResult = 9.1/23.00;
		assertEquals(expectedResult, peRatio,.0051);
	}
	
	@Test
	public void testWhenMarketPriceIsNullPERatio(){
		Double peRatio = stock.calculatePERatio(null);
		assertEquals(0.0, peRatio,.0051);
	}
	
	@Test
	public void testWhenMarketPriceIsZeroPERatio(){
		Double peRatio = stock.calculatePERatio(0.0);
		assertEquals(0.0, peRatio,.0051);
	}
	
	@Test
	public void testWhenMarketPriceIsNegativePERatio(){
		Double peRatio = stock.calculatePERatio(-19.0);
		assertEquals(0.0, peRatio,.0051);
	}
	
	@Test
	public void testForVolumeWeightedWithInTimeFrameStock(){
		stock = new Stock("JPA", StockType.COMMON, 10.0, 20.0, 100.0);
		stock.setTradeList(tradeList);
		Double expectedResult = ((150.00*10)+(100.00*5)+(170.00*15)+(200.00*25))/(10+5+15+25);
		Double volumeWeightedStockPrice = stock.calculateVolumeWeightedStockPrice(15);
		assertEquals(expectedResult,volumeWeightedStockPrice,.0006);
		
	}
	
	@Test
	public void testForVolumeWeightedStockWhenTimeFrameZero(){
		stock = new Stock("JPA", StockType.COMMON, 10.0, 20.0, 100.0);
		stock.setTradeList(tradeList);
		Double volumeWeightedStockPrice = stock.calculateVolumeWeightedStockPrice(0);
		assertEquals(0.0,volumeWeightedStockPrice,.0006);
		
	}
	
	@Test
	public void testForVolumeWeightedStockWhenTimeFrameNegative(){
		stock = new Stock("JPA", StockType.COMMON, 10.0, 20.0, 100.0);
		stock.setTradeList(tradeList);
		Double volumeWeightedStockPrice = stock.calculateVolumeWeightedStockPrice(-10);
		assertEquals(0.0,volumeWeightedStockPrice,.0006);
		
	}
	
	@Test
	public void testForVolumeWeightedStockWhenAllTradeBoughtBeforeTheTimeIntervalk(){
		
		stock = new Stock("JPA", StockType.COMMON, 10.0, 20.0, 100.0);
		
		tradeList = new ArrayList<>();
		Trade trade1 = new Trade();
		trade1.setPrice(150.00);
		trade1.setQuantity(10);
		trade1.setType(TradeType.BUY);
		//Set Trade Before 20 minutes
		trade1.setDate(new Date(new Date().getTime() - (20 * 60 * 1000)));
		
		Trade trade2 = new Trade();
		trade2.setPrice(100.00);
		trade2.setQuantity(5);
		trade2.setType(TradeType.SELL);
		trade2.setDate(new Date(new Date().getTime() - (18 * 60 * 1000)));
		
		tradeList.add(trade1);
		tradeList.add(trade2);
		
		stock.setTradeList(tradeList);
		Double volumeWeightedStockPrice = stock.calculateVolumeWeightedStockPrice(15);
		assertEquals(0.0,volumeWeightedStockPrice,.0006);
		
	}
	
	@Test
	public void testForVolumeWeightedStockWhenOneTradeBoughtBeforeTheTimeIntervalk(){
		
		stock = new Stock("JPA", StockType.COMMON, 10.0, 20.0, 100.0);
		
		tradeList = new ArrayList<>();
		Trade trade1 = new Trade();
		trade1.setPrice(150.00);
		trade1.setQuantity(10);
		trade1.setType(TradeType.BUY);
		//Set Trade Before 20 minutes
		trade1.setDate(new Date(new Date().getTime() - (16 * 60 * 1000)));
		
		Trade trade2 = new Trade();
		trade2.setPrice(100.00);
		trade2.setQuantity(5);
		trade2.setType(TradeType.SELL);
		trade2.setDate(new Date(new Date().getTime() - (10 * 60 * 1000)));
		
		tradeList.add(trade1);
		tradeList.add(trade2);
		
		stock.setTradeList(tradeList);
		
		Double expectedResult = (100.00*5)/5;
		Double volumeWeightedStockPrice = stock.calculateVolumeWeightedStockPrice(15);
		assertEquals(expectedResult,volumeWeightedStockPrice,.0006);
		
	}

}
