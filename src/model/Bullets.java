package model;

public class Bullets {
	protected int physicalATK;
	protected double speedATK;
	
	public Bullets() {
		
	}
	
	public int getphysATK() {
		return this.physicalATK;
	}
		
	public double getSpeed() {
		return this.speedATK;
	}
	
	
	public void setPhysATK(int newPower) {
		this.physicalATK = newPower;
	}
	
	public void setSpeedATK(double attackSpeed) {
		this.speedATK = attackSpeed;
	}
}
