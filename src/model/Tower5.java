/**
 * @author chendongzhe, xiaomin zhao, jiacheng he, peilin feng 
 * this is one type of tower, with it's own attributes.
 */
package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tower5 extends Tower {
	private int cost =25;
	private int attack = 3;
	private double attackSpeed = .5;
	public Tower5() {
		super.setCost(cost);
		super.setAttack(attack);
		super.setSpeed(attackSpeed);
	}
	
}
