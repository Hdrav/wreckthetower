
public interface EnemyStrategy {

	public void callForBackup(int towerLife,LineUp enemyLineup);
	
	public void buyCatapult(int soldiersLost, Tower enemyTower);
	
	public void normalFormation(Lineup enemyLineup);
	
	public  void defenseFormation(Lineup enemyLineup);
	
	public abstract void attackFormation(Lineup enemyLineup);
	
	public void executeStrategy(int towerLife, Lineup enemyLineup,
		                        int soldiersLost, Tower enemyTower);
}
