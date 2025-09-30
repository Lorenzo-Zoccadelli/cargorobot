package main.java.model;

import java.util.Objects;

public class Slot {
	private String name;
	private Integer posX;
	private Integer posY;
	private Integer loadingPosX;
	private Integer loadingPosY;
	
	public Slot() {}
	
	public Slot(String name, Integer posX, Integer posY, Integer loadingPosX, Integer loadingPosY) {
		this.name = name;
		this.posX = posX;
		this.posY = posY;
		this.loadingPosX = loadingPosX;
		this.loadingPosY = loadingPosY;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPosX() {
		return posX;
	}

	public void setPosX(Integer posX) {
		this.posX = posX;
	}

	public Integer getPosY() {
		return posY;
	}

	public void setPosY(Integer posY) {
		this.posY = posY;
	}

	public Integer getLoadingPosX() {
		return loadingPosX;
	}

	public void setLoadingPosX(Integer loadingPosX) {
		this.loadingPosX = loadingPosX;
	}

	public Integer getLoadingPosY() {
		return loadingPosY;
	}

	public void setLoadingPosY(Integer loadingPosY) {
		this.loadingPosY = loadingPosY;
	}

	@Override
	public int hashCode() {
		return Objects.hash(posX, posY);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Slot other = (Slot) obj;
		return Objects.equals(posX, other.posX) && Objects.equals(posY, other.posY);
	}

	
	
	
	
	
}
