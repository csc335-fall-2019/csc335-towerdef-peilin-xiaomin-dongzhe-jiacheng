package model;

import java.io.Serializable;

public class TowerDefMoveMessage implements Serializable {
	private int row;
	private int col;
	private Object obj;
	private int money;
	
	public TowerDefMoveMessage(int row, int col, Object obj,int money) {
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
    
    public Object getObj() {
		return obj;  	
    }
    public int getMoney() {
    	return money;
    }

}
