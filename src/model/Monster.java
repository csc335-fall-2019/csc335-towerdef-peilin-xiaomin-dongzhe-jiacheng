/**
 * @author chendongzhe, xiaomin zhao, jiacheng he, peilin he
 */
package model;

import javafx.scene.image.ImageView;

public class Monster {
	protected int gold;
	protected double speed;
	protected int lossHealth;
	protected double health;
	protected Point point;
	protected ImageView img;

	//protected boolean dead = false;
	public Monster() {
		
	}
	
	public Monster(int gold,double speed,double health, int lossHealth) {
		this.gold =gold; // the value of the monster, when kill
		this.speed = speed; // the speed of the monster moves
		this.health = health; // the damage this monster can take
		this.lossHealth = lossHealth;//the damage this monster caused
	}
	/**
	 * a get method return the damage this monster caused
	 * @return the damage this monster caused
	 */
	public int lossPlayerHealth() {
		return lossHealth;
	}
	/**
	 * set the damage the monster caused
	 * @param lossHealth, the damage this monster caused
	 */
	public void setLossPlayerHealth(int lossHealth) {
		this.lossHealth = lossHealth;
	}
	/**
	 * return the value of the monster
	 * @return  value of the monster
	 */
	public int getGold() {
		return gold;
	}
	/**
	 * return the speed of the monster
	 * @return speed of the monster
	 */
	public double getSpeed() {
		return speed;
	}
	/**
	 * return the damage of the monster can take
	 * @return the damage of the monster can take
	 */
	public double getHealth() {
		return health;
	}
	/**
	 * return the location
	 * @return location, the position
	 */
	public Point getPoint() {
		return point;
	}
	/**
	 * set the damage of the monster can take
	 * @param health, the damage of the monster can take
	 */
	public void setHealth(double health) {
		this.health = health;
	}
	/**
	 * reset the life of player after a monster hit
	 * @param health, the original life
	 */
	public void healthLoss(double health) {
		this.health -= health;
	}
	/**
	 * if the monster is die
	 * @return, if the monster is die
	 */
	public boolean dead() {
		return health <=0;
	}
	/**
	 * set the initial money
	 * @param gold, the value fo teh monster
	 */
	public void setGold(int gold) {
		this.gold = gold;
		
	}
	/**
	 * setter to set the speed of the monster
	 * @param speed, the speed of monster move
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	/**
	 * setter to set the location to be point
	 * @param point, a location
	 */
	public void setPoint(Point point) {
		this.point = point;
	}
	/**
	 * set the image of the monster
	 * @param img, a image of the monster
	 */
	public void setImg(ImageView img) {
		this.img = img;
	}
	/**
	 * return the image of the img of monster
	 * @return , the image of the monster
	 */
	public ImageView getImg() {
		return img;
	}
}
