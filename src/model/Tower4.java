package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tower4 extends Tower {
	private int cost =100;
	private int attack = 30;
	private double attackSpeed = 5.3;
	public Tower4() {
		super.setCost(cost);
		super.setAttack(attack);
		super.setSpeed(attackSpeed);
	}

}
