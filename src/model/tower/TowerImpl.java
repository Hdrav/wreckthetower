package model.tower;

public class TowerImpl implements Tower {
	
	
	private int maxHealth;
	private int currentHealth;
	private int receivedHit;
	private boolean destroyed;

	
	public TowerImpl(int health) {
		this.setHealth(health);
		this.receivedHit=0;
		this.destroyed=false;
	}
	
	public int getHealth() {
		return this.currentHealth;
	}

	@Override
	public int getMaxHealth() {
		
		return this.maxHealth;
	}

	public boolean isDestroyed() {
		return destroyed;
	}
	
	public int percentageOfLife() {
		return (this.currentHealth*100)/this.maxHealth;
	}

	public void reduceHealth(int damage) {
		if(currentHealth<=damage) {
			this.currentHealth=0;
			this.destroyed=true;
		}
		else {
			this.currentHealth-=damage;
			this.receivedHit=this.receivedHit+1;
		}
	}

	public void setHealth(int health) {
		this.maxHealth=health;
		this.currentHealth=health;
		
	}

}
