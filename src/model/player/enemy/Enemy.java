package model.player.enemy;

import model.player.User;
import model.player.enemy.strategy.EnemyStrategy;
import model.tower.Tower;
import model.unit.UnitToMake;

public class Enemy extends User {
	/**
	 * the model of the enemy
	 * it doesn't use money
	 * */
	private EnemyStrategy currentStrategy;
	private int soldierLost;
	private UnitToMake unitToMake = new UnitToMake();
	/**
	 * constructor
	 * */
	public Enemy (int money, Tower tower,int unitNumber) {
		super(money,tower,unitNumber);
	}
	
	/**
	 *@param currentStrategy 
	 */
	public void setBattleStrategy (EnemyStrategy currentStrategy) { 
		this.currentStrategy=currentStrategy;
		this.setUnitTemplateList(currentStrategy.getUnitTemplateList());
	}
	
	/**
	 *@return the strategy 
	 */
	public EnemyStrategy getCurrentStrategy() {
		return currentStrategy;
	}
	/**
	 * @return unitToMake 
	 */
	public UnitToMake battleStrategy() {
		
		if(this.currentStrategy!=null) {
			this.unitToMake=currentStrategy.enemyStrategyLoop(this.getTower(),this.soldierLost,this.getUnitQueue());
			return this.unitToMake;
		}
		else return null;
	}
}
