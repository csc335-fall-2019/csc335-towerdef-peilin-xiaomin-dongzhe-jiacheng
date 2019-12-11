package model;

import java.io.Serializable;

public class MonsterMoveMessage implements Serializable {
	public Point currPoint;
	public Point nextRoad;
	public Monster monster;
	public int count;
	
	public MonsterMoveMessage(Point currPoint, Point nextRoad, Monster monster, int count) {
		this.currPoint = currPoint;
		this.nextRoad = nextRoad;
		this.monster = monster;
		this.count = count;
	}
	
	public Point getCurrPoint() {
		return currPoint;
	}
	
	public Point getNextRoad() {
		return nextRoad;
	}
	
	public Monster getMonster() {
		return monster;
	}

	public int getCount() {
		return count;
	}
}
