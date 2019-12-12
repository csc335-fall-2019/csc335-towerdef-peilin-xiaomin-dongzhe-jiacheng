package model;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, Monster6.java
 * 
 * purpose: 
 */

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Monster6 extends Monster {
	
	private int gold = 9;
	private double speed = 3.2;
	private int health = 8;
	private int lossHealth = 6;
	public Monster6() {
		super.setLossPlayerHealth(lossHealth);
		super.setGold(gold);
		super.setSpeed(speed);
		super.setHealth(health);
		
	}

}
