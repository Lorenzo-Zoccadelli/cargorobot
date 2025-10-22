package it.unibo.cargogui.coap;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.unibo.cargogui.ws.StivaWS;

@Component
public class CargoserviceCoAPObserver {

    private final CoapClient client;
    private final StivaWS ws;
    
    private static final String coapUrl = "coap://localhost:9091/ctx_cargoservice/cargoservice";

    @Autowired
    public CargoserviceCoAPObserver(StivaWS ws) {
        this.ws = ws;
        this.client = new CoapClient(coapUrl);

        client.observe(new CoapHandler() {
            @Override
            public void onLoad(CoapResponse response) {
                String data = response.getResponseText();
                System.out.println("CoAP observer: " + data);
                ws.broadcast(data);
            }

            @Override
            public void onError() {
                System.err.println("Errore osservando la risorsa CoAP");
            }
        });
    }
}
