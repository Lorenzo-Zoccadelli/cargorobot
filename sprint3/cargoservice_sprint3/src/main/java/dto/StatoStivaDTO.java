package main.java.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.java.model.SlotMap;

public class StatoStivaDTO {
	private SlotMap slotMap; 
	private Integer maxLoad;
	
	public StatoStivaDTO(SlotMap slotMap, Integer maxLoad) {
		this.slotMap = slotMap;
		this.maxLoad = maxLoad;
	}
	
	@Override
	public String toString() {
		Gson g = new GsonBuilder().serializeNulls().create();
		return g.toJson(this);
	}
	
}
