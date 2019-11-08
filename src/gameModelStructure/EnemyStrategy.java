package gameModelStructure;

public interface EnemyStrategy {

	public void callForBackup(int towerLife,LineUp enemyLineup);
	
	public void buyCatapult(int soldiersLost, Tower enemyTower);
	
	public void normalFormation(LineUp enemyLineup);
	
	public  void defenseFormation(LineUp enemyLineup);
	
	public abstract void attackFormation(LineUp enemyLineup);
	
	public void executeStrategy(LineUp enemyLineup,
		                        int soldiersLost, Tower enemyTower);
}
