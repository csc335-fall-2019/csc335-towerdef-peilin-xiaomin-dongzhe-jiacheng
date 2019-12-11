/**
 * One kind of monster.
 */
package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Monster5 extends Monster {
	private int gold = 1;// the reward value when the monster is killed
	private int speed = 5; // speed of the monster
	private int health = 2; // damage this monster can take
	private int lossHealth = 1; //damage this monster cause
	public Monster5() { // construct the monster
		super.setLossPlayerHealth(lossHealth);
		super.setGold(gold);
		super.setSpeed(speed);
		super.setHealth(health);
	}

}
