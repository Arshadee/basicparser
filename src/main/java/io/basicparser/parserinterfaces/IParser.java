package io.basicparser.parserinterfaces;

import java.util.List;

import io.basicparser.dataobjectsInterfaces.IDataModel;

public interface IParser {
	
	public IDataModel parse(List<String> expression);

}
