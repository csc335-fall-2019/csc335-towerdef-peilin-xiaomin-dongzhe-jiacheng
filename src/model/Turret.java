package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turret extends Tower {
	private int cost =20;
	private int attack = 10;
	private double attackSpeed = 5;
	public Turret() {
		super.setCost(cost);
		super.setImg(new ImageView(new Image("/img/tower2.png")));
		super.setBullet(new ImageView(new Image("/img/bullet1.jpg")));
		super.setAttack(attack);
		super.setSpeed(attackSpeed);
	}
	@Override
	public void setAttackRange(Map map) {
		// TODO Auto-generated method stub
		
	}
	
}
