
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
	/**
	 * get the row of the position
	 * @return, the row of the position 
	 */
    public int getRow() {
		return row;  	
    }
    
	/**
	 * get the column of the position 
	 * @return col position 
	 */
    public int getColumn() {
    	return col;
    }
    /**
     * get the tower at the location
     * @return the tower obj
     */
    public Tower getTower() {
		return tower;  	
    }
    /**
     * return the money
     * @return an integer that is the value of the tower
     */
    public int getMoney() {
    	return money;
    }

}
