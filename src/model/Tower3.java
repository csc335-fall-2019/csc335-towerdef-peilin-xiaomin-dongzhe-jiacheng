/**
 * One kind of tower.
 */
package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tower3 extends Tower {
	private int cost =15; // the value of the tower
	private int attack = 2; // the damage this tower can cause
	private double attackSpeed = 0.9; // the speed to send bullet
	public Tower3() { //construct the tower
		super.setCost(cost);
		super.setAttack(attack);
		super.setSpeed(attackSpeed);
	}


}
