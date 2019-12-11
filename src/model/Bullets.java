/**
 * This class is the base bullet class.
 */
package model;

public class Bullets {
	protected int attack; // the damage this bullet can caused
	protected double speed; // the speed of the bullet can move
	
	public Bullets() {
		
	}
	
	public int getAttack() { // A get method to get the damage
		return this.attack;
	}
		
	public double getSpeed() { // a get method to get the speed 
		return this.speed;
	}
	
	
	public void setAttack(int attack) { //set method to set the damage
		this.attack = attack;
	}
	
	public void setSpeed(double attackSpeed) { // set method to set the speed
		this.speed = attackSpeed;
	}
}
