package de.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// https://examples.javacodegeeks.com/core-java/lang/processbuilder/java-lang-processbuilder-example/

public class ProcessBuilderExample {

	public static void main(String[] args) throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder("echo", "This is a ProcessBuilder example");
		System.out.println("Run echo command");
		Process process = pb.start();
		int errCode = process.waitFor();
		System.out.println("Echo command executed, any errors ?" + (errCode == 0 ? "No" : "Yes"));
		System.out.println("Echo output:\n" + output(process.getInputStream()));
	}

	//handles the input from the command line arguments
	private static String output(InputStream inputStream) throws IOException {
			StringBuilder sb = new StringBuilder();
			BufferedReader br = null;
			try{
				br = new BufferedReader(new InputStreamReader(inputStream));
				String line = null;
				while ((line = br.readLine())!= null ) {
					sb.append(line + System.getProperty("line.separator"));
				}
			} finally {
				br.close();
			}
			return sb.toString();
	}
}
