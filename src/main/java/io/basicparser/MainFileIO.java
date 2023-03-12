package io.basicparser;

import java.util.List;

import io.basicparser.dataobjects.DataModel;
import io.basicparser.dataobjectsInterfaces.IDataModel;
import io.basicparser.display.DisplayDataImpl;
import io.basicparser.displayinterfaces.IDisplayData;
import io.basicparser.fileio.ReadFile;
import io.basicparser.parser.ConverterRecursiveImpl;
import io.basicparser.parser.Parser;
import io.basicparser.parserinterfaces.IConverter;
import io.basicparser.parserinterfaces.IParser;

public class MainFileIO {
	
	public static void main(String[] args) {
		IDataModel dataModel = new DataModel();
		IParser parser = new Parser();
		IConverter converter = new ConverterRecursiveImpl();
		ReadFile rf = new ReadFile();
		IDisplayData displayData = new DisplayDataImpl();
		
		String filePath = "src/main/resources/permExpr.txt";
		String expression = rf.getPermTreeExprStringFrmFile(filePath);
		
		List<String> tokens = converter.mapToStringTokenList(expression);
		
		dataModel = parser.parse(tokens);
		System.out.println();

		converter.mapTreeToTreeObj(
				dataModel.getRoot().getName(),
				dataModel.getRoot(),
				dataModel.getParsingMap());

	

		String result = displayData.display(dataModel.getRoot(), "", new StringBuilder()).toString();

		System.out.println("Object Tree - Result:");
		System.out.println("Algorithm used "+converter.getAlgorthmName());
		System.out.println(result);
		System.out.println();
		System.out.println("Size of Permutation Tree : "+(dataModel.getParsingMap().keySet().size()-1));

		
	}

}
