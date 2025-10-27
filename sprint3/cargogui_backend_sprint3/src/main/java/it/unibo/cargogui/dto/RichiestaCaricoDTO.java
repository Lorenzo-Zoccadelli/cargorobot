package it.unibo.cargogui.dto;

public class RichiestaCaricoDTO{
	private Integer productId;
	
	public RichiestaCaricoDTO() {}
	
	public RichiestaCaricoDTO(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	
}