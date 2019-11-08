package gameModelStructure;

public class TowerImpl implements Tower {
	
	private static int HEALTH=500;
	
	private int maxHealth;
	private int currentHealth;
	private int receivedHit;
	private boolean destroyed;

	
	public TowerImpl() {
		this.setHealth(TowerImpl.HEALTH);
		this.receivedHit=0;
		this.destroyed=false;
	}
	
	public void setHealth(int health) {
		this.maxHealth=health;
		this.currentHealth=health;
		
	}

	public int getHealth() {
		return this.currentHealth;
	}

	public void reduceHealth(int damage) {
		this.currentHealth-=damage;
		this.receivedHit=this.receivedHit+1;
		if(isDestroyed()) {
			this.destroyed=true;
		}
	}
	
	public boolean isDestroyed() {
		if (this.currentHealth<=0)
			return true;
		else return false;
	}

	public int percentageOfLife() {
		return (this.currentHealth*100)/this.maxHealth;
	}

}
