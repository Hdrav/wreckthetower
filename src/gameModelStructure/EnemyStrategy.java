package gameModelStructure;

import java.util.List;

public interface EnemyStrategy {

	/*public void callForBackup(int towerLife,List<UnitImpl> enemyLineup);
	
	public void buyCatapult(int soldiersLost, Tower enemyTower);
	
	public void normalFormation(List<UnitImpl> enemyLineup);
	
	public  void defenseFormation(List enemyLineup);
	
	public abstract void attackFormation( enemyLineup);
	
	public void executeStrategy( enemyLineup,
		                        int soldiersLost, Tower enemyTower);*/
//	public EnemyStrategy(Tower enemyTower, int soldiersLost, List<UnitImpl> enemyLineup);
	public void intenseAttack();
	public void normalAttack();
	public void allInAttack();
	public void allInDefense();
	public UnitToMake enemyStrategyLoop(Tower enemyTower,int soldiersLost,List<Unit> enemyLineup);
	
	//public void executeStrategy(Tower enemyTower,int soldiersLost,List<UnitImpl> enemyLineup);
}
