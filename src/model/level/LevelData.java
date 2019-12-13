package model.level;

import model.player.enemy.strategy.*;
public enum LevelData {
	/**
	 * enum const for every level
	 * 
	 * */
	LEVEL_1(150,350,350,new LevelOneStrategy(),3),
	LEVEL_2(150,400,350,new Level2Strategy(),3),
	LEVEL_3(190,500,350,new Level3Strategy(),3),
	LEVEL_4(190,550,300,new Level4Strategy(),3);
	
	private int gold;
	private int towerEnemyLife;
	private int towerUserLife;
	private EnemyStrategy enemyStrategy;
	private int numberOfUnit;
	
	LevelData(int gold, int towerEnemyLife,int towerUserLife, EnemyStrategy enemyStrategy,int numberOfUnit) {

		this.gold=gold;
		this.towerEnemyLife=towerEnemyLife;
		this.towerUserLife=towerUserLife;
		this.enemyStrategy=enemyStrategy;
		this.numberOfUnit=numberOfUnit;
		
	}
	
	/**
	 *@param level to be initialize
	 *@return the LevelData representing that level 
	 * */
	public static LevelData getLevelData(int level) {
		if(level==1) return LevelData.LEVEL_1;
		if(level==2) return LevelData.LEVEL_2;
		if(level==3) return LevelData.LEVEL_3;
		if(level==4) return LevelData.LEVEL_4;
		return LevelData.LEVEL_4;
	}

	public int getGold() {
		return gold;
	}

	public EnemyStrategy getEnemyStrategy() {
		return enemyStrategy;
	}

	public int getNumberOfUnit() {
		return numberOfUnit;
	}

	public int getTowerUserLife() {
		return towerUserLife;
	}

	public int getTowerEnemyLife() {
		return towerEnemyLife;
	}
	

	
	
}
