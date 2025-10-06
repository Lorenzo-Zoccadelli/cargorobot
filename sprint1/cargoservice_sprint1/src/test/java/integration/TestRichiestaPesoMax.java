package test.java.integration;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;

public class TestRichiestaPesoMax {
	public static final String freecaller = "unibocaller";
    private static Interaction conn;
    private static IApplMessage answer;
    
    private static String host="localhost";
    private static String port="9091";
    
    public static void main(String[] args) throws Exception{
    	
    	try {
            CommUtils.outcyan("BasicRobotMovesHelper | connectService Hostname: " + host);
            CommUtils.outcyan("connectService Port:     " + port);
            conn = ConnectionFactory.createClientSupport23(ProtocolType.tcp, host, port);
        } catch (Exception e) {
            CommUtils.outred("BasicRobotMovesHelper | ERROR:" + e.getMessage());
            System.exit(1);
        }

		 IApplMessage richiesta, ans;
		 richiesta = CommUtils.buildRequest(freecaller, "resetStiva", 
				 "resetStiva(1)", "cargoservice");
		 ans = conn.request(richiesta);
		 System.out.println(ans.msgContent());
		 
		 for(int i=0; i<4; i++) {
			 richiesta = CommUtils.buildRequest(freecaller, "richiestaCarico", 
					 "richiestaCarico(6)", "cargoservice");
			 ans = conn.request(richiesta);
			 System.out.println(ans.msgContent());
			 
			 Thread.sleep(1000);
			 
			 richiesta = CommUtils.buildEvent(freecaller, "containerRilevato", 
					 "containerRilevato(6)");
			 conn.forward(richiesta);
			 Thread.sleep(10000);
		 }
    }
}
