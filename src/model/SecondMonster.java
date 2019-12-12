package model;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, SecondMonster.java
 * 
 * purpose: 
 */

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SecondMonster extends Monster {
	private int gold = 6;
	private int speed = 1;
	private int health = 11;
	private int lossHealth = 3;
	public SecondMonster() {
		super.setLossPlayerHealth(lossHealth);
		super.setGold(gold);
		super.setSpeed(speed);
		super.setHealth(health);
		
	}

}
