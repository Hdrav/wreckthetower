package gameModelStructure;

public interface Tower {

	public void setHealth(int health);
	public int getHealth();
	public void	reduceHealth(int damage);
	public int percentageOfLife();
	public boolean isDestroyed();
}