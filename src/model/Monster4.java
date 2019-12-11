/**
 * One kind of monster.
 */
package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Monster4 extends Monster {
	private int gold = 10;// the reward value when the monster is killed
	private int speed = 2; // speed of the monster
	private int health = 7; // damage this monster can take
	private int lossHealth = 5; //damage this monster cause
	public Monster4() { // construct the monster
		super.setLossPlayerHealth(lossHealth);
		super.setGold(gold);
		super.setSpeed(speed);
		super.setHealth(health);
	}
}
