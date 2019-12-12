/**
 * @author chendongzhe, xiaomin zhao, peilin feng, jiacheng he
 * This is one type of monster with it's own property.
 */
package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Monster6 extends Monster {
	
	private int gold = 9;
	private double speed = 3.2;
	private int health = 8;
	private int lossHealth = 6;
	public Monster6() {
		super.setLossPlayerHealth(lossHealth);
		super.setGold(gold);
		super.setSpeed(speed);
		super.setHealth(health);
		
	}

}
