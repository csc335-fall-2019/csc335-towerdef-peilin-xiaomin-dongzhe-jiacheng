package model;

import javafx.scene.image.ImageView;

public class Monster {
	protected int gold;
	protected int speed;
	protected int armor;
	protected int magicResist;
	protected int health;
	protected int energy;
	private Point point;
	private ImageView img;

	//protected boolean dead = false;
	public Monster() {
		
	}
	public Monster(int gold,int speed,int armor,int magicResist,int health, int energy) {
		this.gold =gold;
		this.speed = speed;
		this.armor = armor;
		this.magicResist = magicResist;
		this.health = health;
		this.energy = energy;
	}
	
	public int getGold() {
		return gold;
	}
	public int getSpeed() {
		return speed;
	}
	public int getArmor() {
		return armor;
	}
	public int getMagicResist() {
		return magicResist;
	}
	public int getHealth() {
		return health;
	}
	public int getEnergy() {
		return energy;
	}
	public Point getPoint() {
		return point;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public boolean dead() {
		return health == 0;
	}
	public void newSpeed(int speed) {
		this.speed = speed;
	}
	public void setGold(int gold) {
		this.gold = gold;
		
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setArmor(int armor) {
		this.armor = armor;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public void setImg(ImageView img) {
		this.img = img;
	}
	public ImageView getImg() {
		return img;
	}
}
