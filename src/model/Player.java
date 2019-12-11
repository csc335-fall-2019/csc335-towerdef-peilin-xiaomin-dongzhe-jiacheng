/**
 * This is the user player class.
 */
package model;

public class Player {
	protected int health; // damage this monster can take
	protected int money; // The money of player

	public Player(int health) {
		this.health = health; 
		this.money = 100;
	}
	
	public int getHealth() { // get method return the life of the player
		return this.health;
	}
	
	public int getMoney() { // get method return the money of the player have 
		return this.money;
	}
	
	public void lossHealth(int loss) { //minus the life when monster attack
		this.health -= loss;
	}
	
	public void changeMoney(int money) { //increase the money, when we killed monsters
		this.money += money;
	}
	public boolean canBuyTower(int cost) { // checker, return the ability to buy a tower
		return money >= cost;
	}
}
