package basicparser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.basicparser.dataobjects.DataModel;
import io.basicparser.dataobjectsInterfaces.IDataModel;
import io.basicparser.display.DisplayDataImpl;
import io.basicparser.displayinterfaces.IDisplayData;
import io.basicparser.exceptions.BasicParserException;
import io.basicparser.parser.ConverterRecursiveImpl;
import io.basicparser.parser.Parser;
import io.basicparser.parserinterfaces.IConverter;
import io.basicparser.parserinterfaces.IParser;
import io.basicparser.validations.ExpressionValidator;

public class BasicParserStringFromNodeTest {
	
	@Test
	void testCase6Success() throws BasicParserException {
		
		String testExpression =
				"root(branch1(leaf1())branch2(leaf2())branch3(branch4(leaf3())branch5(leaf4())leaf5()))";
		IParser parser = new Parser();
		IConverter converter = new ConverterRecursiveImpl();
		IDataModel dataModel = new DataModel();
		List<String> tokens = converter.mapToStringTokenList(testExpression);
		IDisplayData displayData = new DisplayDataImpl();
		
		ExpressionValidator.validateExprBalanceBrace(tokens);
	    ExpressionValidator.validateExprMisplacedBraceNode(tokens);
	    
		dataModel = parser.parse(tokens);
		converter.mapTreeToTreeObj(dataModel.getRoot().getName(), dataModel.getRoot(),
				dataModel.getParsingMap());
		//String result = displayData.display(dataModel.getRoot(), "", new StringBuilder()).toString();
		String result = dataModel.getRoot().toString();
		
		assertEquals(testExpression, result);
	}

}
