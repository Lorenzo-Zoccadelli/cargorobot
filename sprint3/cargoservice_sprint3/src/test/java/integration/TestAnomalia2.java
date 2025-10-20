package test.java.integration;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;

public class TestAnomalia2{
	public static final String freecaller = "unibocaller";
    private static Interaction connCS, connCR;
    private static IApplMessage risposta;

    private static String hostCS = "localhost";
    private static String portCS = "9091";
    
    private static String hostCR = "localhost";
    private static String portCR = "9092";

    public static void main(String[] args) throws Exception {

        // Connessione al servizio cargoservice
        try {
            CommUtils.outcyan("Connessione a cargoservice su " + hostCS + ":" + portCS);
            connCS = ConnectionFactory.createClientSupport23(ProtocolType.tcp, hostCS, portCS);
            CommUtils.outcyan("Connessione a cargoservice su " + hostCR + ":" + portCR);
            connCR = ConnectionFactory.createClientSupport23(ProtocolType.tcp, hostCR, portCR);
        } catch (Exception e) {
            CommUtils.outred("Errore di connessione: " + e.getMessage());
            System.exit(1);
        }

        // Reset della stiva
        IApplMessage richiesta = CommUtils.buildRequest(
                freecaller, "resetStiva",
                "resetStiva(1)", "cargoservice");
        risposta = connCS.request(richiesta);
        CommUtils.outgreen("Risposta resetStiva: " + risposta.msgContent());
        
        // sistema accetta una richiesta di carico
        Thread.sleep(2000);
        richiesta = CommUtils.buildRequest(
                freecaller, "richiestaCarico",
                "richiestaCarico(6)", "cargoservice");
        risposta = connCS.request(richiesta);
        CommUtils.outgreen("Risposta richiestaCarico: " + risposta.msgContent());


        
        //Container venga rilevato
        Thread.sleep(1000);
        IApplMessage container = CommUtils.buildEvent(
                freecaller, "containerRilevato",
                "containerRilevato(6)");
        connCS.forward(container);
        CommUtils.outblue("Evento containerRilevato INVIATO!");

        
        
        //Simuliamo un’anomalia mentre il sistema è in (elaboraRichiesta)
        Thread.sleep(1500);
        IApplMessage anomalia = CommUtils.buildEvent(
                freecaller, "rilevazioneAnomalia",
                "rilevazioneAnomalia(101)");
        connCS.forward(anomalia);
        connCR.forward(anomalia);
        CommUtils.outred("Evento di anomalia INVIATO");

        // Risoluzione anomalia
        Thread.sleep(6000);
        IApplMessage risolta = CommUtils.buildEvent(
                freecaller, "risoluzioneAnomalia",
                "risoluzioneAnomalia(101)");
        connCS.forward(risolta);
        connCR.forward(risolta);
        CommUtils.outgreen("Evento di risoluzione anomalia INVIATO!");
        
        CommUtils.outcyan("Test completato.");
    }
}
