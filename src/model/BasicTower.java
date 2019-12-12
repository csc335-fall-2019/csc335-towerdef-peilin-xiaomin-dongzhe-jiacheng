package model;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, BasicTower.java
 * 
 * purpose: 
 */

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BasicTower extends Tower {
	private int cost =10;
	private int attack = 1;
	private double attackSpeed = 1;
	public BasicTower() {
		super.setCost(cost);
		super.setAttack(attack);
		super.setSpeed(attackSpeed);
		
	}
	
	
}
