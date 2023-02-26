package io.basicparser.validations;

import java.util.List;
import java.util.Set;

import io.basicparser.exceptions.BasicParserException;

/**
 * This class uses the ExpressionChecker and BasicParserException classes to determine if a 
 * potential parsing exception need to be thrown and handled
 * 
 * @author arshadmayet
 *
 */
public class ExpressionValidator {
	/**
     * This method validates that the brackets balance in the string expression
     * 
     * @param expression
     * @throws BasicParserException
     */
	public static void validateExprBalanceBrace(List<String> expression) throws BasicParserException{
		if (!ExpressionChecker.checkBraces(expression))
			throw new BasicParserException("Parentesis '(' , ')'  mismatched");
	}

	/**
	 * This method validate thats the Node have correct opening and closing brackets and that each bracket
	 * is not incorrectly placed
	 * 
	 * @param expression
	 * @throws BasicParserException
	 */
	public static void validateExprMisplacedBraceNode(List<String> expression) throws BasicParserException{
		if (!ExpressionChecker.checkValidExpression(expression))
			throw new BasicParserException("String Expression Incorrect misplaced bracket(s) or node value");
	}

	/**
	 * This method validate that the string expression has a root node and does not represent a disjointed tree
	 * 
	 * @param expression
	 * @param result
	 * @throws BasicParserException
	 */
	public static void validateExprHasRootAndNotDisjoint(String expression, String result)
			throws BasicParserException{
		if (!ExpressionChecker.checkForRootNodeAndDisjointTree(expression, result))
			throw new BasicParserException("Expression does not have a root node or represents a dis-joint tree");
	}
	
	/**
	 * This method validate that the string expression does not have a cyclic relation
	 * 
	 * @param expression
	 * @throws BasicParserException
	 */
	public static void validateExprHasCyclicRelation(List<String> expression) throws BasicParserException {
		Set<String> difference = ExpressionChecker.checkForNoCyclicRelation(expression);
		if(!difference.isEmpty()) {
			throw new BasicParserException("Expression has cyclic relationship - with element(s)"+difference);
		}
	}
}
