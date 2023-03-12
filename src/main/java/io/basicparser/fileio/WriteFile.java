package io.basicparser.fileio;

import java.io.FileWriter;
import java.io.IOException; // Import the IOException class to handle errors

public class WriteFile {

	public void writePermTreeStringToFile(String filePath, String payload) {
			  try {
			      FileWriter myWriter = new FileWriter("src/main/resources/"+filePath);
			      myWriter.write(payload);
			      myWriter.close();
			      System.out.println("Successfully wrote to the file.");
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
	}
}
