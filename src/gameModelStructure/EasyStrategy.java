package gameModelStructure;

public class EasyStrategy extends AbstractStrategy {

	private static int LIFE_PERCENTAGE=100;
	private static int DEAD_PÈRCENTAGE=30;
	private static double SCND_LIFE_PERCENTAGE=50;
	
	@Override
	public void callForBackup(int towerLife,LineUp enemyLineup) {
		
		super.callForBackup(towerLife, enemyLineup);
		if((towerLife<=SCND_LIFE_PERCENTAGE)&&(enemyLineup.areUnitLocked)) {
			enemyLineup.setUnlocked();
		}
	}

	@Override
	public void buyCatapult(int soldiersLost,Tower enemyTower) {
		super.buyCatapult(soldiersLost, enemyTower);
	}
	
	
	@Override
	public void normalFormation(LineUp enemyLineup) {
		super.normalFormation(enemyLineup);
	}
	

	public void defenseFormation(LineUp enemyLineup) { 	//se i nemici sono a una certa distanza inizia
														//a creare più unità per la difesa
		while((enemyLineup.EnemyNear)&&( !enemyLineup.unitLimit)) {
														//metti un delay
			enemyLineup.placeUnit(); 
		}
	}
	
	public void attackFormation(LineUp enemyLineup) {
		while((enemyLineup.UnitFar)&&( !enemyLineup.unitLimit)) {
													//metti un delay
			enemyLineup.placeUnit();
		}
	}
	
	public void executeStrategy( LineUp enemyLineup,
		                        int soldiersLost, Tower enemyTower) {
		
		super.executeStrategy(enemyTower.getLife(), enemyLineup,
							  soldiersLost, enemyTower);
	}
}