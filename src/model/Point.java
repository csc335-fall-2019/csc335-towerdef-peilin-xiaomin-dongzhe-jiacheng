package model;

import java.util.ArrayList;

public class Point {
	private int x;
	private int y;
	private boolean road;
	private boolean isPass = false;
	private Tower tower;
	private ArrayList<Monster> monsters = new ArrayList<Monster>();
	private boolean start = false;
	private boolean end = false;
	private ArrayList<Point> adjacentPoints = new ArrayList<Point>();
	
	public Point(int x,int y,boolean isRoad) {
		this.x = x;
		this.y = y;
		this.road = isRoad;
	}
	public void pass() {
		isPass = true;
	}
	public boolean getPass() {
		return isPass;
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
	
	//public ArrayList<Point> getAdjacentPoints
	
	public void setRoad() {
		this.road = true;
	}
	public boolean canSetTower() {
		return !road && tower == null;
		}
	public void setTower(Tower tower) {
		this.tower = tower;
	}
	public boolean hasTower() {
		return tower != null;
	}
	public void setMonster(Monster monster) {
		monsters.add(monster);
	}
	public Tower getTower() {
		return tower;
	}
	public ArrayList<Monster> getMonster() {
		return monsters;
	}

	public void sellTower() {
		this.tower = null;
	}

	public void clearMonster() {
		for(Monster monster:monsters) {
			if(monster.dead()) {
				monsters.remove(monster);
			}
		}
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
	public boolean isStart() {
		return start;
	}
	public boolean isEnd() {
		return end;
	}
	public String toString() {
		if(start) return "s";
		if(end) return "e";
		if (road) return "-";
		return "0";
	}
}
