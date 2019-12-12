package model;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, Tower4.java
 * 
 * purpose: 
 */

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tower4 extends Tower {
	private int cost =100;
	private int attack = 30;
	private double attackSpeed = 5.3;
	public Tower4() {
		super.setCost(cost);
		super.setAttack(attack);
		super.setSpeed(attackSpeed);
	}

}
