package model;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BasicTower extends Tower {
	private int cost =10;
	private int attack = 1;
	private double attackSpeed = 1;
	public BasicTower() {
		super.setCost(cost);
		super.setAttack(attack);
		super.setSpeed(attackSpeed);
		super.setImg(new ImageView(new Image("/img/TOWER.png")));
		super.setBullet(new ImageView(new Image("/img/bullet.png")));
	}
	
	
}
