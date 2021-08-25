package com.au.userdataprocessor.exception;

/**
 * Exception denoting malformed business rule failure 
 * 
 * @author deepalipimparkar
 *
 */
public class BusinessRuleFailedException extends Exception {
	private static final long serialVersionUID = 1L;

	public BusinessRuleFailedException(String errorMessage) {
        super(errorMessage);
    }
}
