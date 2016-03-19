package de.processor;

import java.io.IOException;

public class ProcessBuilderInheritIOExample {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder("echo", "Hello people, \nThis is a ProcessBuilding Example" );
		pb.inheritIO();
		System.out.println("Run Echo command with InheritIO Set");
		Process process = pb.start();
		process.waitFor();
		
	}

}
