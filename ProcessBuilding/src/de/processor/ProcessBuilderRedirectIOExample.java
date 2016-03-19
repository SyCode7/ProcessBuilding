package de.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class ProcessBuilderRedirectIOExample {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// create a ProcessBuilder instance
		ProcessBuilder pb =  new ProcessBuilder("src/resources/ping.bat");
		System.out.println("Configure parameters");
		Map<String, String> env = pb.environment();
		env.put("name", "ping command");
		env.put("echoCount", "2");
		
		
		// redirect the output to a file ....to be written in the file
		System.out.println("Redirect output and error to file");
		File outputFile = new File("src/resources/PingLog.txt");
		File errorFile = new File("src/resources/PingErrorLog.txt");
		pb.redirectOutput(outputFile);
		pb.redirectError(errorFile);
		
		Process process = pb.start();
		process.waitFor();
		
		System.out.println("\nPrint common output: ");
		printFile(outputFile);
		
		System.out.println("\nRedirect input source to a file");
		pb = new ProcessBuilder("cmd");
		pb.environment().putAll(env);
		pb.inheritIO();
		pb.redirectInput(new File ("src/resources/ping.bat"));
		process = pb.start();
		process.waitFor();
		
		
	}

	private static void printFile(File file) throws IOException {
		System.out.println("*****************************************");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
			
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
		fr.close();
		System.out.println("*********************************");
		
		
	}

}
