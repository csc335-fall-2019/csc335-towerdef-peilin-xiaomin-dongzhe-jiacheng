package model;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, Turret.java
 * 
 * purpose: 
 */

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turret extends Tower {
	private int cost =20;
	private int attack = 10;
	private double attackSpeed = 5;
	public Turret() {
		super.setCost(cost);
		super.setAttack(attack);
		super.setSpeed(attackSpeed);
	}

	
}
