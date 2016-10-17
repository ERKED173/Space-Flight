package ru.erked.sflight.tech;

public class Rocket {

	private String nameUS;
	private String nameRU;
	private int hp;
	private int speedC;
	private int speedF;
	private int speedM;
	private int cost;
	
	public Rocket(String nameUS, String nameRU, int hp, int speedC, int speedF, int speedM, int cost){
		this.setNameUS(nameUS);
		this.setNameRU(nameRU);
		this.setHp(hp);
		this.setSpeedC(speedC);
		this.setSpeedF(speedF);
		this.setSpeedM(speedM);
		this.setCost(cost);
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
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getSpeedC() {
		return speedC;
	}

	public void setSpeedC(int speedC) {
		this.speedC = speedC;
	}

	public int getSpeedF() {
		return speedF;
	}

	public void setSpeedF(int speedF) {
		this.speedF = speedF;
	}

	public int getSpeedM() {
		return speedM;
	}

	public void setSpeedM(int speedM) {
		this.speedM = speedM;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
}
