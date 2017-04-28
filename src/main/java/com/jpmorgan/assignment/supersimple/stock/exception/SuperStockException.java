package com.jpmorgan.assignment.supersimple.stock.exception;


/**
 * Custom exception class for the application
 * 
 * @author Girish Rajasekar
 */
public class SuperStockException extends Exception{

	private static final long serialVersionUID = 1L;

	public SuperStockException(){
		
	}
	
	public SuperStockException(String message){
		super(message);
	}
	
	public SuperStockException(Throwable cause){
		super(cause);
	}
	
	public SuperStockException(String message,Throwable cause){
		super(message,cause);
	}
}
