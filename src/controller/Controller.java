package controller;

import java.io.IOException;

import gameModelStructure.Enemy;
import gameModelStructure.Player;
import gameModelStructure.UnitToMake;


public interface Controller {
	
	public void setEnemy(Enemy enemy);
	public Player getEnemy();
	public Player getUser();
	public String[] extractEquipmentNameFromSetting(int index) throws IOException;
	public void setEquipmentUnitTemplate();
	public boolean canUserBuy(int index) ;
	public UnitToMake nextUnitToMake();
}
