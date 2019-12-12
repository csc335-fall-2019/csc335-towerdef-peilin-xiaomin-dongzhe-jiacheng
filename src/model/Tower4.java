package model;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, Tower4.java
 * 
 * purpose: this is one type of tower, with it's own attributes.
 */


public class Tower4 extends Tower {
	private int cost =100;
	private int attack = 30;
	private double attackSpeed = 5.3;
	public Tower4() {
		super.setCost(cost);
		super.setAttack(attack);
		super.setSpeed(attackSpeed);
	}

}
