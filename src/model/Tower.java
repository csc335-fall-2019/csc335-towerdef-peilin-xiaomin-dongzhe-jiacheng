package model;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tower {
	protected int cost;
	protected Point point;
	protected int range;
	protected Bullets bullet;
	private int energy = 0;
	private	ArrayList <Point> rangePoint = new ArrayList<>();
	protected ImageView img;
	public Tower() {
		this.bullet = new Bullets();
	}
	
	public int getEnergy() {
		return this.energy;
	}
	
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
	
	
	public int getCost() {
		return cost;
	}

	public int getAttack() {
		return this.bullet.getphysATK();
	}
	public double getattackSpeed() {
		return this.bullet.getSpeed();
	}
	public void setAttack(int attack) {
		this.bullet.setPhysATK(attack);
	}
	public void setAttackSpeed(double attackSpeed) {
		this.bullet.setSpeedATK(attackSpeed);
	}
	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setRange(int range) {
		this.range = range;
	}
	public int getRange() {
		return range;
	}
	public Point getPoint() {
		return point;
	}
	public void setPos(Point point) {
		this.point = point;
	}
	public void addRange(Map map) {
		
	}
	public ArrayList <Point> getRangePoint(){
		return rangePoint;
	}
	public void setImg(ImageView img) {
		this.img = img;
	}
	public ImageView getImg() {
		return img;
	}
}
