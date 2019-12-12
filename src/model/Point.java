package model;

import java.util.ArrayList;

public class Point {
	private int x;
	private int y;
	private boolean road;
	private Tower tower;
	private ArrayList<Monster> monsters = new ArrayList<Monster>();
	private boolean start = false;
	private boolean end = false;
	private boolean disable = false;
	
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
	public Tower getTower() {
		return tower;
	}
	public ArrayList<Monster> getMonster() {
		return monsters;
	}
	public boolean isStart() {
		return start;
	}
	public boolean isEnd() {
		return end;
	}
	public boolean isdisabled() {
		return disable;
	}
	
	public void setRoad() {
		this.road = true;
	}
	public boolean canSetTower() {
		return !road && tower == null;
		}
	public void setTower(Tower tower) {
		this.tower = tower;
	}
	public void sellTower() {
		this.tower = null;
	}
	public void setMonster(Monster monster) {
		monsters.add(monster);
	}
	
	public void clearMonster(Monster monster) {
		for(Monster monsterIn:monsters) {
			if(monster.getHealth() == monsterIn.getHealth()) {
				monsters.remove(monsterIn);
				break;
			}
		}
	}
	
	public void setStart() {
		start = true;
	}
	public void setEnd() {
		end = true;
	}
	
	public void setDisable() {
		this.disable = true;
	}
	
//	public String toString() {
//		if(start) return "s"+ " `(" + x + "," + y + ")";
//		if(end) return "e" + " `(" + x + "," + y + ")";
//		if (road) return "-" + " `(" + x + "," + y + ")";
//		return "0" + " `(" + x + "," + y + ")";
//	}
}
