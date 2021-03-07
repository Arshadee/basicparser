package io.basicparser;

import java.util.Scanner;

import io.basicparser.exceptions.BasicParserException;

/******************************************************************************
 * 
 * Online Java Compiler. Code, Compile, Run and Debug java program online. Write
 * your code in this editor and press "Run" button to execute it.
 * 
 *******************************************************************************/

public class Main {

	public static void main(String[] args) throws IllegalArgumentException {
		boolean cont = true;
		System.out.println("******************************************************");
		do {
			// String sample = "r[r]\n" + "|--a[a]\n" + "|--|--b[b]\n" + "|--|--|--c[c]\n" +
			// "|--|--|--d[d]\n" + "|--e[e]\n";
			String sample = "r[r]\n" + "|--a[a]\n" + "|--b[b]\n" + "|--|--c[c]\n" + "|--|--d[d]\n";

			StringBuilder example = new StringBuilder();
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

			Scanner myObj = new Scanner(System.in);
			System.out.println("Enter expression");
			String expression = myObj.nextLine();
			//myObj.close();

			try {
				ExpressionValidator.validateExprBalanceBrace(expression);
				ExpressionValidator.validateExprMisplacedBraceNode(expression);
			} catch (BasicParserException bpe) {
				System.out.println("Expression Error : " + bpe.getMessage());
				expression = "r()";
			}

			dataModel = parser.parse(expression);
			System.out.println();

			Converter.mapTreeToTreeObj(dataModel.getRoot().getName(), null, dataModel.getObjectTree(),
					dataModel.getParsingMap());

			String result = Display.display(dataModel.getObjectTree(), "", new StringBuilder()).toString();

			try {
				ExpressionValidator.validateExprHasRootAndNotDisjoint(expression, result);
			} catch (BasicParserException bpe) {
				System.out.println("Expression Error : " + bpe.getMessage());
				expression = "r()";
			}

			System.out.println("Object Tree");
			System.out.println(result);

			Scanner loop = new Scanner(System.in);
			System.out.println("Do you wish to continue y/n");
			String play = loop.nextLine();
			//loop.close();

			if (!play.equalsIgnoreCase("y")) {
				cont = false;
				System.out.println("bye");
			}
			System.out.println("******************************************************");
		} while (cont);
	}
}
