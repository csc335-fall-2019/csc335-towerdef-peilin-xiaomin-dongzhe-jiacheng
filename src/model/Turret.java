package model;

public class Turret extends Tower {
	private int cost = 20;
	private int attack = 5;
	private int range = 3;
	private double attackSpeed = 0.5;
	
	public Turret() {
		super.setCost(cost);
		super.setAttack(attack);
		super.setAttackSpeed(attackSpeed);
		super.setRange(range);
	}
	
}
