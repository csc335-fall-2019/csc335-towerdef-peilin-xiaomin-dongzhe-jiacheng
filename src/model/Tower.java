
package model;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, Tower.java
 * 
 * purpose: This is the base class for other towers to extend
 */

import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class Tower {
	protected int cost;  //value of the tower
	protected Point point; //a location in the game board
	protected int range;
	protected ImageView bullet;
	protected int attack;
	protected double attackSpeed;
	protected ImageView img;
	protected ArrayList<Point> attackRange;
	
	public Tower() {
		attackRange = new ArrayList<Point>();
	}
	
	/**
	 * get method, return the value of the tower
	 * @return, the cost of tower
	 */
	public int getCost() {
		return cost;
	}
	/**
	 * get method, return the location of the tower
	 * @return location of the tower
	 */
	public Point getPoint() {
		return point;
	}
	/**
	 * get method, return the bullet of this tower
	 * @return the bullet of this tower
	 */
	public ImageView getBullet() {
		return bullet;
	}
	/**
	 * set the bullet with this tower
	 * @param img, the image
	 */
	public void setBullet(ImageView img) {
		this.bullet = img;
	}
	/**
	 * set the speed of bullet comes out
	 * @param speed, a double
	 */
	public void setSpeed(double speed) {
		this.attackSpeed = speed;
	}
	/**
	 * getter, return the speed

	 * @return speed
	 */
	public double getSpeed() {
		return attackSpeed;
	}
	/**
	 * getter, return the image of the tower
	 * @return the image of the tower
	 */
	public ImageView getImg() {
		return img;
	}
	/**
	 * getter, return the list of location the bullet can reach
	 * @return the list of location the bullet can reach
	 */
	public ArrayList<Point> getAttackRange() {
		return attackRange;
	}
	/**
	 * set the damage value
	 * @param attack, the value
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}
	/**
	 * getter, return the attack value
	 * @return the attack value
	 */
	public int getAttack() {
		return attack;
	}
	/**
	 * set the cost the tower
	 * @param cost, the value of the tower
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}
	/**
	 * set the location of the tower
	 * @param point, the point
	 */
	public void setPoint(Point point) {
		this.point = point;
	}
/**
 * set the range(integer)
 * @param range, the range
 */
	public void setRange(int range) {
		this.range = range;
	}
/**
 * set the image of the tower
 * @param img, the image of the tower
 */
	public void setImg(ImageView img) {
		this.img = img;
	}
	/**
	 * add a point to the list of location the tower can reach
	 * @param point, a point
	 */
	public void addAttackRange(Point point) {
		this.attackRange.add(point);
	}
}
