package ru.erked.sflight.game;

import ru.erked.sflight.random.InfoAndStats;
/** Класс ракетной детали
 * 
 * @author ERKED
 *
 */
public class RocketDetail {

	private String nameUS;
	private String nameRU;
	private float thrust;
	private float specificImpulse;
	private float workingTime;
	private int price;
	private int amount;
	private int level;
	private boolean isUnlocked;
	
	/**
	 * 
	 * @param nameUS Английское название детали
	 * @param nameRU Русское название детали
	 * @param thrust Тяга детали
	 * @param specificImpulse Импельс детали
	 * @param workingTime Рабочее время детали
	 * @param price Цена детали
	 * @param amount Количество деталей
	 * @param level Уровень детали
	 * @param isUnlocked Исследована ли деталь
	 */
	public RocketDetail
			(
			String nameUS,
			String nameRU,
			float thrust,
			float specificImpulse,
			float workingTime,
			int price,
			int amount,
			int level,
			boolean isUnlocked
			)
	{
		this.nameUS = nameUS;
		this.nameRU = nameRU;
		this.thrust = thrust;
		this.specificImpulse = specificImpulse;
		this.workingTime = workingTime;
		this.price = price;
		this.isUnlocked = isUnlocked;
		this.amount = amount;
	}
	
	/**Возврат имени детали*/
	public String getRocketDetailName(){
		if(!InfoAndStats.lngRussian) 
			return nameUS;
		else
			return nameRU;
	}
	
	/**Установка имени детали*/
	public void setRocketDetailName(String nameUS, String nameRU){
		this.nameUS = nameUS;
		this.nameRU = nameRU;
	}

	/**Возврат тяги детали*/
	public float getRocketDetailThrust(){
		return thrust;
	}
	
	/**Установка тяги детали*/
	public void setRocketDetailThrust(float thrust){
		this.thrust = thrust;
	}
	
	/**Возврат импульса детали*/
	public float getRocketDetailImpulse(){
		return specificImpulse;
	}
	
	/**Установка импульса детали*/
	public void setRocketDetailImpulse(float specificImpulse){
		this.specificImpulse = specificImpulse;
	}
	
	/**Возврат рабочего времени детали*/
	public float getRocketDetailTime(){
		return workingTime;
	}
	
	/**Установка рабочего времени детали*/
	public void setRocketDetailTime(float workingTime){
		this.workingTime = workingTime;
	}
	
	/**Возврат "доступности" детали*/
	public boolean getRocketDetailAccessibility(){
		return isUnlocked;
	}
	
	/**Установка "доступности" детали*/
	public void setRocketDetailAccessibility(boolean isUnlocked){
		this.isUnlocked = isUnlocked;
	}
	
	/**Возврат цены детали*/
	public int getRocketDetailPrice(){
		return price;
	}
	
	/**Установка цены детали*/
	public void setRocketDetailPrice(int price){
		this.price = price;
	}
	
	/**Возврат количества детали*/
	public int getRocketDetailAmount(){
		return amount;
	}
	
	/**Установка количества детали*/
	public void setRocketDetailAmount(int amount){
		this.amount = amount;
	}
	
	/**Возврат уровня детали*/
	public int getRocketDetailLevel(){
		return level;
	}
	
	/**Установка уровня детали*/
	public void setRocketDetailLevel(int level){
		this.level = level;
	}
}
