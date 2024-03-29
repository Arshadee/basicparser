package io.basicparser.parserinterfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import io.basicparser.dataobjectsInterfaces.INode;

public interface IConverter {
	
	public String getAlgorthmName();
	public void mapTreeToTreeObj(String name, INode<String> treeNode, 
			Map<String, INode<String>> treeMap);

	/**
	 * Overrided from IConverter Interface
	 * 
	 * This method converts the String expression into List of String tokens
	 * 
	 * @param expression
	 * @return
	 */
	public default List<String> mapToStringTokenList(String expression) {
		expression = expression.replace("(", " ( ").replace(")", " ) ");
		List<String> tokens = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(expression, " ");
		while (tokenizer.hasMoreElements()) {
			String tok = tokenizer.nextToken();
			tokens.add(tok);
		}
		return tokens;
	}
}
