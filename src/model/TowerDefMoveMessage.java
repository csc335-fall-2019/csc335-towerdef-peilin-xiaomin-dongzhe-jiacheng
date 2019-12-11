package model;

import java.io.Serializable;

public class TowerDefMoveMessage implements Serializable {
	private int row;
	private int col;
	private Tower obj;
	private int money;
	
	public TowerDefMoveMessage(int row, int col, Tower obj,int money) {
		this.row = row;
		this.col = col;
		this.obj = obj;
		this.money = money;
	}
	
    public int getRow() {
		return row;  	
    }

    public int getColumn() {
    	return col;
    }
    
    public Tower getTower() {
		return obj;  	
    }
    
    public int getMoney() {
    	return money;
    }

}
