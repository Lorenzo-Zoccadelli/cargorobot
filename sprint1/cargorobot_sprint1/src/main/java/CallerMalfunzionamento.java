package main.java;

import unibo.basicomm23.interfaces.*;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.*;

public class CallerMalfunzionamento {
	
	public static final String freecaller = "unibocaller";
	public static final int sT = 340;
    private static Interaction conn;
    private static IApplMessage answer;
    
    private static String host="localhost";
    private static String port="8082";
    

	public static void main(String[] args) throws Exception {
		 try {
	            CommUtils.outcyan("BasicRobotMovesHelper | connectService Hostname: " + host);
	            CommUtils.outcyan("connectService Port:     " + port);
	            conn = ConnectionFactory.createClientSupport23(ProtocolType.tcp, host, port);
	        } catch (Exception e) {
	            CommUtils.outred("BasicRobotMovesHelper | ERROR:" + e.getMessage());
	            System.exit(1);
	        }

		 //IApplMessage malfunzionamento = CommUtils.buildEvent(freecaller, "rilevazioneAnomalia", "rilevazioneAnomalia(1)");
		 IApplMessage malfunzionamento = CommUtils.buildEvent(freecaller, "risoluzioneAnomalia", "risoluzioneAnomalia(1)");
		 conn.forward(malfunzionamento);
		 
	}

}
