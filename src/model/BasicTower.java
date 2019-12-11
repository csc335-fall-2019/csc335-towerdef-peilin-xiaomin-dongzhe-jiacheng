package model;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BasicTower extends Tower {
	private int cost =10;
	private int attack = 1;
	private double attackSpeed = 1;
	public BasicTower() {
		super.setCost(cost);
		super.setImg(new ImageView(new Image("/img/TOWER.png")));
		super.setBullet(new ImageView(new Image("/img/bullet.png")));
		super.setAttack(attack);
		super.setSpeed(attackSpeed);
	}
	
	public void setAttackRange(Map map) {
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
