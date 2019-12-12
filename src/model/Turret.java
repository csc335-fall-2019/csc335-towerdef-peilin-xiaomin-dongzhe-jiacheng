package model;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, Turret.java
 * 
 * purpose: this is one type of tower, with it's own attributes.
 */


public class Turret extends Tower {
	private int cost =20;
	private int attack = 10;
	private double attackSpeed = 5;
	public Turret() {
		super.setCost(cost);
		super.setAttack(attack);
		super.setSpeed(attackSpeed);
	}

	
}
