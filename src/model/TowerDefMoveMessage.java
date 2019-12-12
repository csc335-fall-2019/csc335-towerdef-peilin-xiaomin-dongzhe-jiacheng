/**
 * The move message class.
 */
package model;

import java.io.Serializable;

public class TowerDefMoveMessage implements Serializable {
	private int row;
	private int col;
	private Tower tower;
	private int money;
	

	public TowerDefMoveMessage(int row, int col, Tower obj,int money) { // construct
		this.row = row;
		this.col = col;
		this.tower = tower;
		this.money = money;
	}
	
    public int getRow() { // get the row of the position
		return row;  	
    }

    public int getColumn() { // get the column of the position 
    	return col;
    }
    

    public Tower getTower() { //get the tower at the location
		return tower;  	
    }
    
    public int getMoney() { // get money
    	return money;
    }

}
