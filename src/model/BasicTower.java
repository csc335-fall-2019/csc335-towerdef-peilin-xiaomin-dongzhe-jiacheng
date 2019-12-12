/**
 * This is a basic tower, and it's attributes.
 */
package model;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BasicTower extends Tower {
	private int cost =10; // the cost to buy this tower
	private int attack = 1;  // the harm of one bullet from this tower
	private double attackSpeed = 1; //the speed of sending bullet
	public BasicTower() {  // extend from the tower class
		super.setCost(cost);
		super.setAttack(attack);
		super.setSpeed(attackSpeed);
		super.setImg(new ImageView(new Image("/img/TOWER.png")));
		super.setBullet(new ImageView(new Image("/img/bullet.png")));
	}
	

	public void setAttackRange(Map map) { // set the range of the bullet to send, only send to road
		int x = this.getPoint().getX();
		int y = this.getPoint().getY();
		if (x > 0 && map.getGraph()[x-1][y].isRoad()) {  
			this.addAttackRange(map.getGraph()[x-1][y]);
		}
		if (x < map.getGraph().length && map.getGraph()[x+1][y].isRoad()) {
			this.addAttackRange(map.getGraph()[x+1][y]);
		}	
		if (y > 0 && map.getGraph()[x][y-1].isRoad()) {
			this.addAttackRange(map.getGraph()[x][y-1]);
		}
		if (y < map.getGraph()[x].length && map.getGraph()[x][y+1].isRoad()) {
			this.addAttackRange(map.getGraph()[x][y+1]);
		}
	}
	
}
