package model;

public class Player {
	protected int health;
	protected int money;

	public Player(int health) {
		this.health = health;
		this.money = 20;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void autoMoney() {
		this.money++;
	}
	
	public void earnMoney(int earned) {
		this.money += earned;
	}
	public boolean canBuyTower(int cost) {
		return money>= cost;
	}
	public void buy(int cost) {
		money -= cost;
	}
}
