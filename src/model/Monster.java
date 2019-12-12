package model;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, Monster.java
 * 
 * purpose: 
 */

import javafx.scene.image.ImageView;

public class Monster {
	protected int gold;
	protected double speed;
	protected int lossHealth;
	protected double health;
	protected Point point;
	protected ImageView img;

	//protected boolean dead = false;
	public Monster() {
		
	}
	
	public Monster(int gold,double speed,double health, int lossHealth) {
		this.gold =gold;
		this.speed = speed;
		this.health = health;
		this.lossHealth = lossHealth;
	}
	public int lossPlayerHealth() {
		return lossHealth;
	}
	public void setLossPlayerHealth(int lossHealth) {
		this.lossHealth = lossHealth;
	}
	
	public int getGold() {
		return gold;
	}
	public double getSpeed() {
		return speed;
	}
	
	public double getHealth() {
		return health;
	}
	
	public Point getPoint() {
		return point;
	}
	public void setHealth(double health) {
		this.health = health;
	}
	public void healthLoss(double health) {
		this.health -= health;
	}
	public boolean dead() {
		return health <=0;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
		
	}
	public void setSpeed(double speed) {
		this.speed = speed;
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
