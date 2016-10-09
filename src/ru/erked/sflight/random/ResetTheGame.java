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
		InfoAndStats.moneyAmount = 1;
		InfoAndStats.fuelAmount = 1;
		InfoAndStats.metalAmount = 1;
		InfoAndStats.moneyLevel = 0;
		InfoAndStats.fuelLevel = 0;
		InfoAndStats.metalLevel = 0;
	}
	
}
