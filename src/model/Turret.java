package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turret extends Tower {
	private int cost = 20;
	private int range = 3;
	private int attack = 5;
	private int speed = 3;
	
	public Turret() {
		super.setCost(cost);
		super.setRange(range);
		super.setAttack(attack);
		super.setSpeed(speed);
		super.setImg(new ImageView(new Image("/img/tower2.png")));
	}
	
}
