package model;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, Tower3.java
 * 
 * purpose: 
 */

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tower3 extends Tower {
	private int cost =15;
	private int attack = 2;
	private double attackSpeed = 0.9;
	public Tower3() {
		super.setCost(cost);
		super.setAttack(attack);
		super.setSpeed(attackSpeed);
	}


}
