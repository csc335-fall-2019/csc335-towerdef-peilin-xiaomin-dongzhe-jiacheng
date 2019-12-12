/**
 * A kind of tower.
 */
package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turret extends Tower {
	private int cost =20; // the value of the tower
	private int attack = 10; // the damage this tower can cause
	private double attackSpeed = 5; // the speed to send bullet
	public Turret() { //construct the tower
		super.setCost(cost);
		super.setAttack(attack);
		super.setSpeed(attackSpeed);
	}

	
}
