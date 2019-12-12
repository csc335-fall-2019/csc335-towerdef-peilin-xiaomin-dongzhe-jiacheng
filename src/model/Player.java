package model;

public class Player {
	protected int health;
	protected int money;

	public Player(int health) {
		this.health = health;
		this.money = 75;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void lossHealth(int loss) {
		this.health -= loss;
	}
	
	public void changeMoney(int money) {
		this.money += money;
	}
	public boolean canBuyTower(int cost) {
		return money >= cost;
	}
}
