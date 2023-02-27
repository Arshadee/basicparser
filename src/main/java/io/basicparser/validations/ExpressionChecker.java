package io.basicparser.validations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class houses the string expression formatting rules
 * required for parsing and is used by the Validator class
 * 
 * @author arshadmayet
 *
 */
public class ExpressionChecker {

	/**
     * This method initiates check bracket balance of the input expression
     * 
     * @param expression
     * @return boolean result
     */
    public static boolean checkBraces(List<String >chars){
      boolean result = chkBracketBalance(chars,0,0,"-");
      return result;
      
    }
    
    /**
     * This method checks if brackets of the expression has correctly balanced brackets
     * so that the expression may be parsed
     * 
     * @param chars
     * @param index
     * @param noOfOpenBrackets
     * @param lastBracket
     * @return boolean result
     */
    private static boolean chkBracketBalance(List<String> chars,int index, int noOfOpenBrackets, String lastBracket){
    	if(index==chars.size()) {
    		return noOfOpenBrackets==0 && lastBracket != "(";
    	}
    	String cursor = chars.get(index);
    	if (cursor.equals("(")) {
    		return chkBracketBalance(chars,index+1,noOfOpenBrackets+1,"(");
    	}
     	if (cursor.equals(")")) {
     		return chkBracketBalance(chars,index+1,noOfOpenBrackets-1,")");
     	}
      	return chkBracketBalance(chars,index+1,noOfOpenBrackets,lastBracket);
    }
    
    /**
     * This method initiates the String Expression validation check
     * 
     * @param expression
     * @return boalean result
     */
     public static boolean checkValidExpression(List<String> expression) {
 	      boolean result = checkNodeNBrackets(expression) && checkNodeWithNoBrackets(expression) ;
 	      return result;
 	 }
	
    /**
     * This method performs the String Expresion validation check scanning each char, checking nodes and braces 
     * are in correct places /order
     * 
     * @param chars
     * @return boolean result
     */
	private static boolean checkNodeNBrackets(List<String> chars) {
		if(chars.size()<=1) return false;
		@SuppressWarnings("unused")
		boolean isChar= false;
		boolean isOpen = false;
		boolean isClose= false;

	for(String c: chars) {
			boolean isCharCurrent = (!c.equals("("))&&(!c.equals(")"));
			boolean isOpenCurrent = c.equals("(");
			boolean isCloseCurrent=c.equals(")");
			//..((
			if(isOpen && (isOpenCurrent)) return false;
			// ..)(
			if(isClose && (isOpenCurrent)) return false;
			
			isChar = isCharCurrent;
			isOpen = isOpenCurrent;
			isClose = isCloseCurrent;
		}		
		return true;
	}
	
	/**
	 * This method checks for any element without brackets or valid brackets
	 * 
	 * @param chars
	 * @return boolean
	 */
	private static boolean checkNodeWithNoBrackets(List<String> chars) {
		
		for(int i=0; i<chars.size();i++) {
			boolean isOpenCurrent = chars.get(i).equals("(");
			boolean isCharPrev = 
					(i>0)? (!chars.get(i-1).equals("("))&&(!chars.get(i-1).equals(")")) : false;
            
		 if(isCharPrev && !isOpenCurrent) return false;
			
		}		
		return true;
	}

	
	/**
	 * This method checks if the string expression has a root node or that it does not represent a dis-jointed tree
	 * checks the string expression against the result
	 * 
	 * @param expression
	 * @param result
	 * @return boolean result
	 */
	public static boolean checkForRootNodeAndDisjointTree(String expression,String result) {
		return expression.charAt(0) == result.charAt(0);
	}
	
	/**
	 * This method checks for any cyclic relationship the expression string
	 * i.e. parents and any of the sub nodes may not have the same name
	 * 
	 * @param list of tokens from expression
	 * @return Set of Differences
	 */
	public static Set<String> checkForNoCyclicRelation(List<String> tokens) {
		
		List<String> elements = tokens.stream()
				.filter(x->(!x.equals(")")))
				.filter(x->(!x.equals("(")))
				.collect(Collectors.toList());
		
		
		Set<String> items = new HashSet<>();
		Set<String> difference = elements.stream()
								.filter(n -> !items.add(n))
								.collect(Collectors.toSet());
		
		return  difference;
	}
}
