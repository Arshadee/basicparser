package io.basicparser;

import io.basicparser.exceptions.BasicParserException;

public class ExpressionValidator {
    /**
     * This method validates that the brackets balance in the string expression
     * @param expression
     * @throws IllegalArgumentException
     */
	public static void validateExprBalanceBrace(String expression) throws BasicParserException{
	//throws IllegalArgumentException {
		if (!ExpressionChecker.checkBraces(expression))
			//throw new IllegalArgumentException("Parentesis '(' , ')'  mismatched");
			throw new BasicParserException("Parentesis '(' , ')'  mismatched");

	}

	/**
	 * This method validate thats the Node have correct opening and closing brackets and that each bracket
	 * is not incorrectly placed
	 * @param expression
	 * @throws IllegalArgumentException
	 */
	public static void validateExprMisplacedBraceNode(String expression) throws BasicParserException{
			//throws IllegalArgumentException {
		if (!ExpressionChecker.checkValidExpression(expression))
			//throw new IllegalArgumentException("String Expression Incorrect misplaced bracket(s) or node value");
			throw new BasicParserException("String Expression Incorrect misplaced bracket(s) or node value");
	}

	/**
	 * This method validate that the string expression has a root node and does not represent a disjointed tree
	 * @param expression
	 * @param result
	 * @throws IllegalArgumentException
	 */
	public static void validateExprHasRootAndNotDisjoint(String expression, String result)
			throws BasicParserException{
			//throws IllegalArgumentException {
		if (!ExpressionChecker.checkForRootNodeAndDisjointTree(expression, result))
			//throw new IllegalArgumentException("Expression does not have a root node or represents a dis-joint tree");
			throw new BasicParserException("Expression does not have a root node or represents a dis-joint tree");
	}
}
