package ru.erked.sflight.game;

import ru.erked.sflight.random.InfoAndStats;
/** ����� �������� ������
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
	 * @param nameUS ���������� �������� ������
	 * @param nameRU ������� �������� ������
	 * @param thrust ���� ������
	 * @param specificImpulse ������� ������
	 * @param workingTime ������� ����� ������
	 * @param price ���� ������
	 * @param amount ���������� �������
	 * @param level ������� ������
	 * @param isUnlocked ����������� �� ������
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
	
	/**������� ����� ������*/
	public String getRocketDetailName(){
		if(!InfoAndStats.lngRussian) 
			return nameUS;
		else
			return nameRU;
	}
	
	/**��������� ����� ������*/
	public void setRocketDetailName(String nameUS, String nameRU){
		this.nameUS = nameUS;
		this.nameRU = nameRU;
	}

	/**������� ���� ������*/
	public float getRocketDetailThrust(){
		return thrust;
	}
	
	/**��������� ���� ������*/
	public void setRocketDetailThrust(float thrust){
		this.thrust = thrust;
	}
	
	/**������� �������� ������*/
	public float getRocketDetailImpulse(){
		return specificImpulse;
	}
	
	/**��������� �������� ������*/
	public void setRocketDetailImpulse(float specificImpulse){
		this.specificImpulse = specificImpulse;
	}
	
	/**������� �������� ������� ������*/
	public float getRocketDetailTime(){
		return workingTime;
	}
	
	/**��������� �������� ������� ������*/
	public void setRocketDetailTime(float workingTime){
		this.workingTime = workingTime;
	}
	
	/**������� "�����������" ������*/
	public boolean getRocketDetailAccessibility(){
		return isUnlocked;
	}
	
	/**��������� "�����������" ������*/
	public void setRocketDetailAccessibility(boolean isUnlocked){
		this.isUnlocked = isUnlocked;
	}
	
	/**������� ���� ������*/
	public int getRocketDetailPrice(){
		return price;
	}
	
	/**��������� ���� ������*/
	public void setRocketDetailPrice(int price){
		this.price = price;
	}
	
	/**������� ���������� ������*/
	public int getRocketDetailAmount(){
		return amount;
	}
	
	/**��������� ���������� ������*/
	public void setRocketDetailAmount(int amount){
		this.amount = amount;
	}
	
	/**������� ������ ������*/
	public int getRocketDetailLevel(){
		return level;
	}
	
	/**��������� ������ ������*/
	public void setRocketDetailLevel(int level){
		this.level = level;
	}
}
