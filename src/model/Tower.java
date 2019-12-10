package model;

import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class Tower {
	protected int cost;
	protected Point point;
	protected int range;
	protected Bullets bullet;
	private int energy = 0;
	protected ImageView img;
	
	public Tower() {
		this.bullet = new Bullets();
	}
	
	public int getCost() {
		return cost;
	}
	
	public Point getPoint() {
		return point;
	}
	
	public int getRange() {
		return range;
	}
	
	public Bullets getBullet() {
		return bullet;
	}
	
	public int getAttack() {
		return this.bullet.getAttack();
	}
	
	public double getattackSpeed() {
		return this.bullet.getSpeed();
	}
	
//	public ArrayList <Point> getRangePoint(){
//		return rangePoints;
//	}
//	
//	public String getType() {
//		return type;
//	}
	
	public int getEnergy() {
		return this.energy;
	}
	
	public ImageView getImg() {
		return img;
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
	
	public void setBullet(Bullets bullet) {
		this.bullet = bullet;
	}
	
	public void setAttack(int attack) {
		this.bullet.setAttack(attack);
	}
	
	public void setAttackSpeed(double attackSpeed) {
		this.bullet.setSpeed(attackSpeed);
	}
	
//	public void setRangePoint(ArrayList<Point> rangePoints) {
//		this.rangePoints = rangePoints;
//	}
//	
//	public void setType(String type) {
//		this.type = type;
//	}
	
	public void EnergyUP(int amount) {
		this.energy += amount;	
	}
	
	public boolean checkUltimate() {
		if(this.energy >= 100) {
			return true;
		}
		return false;
	}
	
	public void EnergyClear() {
		this.energy = 0;
	}
	
	public void setImg(ImageView img) {
		this.img = img;
	}

}
