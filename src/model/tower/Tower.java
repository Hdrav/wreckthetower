package model.tower;

public interface Tower {

	/**
	 *@return the health of the tower
	 */
	public int getHealth();
	/**
	 *@return the max health of the tower
	 **/
	public int getMaxHealth();
	/**
	 * 
	 * @return true if the tower has 0 health, false otherwise
	 */
	public boolean isDestroyed();
	/**
	 * 
	 * @return the percentage of health of the tower
	 */
	public int percentageOfLife();
	/**
	 * 
	 * @param damage inflicted to the tower
	 */
	public void	reduceHealth(int damage);
	/**
	 * 
	 * @param health of the tower to set
	 */
	public void setHealth(int health);
}