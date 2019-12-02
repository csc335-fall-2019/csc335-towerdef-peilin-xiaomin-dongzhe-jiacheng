package model;

import java.io.Serializable;

public class TowerDefMoveMessage implements Serializable {
	private int row;
	private int col;
	private Object obj;
	
	public TowerDefMoveMessage(int row, int col, Object obj) {
		this.row = row;
		this.col = col;
		this.obj = obj;
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

}
