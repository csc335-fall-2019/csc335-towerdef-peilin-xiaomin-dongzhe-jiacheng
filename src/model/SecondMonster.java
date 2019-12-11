/**
 * One kind of monster.
 */
package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SecondMonster extends Monster {
	private int gold = 6; // the reward value when the monster is killed
	private int speed = 1; // speed of the monster
	private int health = 11; // damage this monster can take
	private int lossHealth = 3; //damage this monster cause
	public SecondMonster() { //construct the monster
		super.setLossPlayerHealth(lossHealth);
		super.setGold(gold);
		super.setSpeed(speed);
		super.setHealth(health);
		super.setImg(new ImageView(new Image("/img/enemy2.gif")));
	}

}
