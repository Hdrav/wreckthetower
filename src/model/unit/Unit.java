package model.unit;

public interface Unit {
	/**
	 * 
	 * @return the current life of the unit
	 */
	public int getCurrentLife();
	/**
	 * 
	 * @return the damage of the unit
	 */
	public int getDamage();
	/**
	 * 
	 * @return the speed constant of the unit
	 */
	public double getSpeed();
	/**
	 * 
	 * @return the life of the unit
	 */
	public int getLife();
	/**
	 * 
	 * @return the gold value of the unit
	 */
	public int getValue();
	/**
	 * 
	 * @return true if the unit is alive
	 */
	public boolean isAlive();
	/**
	 * 
	 * @param damage inflicted at the unit
	 */
	public void reduceHealth(int damage);

}
