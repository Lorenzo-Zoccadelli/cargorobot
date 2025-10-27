package it.unibo.cargogui.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unibo.cargogui.dto.RichiestaCaricoDTO;
import it.unibo.cargogui.dto.RispostaDTO;
import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;

@RestController
@RequestMapping("/api/cargoservice")
public class CargoserviceReqController {
	
	public static final String freecaller = "unibocaller";
    private static Interaction conn;
    
    private static String host="cargoservice";
    private static String port="9091";
	

	@PostMapping("/richiesta-carico")
	public RispostaDTO richiestaCarico(@RequestBody RichiestaCaricoDTO richiesta) throws Exception {
		try {
            conn = ConnectionFactory.createClientSupport23(ProtocolType.tcp, host, port);
        } catch (Exception e) {
            CommUtils.outred("BasicRobotMovesHelper | ERROR:" + e.getMessage());
            System.exit(1);
        }
		
		IApplMessage qakReq, ans;
		qakReq = CommUtils.buildRequest(freecaller, "richiestaCarico", 
				 "richiestaCarico(" + richiesta.getProductId() + ")", "cargoservice");
		ans = conn.request(qakReq);
		
		System.out.println(ans);
		
		conn.close();
		
		return new RispostaDTO();
		
	}
	
}
