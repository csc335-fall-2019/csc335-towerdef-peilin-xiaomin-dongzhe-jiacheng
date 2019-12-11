package model;

import java.io.Serializable;

public class MonsterSetMessage implements Serializable {
	
	private Monster monster;
	private Point point;
	// private int money;
	

	public MonsterSetMessage(Monster monster, Point point) {
		this.monster = monster;
		this.point = point;
		// this.money = money;
	}
	
	public Monster getMonster() {
		return monster;
	}
	
	public Point getPoint() {
		return point;
	}

	
}
