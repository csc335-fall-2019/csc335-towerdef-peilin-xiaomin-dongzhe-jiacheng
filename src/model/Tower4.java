/**
 * One kind of tower.
 */
package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tower4 extends Tower {
	private int cost =100; // the value of the tower
	private int attack = 30; // the damage this tower can cause
	private double attackSpeed = 5.3; // the speed to send bullet
	public Tower4() { //construct the tower
		super.setCost(cost);
		super.setImg(new ImageView(new Image("/img/turret2.jpg")));
		super.setBullet(new ImageView(new Image("/img/bullet3.jpg")));
		super.setAttack(attack);
		super.setSpeed(attackSpeed);
	}
	@Override
	public void setAttackRange(Map map) {
		// TODO Auto-generated method stub
		
	}
}
