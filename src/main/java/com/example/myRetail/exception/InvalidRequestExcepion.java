package com.example.myRetail.exception;

public class InvalidRequestExcepion  extends RuntimeException{
	

	private static final long serialVersionUID = 4882170771918788635L;

	public InvalidRequestExcepion(Throwable cause){
		super(cause) ;
	}
	public InvalidRequestExcepion(String message , Throwable cause){
		super(message,cause) ;
	}
	
	public InvalidRequestExcepion(String message ){
		super(message) ;
	}

}
