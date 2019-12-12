package model;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, Monster3.java
 * 
 * purpose: This is one type of monster with it's own property.
 */



public class Monster3 extends Monster {
	private int gold = 14;
	private double speed = 10;
	private int health = 100;
	private int lossHealth = 20;
	public Monster3() {
		super.setLossPlayerHealth(lossHealth);
		super.setGold(gold);
		super.setSpeed(speed);
		super.setHealth(health);
	}
}
