package model;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.image.ImageView;

public class Tower {
	private int cost;
	private Point point;
	private int range;
	private int attack;
	private int speed;
	private ArrayList<Monster> availMonsters;
	private ImageView img;
	
//	private int energy = 0;

	
	public Tower() {
		availMonsters = new ArrayList<Monster>();
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
	
	public int getAttack() {
		return attack;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public ArrayList<Monster> getAvailMonsters() {
		return availMonsters;
	}
	
	public ImageView getImg() {
		return img;
	}

	
//	public int getEnergy() {
//		return this.energy;
//	}
//	

	
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public void setPoint(Point point) {
		this.point = point;
	}

	public void setRange(int range) {
		this.range = range;
	}
	
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
//	public void EnergyUP(int amount) {
//		this.energy += amount;	
//	}
//	
//	public boolean checkUltimate() {
//		if(this.energy >= 100) {
//			return true;
//		}
//		return false;
//	}
//	
//	public void EnergyClear() {
//		this.energy = 0;
//	}
//	
	
	public void setImg(ImageView img) {
		this.img = img;
	}
	
	public void addMonster(Monster monster) {
		this.availMonsters.add(monster);
	}
	
	public void removeMonster(Monster monster) {
		this.availMonsters.remove(monster);
	}
	
	public void clearDeadMonsters() {
		ArrayList<Monster> deadMonsters = new ArrayList<Monster>();
		for (Monster monster: availMonsters) {
			if (monster.getBlood() == 0) {
				deadMonsters.add(monster);
			}
		}
		availMonsters.removeAll(deadMonsters);	
	}

}
