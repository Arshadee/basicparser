package basicparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.basicparser.dataobjects.DataModel;
import io.basicparser.dataobjectsInterfaces.IDataModel;
import io.basicparser.display.Display;
import io.basicparser.exceptions.BasicParserException;
import io.basicparser.parser.Parser;
import io.basicparser.parserinterfaces.IConverter;
import io.basicparser.parserinterfaces.IParser;
import io.basicparser.parser.ConverterRecursiveImpl;
import io.basicparser.validations.ExpressionValidator;

class BasicParserRecursiveTest {


	@Test
	void testCase1Success() throws BasicParserException {
		
		String testExpression = "r(a(b(c()d()))e())";
		IConverter converter = new ConverterRecursiveImpl();
		List<String> tokens = converter.mapToStringTokenList(testExpression);
		IDataModel dataModel = new DataModel();
		IParser parser = new Parser();

		ExpressionValidator.validateExprBalanceBrace(tokens);
	    ExpressionValidator.validateExprMisplacedBraceNode(tokens);

		dataModel = parser.parse(testExpression);
		converter.mapTreeToTreeObj(dataModel.getRoot().getName(), dataModel.getRoot(),
				dataModel.getParsingMap());
		String result = Display.display(dataModel.getRoot(), "", new StringBuilder()).toString();

		assertEquals(ExpectedResults.testCase1, result);
	}

	@Test
	void testCase2Success() throws BasicParserException {
		
		String testExpression = "r(a(b()c())d(e())g(h(i(j()k()l())))m(n())o())";
		IDataModel dataModel = new DataModel();
		IParser parser = new Parser();
		IConverter converter = new ConverterRecursiveImpl();
		List<String> tokens = converter.mapToStringTokenList(testExpression);
		
		ExpressionValidator.validateExprBalanceBrace(tokens);
	    ExpressionValidator.validateExprMisplacedBraceNode(tokens);

		dataModel = parser.parse(testExpression);
		converter.mapTreeToTreeObj(dataModel.getRoot().getName(), dataModel.getRoot(),
				dataModel.getParsingMap());
		String result = Display.display(dataModel.getRoot(), "", new StringBuilder()).toString();

		assertEquals(ExpectedResults.testCase2, result);
	}
	

	@Test
	void testCase3Success() throws BasicParserException {
		
		String testExpression = "r(a()b()c()d(e()f()g(h()i(j()))))";
		IDataModel dataModel = new DataModel();
		IParser parser = new Parser();
		IConverter converter = new ConverterRecursiveImpl();
		
		List<String> tokens = converter.mapToStringTokenList(testExpression);
		
		ExpressionValidator.validateExprBalanceBrace(tokens);
	    ExpressionValidator.validateExprMisplacedBraceNode(tokens);

		dataModel = parser.parse(testExpression);
		converter.mapTreeToTreeObj(dataModel.getRoot().getName(), dataModel.getRoot(),
				dataModel.getParsingMap());
		String result = Display.display(dataModel.getRoot(), "", new StringBuilder()).toString();

		assertEquals(ExpectedResults.testCase3, result);
	}

	@Test
	void testCase4Success() throws BasicParserException {
		
		String testExpression = "r(a(b()c(d()e()f(g()h()i()j(k()l()m()n(v()s()w()t())o(x()u()B(*())y()))))))";
		IDataModel dataModel = new DataModel();
		IParser parser = new Parser();
		IConverter converter = new ConverterRecursiveImpl();
		List<String> tokens = converter.mapToStringTokenList(testExpression);
		
		ExpressionValidator.validateExprBalanceBrace(tokens);
	    ExpressionValidator.validateExprMisplacedBraceNode(tokens);
	    
		dataModel = parser.parse(testExpression);
		converter.mapTreeToTreeObj(dataModel.getRoot().getName(), dataModel.getRoot(),
				dataModel.getParsingMap());
		String result = Display.display(dataModel.getRoot(), "", new StringBuilder()).toString();

		assertEquals(ExpectedResults.testCase4, result);
	}
	
	
	@Test
	void testCase5Success() throws BasicParserException {
		
		String testExpression = "r(a(b(c(d())e())f())g())";
		IDataModel dataModel = new DataModel();
		IParser parser = new Parser();
		IConverter converter = new ConverterRecursiveImpl();
		List<String> tokens = converter.mapToStringTokenList(testExpression);
		
		ExpressionValidator.validateExprBalanceBrace(tokens);
	    ExpressionValidator.validateExprMisplacedBraceNode(tokens);
	    
	    dataModel = parser.parse(testExpression);
		converter.mapTreeToTreeObj(dataModel.getRoot().getName(), dataModel.getRoot(),
				dataModel.getParsingMap());
		String result = Display.display(dataModel.getRoot(), "", new StringBuilder()).toString();

		assertEquals(ExpectedResults.testCase5, result);
	}

	@Test
	void testCase6Success() throws BasicParserException {
		
		String testExpression =
				"root(branch1(leaf1())branch2(leaf2())branch3(branch4(leaf3())branch5(leaf4())leaf5()))";
		IParser parser = new Parser();
		IConverter converter = new ConverterRecursiveImpl();
		IDataModel dataModel = new DataModel();
		List<String> tokens = converter.mapToStringTokenList(testExpression);
		
		ExpressionValidator.validateExprBalanceBrace(tokens);
	    ExpressionValidator.validateExprMisplacedBraceNode(tokens);
	    
		dataModel = parser.parse(tokens);
		converter.mapTreeToTreeObj(dataModel.getRoot().getName(), dataModel.getRoot(),
				dataModel.getParsingMap());
		String result = Display.display(dataModel.getRoot(), "", new StringBuilder()).toString();

		assertEquals(ExpectedResults.testCase6, result);
	}
	
	@Test
	void testCase7ExceptionIncorrectParenthesisBalance() {
		
		String testExpression = "r(a(b()c())d(e())g(h(i(j()k()l()))m(n())o())"; // error brackt impbalance
		IConverter converter = new ConverterRecursiveImpl();
		List<String> tokens = converter.mapToStringTokenList(testExpression);
		
		Exception exception = assertThrows(BasicParserException.class, () -> {
			ExpressionValidator.validateExprBalanceBrace(tokens);
		});

		String expectedMessage = "Parentesis '(' , ')'  mismatched";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testCase8ExceptionIncorrectExpression() {
		
		String testExpression = "r";
		IConverter converter = new ConverterRecursiveImpl();
		List<String> tokens = converter.mapToStringTokenList(testExpression);
		
		Exception exception = assertThrows(BasicParserException.class, () -> {
		    ExpressionValidator.validateExprMisplacedBraceNode(tokens);
		});

		String expectedMessage = "String Expression Incorrect misplaced bracket(s) or node value";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testCase9ExceptionIncorrectExpression() {
		
		String testExpression = "r(a(b(c()d()))e()()";
		IConverter converter = new ConverterRecursiveImpl();
		
		List<String> tokens = converter.mapToStringTokenList(testExpression);
		
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
		IConverter converter = new ConverterRecursiveImpl();
		
		List<String> tokens = converter.mapToStringTokenList(testExpression);
		
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
		IParser parser = new Parser();
		IConverter converter = new ConverterRecursiveImpl();
		

		Exception exception = assertThrows(BasicParserException.class, () -> {
			IDataModel dataModel = new DataModel();
			dataModel = parser.parse(testExpression);
			converter.mapTreeToTreeObj(dataModel.getRoot().getName(), dataModel.getRoot(),
					dataModel.getParsingMap());
			String result = Display.display(dataModel.getRoot(), "", new StringBuilder()).toString();
			ExpressionValidator.validateExprHasRootAndNotDisjoint(testExpression, result);

		});

		String expectedMessage = "Expression does not have a root node or represents a dis-joint tree";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testCase12ExceptionIncorrectExpressionDisjointTree() {
		
		String testExpression = "r(a()b()c(d()e()))x(h()i())";
		IParser parser = new Parser();
		IConverter converter = new ConverterRecursiveImpl();
		
		Exception exception = assertThrows(BasicParserException.class, () -> {
			IDataModel dataModel = new DataModel();
			dataModel = parser.parse(testExpression);
			converter.mapTreeToTreeObj(dataModel.getRoot().getName(), dataModel.getRoot(),
			dataModel.getParsingMap());
			String result = Display.display(dataModel.getRoot(), "", new StringBuilder()).toString();

			ExpressionValidator.validateExprHasRootAndNotDisjoint(testExpression, result);

		});

		String expectedMessage = "Expression does not have a root node or represents a dis-joint tree";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	

	@Test
	void testCase13ExceptionIncorrectExpressionElementNoBrackets() {
		
		String testExpression =
				"root(trunk(branch1(leaf1())branch2(leaf2())branch3(branch4(leaf3())branch5(leaf4())leaf5(leaf6))))";
		
		IConverter converter = new ConverterRecursiveImpl();
		List<String> tokens = converter.mapToStringTokenList(testExpression);

		Exception exception = assertThrows(BasicParserException.class, () -> {
		    ExpressionValidator.validateExprMisplacedBraceNode(tokens);
		});
		
		String expectedMessage = "String Expression Incorrect misplaced bracket(s) or node value";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	
	@Test
	void testCase14ExceptionIncorrectExpressionCyclicRelations() {
	
		String testExpression =
				"root(trunk(branch1(leaf1())branch2(leaf2())branch3(branch4(leaf3(branch1()))branch5(leaf4(branch2()))leaf5(leaf6))))";
		
		IConverter converter = new ConverterRecursiveImpl();
		List<String> tokens = converter.mapToStringTokenList(testExpression);

		Exception exception = assertThrows(BasicParserException.class, () -> {
		    ExpressionValidator.validateExprHasCyclicRelation(tokens);
		});
		
		String expectedMessage = "Expression has cyclic relationship - with element(s)[branch2, branch1]";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
}

