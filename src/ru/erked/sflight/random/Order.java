package ru.erked.sflight.random;


/** Класс заказов
 * @author ERKED*/
public class Order {
	
	private String orderName; 
	private float orderMass;
	private int orderLevel;
	private int orderReward;
	
	/**
	 * 
	 * @param orderName Название заказа
	 * @param orderMass Масса груза, которую просят вывести на орбиту
	 * @param orderLevel Необходимая "дальность" полёта ракеты
	 * @param orderReward Награда за успешное выполнение заказа
	 */
	public Order(String orderName, float orderMass, int orderLevel, int orderReward){
		this.orderName = orderName;
		this.orderMass = orderMass;
		this.orderLevel = orderLevel;
		this.orderReward = orderReward;
	}
	
	public String getOrderName(){
		return orderName;
	}
	
	public float getOrderMass(){
		return orderMass;
	}
	
	public int getOrderLevel(){
		return orderLevel;
	}
	
	public int getOrderReward(){
		return orderReward;
	}
	
	public void setOrderName(String name){
		this.orderName = name;
	}
	
	public void setOrderMass(float mass){
		this.orderMass = mass;
	}
	
	public void setOrderLevel(int level){
		this.orderLevel = level;
	}
	
	public void setOrderReward(int reward){
		this.orderReward = reward;
	}
}
