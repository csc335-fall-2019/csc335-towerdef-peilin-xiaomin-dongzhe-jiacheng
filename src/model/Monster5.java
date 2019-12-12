/**
 * @author chendongzhe, xiaomin zhao, peilin feng, jiacheng he
 * This is one type of monster with it's own property.
 */
package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Monster5 extends Monster {
	private int gold = 1;
	private int speed = 5;
	private int health = 2;
	private int lossHealth = 1;
	public Monster5() {
		super.setLossPlayerHealth(lossHealth);
		super.setGold(gold);
		super.setSpeed(speed);
		super.setHealth(health);
	}

}
