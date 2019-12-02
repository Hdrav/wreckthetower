package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.player.Player;
import model.player.enemy.Enemy;
import model.unit.UnitToMake;


public interface Controller {
	/*
	 * interface of the controller
	 * 
	 * */


	 /**
    *
    * @param enemy
    *            the enemy to add
    */
	public void setEnemy(Enemy enemy);
	
	/**
	 *@return the enemy
	 * */
	public Enemy getEnemy();
	/**
	 *@return player
	 * */
	public Player getUser();
	/**
	 *@return the level
	 * */
	public int getLevel();
	 /**
    *
    * @param level the level to set
    */
	public void initializeGame(int level);
	/**
    *
    * @param the index of the template unit to
    * @return the array that contains the names of the weapon and the armor
    */
	public String[] extractEquipmentNameFromSetting(int index) throws IOException;
	/*
	 * sets the unit template of the user
	 * */
	public void setEquipmentUnitTemplate();
	/**
	 *@param the index of the template unit
	 * @return true if the user can buy the unit
	 */
	public boolean canUserBuy(int index);
	/**
	 * 
	 * @return return the list of index of the unit to make
	 */
	public UnitToMake nextUnitToMake();
	/**
	 * starts music 
	 * */
	public void startMusic();
	/**
	 * @param the level to be set
	 * */
	public void setLevel(int level);
	/**
	 * if the level is > max level
	 * @param the level
	 * */
	public void setLevelReach(int level)throws FileNotFoundException;
	/**
	 *@return the max level 
	 **/
	public int getMaxLevel();
	/**
	 * stop the music 
	 * */
	public void stopMusic();

}
