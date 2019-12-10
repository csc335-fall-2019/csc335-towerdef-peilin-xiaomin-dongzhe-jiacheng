package model;

import java.util.ArrayList;

public class Point {
	private int x;
	private int y;
	private boolean road;
	private boolean start = false;
	private boolean end = false;
	private Tower tower;
	private ArrayList<Monster> monsters = new ArrayList<Monster>();
	
	public Point(int x,int y,boolean isRoad) {
		this.x = x;
		this.y = y;
		this.road = isRoad;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean isRoad() {
		return road;
	}
	public boolean isStart() {
		return start;
	}
	public boolean isEnd() {
		return end;
	}
	public Tower getTower() {
		return tower;
	}
	public ArrayList<Monster> getMonster() {
		return monsters;
	}
	
	
	public void setRoad() {
		this.road = true;
	}
	public void setStart() {
		this.start = true;
	}
	public void setEnd() {
		this.end = true;
	}
	public void setTower(Tower tower) {
		this.tower = tower;
	}
	public void setMonsters(ArrayList<Monster> monsters) {
		this.monsters = monsters;
	}
	
	public boolean canSetTower() {
		return !road && tower == null;
	}
	public void sellTower() {
		this.tower = null;
	}
	
	public void addMonster(Monster monster) {
		monsters.add(monster);
	}
	
	public void clearDeadMonster() {
		for (Monster monster : monsters) {
			if(monster.isDead()) {
				monsters.remove(monster);
			}
		}
	}
	
	public void clearLeftMonster(Monster monster) {
		for(Monster currMonster : monsters) {
			if(monster.getBlood() == currMonster.getBlood()) {
				monsters.remove(currMonster);
				break;
			}
		}
	}

	public String toString() {
		if(start) return "s"+ " `(" + x + "," + y + ")";
		if(end) return "e" + " `(" + x + "," + y + ")";
		if (road) return "-" + " `(" + x + "," + y + ")";
		return "0" + " `(" + x + "," + y + ")";
	}
}
