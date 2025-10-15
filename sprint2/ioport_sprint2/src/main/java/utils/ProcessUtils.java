package main.java.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.StringJoiner;

public class ProcessUtils {
	
	public static String readAllStdOutput(Process process) throws InterruptedException, IOException {
		
		var reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		var line = "";
		var sj = new StringJoiner("");

	    while ((line = reader.readLine())!=null) {
	    	sj.add(line);
	    }

	    var exitcode = process.waitFor();
	    
	    if(exitcode!=0) {
	    	throw new RuntimeException("Process exited with status " + exitcode);
	    }
		
	    return sj.toString();
	}
	
	public static Optional<String> getStringEnvVar(String name) {
		var value = System.getenv(name);
		if(value==null) return Optional.empty();
		
		if(value.equals("")) return Optional.empty();
		
		return Optional.of(value);
	}
	
	public static Optional<Integer> getIntEnvVar(String name) {
		var value = System.getenv(name);
		if(value==null) return Optional.empty();
		
		try {
			return Optional.of(Integer.parseInt(value));
		}
		catch(Exception e) {
			return Optional.empty();
		}
	}
	
	public static Optional<Integer> getDoubleEnvVar(String name) {
		var value = System.getenv(name);
		if(value==null) return Optional.empty();
		
		try {
			return Optional.of(Integer.parseInt(value));
		}
		catch(Exception e) {
			return Optional.empty();
		}
	}
	
	
}
