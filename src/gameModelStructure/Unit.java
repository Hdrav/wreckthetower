package gameModelStructure;

public interface Unit {
	
	public int getCurrentLife();
	public int getDamage();
	public double getSpeed();
	public int getLife();
	public double getTimeOfTraining();
	public int getBodySize();
	public int getFootPosition();
	public boolean isPlayerUnit();
	public int getUnitReach();
	public void reduceHealth(int damage);

}
