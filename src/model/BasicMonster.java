
package model;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, BasicMonster.java
 * 
 * purpose: This is one type of monster with it's own property.
 */


public class BasicMonster extends Monster {
	private int gold = 5;
	private int speed = 1;
	private int health = 10;
	private int lossHealth = 4;
	public BasicMonster() {
		super.setLossPlayerHealth(lossHealth);
		super.setGold(gold);
		super.setSpeed(speed);
		super.setHealth(health);	
		}
}
