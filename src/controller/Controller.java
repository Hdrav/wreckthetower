package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
	 * read from file the max level reached by the player
	 * @throws FileNotFoundException
	 */
	public void extractLevelReached() throws FileNotFoundException;
	
	/**
    *
    * @param the index of the template unit to
    * @return the array that contains the names of the weapon and the armor
    */
	public static String[] extractEquipmentNameFromSetting(int index) throws IOException{
		BufferedReader file = new BufferedReader(new FileReader(new File("").getAbsolutePath()+"/resources/setting_files/unit_setting.txt"));
	    String extractedLine=new String("");
	    String line=new String("");
	    String equipmentName[];
	    int lineIndex=0;
	    
	    while (line!=null && lineIndex<=index ) {
    		if(lineIndex==index) {
    			extractedLine=file.readLine();
    		}
    		else{
    			line=file.readLine();
    		}
    		lineIndex=lineIndex+1;
	    }
	    
	    file.close();
	    equipmentName=extractedLine.split(",");
	    
	   
		return equipmentName;};
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
