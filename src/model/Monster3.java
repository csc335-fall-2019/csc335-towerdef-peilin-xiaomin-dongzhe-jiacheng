/**
 * One kind of the monster.
 */
package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Monster3 extends Monster {
	private int gold = 14;//value of this monster, when we killed the monster
	private double speed = 10;//speed of the monster
	private int health = 100; //the damage this monster can take
	private int lossHealth = 20; //damage can caused 
	public Monster3() { // construct the monster
		super.setLossPlayerHealth(lossHealth);
		super.setGold(gold);
		super.setSpeed(speed);
		super.setHealth(health);
	}
}
