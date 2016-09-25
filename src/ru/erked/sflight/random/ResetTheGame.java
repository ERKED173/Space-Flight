package ru.erked.sflight.random;

public class ResetTheGame {

	public static void reset(){
		InfoAndStats.elapsedTime = 0;
		InfoAndStats.launch = 0;
		InfoAndStats.money = 50;
		InfoAndStats.fuel = 50;
		InfoAndStats.metal = 50;
	}
	
}
