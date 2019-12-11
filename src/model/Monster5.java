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
		super.setImg(new ImageView(new Image("/img/enemy5.gif")));
	}

}
