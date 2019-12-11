/**
 * This is the base class for other towers to extend
 */
package model;

import java.util.ArrayList;

import javafx.scene.image.ImageView;

public abstract class Tower {
	protected int cost; //value of the tower
	protected Point point; //a location in the game board
	protected int range; // range the tower can attack
	protected ImageView bullet; //the bullet
	protected int attack; //damage the tower can cause
	protected double attackSpeed; //speed to send bullets
	protected ImageView img;
	private ArrayList<Point> attackRange; // list of locations the bullet can reach
	
	public Tower() {
		attackRange = new ArrayList<Point>();
	}
	
	
	public int getCost() { // get method, return the value of the tower
		return cost;
	}
	
	public Point getPoint() { // get method, return the location of the tower
		return point;
	}
	public ImageView getBullet() { //get method, return the bullet of this tower
		return bullet;
	}
	public void setBullet(ImageView img) { //set the bullet with this tower
		this.bullet = img;
	}
	
	public void setSpeed(double speed) { //set the speed of bullet comes out
		this.attackSpeed = speed;
	}
	public double getSpeed() { // getter, return the speed
		return attackSpeed;
	}
	public ImageView getImg() { // getter, return the image of the tower
		return img;
	}
	
	public ArrayList<Point> getAttackRange() { // getter, return the list of location the bullet can reach
		return attackRange;
	}
	
	public void setAttack(int attack) { //set the damage value
		this.attack = attack;
	}
	public int getAttack() { // getter, return the attack value
		return attack;
	}
	
	public void setCost(int cost) { // set the cost the tower
		this.cost = cost;
	}
	
	public void setPoint(Point point) { // set the location of the tower
		this.point = point;
	}

	public void setRange(int range) {//set the range(integer)
		this.range = range;
	}

	public void setPos(Point point) { //set the location of the tower
		this.point = point;
	}
	
	public void setImg(ImageView img) { //set the image of the tower
		this.img = img;
	}
	
	public void addAttackRange(Point point) { // add a point to the list of location the tower can reach
		this.attackRange.add(point);
	}
	
	public abstract void setAttackRange(Map map); //
}
