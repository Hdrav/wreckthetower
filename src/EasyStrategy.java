
public class EasyStrategy extends AbstractStrategy {

	private static int LIFE_PERCENTAGE=70;
	private static int DEAD_PÈRCENTAGE=30;
	
	@Override
	public void callForBackup(int towerLife,LineUp enemyLineup) {
		
		super.callForBackup(towerLife, enemyLineup);
		if(towerLife<=LIFE_PERCENTAGE*0.7) {
			enemyLineup.setUnlocked();
		}
	}

	@Override
	public void buyCatapult(int soldiersLost) {
		super.buyCatapult(soldiersLost, enemyTower);
		
	}
	@Override
	public void defenseFormation() {
		
	}
}
