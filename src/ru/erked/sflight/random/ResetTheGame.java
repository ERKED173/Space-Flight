package ru.erked.sflight.random;

public class ResetTheGame {

	public static void reset(){
		InfoAndStats.elapsedTime = 0;
		InfoAndStats.launch = 0;
		InfoAndStats.money = 3;
		InfoAndStats.fuel = 4;
		InfoAndStats.metal = 7;
		InfoAndStats.moneyFull = 10;
		InfoAndStats.fuelFull = 10;
		InfoAndStats.metalFull = 10;
	}
	
}
