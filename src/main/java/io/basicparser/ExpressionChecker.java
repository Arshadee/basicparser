package io.basicparser;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class houses the string expression formatting rules
 * required for parsing and is used by the Validator class
 * @author arshadmayet
 *
 */
public class ExpressionChecker {

    /**
     * This method initiates check bracket balance of the input expression
     * @param expression
     * @return
     */
    public static boolean checkBraces(String expression){
      List<Character> chars = expression.chars().mapToObj(e->(char)e).collect(Collectors.toList());
      boolean result = chkBracketBalance(chars,0,0,'-');
      return result;
      
    }
    
    /**
     * This method checks if brackets of the expression has correctly balanced brackets
     * so that the expression may be parsed
     * @param chars
     * @param index
     * @param noOfOpenBrackets
     * @param lastBracket
     * @return
     */
    private static boolean chkBracketBalance(List<Character> chars,int index, int noOfOpenBrackets, char lastBracket){
    	if(index==chars.size()) return noOfOpenBrackets==0 && lastBracket != '(';
    	else if (chars.get(index)=='(') return chkBracketBalance(chars,index+1,noOfOpenBrackets+1,'(');
     	else if (chars.get(index)==')') return chkBracketBalance(chars,index+1,noOfOpenBrackets-1,')');
      	else return chkBracketBalance(chars,index+1,noOfOpenBrackets,lastBracket);
    }
    
    /**
     * This method initiates the String Expression validation check
     * @param expression
     * @return
     */
    public static boolean checkValidExpression(String expression) {
		 List<Character> chars = expression.chars().mapToObj(e->(char)e).collect(Collectors.toList());
	      boolean result = checkNodeNBrackets(chars);
	      return result;
	}
	
    /**
     * This method performs the String Expresion validation check scanning each char, checking nodes and braces 
     * are in correct places /order
     * @param chars
     * @return
     */
	private static boolean checkNodeNBrackets(List<Character> chars) {
		if(chars.size()<=1) return false;
		boolean isChar = false;
		boolean isOpen = false;
		boolean isClose= false;
		for(Character c: chars) {
			boolean isCharCurrent = (!c.equals('('))&&(!c.equals(')'));
			boolean isOpenCurrent = c.equals('(');
			boolean isCloseCurrent=c.equals(')');
			
			//rx or r)
			if(isChar && (isCharCurrent || isCloseCurrent)) return false;
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
	 * This method checks if the string expression has a root node or that it does not represent a dis-jointed tree
	 * checks the string expression against the result
	 * @param expression
	 * @param result
	 * @return
	 */
	public static boolean checkForRootNodeAndDisjointTree(String expression,String result) {
		return expression.charAt(0) == result.charAt(0);
	}
	
}
