package model;

import javafx.scene.image.Image;

public class Tower {
	protected int cost;
	protected int attack;
	//protected String name;
	protected int attackSpeed;
	protected int xPos;
	protected int yPos;
	protected int range;
	protected int abilityPower;
	//protected Image tower;
	public Tower() {
		
	}
	
	public int getCost() {
		return cost;
	}
	public int getAbilityPower() {
		return this.abilityPower;
	}
	public void setAbilityPower(int newPower) {
		this.abilityPower = newPower;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getAttack() {
		return attack;
	}
	public int getattackSpeed() {
		return cost;
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
}
