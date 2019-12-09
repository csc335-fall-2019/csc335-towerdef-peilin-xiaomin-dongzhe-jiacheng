package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turret extends Tower {
	private int cost = 20;
	private int attack = 5;
	private int range = 3;
	private double attackSpeed = 0.5;
	
	public Turret() {
		super.setCost(cost);
		super.setAttack(attack);
		super.setAttackSpeed(attackSpeed);
		super.setRange(range);
		super.setImg(new ImageView(new Image("/img/tower2.png")));
	}
	
}
