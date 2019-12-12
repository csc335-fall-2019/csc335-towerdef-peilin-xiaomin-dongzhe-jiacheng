package model;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, Monster4.java
 * 
 * purpose: This is one type of monster with it's own property.
 */

public class Monster4 extends Monster {
	private int gold = 10;
	private int speed = 2;
	private int health = 7;
	private int lossHealth = 5;
	public Monster4() {
		super.setLossPlayerHealth(lossHealth);
		super.setGold(gold);
		super.setSpeed(speed);
		super.setHealth(health);
	}
}
