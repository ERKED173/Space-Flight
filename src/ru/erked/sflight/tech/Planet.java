package ru.erked.sflight.tech;

public class Planet {

	private String nameUS;
	private String nameRU;
	private int level;
	private int fuelTo;
	private boolean isSelected;
	private boolean isAvailable;
	
	public Planet(String nameUS, String nameRU, int level, int fuelTo, boolean isSelected, boolean isAvailable){
		this.setNameUS(nameUS);
		this.setNameRU(nameRU);
		this.setLevel(level);
		this.setFuelTo(fuelTo);
		this.setSelected(isSelected);
		this.setAvailable(isAvailable);
	}

	public String getNameUS() {
		return nameUS;
	}

	public void setNameUS(String nameUS) {
		this.nameUS = nameUS;
	}
	
	public String getNameRU() {
		return nameRU;
	}

	public void setNameRU(String nameRU) {
		this.nameRU = nameRU;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getFuelTo() {
		return fuelTo;
	}

	public void setFuelTo(int fuelTo) {
		this.fuelTo = fuelTo;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	
	
}
