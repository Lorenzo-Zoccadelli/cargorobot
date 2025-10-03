package test.java;

import main.java.model.Product;
import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;

public class PopolaDbProdotti {
	public static final String freecaller = "unibocaller";
    private static Interaction conn;
    private static IApplMessage answer;
    
    private static String host="localhost";
    private static String port="8111";
    

	public static void main(String[] args) throws Exception {
		 try {
	            CommUtils.outcyan("BasicRobotMovesHelper | connectService Hostname: " + host);
	            CommUtils.outcyan("connectService Port:     " + port);
	            conn = ConnectionFactory.createClientSupport23(ProtocolType.tcp, host, port);
	        } catch (Exception e) {
	            CommUtils.outred("BasicRobotMovesHelper | ERROR:" + e.getMessage());
	            System.exit(1);
	        }

		 
		 	salvaProdotto(new Product(1, "prod1", 20));
		 	salvaProdotto(new Product(2, "prod2", 40));
		 	salvaProdotto(new Product(3, "prod3", 50));
		 	salvaProdotto(new Product(4, "prod4", 10));
		 	salvaProdotto(new Product(5, "prod5", 10));
		 	salvaProdotto(new Product(6, "prod5", 30));
		 
	}

	
	private static void salvaProdotto(Product p) throws Exception {
		 IApplMessage richiesta, ans;
		 System.out.println(p);
		 richiesta = CommUtils.buildRequest(freecaller, "createProduct", "product('"+p.toString()+"')", "productservice");
		 ans = conn.request(richiesta);
		 System.out.println(ans);
	}
}
