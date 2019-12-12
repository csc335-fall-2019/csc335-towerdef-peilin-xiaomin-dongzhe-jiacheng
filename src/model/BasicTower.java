package model;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, BasicTower.java
 * 
 * purpose: this is one type of tower, with it's own attributes.
 */



public class BasicTower extends Tower {
	private int cost =10;
	private int attack = 1;
	private double attackSpeed = 1;
	public BasicTower() {
		super.setCost(cost);
		super.setAttack(attack);
		super.setSpeed(attackSpeed);
		
	}
	
	
}
