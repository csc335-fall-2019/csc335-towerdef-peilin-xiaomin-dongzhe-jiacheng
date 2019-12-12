/**
 * @author chendongzhe, xiaomin zhao, peilin feng, jiacheng he
 * This is one type of monster with it's own property.
 */
package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BasicMonster extends Monster {
	private int gold = 5;
	private int speed = 1;
	private int health = 10;
	private int lossHealth = 4;
	public BasicMonster() {
		super.setLossPlayerHealth(lossHealth);
		super.setGold(gold);
		super.setSpeed(speed);
		super.setHealth(health);	
		}
}
