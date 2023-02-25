package basicparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.basicparser.dataobjects.DataModel;
import io.basicparser.display.Display;
import io.basicparser.exceptions.BasicParserException;
import io.basicparser.parser.Converter;
import io.basicparser.parser.Parser;
import io.basicparser.validations.ExpressionValidator;

class BasicParserTest {


	@Test
	void testCase1Success() throws BasicParserException {
		String testExpression = "r(a(b(c()d()))e())";
		List<String> tokens = Converter.mapToStringTokenList(testExpression);

		DataModel dataModel = new DataModel();
		Parser parser = new Parser();

		ExpressionValidator.validateExprBalanceBrace(tokens);
	    ExpressionValidator.validateExprMisplacedBraceNode(tokens);

		dataModel = parser.parse(testExpression);

		Converter.mapTreeToTreeObjRec(dataModel.getRoot().getName(), dataModel.getObjectTree(),
				dataModel.getParsingMap());

		String result = Display.display(dataModel.getObjectTree(), "", new StringBuilder()).toString();

		assertEquals(ExpectedResults.testCase1, result);
	}

	@Test
	void testCase2Success() throws BasicParserException {
		String testExpression = "r(a(b()c())d(e())g(h(i(j()k()l())))m(n())o())";
		DataModel dataModel = new DataModel();
		Parser parser = new Parser();
		List<String> tokens = Converter.mapToStringTokenList(testExpression);
		
		ExpressionValidator.validateExprBalanceBrace(tokens);
	    ExpressionValidator.validateExprMisplacedBraceNode(tokens);

		dataModel = parser.parse(testExpression);

		Converter.mapTreeToTreeObjRec(dataModel.getRoot().getName(), dataModel.getObjectTree(),
				dataModel.getParsingMap());

		String result = Display.display(dataModel.getObjectTree(), "", new StringBuilder()).toString();

		assertEquals(ExpectedResults.testCase2, result);
	}

	@Test
	void testCase3Success() throws BasicParserException {
		String testExpression = "r(a()b()c()d(e()f()g(h()i(j()))))";
		DataModel dataModel = new DataModel();
		Parser parser = new Parser();
		List<String> tokens = Converter.mapToStringTokenList(testExpression);
		
		ExpressionValidator.validateExprBalanceBrace(tokens);
	    ExpressionValidator.validateExprMisplacedBraceNode(tokens);

		dataModel = parser.parse(testExpression);

		Converter.mapTreeToTreeObjRec(dataModel.getRoot().getName(), dataModel.getObjectTree(),
				dataModel.getParsingMap());

		String result = Display.display(dataModel.getObjectTree(), "", new StringBuilder()).toString();

		assertEquals(ExpectedResults.testCase3, result);
	}

	@Test
	void testCase4Success() throws BasicParserException {
		String testExpression = "r(a(b()c(d()e()f(g()h()i()j(k()l()m()n(v()s()w()t())o(x()u()B(*())y()))))))";
		DataModel dataModel = new DataModel();
		Parser parser = new Parser();
		List<String> tokens = Converter.mapToStringTokenList(testExpression);
		
		ExpressionValidator.validateExprBalanceBrace(tokens);
	    ExpressionValidator.validateExprMisplacedBraceNode(tokens);
	    
		dataModel = parser.parse(testExpression);
		
		Converter.mapTreeToTreeObjRec(dataModel.getRoot().getName(), dataModel.getObjectTree(),
				dataModel.getParsingMap());

		String result = Display.display(dataModel.getObjectTree(), "", new StringBuilder()).toString();

		assertEquals(ExpectedResults.testCase4, result);
	}
	@Test
	void testCase5Success() throws BasicParserException {
		String testExpression = "r(a(b(c(d())e())f())g())";
		DataModel dataModel = new DataModel();
		Parser parser = new Parser();
		List<String> tokens = Converter.mapToStringTokenList(testExpression);
		
		ExpressionValidator.validateExprBalanceBrace(tokens);
	    ExpressionValidator.validateExprMisplacedBraceNode(tokens);
	    
		dataModel = parser.parse(testExpression);
		
		Converter.mapTreeToTreeObjRec(dataModel.getRoot().getName(), dataModel.getObjectTree(),
				dataModel.getParsingMap());

		String result = Display.display(dataModel.getObjectTree(), "", new StringBuilder()).toString();

		assertEquals(ExpectedResults.testCase5, result);
	}

	@Test
	void testCase6ExceptionIncorrectParenthesisBalance() {
		String testExpression = "r(a(b()c())d(e())g(h(i(j()k()l()))m(n())o())"; // error brackt impbalance
		List<String> tokens = Converter.mapToStringTokenList(testExpression);
		
		Exception exception = assertThrows(BasicParserException.class, () -> {
			ExpressionValidator.validateExprBalanceBrace(tokens);
		});

		String expectedMessage = "Parentesis '(' , ')'  mismatched";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testCase7ExceptionIncorrectExpression() {
		String testExpression = "r";
		List<String> tokens = Converter.mapToStringTokenList(testExpression);
		
		Exception exception = assertThrows(BasicParserException.class, () -> {
		    ExpressionValidator.validateExprMisplacedBraceNode(tokens);
		});

		String expectedMessage = "String Expression Incorrect misplaced bracket(s) or node value";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
//	@Test
//	void testCase8ExceptionIncorrectExpression() {
//		String testExpression = "r(a(b(c()d()))eg())";
//		List<String> tokens = Converter.mapToStringTokenList(testExpression);
//		
//		Exception exception = assertThrows(BasicParserException.class, () -> {
//			ExpressionValidator.validateExprBalanceBrace(tokens);
//		    ExpressionValidator.validateExprMisplacedBraceNode(tokens);
//		});
//
//		String expectedMessage = "String Expression Incorrect misplaced bracket(s) or node value";
//		String actualMessage = exception.getMessage();
//
//		assertTrue(actualMessage.contains(expectedMessage));
//	}

	@Test
	void testCase9ExceptionIncorrectExpression() {
		String testExpression = "r(a(b(c()d()))e()()";
		List<String> tokens = Converter.mapToStringTokenList(testExpression);
		
		Exception exception = assertThrows(BasicParserException.class, () -> {
		    ExpressionValidator.validateExprMisplacedBraceNode(tokens);
		});

		String expectedMessage = "String Expression Incorrect misplaced bracket(s) or node value";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testCase10ExceptionIncorrectExpression() {
		String testExpression = "r(a(b(c()d()))e(())";
		List<String> tokens = Converter.mapToStringTokenList(testExpression);
		
		Exception exception = assertThrows(BasicParserException.class, () -> {
		    ExpressionValidator.validateExprMisplacedBraceNode(tokens);
		});

		String expectedMessage = "String Expression Incorrect misplaced bracket(s) or node value";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testCase11ExceptionIncorrectExpressionDisjointTree() {
		String testExpression = "a()b(c()d())e(f())";

		Parser parser = new Parser();

		Exception exception = assertThrows(BasicParserException.class, () -> {
			DataModel dataModel = new DataModel();
			dataModel = parser.parse(testExpression);
			Converter.mapTreeToTreeObjRec(dataModel.getRoot().getName(), dataModel.getObjectTree(),
					dataModel.getParsingMap());
			String result = Display.display(dataModel.getObjectTree(), "", new StringBuilder()).toString();
			ExpressionValidator.validateExprHasRootAndNotDisjoint(testExpression, result);

		});

		String expectedMessage = "Expression does not have a root node or represents a dis-joint tree";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testCase12ExceptionIncorrectExpressionDisjointTree() {
		String testExpression = "r(a()b()c(d()e()))x(h()i())";
		Parser parser = new Parser();

		Exception exception = assertThrows(BasicParserException.class, () -> {
			DataModel dataModel = new DataModel();
			dataModel = parser.parse(testExpression);

			Converter.mapTreeToTreeObjRec(dataModel.getRoot().getName(), dataModel.getObjectTree(),
					dataModel.getParsingMap());

			String result = Display.display(dataModel.getObjectTree(), "", new StringBuilder()).toString();

			ExpressionValidator.validateExprHasRootAndNotDisjoint(testExpression, result);

		});

		String expectedMessage = "Expression does not have a root node or represents a dis-joint tree";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
}

