package io.basicparser;

import java.util.List;
import java.util.Scanner;

import io.basicparser.exceptions.BasicParserException;

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
		do {
			// String sample = "r[r]\n" + "|--a[a]\n" + "|--|--b[b]\n" + "|--|--|--c[c]\n" +
			// "|--|--|--d[d]\n" + "|--e[e]\n";
			String sample = "r[r]\n" + "|--a[a]\n" + "|--b[b]\n" + "|--|--c[c]\n" + "|--|--d[d]\n";

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
			example.append("Note Expression Rules:\n");
			example.append("* Expression must have a root node - node bracketsing the entire expression\n");
			example.append("* Each node must be 1 char.\n");
			example.append("* Each Node must be 1 char and followed by open bracket thats eventually closed\n");
			example.append("* Each bracket must belong o a node.\n");
			example.append("* Expression cannot represent a disjoint tree\n");

			System.out.println(example);
			DataModel dataModel = new DataModel();
			Parser parser = new Parser();

			@SuppressWarnings("resource")
			Scanner myObj = new Scanner(System.in);
			System.out.println("Enter expression:");
			String expression = myObj.nextLine().replaceAll("\\s", "");

			List<String> tokens = Converter.mapToStringTokenList(expression);

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

			/*
			 * Recursive DFS Call - to parse expression
			 */
			// Converter.mapTreeToTreeObj(dataModel.getRoot().getName(), null,
			// dataModel.getObjectTree(),
			// dataModel.getParsingMap());

			/*
		    *  Iterative DFS Call - to parse expression		
		    */
			Converter.mapTreeToTreeObjItr(dataModel.getRoot().getName(), null, dataModel.getObjectTree(),
					dataModel.getParsingMap());

			String result = Display.display(dataModel.getObjectTree(), "", new StringBuilder()).toString();

			try {
				ExpressionValidator.validateExprHasRootAndNotDisjoint(expression, result);
			} catch (BasicParserException bpe) {
				errorHandling(bpe, expression);
				continue;
			}

			System.out.println("Object Tree - Result:");
			System.out.println(result);

			@SuppressWarnings("resource")
			Scanner loop = new Scanner(System.in);
			System.out.println("Do you wish to continue y/n");
			String play = loop.nextLine();
			// loop.close();

			if (!play.equalsIgnoreCase("y")) {
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
