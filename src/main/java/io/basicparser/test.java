package io.basicparser;

/**
 * Tesing Applcation - used to assist with debugging various components
 * of this application
 */
import io.basicparser.exceptions.BasicParserException;

public class Test {
	
	static String tc1 =
			"r[r]\n" + 
			"|--a[a]\n" + 
			"|--|--b[b]\n" + 
			"|--|--|--c[c]\n" + 
			"|--|--|--d[d]\n" + 
			"|--e[e]";
	
	public static void main(String[]args) {
		//System.out.println(tc1);
		DataModel dataModel = new DataModel();
	      //Parser parser = new Parser(dataModel);
		  Parser parser = new Parser();

	      //String s ="r(a(b(c()d()))e())";
	      //String s = "r(a(b()c())d(e())g(h(i(j()k()l())))m(n())o())";
	      //String s = "r(a()b()c()d(e()f()g(h()i(j()))))";
	      String s = "r(a(b()c(d()e()f(g()h()i()j(k()l()m()n(v()s()w()t())o(x()u()B(*())y()))))))";
		  
		  //error cases
	      //String s = "r(a(b()c())d(e())g(h(i(j()k()l()))m(n())o())"; //error brackt imbalance
		  //String s ="r(a(b(c()d()))eg())";
		  //String s ="a()b(c()d())e(f())";
		  //String s = "r(a()b()c(d()e()))x(h()i())";
		  		
	      System.out.println(s);
	      
	      try {
			ExpressionValidator.validateExprBalanceBrace(s);
			ExpressionValidator.validateExprMisplacedBraceNode(s);
		} catch (BasicParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      
	      dataModel = parser.parse(s);
	      System.out.println();
	      //System.out.println("Map of Relations to be used to build Object Tree");
	      //Display.display(dataModel.getParsingMap());
	      Converter.mapTreeToTreeObj(dataModel.getRoot().getName(), null, dataModel.getObjectTree(), dataModel.getParsingMap());
	      //System.out.println();
	      //Display.display(dataModel.getObjectTree(), " ");
	      String result = Display.display(dataModel.getObjectTree(), "",new StringBuilder()).toString();
	      
	     // if(!ExpressionValidator.checkForRootNode(s,result)) throw new IllegalArgumentException("Expression does not have a root node or represents a dis-joint tree");
	      try {
			ExpressionValidator.validateExprHasRootAndNotDisjoint(s, result);
		} catch (BasicParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      System.out.println("Object Tree");
	      System.out.println(result);
		
	}

}
