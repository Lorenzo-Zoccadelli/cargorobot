package main.java.model;

import java.util.Objects;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.java.utils.JsonUtils;

public class Product {
	

		
	private void setupErrorroduct( String cause) {
		productId = 0;
		weight    = 0;
		name      = "error-"+cause;
	}
		
		
	
	
	
	private Integer productId;
	private String name;
	private Integer weight;
	
	public Product() {}
	
	public Product(Integer productId, String name, Integer weight) {
		if( productId == 0 ) {
			setupErrorroduct( "idzero" );
		}
		if( productId < 0 || weight < 0 ) {
			setupErrorroduct( "negativevalue" );
		}else {
		    this.productId = productId;
		    this.name      = name;
		    this.weight    = weight;
			//CommUtils.outblue("Product | created 3:" +this);
		}
	}
	
	public Product(String pId, String pname, String pweight) {		
		try {
			productId = Integer.parseInt(pId);
			name      = pname;
			weight    = Integer.parseInt(pweight);
		}catch(Exception e) {
			setupErrorroduct( "parseInt" );
		}
	}
	
	public Product(String jsonStr)   {
		this( JsonUtils.getJsonInt(jsonStr, "productId"), 
				JsonUtils.getJsonString(jsonStr,"name"), 
				JsonUtils.getJsonInt(jsonStr,"weight") );
	}
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, productId, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(name, other.name) && Objects.equals(productId, other.productId)
				&& Objects.equals(weight, other.weight);
	}
	
	@Override
	public String toString() {
		return "{\"productId\":ID,\"name\":NAME,\"weight\":W}"
				.replace("ID", ""+productId).replace("NAME", "\""+name+"\"").replace("W",""+weight);
	}

	
	
	
}




