package io.basicparser.exceptions;

/**
 * This class wraps general exception a BasicParserException
 * to be applied when validating string expression
 * @author arshadmayet
 *
 */
public class BasicParserException extends Exception{  
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BasicParserException(String msg){
		super(msg);
	}

}
