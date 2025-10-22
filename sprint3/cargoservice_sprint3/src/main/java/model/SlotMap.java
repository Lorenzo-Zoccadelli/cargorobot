package main.java.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class SlotMap {
	private Map<Slot, Product> slotMap;
	
	public SlotMap() {
		this.slotMap = new HashMap<>();
	}
	
	protected void addSlot(Slot slot) {
		if(!slotMap.containsKey(slot)) {
			slotMap.put(slot, null);
		}
		else {
			throw new IllegalStateException("Slot "+slot+" already exists");
		}
	}
	
	public boolean slotExists(Slot slot) {
		return slotMap.containsKey(slot);
	}
	
	public boolean isSlotEmpty(Slot slot) {
		return slotMap.get(slot)==null;
	}
	
	public boolean isAnySlotEmpty() {
		for(var slot : slotMap.keySet()) {
			if(slotMap.get(slot) == null) return true;
		}
		
		return false;
	}
	
	public Slot getFirstEmptySlot() {
		for(var slot : slotMap.keySet()) {
			if(slotMap.get(slot) == null) return slot;
		}
		
		throw new IllegalStateException("All slots are full");
	}
	
	public void putProductIntoSlot(Slot slot, Product product) {
		if(slotExists(slot) && isSlotEmpty(slot)) slotMap.put(slot, product);
		else throw new IllegalStateException("Slot "+slot+" does not exists or is full");
	}
	
	public List<Product> getRegisteredProducts(){
		return slotMap.values().stream().filter(s -> s!=null).toList();
	}
	
	public double getTotalWeight() {
		return getRegisteredProducts().stream().mapToDouble(Product::getWeight).sum();
	}
	
	public HashMap<Slot, Product> getMap(){
		return new HashMap<>(slotMap);
	}
	
	
	@Override
	public String toString() {
		Gson g = new GsonBuilder().serializeNulls().create();
		return g.toJson(this);
	}
}
