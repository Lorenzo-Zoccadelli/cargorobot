package test.java;

import static org.junit.Assert.*;


import org.junit.BeforeClass;
import org.junit.Test;


import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;


public class TestSonar {
	
	private static final String freecaller = "lettore_sonar_fisico";
	private static Interaction conn;
	private static IApplMessage req, ev;
	
	private static String host = "localhost";
	private static String port = "9093";   
	
	@BeforeClass
	public static void init() throws Exception {
		try {
			CommUtils.outcyan("Connecting to ctx_ioport on " + host + ":" + port);
			conn = ConnectionFactory.createClientSupport23(ProtocolType.tcp, host, port);
			assertTrue(conn != null);
		} catch (Exception e) {
			CommUtils.outred("ERROR connecting to ioport: " + e.getMessage());
			System.exit(1);
		}
	}
	

	@Test
	public void testRilevazioneContainer() throws Exception {
		CommUtils.outmagenta("TEST: Rilevazione container");
		
		
		ev = CommUtils.buildEvent(freecaller, "rilevazioneDistanza", "rilevazioneDistanza(4)");
		conn.forward(ev);
		Thread.sleep(1000);
		
		ev = CommUtils.buildEvent(freecaller, "rilevazioneDistanza", "rilevazioneDistanza(4)");
		conn.forward(ev);
		Thread.sleep(1000);
		
		ev = CommUtils.buildEvent(freecaller, "rilevazioneDistanza", "rilevazioneDistanza(4)");
		conn.forward(ev);
		Thread.sleep(1000);
		
		CommUtils.outgreen("In teoria inviate almeno 3");
		
		ev = CommUtils.buildEvent(freecaller, "rilevazioneDistanza", "rilevazioneDistanza(4)");
		conn.forward(ev);
		Thread.sleep(1000);
		
	}
	
	
	
	@Test
	public void testRilevazioneAnomalia() throws Exception {
		CommUtils.outmagenta("=== TEST: Rilevazione anomalia ===");
		
		ev = CommUtils.buildEvent(freecaller, "rilevazioneDistanza", "rilevazioneDistanza(15)");
		conn.forward(ev);
		Thread.sleep(1000);
		
		ev = CommUtils.buildEvent(freecaller, "rilevazioneDistanza", "rilevazioneDistanza(15)");
		conn.forward(ev);
		Thread.sleep(1000);
		
		ev = CommUtils.buildEvent(freecaller, "rilevazioneDistanza", "rilevazioneDistanza(15)");
		conn.forward(ev);
		Thread.sleep(1000);
		
		CommUtils.outgreen("In teoria rilevata distanza > DFREE per 3 volte");
		
		
		ev = CommUtils.buildEvent(freecaller, "rilevazioneDistanza", "rilevazioneDistanza(15)");
		conn.forward(ev);
		Thread.sleep(1000);	
	}
	
	@Test
	public void testRisoluzioneAnomalia() throws Exception {
		CommUtils.outmagenta("=== TEST: Rilevazione anomalia ===");
		
		ev = CommUtils.buildEvent(freecaller, "rilevazioneDistanza", "rilevazioneDistanza(15)");
		conn.forward(ev);
		Thread.sleep(1000);
		
		ev = CommUtils.buildEvent(freecaller, "rilevazioneDistanza", "rilevazioneDistanza(15)");
		conn.forward(ev);
		Thread.sleep(1000);
		
		ev = CommUtils.buildEvent(freecaller, "rilevazioneDistanza", "rilevazioneDistanza(15)");
		conn.forward(ev);
		Thread.sleep(1000);
		
		CommUtils.outgreen("In teoria rilevata distanza > DFREE per 3 volte");
		
		
		ev = CommUtils.buildEvent(freecaller, "rilevazioneDistanza", "rilevazioneDistanza(8)");
		conn.forward(ev);
		Thread.sleep(1000);	
	}
	
	
	
	@Test
	public void testRilevazioneVuoto() throws Exception {
		CommUtils.outmagenta("=== TEST: Rilevazione vuoto ===");
		
		ev = CommUtils.buildEvent(freecaller, "rilevazioneDistanza", "rilevazioneDistanza(7)");
		conn.forward(ev);
		Thread.sleep(1000);
		
		
		CommUtils.outgreen("Inviata rilevazione a vuoto");
	}
	

}
