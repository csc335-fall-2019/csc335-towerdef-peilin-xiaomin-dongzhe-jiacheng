package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tower3 extends Tower {
	private int cost =15;
	private int attack = 2;
	private double attackSpeed = 0.9;
	public Tower3() {
		super.setCost(cost);
		super.setImg(new ImageView(new Image("/img/turret1.png")));
		super.setBullet(new ImageView(new Image("/img/bullet2.jpeg")));
		super.setAttack(attack);
		super.setSpeed(attackSpeed);
	}


}
