/**
 * THis is a class for the player. 
 */
package model;

public class Player {
	protected int health;
	protected int money;

	public Player(int health) {
		this.health = health;
		this.money = 75;
	}
	/**
	 * get method return the life of the player
	 * @return the life of the layer
	 */
	public int getHealth() {
		return this.health;
	}
	/**
	 * get method return the money of the player have
	 * @return the current money
	 */
	public int getMoney() {
		return this.money;
	}
	/**
	 * minus the life when monster attack
	 * @param loss, the damage
	 */
	public void lossHealth(int loss) {
		this.health -= loss;
	}
	/**
	 * increase the money, when we killed monsters
	 * @param money, the value of the monster
	 */
	public void changeMoney(int money) {
		this.money += money;
	}
	/**
	 * checker, return the ability to buy a tower
	 * @param cost, the value of the tower
	 * @return if we can buy tower
	 */
	public boolean canBuyTower(int cost) {
		return money >= cost;
	}
}
