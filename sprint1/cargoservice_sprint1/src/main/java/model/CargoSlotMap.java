package main.java.model;

public class CargoSlotMap extends SlotMap{
	
	public CargoSlotMap() {
		super();
		addSlot(new Slot("Slot1", 1, 1));
		addSlot(new Slot("Slot2", 3, 1));
		addSlot(new Slot("Slot1", 1, 4));
		addSlot(new Slot("Slot1", 3, 4));
	}
}
