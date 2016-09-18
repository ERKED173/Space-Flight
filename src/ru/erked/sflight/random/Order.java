package ru.erked.sflight.random;


/** ����� �������
 * @author ERKED*/
public class Order {
	
	private String orderName; 
	private float orderMass;
	private int orderLevel;
	private int orderReward;
	
	/**
	 * 
	 * @param orderName �������� ������
	 * @param orderMass ����� �����, ������� ������ ������� �� ������
	 * @param orderLevel ����������� "���������" ����� ������
	 * @param orderReward ������� �� �������� ���������� ������
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
