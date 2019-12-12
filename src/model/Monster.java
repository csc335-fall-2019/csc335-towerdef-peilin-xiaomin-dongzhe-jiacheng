/**
 * This is a base monster class, for monster classes to extend.
 */
package model;

import javafx.scene.image.ImageView;

public class Monster {

	protected int gold;// the value of the monster, when kill
	protected double speed; // the speed of the monster moves
	
	protected int lossHealth; //the damage this monster caused
	
	protected double health; // the damage this monster can take
	private Point point;
	private ImageView img;

	//protected boolean dead = false;
	public Monster() {
		
	}

	public Monster(int gold,double d,double e, int lossHealth) { // construct the monster

	
		this.gold =gold;
		this.speed = d;
		this.health = e;
		this.lossHealth = lossHealth;
	}
	public int lossPlayerHealth() { //a get method return the damage this monster caused
		return lossHealth;
	}
	public void setLossPlayerHealth(int lossHealth) {
		this.lossHealth = lossHealth; //set the damage the monster caused
	}
	
	public int getGold() { // return the value of the monster
		return gold;
	}
	public double getSpeed() { //return the speed of the monster
		return speed;
	}
	
	public double getHealth() { //return the damage of the monster can take
		return health;
	}
	
	public Point getPoint() { // return the location 
		return point;
	}

	public void setHealth(double d) { //set the damage of the monster can take
		this.health = d;
	}
	public void healthLoss(double health) { // reset the life of player after a monster hit
		this.health -= health;
	}
	public boolean dead() { // determine if the player die, when life is 0
		return health <=0;
	}
	
	public void setGold(int gold) { // set the initial money
		this.gold = gold;
		
	}
	public void setSpeed(double speed) {// setter to set the speed of the monster
		this.speed = speed;
	}
	
	public void setPoint(Point point) { // setter to set the location to be point
		this.point = point;
	}
	public void setImg(ImageView img) { //set the image of the monster
		this.img = img;
	}
	public ImageView getImg() { //get method, return the image of the monster
		return img;
	}
}
