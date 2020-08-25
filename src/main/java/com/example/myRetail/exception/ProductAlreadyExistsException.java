package com.example.myRetail.exception;

public class ProductAlreadyExistsException extends RuntimeException{
	
		private static final long serialVersionUID = 4882170771918788635L;

		public ProductAlreadyExistsException(Throwable cause){
			super(cause) ;
		}
		public ProductAlreadyExistsException(String message , Throwable cause){
			super(message,cause) ;
		}
		
		public ProductAlreadyExistsException(String message ){
			super(message) ;
		}

}
