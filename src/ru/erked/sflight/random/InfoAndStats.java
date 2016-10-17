package ru.erked.sflight.random;

import ru.erked.sflight.tech.Rocket;

public class InfoAndStats {
	
	public static long elapsedTime;
	public static long launch;
	public static long money;
	public static long fuel;
	public static long metal;
	public static long moneyFull;
	public static long fuelFull;
	public static long metalFull;
	public static long moneyAmount;
	public static long fuelAmount;
	public static long metalAmount;
	public static long moneyLevel;
	public static long fuelLevel;
	public static long metalLevel;
	
	public static Rocket rocketBall = new Rocket("Ball-1", "Шар-1", 1, 1, 1, 1, 5);
	public static Rocket rocketCircle = new Rocket("Circle-M", "Сфера-М", 2, 1, 1, 1, 10);
	public static Rocket rocketBasic = new Rocket("Basis-S", "Базис-С", 2, 2, 1, 1, 15);
	
	public static boolean lngRussian = false;
}