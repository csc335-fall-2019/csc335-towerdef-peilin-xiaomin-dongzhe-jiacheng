package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Monster6 extends Monster {
	
	private int gold = 9; // the reward value when the monster is killed
	private double speed = 3.2; // speed of the monster
	private int health = 8;// damage this monster can take
	private int lossHealth = 6;//damage this monster cause
	public Monster6() { // construct the monster
		super.setLossPlayerHealth(lossHealth);
		super.setGold(gold);
		super.setSpeed(speed);
		super.setHealth(health);
		super.setImg(new ImageView(new Image("/img/enemy6.gif")));
	}

}
