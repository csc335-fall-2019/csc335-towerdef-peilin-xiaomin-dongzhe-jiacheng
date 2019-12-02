package model;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Tower {
	protected int cost;
	protected int xPos;
	protected int yPos;
	protected int range;
	protected Bullets bullet;
	private int energy = 0;
	private	ArrayList <Point> rangePoint = new ArrayList<>();
	public Tower() {
		
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
	public int getAbilityPower() {
		return this.bullet.magicATK;
	}
	public int getAttack() {
		return this.bullet.physicalATK;
	}
	public int getattackSpeed() {
		return this.bullet.getSpeed();
	}
	public void setAbilityPower(int newPower) {
		this.bullet.setMagicATK(newPower);
	}
	public void setAttack(int attack) {
		this.bullet.setPhysATK(attack);
	}
	public void setAttackSpeed(int attackSpeed) {
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
	public int getX() {
		return xPos;
	}
	public int getY() {
		return yPos;
	}
	public void setPos(int x,int y) {
		xPos = x;
		yPos = y;
	}
	public void addRange(Map map) {
		
	}
	public ArrayList <Point> getRangePoint(){
		return rangePoint;
	}
}
