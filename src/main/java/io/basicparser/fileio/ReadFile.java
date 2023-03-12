package io.basicparser.fileio;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.InputStream;
import java.util.Scanner; // Import the Scanner class to read text files

import java.lang.StringBuilder;

public class ReadFile {

	public String getPermTreeExprStringFrmFile(String filePath) {
		StringBuilder expression = new StringBuilder();
		try {
			InputStream is = this.getClass().getResourceAsStream(filePath);
			File myObj = new File(filePath);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(data);
				expression.append(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return expression.toString();
	}
}
