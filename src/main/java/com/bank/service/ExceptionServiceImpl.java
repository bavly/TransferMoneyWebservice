package com.bank.service;

public class ExceptionServiceImpl extends RuntimeException{


  	/**
	 * class for exception at RuntimeException
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionServiceImpl(String error) {
        super(error);
    }

}
