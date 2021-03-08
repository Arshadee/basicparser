package basicparser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.basicparser.Converter;
import io.basicparser.DataModel;
import io.basicparser.Display;
import io.basicparser.ExpressionValidator;
import io.basicparser.Parser;
import io.basicparser.exceptions.BasicParserException;

class BasicParserTest {


	@Test
	void testCase1Success() throws BasicParserException {
		String testExpression = "r(a(b(c()d()))e())";
		DataModel dataModel = new DataModel();
		Parser parser = new Parser();

		ExpressionValidator.validateExprBalanceBrace(testExpression);
	    ExpressionValidator.validateExprMisplacedBraceNode(testExpression);

		dataModel = parser.parse(testExpression);

		//Display.display(dataModel.getParsingMap());
		Converter.mapTreeToTreeObj(dataModel.getRoot().getName(), null, dataModel.getObjectTree(),
				dataModel.getParsingMap());

		String result = Display.display(dataModel.getObjectTree(), "", new StringBuilder()).toString();

		assertEquals(ExpectedResults.testCase1, result);
	}

	@Test
	void testCase2Success() throws BasicParserException {
		String testExpression = "r(a(b()c())d(e())g(h(i(j()k()l())))m(n())o())";
		DataModel dataModel = new DataModel();
		Parser parser = new Parser();

		ExpressionValidator.validateExprBalanceBrace(testExpression);
	    ExpressionValidator.validateExprMisplacedBraceNode(testExpression);

		dataModel = parser.parse(testExpression);

		Converter.mapTreeToTreeObj(dataModel.getRoot().getName(), null, dataModel.getObjectTree(),
				dataModel.getParsingMap());

		String result = Display.display(dataModel.getObjectTree(), "", new StringBuilder()).toString();

		assertEquals(ExpectedResults.testCase2, result);
	}

	@Test
	void testCase3Success() throws BasicParserException {
		String testExpression = "r(a()b()c()d(e()f()g(h()i(j()))))";
		DataModel dataModel = new DataModel();
		Parser parser = new Parser();

		ExpressionValidator.validateExprBalanceBrace(testExpression);
	    ExpressionValidator.validateExprMisplacedBraceNode(testExpression);

		dataModel = parser.parse(testExpression);

		Converter.mapTreeToTreeObj(dataModel.getRoot().getName(), null, dataModel.getObjectTree(),
				dataModel.getParsingMap());

		String result = Display.display(dataModel.getObjectTree(), "", new StringBuilder()).toString();

		assertEquals(ExpectedResults.testCase3, result);
	}

	@Test
	void testCase4Success() throws BasicParserException {
		String testExpression = "r(a(b()c(d()e()f(g()h()i()j(k()l()m()n(v()s()w()t())o(x()u()B(*())y()))))))";
		DataModel dataModel = new DataModel();
		Parser parser = new Parser();
		
		ExpressionValidator.validateExprBalanceBrace(testExpression);
	    ExpressionValidator.validateExprMisplacedBraceNode(testExpression);
	    
		dataModel = parser.parse(testExpression);
		
		Converter.mapTreeToTreeObj(dataModel.getRoot().getName(), null, dataModel.getObjectTree(),
				dataModel.getParsingMap());

		String result = Display.display(dataModel.getObjectTree(), "", new StringBuilder()).toString();

		assertEquals(ExpectedResults.testCase4, result);

	}

	@Test
	void testCase5ExceptionIncorrectParenthesisBalance() {
		String testExpression = "r(a(b()c())d(e())g(h(i(j()k()l()))m(n())o())"; // error brackt impbalance
		Exception exception = assertThrows(BasicParserException.class, () -> {
			ExpressionValidator.validateExprBalanceBrace(testExpression);
		});

		String expectedMessage = "Parentesis '(' , ')'  mismatched";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testCase6ExceptionIncorrectExpression() {
		String testExpression = "r";
		Exception exception = assertThrows(BasicParserException.class, () -> {
		    ExpressionValidator.validateExprMisplacedBraceNode(testExpression);
		});

		String expectedMessage = "String Expression Incorrect misplaced bracket(s) or node value";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testCase7ExceptionIncorrectExpression() {
		String testExpression = "r(a(b(c()d()))eg())";
		Exception exception = assertThrows(BasicParserException.class, () -> {
			ExpressionValidator.validateExprBalanceBrace(testExpression);
		    ExpressionValidator.validateExprMisplacedBraceNode(testExpression);
		});

		String expectedMessage = "String Expression Incorrect misplaced bracket(s) or node value";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testCase8ExceptionIncorrectExpression() {
		String testExpression = "r(a(b(c()d()))e()()";
		Exception exception = assertThrows(BasicParserException.class, () -> {
		    ExpressionValidator.validateExprMisplacedBraceNode(testExpression);
		});

		String expectedMessage = "String Expression Incorrect misplaced bracket(s) or node value";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testCase9ExceptionIncorrectExpression() {
		String testExpression = "r(a(b(c()d()))e(())";
		Exception exception = assertThrows(BasicParserException.class, () -> {
		    ExpressionValidator.validateExprMisplacedBraceNode(testExpression);
		});

		String expectedMessage = "String Expression Incorrect misplaced bracket(s) or node value";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testCase10ExceptionIncorrectExpressionDisjointTree() {
		String testExpression = "a()b(c()d())e(f())";

		Parser parser = new Parser();

		Exception exception = assertThrows(BasicParserException.class, () -> {
			DataModel dataModel = new DataModel();
			dataModel = parser.parse(testExpression);
			Converter.mapTreeToTreeObj(dataModel.getRoot().getName(), null, dataModel.getObjectTree(),
					dataModel.getParsingMap());
			String result = Display.display(dataModel.getObjectTree(), "", new StringBuilder()).toString();
			ExpressionValidator.validateExprHasRootAndNotDisjoint(testExpression, result);

		});

		String expectedMessage = "Expression does not have a root node or represents a dis-joint tree";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testCase11ExceptionIncorrectExpressionDisjointTree() {
		String testExpression = "r(a()b()c(d()e()))x(h()i())";
		Parser parser = new Parser();

		Exception exception = assertThrows(BasicParserException.class, () -> {
			DataModel dataModel = new DataModel();
			dataModel = parser.parse(testExpression);

			Converter.mapTreeToTreeObj(dataModel.getRoot().getName(), null, dataModel.getObjectTree(),
					dataModel.getParsingMap());

			String result = Display.display(dataModel.getObjectTree(), "", new StringBuilder()).toString();

			ExpressionValidator.validateExprHasRootAndNotDisjoint(testExpression, result);

		});

		String expectedMessage = "Expression does not have a root node or represents a dis-joint tree";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	

}
