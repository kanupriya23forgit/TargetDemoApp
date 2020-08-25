package com.example.myRetail.exception;

public class ProductNotFoundException  extends RuntimeException{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4882170771918788635L;

	public ProductNotFoundException(Throwable cause){
		super(cause) ;
	}
	public ProductNotFoundException(String message , Throwable cause){
		super(message,cause) ;
	}
	
	public ProductNotFoundException(String message ){
		super(message) ;
	}

}
