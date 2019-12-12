package model;

/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, TowerDefMoveMessage.java
 * 
 * purpose: this is a TowerDefMoveMessage which send the information about
 * 			current changed Tower and money to GUI which allow the GUI know
 * 			where to show the tower and when money will changed
 */

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
