package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BasicMonster extends Monster {
	private int gold = 10;
	private int speed = 1;
	private int armor = 1;
	private int health = 10;
	private int energy = 10;
	public BasicMonster() {
		super.setArmor(armor);
		super.setEnergy(energy);
		super.setGold(gold);
		super.setSpeed(speed);
		super.setHealth(health);
		super.setImg(new ImageView(new Image("/img/enemy4.gif")));
	}
}
