package model;

import javafx.scene.image.ImageView;

public class Monster {
	protected int gold;
	protected int speed;
	protected int armor;
	protected int magicResist;
	protected int blood;
	protected int energy;
	private Point point;
	private ImageView img;

	public Monster() {
		
	}
	
	public Monster(int gold,int speed,int armor,int magicResist,int blood, int energy) {
		this.gold =gold;
		this.speed = speed;
		this.armor = armor;
		this.magicResist = magicResist;
		this.blood = blood;
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
	public int getBlood() {
		return blood;
	}
	public int getEnergy() {
		return energy;
	}
	public Point getPoint() {
		return point;
	}
	public ImageView getImg() {
		return img;
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
	public void setMagicResist(int magicResist) {
		this.magicResist = magicResist;
	}
	public void setBlood(int blood) {
		this.blood = blood;
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
	
	public boolean isDead() {
		return blood == 0;
	}
	
	public void bloodLoss(int attack) {
		this.blood -= (attack - armor);
	}

}
