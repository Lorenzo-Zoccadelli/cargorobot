package main.java.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.JSONArray;
import org.json.JSONObject;


public class CargoSlotMap extends SlotMap{
	
	public CargoSlotMap() {
		super();
		addSlot(new Slot("Slot1", 1, 1, 1, 0));
		addSlot(new Slot("Slot2", 3, 1, 3, 0));
		addSlot(new Slot("Slot3", 1, 4, 1, 5));
		addSlot(new Slot("Slot4", 3, 4, 3, 5));
	}

	public CargoSlotMap(String jsonFileName) throws IOException {
		super();
		String jsonStr = Files.readString(Path.of(jsonFileName));
		
		JSONObject obj = new JSONObject(jsonStr);
        JSONArray arr = obj.getJSONArray("slots");

        for (int i = 0; i < arr.length(); i++) {
            JSONObject s = arr.getJSONObject(i);
            Slot slot = new Slot(
                s.getString("name"),
                s.getInt("posX"),
                s.getInt("posY"),
                s.getInt("loadingPosX"),
                s.getInt("loadingPosY")
            );
            addSlot(slot);
        }
	}
	
}
