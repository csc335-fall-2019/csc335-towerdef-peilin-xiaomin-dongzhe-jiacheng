/**
 * This is the class for a basic monster, set the attributes of the monster.
 */
package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BasicMonster extends Monster {
	private int gold = 5; // value of the monster
	private int speed = 1; // speed of the monster movement
	private int health = 10; // the life of the monster
	private int lossHealth = 4; // hurt of this monster can caused
	public BasicMonster() { 
		super.setLossPlayerHealth(lossHealth);// extend the monster class
		super.setGold(gold);
		super.setSpeed(speed);
		super.setHealth(health);
		super.setImg(new ImageView(new Image("/img/giphy.gif")));
	}
}
