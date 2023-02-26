package io.basicparser.parser.interfaces;

import java.util.List;

import io.basicparser.dataobjects.DataModel;

public interface IParser {
	
	public DataModel parse(String expression);
	public DataModel parse(List<String> expression);

}
