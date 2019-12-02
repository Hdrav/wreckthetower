package model.player.enemy.strategy;

import java.util.List;

import model.tower.Tower;
import model.unit.Unit;
import model.unit.UnitTemplate;
import model.unit.UnitToMake;

public interface EnemyStrategy {

	public void allInAttack();
	public void allInDefense();
	public UnitToMake enemyStrategyLoop(Tower enemyTower,int soldiersLost,List<Unit> enemyLineup);
	public List<UnitTemplate> getUnitTemplateList();
	/**
	 *interface for the enemy strategy
	 */
	public void intenseAttack();
	public void normalAttack();
	

}
