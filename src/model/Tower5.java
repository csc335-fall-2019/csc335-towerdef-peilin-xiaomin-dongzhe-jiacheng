package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tower5 extends Tower {
	private int cost =25; // the value of the tower
	private int attack = 3; // the damage this tower can cause
	private double attackSpeed = .5; // the speed to send bullet
	public Tower5() {//construct the tower
		super.setCost(cost);
		super.setAttack(attack);
		super.setSpeed(attackSpeed);
		super.setImg(new ImageView(new Image("/img/turret3.jpg")));
		super.setBullet(new ImageView(new Image("/img/bullet4.jpg")));
	}
	
}
