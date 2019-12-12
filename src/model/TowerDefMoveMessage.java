package model;

import java.io.Serializable;

public class TowerDefMoveMessage implements Serializable {
	private int row;
	private int col;
	private Tower tower;
	private int money;
	
	public TowerDefMoveMessage(int row, int col, Tower tower,int money) {
		this.row = row;
		this.col = col;
		this.tower = tower;
		this.money = money;
	}
	
    public int getRow() {
		return row;  	
    }

    public int getColumn() {
    	return col;
    }
    
    public Tower getTower() {
		return tower;  	
    }
    
    public int getMoney() {
    	return money;
    }

}
