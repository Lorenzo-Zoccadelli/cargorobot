package main.java.dto;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.java.model.Product;

public class StatoStivaDTO {
	private HashMap<String, Product> slotMap; 
	private Integer maxLoad;
	
	public StatoStivaDTO(HashMap<String, Product> slotMap, Integer maxLoad) {
		this.slotMap = slotMap;
		this.maxLoad = maxLoad;
	}
	
	@Override
	public String toString() {
		Gson g = new GsonBuilder().serializeNulls().create();
		return g.toJson(this);
	}
	
}
