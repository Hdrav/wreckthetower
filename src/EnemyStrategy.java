
public interface EnemyStrategy {

	public void callForBackup(int towerLife,LineUp enemyLineup);
	
	public void buyCatapult(int soldiersLost, Tower enemyTower);
	
	public  void defenseFormation();
	
	public abstract void attackFormation();
	
	public void executeStrategy(int towerLife, Lineup enemyLineup,
		                        int soldiersLost, Tower enemyTower);
}
