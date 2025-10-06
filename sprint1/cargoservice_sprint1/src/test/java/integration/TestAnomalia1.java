package test.java.integration;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;

public class TestAnomalia1{
	public static final String freecaller = "unibocaller";
    private static Interaction conn;
    private static IApplMessage risposta;

    private static String host = "localhost";
    private static String port = "9091";

    public static void main(String[] args) throws Exception {

        // Connessione al servizio cargoservice
        try {
            CommUtils.outcyan("Connessione a cargoservice su " + host + ":" + port);
            conn = ConnectionFactory.createClientSupport23(ProtocolType.tcp, host, port);
        } catch (Exception e) {
            CommUtils.outred("Errore di connessione: " + e.getMessage());
            System.exit(1);
        }

        // Reset della stiva
        IApplMessage richiesta = CommUtils.buildRequest(
                freecaller, "resetStiva",
                "resetStiva(1)", "cargoservice");
        risposta = conn.request(richiesta);
        CommUtils.outgreen("Risposta resetStiva: " + risposta.msgContent());
        
        // sistema accetta una richiesta di carico
        Thread.sleep(2000);
        richiesta = CommUtils.buildRequest(
                freecaller, "richiestaCarico",
                "richiestaCarico(6)", "cargoservice");
        risposta = conn.request(richiesta);
        CommUtils.outgreen("Risposta richiestaCarico: " + risposta.msgContent());

        

        //Simuliamo un’anomalia mentre il sistema è in (elaboraRichiesta)
        Thread.sleep(2000);
        IApplMessage anomalia = CommUtils.buildEvent(
                freecaller, "rilevazioneAnomalia",
                "rilevazioneAnomalia(101)");
        conn.forward(anomalia);
        CommUtils.outred("Evento di anomalia INVIATO (sistema in wait_requests)");

        // Risoluzione anomalia
        Thread.sleep(5000);
        IApplMessage risolta = CommUtils.buildEvent(
                freecaller, "risoluzioneAnomalia",
                "risoluzioneAnomalia(101)");
        conn.forward(risolta);
        CommUtils.outgreen("Evento di risoluzione anomalia INVIATO!");

        
        //Container venga rilevato
        Thread.sleep(8000);
        IApplMessage container = CommUtils.buildEvent(
                freecaller, "containerRilevato",
                "containerRilevato(6)");
        conn.forward(container);
        CommUtils.outblue("Evento containerRilevato INVIATO!");

        CommUtils.outcyan("Test completato.");
    }
}

