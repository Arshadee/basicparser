package io.basicparser;

import java.util.List;

import io.basicparser.exceptions.BasicParserException;

public class TestIterator {
	static String tc1 = "r[r]\n" + "|--a[a]\n" + "|--|--b[b]\n" + "|--|--|--c[c]\n" + "|--|--|--d[d]\n" + "|--e[e]";

	public static void main(String[] args) {
		DataModel dataModel = new DataModel();
		Parser parser = new Parser();

		// String s ="r(a(b(c()d()))e())";
		// String s = "r(a(b()c())d(e())g(h(i(j()k()l())))m(n())o())";
		// String s = "r(a()b()c()d(e()f()g(h()i(j()))))";
		String s = "root(apple(banana()cliff(drift ( ) egg ( ) f ( g ( ) h ( ) i ( ) j ( k ( ) l ( ) m ( ) n ( v ( ) s ( ) w ( ) t ( ) ) o ( x ( ) u ( ) B ( * ( ) ) y ( ) ) ) ) ) ) )";

		// error cases
		// String s = "r(a(b()c())d(e())g(h(i(j()k()l()))m(n())o())"; //error brackt
		// imbalance
		// String s ="r(a(b(c()d()))eg())";
		// String s ="a()b(c()d())e(f())";
		// String s = "r(a()b()c(d()e()))x(h()i())";

		System.out.println(s);
		List<String> tokens = Converter.mapToStringTokenList(s);

		try {
			ExpressionValidator.validateExprBalanceBrace(tokens);
			ExpressionValidator.validateExprMisplacedBraceNode(tokens);
		} catch (BasicParserException e) {
			e.printStackTrace();
			return;
		}

		dataModel = parser.parse(tokens);
		System.out.println();
		// System.out.println("Map of Relations to be used to build Object Tree");
		// Display.display(dataModel.getParsingMap());
		Converter.mapTreeToTreeObjItr(dataModel.getRoot().getName(), dataModel.getObjectTree(),
				dataModel.getParsingMap());
		// System.out.println();
		// Display.display(dataModel.getObjectTree(), " ");
		String result = Display.display(dataModel.getObjectTree(), "", new StringBuilder()).toString();

		// if(!ExpressionValidator.checkForRootNode(s,result)) throw new
		// IllegalArgumentException("Expression does not have a root node or represents
		// a dis-joint tree");
		try {
			ExpressionValidator.validateExprHasRootAndNotDisjoint(s, result);
		} catch (BasicParserException e) {
			e.printStackTrace();
			return;
		}
		System.out.println("Object Tree");
		System.out.println(result);
	}
}

