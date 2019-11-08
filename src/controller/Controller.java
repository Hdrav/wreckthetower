package controller;

import gameModelStructure.Enemy;
import gameModelStructure.Player;


public interface Controller {
	
	public void setEnemy(Enemy enemy);
	public Player getUser();

}
