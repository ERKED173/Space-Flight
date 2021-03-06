package ru.erked.sflight.random;

import ru.erked.sflight.tech.Planet;
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
	
	public static String currentRocket = "null";
	public static String currentPlanet = "null";
	
	public static Rocket rocketBall = new Rocket("Circle-1", "Круг-1", 1, 1, 1, 1, 5);
	public static Rocket rocketCircle = new Rocket("Sphere-M", "Сфера-М", 2, 1, 1, 1, 10);
	public static Rocket rocketBasic = new Rocket("Basis-S", "Базис-С", 2, 2, 1, 1, 15);
	public static Rocket rocketKinetic = new Rocket("Kinetic-UF", "Кинетик-УФ", 2, 2, 2, 2, 25);
	
	public static Planet planetLoon = new Planet("Loon","Мун",1,10,false,true);
	public static Planet planetEmion = new Planet("Emion","Эмион",1,20,false,true);
	
	public static boolean lngRussian = false;
}