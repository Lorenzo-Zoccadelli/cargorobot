package main.java.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	
}
