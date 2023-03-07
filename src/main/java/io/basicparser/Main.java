package io.basicparser;

import java.util.List;
import java.util.Scanner;

import io.basicparser.dataobjects.DataModel;
import io.basicparser.dataobjectsInterfaces.IDataModel;
import io.basicparser.display.DisplayDataImpl;
import io.basicparser.exceptions.BasicParserException;
import io.basicparser.parser.ConverterIteratorImpl;
import io.basicparser.parser.ConverterRecursiveImpl;
import io.basicparser.parser.Parser;
import io.basicparser.parserinterfaces.IConverter;
import io.basicparser.parserinterfaces.IParser;
import io.basicparser.displayinterfaces.IDisplayData;
import io.basicparser.validations.ExpressionValidator;

/**
 * Main Application class - provides the user interface with this basicParsing
 * app allowing the user to enter formated string expressions to convert to
 * object trees
 * 
 * @author arshadmayet
 *
 */
public class Main {

	public static void main(String[] args) throws IllegalArgumentException {
		
		boolean cont = true;
		IDisplayData displayData = new DisplayDataImpl();
		
		/* Note
		*  To use the Recursive DFS Algorithm
		*	IConverter converter;= new ConverterRecursiveImpl();
		*
		*  To use the Iterative DFS Algorithm
		*  IConverter converter = new ConverterIteratorImpl();
		*/
		IConverter converter;
		
		do {
			// String sample = "r[r]\n" + "|--a[a]\n" + "|--|--b[b]\n" + "|--|--|--c[c]\n" +
			// "|--|--|--d[d]\n" + "|--e[e]\n";
			String sample = "Example\nr[r]\n" + "|--a[a]\n" + "|--b[b]\n" + "|--|--c[c]\n" + "|--|--d[d]\nEndofExample\n";

			StringBuilder example = new StringBuilder();
			example.append("*************************************************************************\n");
			example.append("*****************************Basic Parser********************************\n");
			example.append("*************************************************************************\n");
			example.append("Instructions:\n");
			example.append("--------------\n");
			example.append("Convert string expression r(a()b(c()d())) into\n");
			example.append("Object tree\n");
			example.append(sample);
			example.append("    \n");
			example.append("By default the Recursive DFS Algorithm is used\n");
			example.append("To use the Iterative DFS Algorithm just type ITR: before expression String.\n");
			example.append("    \n");
			example.append("Note Expression Rules:\n");
			example.append("* Expression must have a root node - node bracketsing the entire expression.\n");
			example.append("* Each node must be 1 char.\n");
			example.append("* Each Node must be 1 char and followed by open bracket thats eventually closed.\n");
			example.append("* Each bracket must belong o a node.\n");
			example.append("* Expression cannot represent a disjoint tree.\n");
			example.append("* No cyclic relations.\n");

			System.out.println(example);
			IDataModel dataModel = new DataModel();
			IParser parser = new Parser();

			@SuppressWarnings("resource")
			Scanner myObj = new Scanner(System.in);
			System.out.println("Enter expression:");
			String expression = myObj.nextLine().replaceAll("\\s", "");
			
			converter = new ConverterRecursiveImpl();
			if(expression.toLowerCase().contains("itr:")) {
				converter = new ConverterIteratorImpl();
			}
			
			if(expression.contains(":")) {
				expression = expression.substring(expression.indexOf(":")+1);
				expression = expression.trim();
				System.out.println(expression);
			}
		
			List<String> tokens = converter.mapToStringTokenList(expression);
			String head = tokens.get(0);

			try {
				ExpressionValidator.validateExprBalanceBrace(tokens);
				ExpressionValidator.validateExprMisplacedBraceNode(tokens);
				ExpressionValidator.validateExprHasCyclicRelation(tokens);
			} catch (BasicParserException bpe) {
				errorHandling(bpe, expression);
				continue;
			}

			dataModel = parser.parse(tokens);
			System.out.println();

			converter.mapTreeToTreeObj(
					dataModel.getRoot().getName(),
					dataModel.getRoot(),
					dataModel.getParsingMap());

			try {
				ExpressionValidator.validateExprHasRootAndNotDisjoint(head, dataModel.getRoot().getName());
			} catch (BasicParserException bpe) {
				errorHandling(bpe, expression);
				continue;
			}

			String result = displayData.display(dataModel.getRoot(), "", new StringBuilder()).toString();

			System.out.println("Object Tree - Result:");
			System.out.println("Algorithm used "+converter.getAlgorthmName());
			System.out.println(result);

			@SuppressWarnings("resource")
			Scanner loop = new Scanner(System.in);
			System.out.println("To display the expression Stringfrom the Root Node press [r]");
			System.out.println("Or");
			System.out.println("Do you wish to continue [y/n]");
			String play = loop.nextLine();
			// loop.close();
			if(play.equalsIgnoreCase("r")) {
				System.out.println(dataModel.getRoot());
				System.out.println();
				System.out.println("Do you wish to continue [y/n]");
				play = loop.nextLine();
			}
		
			if (play.equalsIgnoreCase("n")) {
				cont = false;
				System.out.println("bye");
				System.out.println("*************************************************************************\n");
			}

		} while (cont);
	}

	private static void errorHandling(BasicParserException bpe, String expression) {
		System.out.println("Expression Error : " + bpe.getMessage());
		expression = "r()";
		Scanner resume = new Scanner(System.in);
		System.out.println("Hit a key to continue");
		String resumeKey = resume.nextLine().replaceAll("\\s", "");
	}
}
