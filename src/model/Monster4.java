/**
 * @author chendongzhe, xiaomin zhao, peilin feng, jiacheng he
 * This is one type of monster with it's own property.
 */
package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Monster4 extends Monster {
	private int gold = 10;
	private int speed = 2;
	private int health = 7;
	private int lossHealth = 5;
	public Monster4() {
		super.setLossPlayerHealth(lossHealth);
		super.setGold(gold);
		super.setSpeed(speed);
		super.setHealth(health);
	}
}
