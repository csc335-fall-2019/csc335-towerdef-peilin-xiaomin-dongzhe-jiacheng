package model;

import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class Tower {
	protected int cost;
	protected Point point;
	protected int range;
	protected ImageView bullet;
	protected int attack;
	protected double attackSpeed;
	protected ImageView img;
	
	public Tower() {
	}
	
	public int getCost() {
		return cost;
	}
	
	public Point getPoint() {
		return point;
	}
	public ImageView getBullet() {
		return bullet;
	}
	public void setBullet(ImageView img) {
		this.bullet = img;
	}
	
	public void setSpeed(double speed) {
		this.attackSpeed = speed;
	}
	public double getSpeed() {
		return attackSpeed;
	}
	public ImageView getImg() {
		return img;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getAttack() {
		return attack;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public void setPoint(Point point) {
		this.point = point;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public void setPos(Point point) {
		this.point = point;
	}
	
	public void setImg(ImageView img) {
		this.img = img;
	}
}
