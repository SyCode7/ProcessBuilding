package de.processor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;


// started the process builder class with the aim of creating some assignments
public class ProcesBuilderMultipleExamples {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		ProcessBuilder pb = new ProcessBuilder("C:\\WINDOWS\\system32\\cmd.exe", "/C dir & echo example of & echo D:\\workspace_java\\ProcessBuilding");
		pb.directory(new File("src"));
		
		Process process = pb.start();
		IOThreadHandler outputHandler = new IOThreadHandler(process.getInputStream());
		outputHandler.start();
		process.waitFor();
		System.out.println(outputHandler.getOutput());
		
	}
		private static class IOThreadHandler extends Thread{

			public InputStream inputStream;
			public StringBuilder output = new StringBuilder();

			public IOThreadHandler(InputStream inputStream) {
				this.inputStream = inputStream;
			}


			public void run() {

				Scanner br = null;
				try{
					br = new Scanner(new InputStreamReader(inputStream));
					String line;
					while ((br.hasNextLine())) {
						line = br.nextLine();
						output.append(line + System.getProperty("line.separator"));
					}
				
			} finally {
				br.close();
			}
		}
		public StringBuilder getOutput(){
			return output;
		}
		

	}

}
