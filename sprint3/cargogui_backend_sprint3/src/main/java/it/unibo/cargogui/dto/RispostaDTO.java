package it.unibo.cargogui.dto;

public class RispostaDTO {

	private String tipoRisposta;
	private String messaggio;
	
	public RispostaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public RispostaDTO(String tipoRisposta, String messaggio) {
		super();
		this.tipoRisposta = tipoRisposta;
		this.messaggio = messaggio;
	}



	public String getTipoRisposta() {
		return tipoRisposta;
	}
	public void setTipoRisposta(String tipoRisposta) {
		this.tipoRisposta = tipoRisposta;
	}
	public String getMessaggio() {
		return messaggio;
	}
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	
	
}
