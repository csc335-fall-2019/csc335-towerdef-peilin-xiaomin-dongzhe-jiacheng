package model;

public class Bullets {
	protected int attack;
	protected double speed;
	
	public Bullets() {
		
	}
	
	public int getAttack() {
		return this.attack;
	}
		
	public double getSpeed() {
		return this.speed;
	}
	
	
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public void setSpeed(double attackSpeed) {
		this.speed = attackSpeed;
	}
}
