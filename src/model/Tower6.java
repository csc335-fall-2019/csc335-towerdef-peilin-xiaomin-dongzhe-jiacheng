/**
 * @author chendongzhe, xiaomin zhao, jiacheng he, peilin feng 
 * this is one type of tower, with it's own attributes.
 */
package model;

public class Tower6 extends Tower{
	
	private int cost = 135;
	private int attack = 50;
	private double attackSpeed = 3;
	public Tower6() {
		super.setCost(cost);
		super.setAttack(attack);
		super.setSpeed(attackSpeed);
	}
	
}
