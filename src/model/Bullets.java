package model;

public class Bullets {
	protected int physicalATK;
	protected int speedATK;
	
	
	public Bullets() {
		
	}
	
	public int getphysATK() {
		return this.physicalATK;
	}
		
	public int getSpeed() {
		return this.speedATK;
	}
	
	
	public void setPhysATK(int newPower) {
		this.physicalATK = newPower;
	}
	
	public void setSpeedATK(int attackSpeed) {
		this.speedATK = attackSpeed;
	}
}
