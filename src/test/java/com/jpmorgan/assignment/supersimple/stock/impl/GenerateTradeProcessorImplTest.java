/**
 * 
 */
package com.jpmorgan.assignment.supersimple.stock.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.assignment.supersimple.stock.api.TradeProcessor;
import com.jpmorgan.assignment.supersimple.stock.bean.Trade;
import com.jpmorgan.assignment.supersimple.stock.exception.SuperStockException;
import com.jpmorgan.assignment.supersimple.stock.util.SuperStockConstants;
import com.jpmorgan.assignment.supersimple.stock.util.SuperStockConstants.TradeType;

/**
 * @author Girish
 *
 */
public class GenerateTradeProcessorImplTest {

	TradeProcessor tradeProcessor ;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	   tradeProcessor = new GenerateTradeProcessorImpl();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		tradeProcessor = null;
	}

	@Test
	public void testForBuyTrade() throws SuperStockException{
		
		Trade actualTrade = tradeProcessor.instruction(2, 200.00, TradeType.BUY);
		assertNotNull(actualTrade);
		
	}
	
	@Test
	public void testForSellTrade() throws SuperStockException{
		
		Trade actualTrade = tradeProcessor.instruction(2, 200.00, TradeType.SELL);
		assertNotNull(actualTrade);
		
	}
	
	@Test(expected = SuperStockException.class)
	public void testWhenQuantityIsNullForTrade() throws SuperStockException{
		
		tradeProcessor.instruction(null, 200.00, TradeType.BUY);
	}
	
	@Test(expected = SuperStockException.class)
	public void testWhenPriceIsNullForTrade() throws SuperStockException{
		
		tradeProcessor.instruction(2, null, TradeType.BUY);
	}
	
	@Test
	public void testForErrorMessageWhenQuanityIsNullForTrade(){
		try{
			tradeProcessor.instruction(null, 200.00, TradeType.SELL);
		}catch(SuperStockException e){
			
			assertEquals("Invalid Quantity",e.getMessage());
		}
	}
	
	@Test
	public void testForErrorMessageWhenPriceIsNullForTrade(){
		try{
			tradeProcessor.instruction(2, null, TradeType.SELL);
		}catch(SuperStockException e){
			
			assertEquals("Invalid Price",e.getMessage());
		}
	}
	
	@Test
	public void testForValidFetchTrade() throws SuperStockException{
		List<Trade> tradeList = tradeProcessor.fetchTrade("TEA");
		assertNotNull(tradeList);
		
		//Total Number of trades check
		assertEquals(SuperStockConstants.NUM_OF_TRADES_TO_GENERATE*2,tradeList.size());
	}
	
	@Test
	public void testForInValidFetchTrade() throws SuperStockException{
		List<Trade> tradeList = tradeProcessor.fetchTrade("TEA");
		
		//Total Number of trades check
		assertNotEquals(SuperStockConstants.NUM_OF_TRADES_TO_GENERATE*1,tradeList.size());
	}
}
