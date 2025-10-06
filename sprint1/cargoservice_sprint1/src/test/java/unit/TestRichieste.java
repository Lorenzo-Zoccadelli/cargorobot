package test.java.unit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.model.Product;
import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;

public class TestRichieste {

	public static final String freecaller = "unibocaller";
    private static Interaction conn, connPS;
    private static IApplMessage req, ans, rep;
    
    private static String host="localhost";
    private static String port="9091";
    
    private static String hostPS="localhost";
    private static String portPS="8111";
	
    private static void salvaProdotto(Product p) throws Exception {
		 req = CommUtils.buildRequest(freecaller, "createProduct", "product('"+p.toString()+"')", "productservice");
		 ans = connPS.request(req);
		 System.out.println(ans);
	}
    
    private static void eliminaProdotto(int id) throws Exception {
		 req = CommUtils.buildRequest(freecaller, "deleteProduct", "product("+id+")", "productservice");
		 ans = connPS.request(req);
		 System.out.println(ans);
	}
    
    
    @BeforeClass
    public static void init() throws Exception {
    	try {
            CommUtils.outcyan("BasicRobotMovesHelper | connectService Hostname: " + host);
            CommUtils.outcyan("connectService Port:     " + port);
            conn = ConnectionFactory.createClientSupport23(ProtocolType.tcp, host, port);
        } catch (Exception e) {
            CommUtils.outred("BasicRobotMovesHelper | ERROR:" + e.getMessage());
            System.exit(1);
        }
    	
    	try {
            CommUtils.outcyan("BasicRobotMovesHelper | connectService Hostname: " + hostPS);
            CommUtils.outcyan("connectService Port:     " + portPS);
            connPS = ConnectionFactory.createClientSupport23(ProtocolType.tcp, hostPS, portPS);
        } catch (Exception e) {
            CommUtils.outred("BasicRobotMovesHelper | ERROR:" + e.getMessage());
            System.exit(1);
        }
    	
    	eliminaProdotto(1);
    	salvaProdotto(new Product(1, "prod1", 20));
    	eliminaProdotto(2);
	 	salvaProdotto(new Product(2, "prod2", 40));
	 	eliminaProdotto(3);
	 	salvaProdotto(new Product(3, "prod3", 50));
	 	eliminaProdotto(4);
	 	salvaProdotto(new Product(4, "prod4", 10));
	 	eliminaProdotto(5);
	 	salvaProdotto(new Product(5, "prod5", 12));
	 	eliminaProdotto(6);
	 	salvaProdotto(new Product(6, "prod6", 30));
	 	eliminaProdotto(7);
	 	salvaProdotto(new Product(7, "prod7", 1));
	 	
    	
    }
    
    @Before
    public void resetHold() throws Exception {
    	 req = CommUtils.buildRequest(freecaller, "resetStiva", 
				 "resetStiva(1)", "cargoservice");
		 ans = conn.request(req);
		 System.out.println(ans.msgContent());
		 
    }
    
    
	@Test
	public void testRichiestaCaricoOK() throws Exception {
		

		 req = CommUtils.buildRequest(freecaller, "richiestaCarico", 
				 "richiestaCarico(5)", "cargoservice");
		 ans = conn.request(req);
		 System.out.println(ans);
		 assertTrue(ans.msgContent().contains("richiestaCaricoAccettata"));
		 
		 Thread.sleep(1000);
		 
		//mock IOPort per liberare cargoservice
		 req = CommUtils.buildEvent(freecaller, "containerRilevato", 
				 "containerRilevato(1)");
		 conn.forward(req);
		
	}
	
	@Test
	public void testRichiestaCaricoProdNonPres() throws Exception {


		 req = CommUtils.buildRequest(freecaller, "richiestaCarico", 
				 "richiestaCarico(99)", "cargoservice");
		 ans = conn.request(req);
		 System.out.println(ans);
		 assertTrue(ans.msgContent().contains("richiestaCaricoRifiutata"));
		 assertTrue(ans.msgContent().contains("Prodotto non esistente"));
		 
		 Thread.sleep(1000);
		 

		//mock IOPort per liberare cargoservice
		 req = CommUtils.buildEvent(freecaller, "containerRilevato", 
				 "containerRilevato(1)");
		 conn.forward(req);
	}
	
	@Test
	public void testRichiestaCaricoNoSlotLiberi() throws Exception {
		for(int i=0; i<5; i++) {
			req = CommUtils.buildRequest(freecaller, "richiestaCarico", 
					 "richiestaCarico(4)", "cargoservice");
			 ans = conn.request(req);
			 System.out.println(ans.msgContent());
			 
			 if(i<4) {
				 assertTrue(ans.msgContent().contains("richiestaCaricoAccettata"));
			 }
			 else {
				 assertTrue(ans.msgContent().contains("richiestaCaricoRifiutata"));
				 assertTrue(ans.msgContent().contains("Nessuno slot disponibile"));
			 }
			 
			 Thread.sleep(1000);
			 
			 req = CommUtils.buildEvent(freecaller, "containerRilevato", 
					 "containerRilevato(4)");
			 conn.forward(req);
			 Thread.sleep(5000);
		 }
	}
	
	@Test
	public void testRichiestaCaricoOltrePesoMax() throws Exception {
		for(int i=0; i<4; i++) {
			 req = CommUtils.buildRequest(freecaller, "richiestaCarico", 
					 "richiestaCarico(6)", "cargoservice");
			 ans = conn.request(req);
			 System.out.println(ans.msgContent());
			 
			 if(i<3) {
				 assertTrue(ans.msgContent().contains("richiestaCaricoAccettata"));
			 }
			 else {
				 assertTrue(ans.msgContent().contains("richiestaCaricoRifiutata"));
				 assertTrue(ans.msgContent().contains("Il prodotto eccede il peso massimo"));
			 }
			 
			 Thread.sleep(1000);
			 
			 req = CommUtils.buildEvent(freecaller, "containerRilevato", 
					 "containerRilevato(6)");
			 conn.forward(req);
			 Thread.sleep(10000);
		 }
	}
	
}
