package main.java.model;

public class CargoSlotMap extends SlotMap{
	
	public CargoSlotMap() {
		super();
		addSlot(new Slot("Slot1", 1, 1, 1, 0));
		addSlot(new Slot("Slot2", 3, 1, 3, 0));
		addSlot(new Slot("Slot1", 1, 4, 1, 5));
		addSlot(new Slot("Slot1", 3, 4, 3, 5));
	}
}
