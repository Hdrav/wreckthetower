package controller;

import java.io.IOException;

import gameModelStructure.Enemy;
import gameModelStructure.Player;


public interface Controller {
	
	public void setEnemy(Enemy enemy);
	public Player getUser();
	public String[] extractEquipmentNameFromSetting(int index) throws IOException;
	public void setEquipmentUnitTemplate();
}
